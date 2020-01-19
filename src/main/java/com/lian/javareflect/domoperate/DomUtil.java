package com.lian.javareflect.domoperate;


import com.lian.javareflect.service.UserService;
import com.lian.javareflect.service.impl.UserServiceImpl;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class DomUtil {

    private static String fileDir = "C:\\Users\\lenovo\\Desktop\\RD\\文档/";
    private static String errorWordFilePath = "liantengda接口文档.doc";
    private static String errorExcelFilePath = "liantengda接口文档.doc";

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        writerErrorFileContent(fileDir,errorWordFilePath,"WORD");
    }

    private static void writerErrorFileContent(String fileDir, String filePath, String type) {
        Class clz = UserServiceImpl.class;
        ArrayList<String> content = new ArrayList<>();
        XWPFDocument document = new XWPFDocument();
        OutputStream stream = null;
        BufferedOutputStream bufferStream = null;
        try {
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            stream = new FileOutputStream(new File(fileDir+filePath));
            bufferStream = new BufferedOutputStream(stream, 1024);

            String simpleName = clz.getSimpleName();
            content.add(simpleName);

            Method[] methods = clz.getMethods();
            for (int i=0;i<methods.length;i++){
                if(methods[i].getName().startsWith("get")){
                    content.add("根据主键id查询"+methods[i].getName()+"基本信息");
                }else if(methods[i].getName().startsWith("upd")){
                    content.add("更新"+methods[i].getName());
                }else if(methods[i].getName().startsWith("del")){
                    content.add("删除"+methods[i].getName());
                }else if(methods[i].getName().startsWith("add")){
                    content.add("增加"+methods[i].getName());
                }
            }
            // 创建一个段落
            XWPFParagraph p1 = document.createParagraph();
            // 设置居中
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            // 是否加粗
            r1.setBold(true);
            // 与下一行的距离
            r1.setTextPosition(30);
            if (type.equals("EXCEL")) {
                // 段落名称
                r1.setText("EXCEL导入题目错误格式详解");
            }
            if (type.equals("WORD")) {
                // 段落名称
                r1.setText("WORD导入题目错误格式详解");
            }

            // 字体大小
            r1.setFontSize(18);// 字体大小
            // 增加换行
            r1.addCarriageReturn();
            // 创建第二个段落
            XWPFParagraph p2 = document.createParagraph();
            XWPFRun r2 = p2.createRun();
            for (int i = 0; i < content.size(); i++) {
                r2.setText(i + 1 + "、" + content.get(i));
                r2.addCarriageReturn();
            }
            // 设置字体
            r2.setFontFamily("仿宋");
            r2.setFontSize(14);// 字体大小
            document.write(stream);
            stream.close();
            bufferStream.close();
        } catch (Exception ex) {
            System.out.println("写word或Excel错误文件失败");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("写word或Excel错误文件失败");
                }
            }
            if (bufferStream != null) {
                try {
                    bufferStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("写word或Excel错误文件失败：{}");
                }
            }
        }

    }


    /**
     * 获取方法写入doc文档
     * @throws IOException
     */
    public static void getMethodInfoToDoc() throws IOException {
        String filepath = "C:\\Users\\lenovo\\Desktop\\RD\\文档/";
        String htmlName = "hehe.docx";
        Class clz = UserServiceImpl.class;


        OutputStream out = new FileOutputStream(new File(filepath + htmlName));

        String simpleName = clz.getSimpleName();
        byte[] bytes = simpleName.getBytes();
        out.write(bytes);
        out.close();


    }




    /**
     * 2007版本word转换成html
     * @throws IOException
     */
    public static void Word2007ToHtml() throws IOException {
        String filepath = "C:\\Users\\lenovo\\Desktop\\RD\\文档/";
        String fileName = "代表信息接口文档-lian.docx";
        String htmlName = "代表信息接口文档-lian.html";
        final String file = filepath + fileName;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) 加载word文档生成 XWPFDocument对象
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
                File imageFolderFile = new File(filepath);
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                options.setIgnoreStylesIfUnused(false);
                options.setFragment(true);

                // 3) 将 XWPFDocument转换成XHTML
                OutputStream out = new FileOutputStream(new File(filepath + htmlName));
                XHTMLConverter.getInstance().convert(document, out, options);

                //也可以使用字符数组流获取解析的内容
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                XHTMLConverter.getInstance().convert(document, baos, options);
//                String content = baos.toString();
//                System.out.println(content);
//                 baos.close();
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }

    /**
     * /**
     * 2003版本word转换成html
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void Word2003ToHtml() throws IOException, TransformerException, ParserConfigurationException {
        String filepath = "C:/test/";
        final String imagepath = "C:/test/image/";
        String fileName = "滕王阁序2003.doc";
        String htmlName = "滕王阁序2003.html";
        final String file = filepath + fileName;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagepath);
                if(!imgPath.exists()){//图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(imagepath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return imagepath + suggestedName;
            }
        });

        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

        File htmlFile = new File(filepath + htmlName);
        OutputStream outStream = new FileOutputStream(htmlFile);

        //也可以使用字符数组流获取解析的内容
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        OutputStream outStream = new BufferedOutputStream(baos);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);

        //也可以使用字符数组流获取解析的内容
//        String content = baos.toString();
//        System.out.println(content);
//        baos.close();
        outStream.close();
    }

}
