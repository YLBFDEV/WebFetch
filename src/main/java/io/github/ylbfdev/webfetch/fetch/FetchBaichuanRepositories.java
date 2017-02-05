package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.*;


public class FetchBaichuanRepositories extends BaseFetch {
    private static final Logger Log = Logger.getLogger(FetchBaichuanRepositories.class.getName());
    private String baseUrl = "http://repo.baichuan-android.taobao.com/content/groups/BaichuanRepositories/";
    private List<String> jarList = new ArrayList<>();
    private List<JarAttrib> jarAttribList = new ArrayList<>();

    @Override
    public void startFetch(WebDriver driver) {

        initJar();
        for (JarAttrib jarAttrib : jarAttribList) {
            driver.get(baseUrl + jarAttrib.getGroupId().replace(".", "/") + "/" + jarAttrib.getArtifactId());
            Log.info(driver.getTitle());
            Log.info("=====当前版本号：" + jarAttrib.getVersion());
            List<WebElement> versionElement = driver.findElements(By.cssSelector("body > table > tbody > tr"));
            for (WebElement webElement : versionElement) {
                if (webElement.findElements(By.cssSelector("td")).size() >= 2) {
                    String vsrsion = webElement.findElement(By.cssSelector("td:nth-child(1)> a")).getText().replaceAll("/", "");
                    if (vsrsion.contains("xml"))
                        break;
                    try {
                        if (isNewVersion(jarAttrib.getVersion(), vsrsion)) {
                            Log.info("发现新版本号：" + vsrsion);
                            String packaging = "";
                            if (jarAttrib.getPackaging() != null) {
                                packaging = "@" + jarAttrib.getPackaging();
                            }
                            Log.info("引用方法：" + String.format("compile '%s:%s:%s%s'", jarAttrib.getGroupId(), jarAttrib.getArtifactId(), vsrsion, packaging));
                            Log.info("链接地址：" + baseUrl + jarAttrib.getGroupId().replace(".", "/") + "/" + jarAttrib.getArtifactId() + "/" + vsrsion);

                            String date = webElement.findElement(By.cssSelector("td:nth-child(2)")).getText();
                            Date d = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(date);
                            String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
                            Log.info("更新日期：" + formatDate);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private void initJar() {
        jarList.add("com.alibaba:fastjson:1.2.9");
        jarList.add("com.alibaba.alipay:alipaySingle:20160825@jar");
        jarList.add("com.ali.auth.sdk:alibabauth_core:1.2.5@jar");
        jarList.add("com.ali.auth.sdk:alibabauth_ui:1.2.5@aar");
        jarList.add("com.ali.auth.sdk:alibabauth_ext:1.2.5@jar");
        jarList.add("com.ali.auth.sdk:alibabauth_accountlink:1.2.4@jar");
        jarList.add("com.taobao.android:securityguardaar3:5.1.81@aar");
        jarList.add("com.taobao.android:securitybodyaar3:5.1.25@aar");
        jarList.add("com.taobao.android:mtopsdk_allinone_open:1.3.0@jar");
        jarList.add("com.taobao.android:alibc_applink:2.0.0.9@jar");
        jarList.add("com.taobao.android:utdid4all:1.1.5.3_proguard@jar");
        jarList.add("com.alibaba.mtl:app-monitor-sdk:2.5.1.3_for_bc_proguard@jar");
        jarList.add("com.alibaba.sdk.android:alibc_trade_sdk:3.1.1.11@aar");

        for (String jar : jarList) {
            JarAttrib jarAttrib = new JarAttrib();
            String[] jarAttribs = jar.split("[:@]");
            try {
                jarAttrib.setGroupId(jarAttribs[0]);
                jarAttrib.setArtifactId(jarAttribs[1]);
                jarAttrib.setVersion(jarAttribs[2]);
                jarAttrib.setPackaging(jarAttribs[3]);
            } catch (IndexOutOfBoundsException e) {
            } finally {
                jarAttribList.add(jarAttrib);
            }
        }
    }

    /**
     * 判断是否为最新版本方法 将版本号根据.切分为int数组 比较
     *
     * @param localVersion  本地版本号
     * @param onlineVersion 线上版本号
     * @return 是否为新版本
     * @throws IllegalArgumentException argument may not be null !
     */
    public static boolean isNewVersion(String localVersion, String onlineVersion) {
        if (localVersion == null || onlineVersion == null) {
            throw new IllegalArgumentException("argument may not be null !");
        }
        if (localVersion.equals(onlineVersion)) {
            return false;
        }
        String[] localArray = localVersion.replaceAll("[^0-9.]", "").split("[.]");
        String[] onlineArray = onlineVersion.replaceAll("[^0-9.]", "").split("[.]");
        int length = localArray.length < onlineArray.length ? localArray.length : onlineArray.length;
        for (int i = 0; i < length; i++) {
            if (Integer.parseInt(onlineArray[i]) > Integer.parseInt(localArray[i])) {
                return true;
            } else if (Integer.parseInt(onlineArray[i]) < Integer.parseInt(localArray[i])) {
                return false;
            }
            // 相等 比较下一组值
        }
        //比较最后差异组数值
        if (localArray.length < onlineArray.length) {
            return Integer.parseInt(onlineArray[onlineArray.length - 1]) > 0;
        } else if (localArray.length > onlineArray.length) {
            return 0 > Integer.parseInt(localArray[localArray.length - 1]);
        }
        return true;
    }
}

class JarAttrib {
    private String groupId;
    private String artifactId;
    private String version;
    private String packaging;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }
}
