package io.github.ylbfdev.webfetch;

import org.apache.log4j.Logger;

import io.github.ylbfdev.webfetch.constants.Constants;
import io.github.ylbfdev.webfetch.fetch.Fetch520HD;
import io.github.ylbfdev.webfetch.fetch.FetchSglsGameActivitys;
import io.github.ylbfdev.webfetch.fetch.FetchXunLeiMi;
import io.github.ylbfdev.webfetch.utils.ConfUtils;

/**
 * 主页菜单
 */
public class FetchMain {
    private static final Logger Log = Logger.getLogger(FetchMain.class.getName());

    public enum FetchType {
        SGLS, _520HD, XUNLEIMI
    }

    public static void main(String[] args) {
        // 初始化配置参数
        ConfUtils.initializeParams();
        try {
            FetchType fetchType = Enum.valueOf(FetchType.class, Constants.TYPE.toUpperCase());
            // 根据type判断爬虫任务类型
            switch (fetchType) {
                case SGLS:
                    new FetchSglsGameActivitys().init();
                    break;
                case _520HD:
                    new Fetch520HD().init();
                    break;
                case XUNLEIMI:
                    new FetchXunLeiMi().init();
                    break;
                default:
                    Log.error("Unknown crawl type: " + Constants.TYPE + ".\n Exit...");
                    break;
            }
        } catch (Exception e) {
            Log.error("Unknown crawl type: " + Constants.TYPE + ".\n Exit...", e);
        } finally {
            Log.info("程序退出");
        }
    }
}
