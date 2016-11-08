package io.github.ylbfdev.webfetch.utils;

/**
 * 
 * 迅雷链接解密
 * 
 *
 */
public class ThunderDecryption {
	public static String Decryption(String thunderurl) {
		if (thunderurl.contains("thunder://")) {
			String str = thunderurl.replace("thunder://", "");
			str = Base64Utils.getFromBase64(str, "gb2312").replaceAll("^AA|ZZ$", "");
			return str;
		} else {
			return thunderurl;
		}
	}
}
