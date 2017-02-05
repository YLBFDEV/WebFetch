package io.github.ylbfdev.webfetch.fetch;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupFetch {
    private static final Logger Log = Logger.getLogger(JsoupFetch.class.getName());

    public static void init() throws IOException {
        String url = "http://btkitty.bid/torrent/BcEBEQAgCAPATDAmtziA2D-C_xZhjwdE9oScxcj10WrIi0Y1KH0.html";
        String url1 = "http://btkitty.bid/torrent/BcGBEQAwBACxmShXxine_iM0ETpIooaLVSOni2eTSMmqt9kq_gE.html";
        String url2 = "http://btkitty.bid/torrent/BcGBEQAwBASwlag_feOg7D9Ck0BarWzrGyGtqaNZme5hBA4vznR8.html";
        Document doc = getDocument(url2, false);
        fetchBtkitty(doc);
    }

    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得Document
     *
     * @param url         请求地址
     * @param showContent 是否显示内容
     * @return Document
     * @throws IOException 抛出IO
     */
    private static Document getDocument(String url, boolean showContent) throws IOException {
        Document doc = Jsoup
                .connect(url)
                .header("Accept-Language", "zh-CN,zh;q=0.8")//语言
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")//用户代理
                .cookie("auth", "token")
                .timeout(3000).get();
        if (showContent)
            Log.info(doc.html());
        return doc;
    }

    /**
     * 解析Btkitty信息
     *
     * @param doc
     */
    private static void fetchBtkitty(Document doc) {
        String title = doc.title();
        Log.info("===================================================================================================");
        Log.info(title);
        Elements fileinfodd = doc.select("body > div.wrapper > div.content > div.torrent > dl > dd");
        Elements fileinfodt = doc.select("body > div.wrapper > div.content > div.torrent > dl > dt.t1");
        int size = fileinfodt.size() < 9 ? fileinfodt.size() : 9;
        for (int i = 0; i < size; i++) {
            if (fileinfodd.get(i).text().isEmpty()) {
                continue;
            }
            Log.info(String.format("%s %s", fileinfodt.get(i).text(), fileinfodd.get(i).text()));
        }
        Log.info(String.format("磁力链接地址：%s", doc.select("dd.magnet").first().getElementsByTag("a").text()));
        Log.info("===================================================================================================");
        Elements filelist = doc.select("p");
        Log.info(String.format("文件列表总数：%s", filelist.size()));
        Log.info("文件列表：");
        for (Element element : filelist) {
            String file_name = element.getElementsByClass("filename").text();
            String file_size = element.getElementsByClass("size").text();
            Log.info(file_name + " ; " + file_size);
        }
    }


}
