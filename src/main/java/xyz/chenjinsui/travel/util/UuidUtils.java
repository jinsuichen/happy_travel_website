package xyz.chenjinsui.travel.util;


import java.util.UUID;

/**
 * 生成全球唯一的字符
 * @author FengLing
 */
public final class UuidUtils {
    private UuidUtils(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
