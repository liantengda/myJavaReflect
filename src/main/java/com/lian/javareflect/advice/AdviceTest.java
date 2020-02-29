package com.lian.javareflect.advice;

import com.lian.javareflect.common.utils.TestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/* 面向切面编程
*   打印日志，事务控制，异常处理
*   以上这些功能的代码有一个共性，那就是所做的事情和业务没有关系，而且写到哪里基本都长一样，可复用性极强。所以如果我们可以不把他们写道业务里面
*   整个系统只写一次，然后处处可用，那么我们就需要让系统知道，什么时候去调用它，在什么地方调用它。而要想让这个地方知道这段代码在哪里，就需要用到
*   面向切面编程这个思想。
* */
@Slf4j
@Aspect
@Component
public class AdviceTest {

    private final Logger logger = LoggerFactory.getLogger(AdviceTest.class);
    private FileOutputStream fos = null;
    //此处又是一个引用final的常量
    private static final String strLine = "\r\n";

    private String logPath;

    /**
     * 可以在构造方法上使用@Autowire
     * 若不添加会报错，因为
     *
     * 因为Java类会先执行构造方法，然后再给注解了@Autowired 的user注入值，所以在执行构造方法的时候，就会报错。
     *
     * Java变量的初始化顺序为：静态变量或静态语句块–>实例变量或初始化语句块–>构造方法
     * @param testLogUtil
     */
    @Autowired
    public AdviceTest(TestLogUtil testLogUtil){
        logPath = testLogUtil.getLogPath();
    }

    @Pointcut("execution(* com.mysql.*.*(..))")//切点定义
    public void lianPoint(){}


    @Around("lianPoint()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        System.out.println("日志打印路径--------》"+logPath);
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            args[0] = "改变后的参数1";
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());

        File loaclPathOutDir = new File(logPath);
        loaclPathOutDir.mkdirs();
        StringBuilder sb = new StringBuilder();
        try {
            fos = new FileOutputStream(logPath+getCurrentTimeByFormat("yyyyMMdd")+".log",true);
            sb.append(strLine+getCurrentDateTime()+strLine);
            fos.write(sb.toString().getBytes());
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写入日志完成------》");
        return "原返回值：" + returnValue + "，这是返回结果的后缀";
    }
    //Around--->Before--->Around--->After--->AfterReturning
    @Before("lianPoint()")
    public void permissionCheck(JoinPoint point) {
        System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }

    @AfterReturning(pointcut="lianPoint()",
            returning="returnValue")
    public void log(JoinPoint point, Object returnValue) {
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" +
                Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" + returnValue);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }

    @After("lianPoint())")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }

    /**
     * 抛出异常后做什么
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "lianPoint()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        logger.info("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);
        String errorLogPath = logPath+"errorLog/";
        File loaclPathOutDir = new File(errorLogPath);
        loaclPathOutDir.mkdirs();
        StringBuilder sb = new StringBuilder();
        try {
            fos = new FileOutputStream(errorLogPath+getCurrentTimeByFormat("yyyyMMdd")+"Error"+".log",true);
            sb.append(strLine+getCurrentDateTime()+strLine);
            sb.append("调用方法:"+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+strLine);
            sb.append("参数为:"+args+strLine);
            sb.append(this.getStackMsg(sb,ex));
            fos.write(sb.toString().getBytes());
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 根据给定格式输出时间
     * @param format 给定的格式
     * @return
     */
    public static String getCurrentTimeByFormat(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(formatter);
    }
    private String getStackMsg(StringBuilder sb,Exception ex) {
        StackTraceElement[] stackArray = ex.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + strLine);
        }
        return sb.toString();
    }



}
