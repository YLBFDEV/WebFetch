package io.github.ylbfdev.webfetch.utils;


import net.iharder.Base64;

import java.io.UnsupportedEncodingException;


/**
 * @author Song Wenbin
 */
public class Base64Utils {

    /**
     * 将字符串转成unicode
     *
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String convert2Unicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); // 取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); // 取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encodeBytes(b);

        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s, String charsetName) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = Base64.decode(s);
                result = new String(b, charsetName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
