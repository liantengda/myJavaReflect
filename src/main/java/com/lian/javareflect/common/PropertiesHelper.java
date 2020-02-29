package com.lian.javareflect.common;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesHelper {
    private static Properties properties = new Properties();
    //static final 修饰常量，此引用不可变
    private static final String commonProperties = "common.properties";

    static {
        // 首先加载默认配置文件
        System.out.println("comm--------:" + commonProperties);
        loadProperties(commonProperties);
    }

    // 加载配置文件
    public static void loadProperties(String propertiesFile) {
        InputStream is = null;
        try {
            properties.load(PropertiesHelper.class.getClassLoader().getResourceAsStream(propertiesFile));
            log.info("properties = " + properties);
            System.out.println("InputStream = " + properties);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
