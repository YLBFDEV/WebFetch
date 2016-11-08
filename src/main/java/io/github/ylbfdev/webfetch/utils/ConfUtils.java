package io.github.ylbfdev.webfetch.utils;

import java.io.*;
import java.util.Properties;

import org.apache.log4j.Logger;

import io.github.ylbfdev.webfetch.constants.Constants;
import io.github.ylbfdev.webfetch.constants.DBConn;

public class ConfUtils {
    private static final Logger Log = Logger.getLogger(ConfUtils.class.getName());

    /**
     * 从配置文件初始化参数
     */
    public static void initializeParams() {
        InputStream in;
        try {
            Log.info("读取配置文件:" + new File("conf/spider.properties").getAbsolutePath());
            in = new BufferedInputStream(new FileInputStream("conf/spider.properties"));
            Properties properties = new Properties();
            properties.load(in);

            // 从配置文件中读Mybatis路径参数
            Constants.CONFIG_PATH = properties.getProperty("DB.CONFIG_PATH");

            // 从配置文件中读取数据库连接参数
            DBConn.CONN_URL = properties.getProperty("DB.connUrl");
            DBConn.USERNAME = properties.getProperty("DB.username");
            DBConn.PASSWORD = properties.getProperty("DB.password");

            // 从配置文件中读取爬虫任务类型
            Constants.TYPE = properties.getProperty("spider.type");

            Constants.WEB_DRIVER = properties.getProperty("spider.webDriver");
            Constants.DRIVER_PATH = properties.getProperty("spider.driverPath");

            // 关闭配置文件读取链接
            in.close();
            Log.info("读取配置完毕！");
        } catch (FileNotFoundException e) {
            Log.error("配置文件conf/spider.properties没有找到", e);
        } catch (IOException e) {
            Log.error("配置文件conf/spider.properties读取异常", e);
        }
    }
}
