package cn.fishei.util;

import java.util.UUID;

/**
 * 生成组成激活网址尾缀的唯一字符串
 */
public class UuidUtil {

    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    //Test
    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());

    }
}
