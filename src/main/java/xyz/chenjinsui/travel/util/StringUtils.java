package xyz.chenjinsui.travel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtils {
    /**
     * 将URL中的中文转为UTF-8编码
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     *
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        return java.net.URLDecoder.decode(new String(str.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
    }

    public static void main(String[] args) {

        String str = "%E5%AE%89";
        try {
            System.out.println(StringUtils.decode(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
