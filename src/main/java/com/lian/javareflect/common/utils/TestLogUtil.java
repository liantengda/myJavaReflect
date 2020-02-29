package com.lian.javareflect.common.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Value的使用
 */
@Data
@Component
@PropertySource("classpath:common.properties")
public class TestLogUtil {
    @Value("${common.aspect.logPath}")
    private String logPath;


}
