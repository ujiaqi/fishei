package cn.fishei.util;

import java.security.MessageDigest;

public class Md5Util {

    private Md5Util(){}
    /**
     * 将明文密码转成MD5密码
     */
    public static String encodeByMd5(String password) throws Exception{

        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] byteArray = md5.digest(password.getBytes());

        return byteArrayToHexString(byteArray);
    }
    /**
     * 将byte[]转在16进制字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();

        for(byte b : byteArray){

            String hex = byteToHexString(b);

            sb.append(hex);
        }
        return sb.toString();
    }
    /**
     * 将byte转在16进制字符串
     */
    private static String byteToHexString(byte b) {
        //将byte类型赋给int类型
        int n = b;

        if(n < 0){
            n = 256 + n;
        }

        int d1 = n / 16;

        int d2 = n % 16;

        return hex[d1] + hex[d2];
    }
    private static String[] hex = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};


    public static void main(String[] args) throws Exception{
        String password = "yujiaqi66.com";
        String passwordMD5 = Md5Util.encodeByMd5(password);
        System.out.println(password);
        System.out.println(passwordMD5);
    }

}
