package utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class stringUtil {
    private static Logger logger = Logger.getLogger(stringUtil.class);

    public static boolean isBlank(String str){
        int strLen;
        if(str!=null&&(strLen = str.length())!=0){
            for (int i = 0; i < strLen; i++) {
                if(!Character.isWhitespace(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }
    //按字节数和编码方式过滤字符串
    public static String lengthFilter(String str,int length,String charsetName){
        if(isBlank(str)){
            return str;
        }else {
            try{
                byte[] tempBytes = str.getBytes(charsetName);
                byte[] myBytes = Arrays.copyOf(tempBytes, tempBytes.length > length ? length : tempBytes.length);
                char[] chars = (new String(myBytes, charsetName)).toCharArray();
                StringBuilder sb = new StringBuilder();
                char[] var1 = chars;
                int var2 = chars.length;
                for (int var3 = 0; var3 < var2; var3++) {
                    char c = var1[var3];
                    sb.append(c);
                }
                return sb.toString();
            }catch (UnsupportedEncodingException var4){
                logger.error("filter error",var4);
                return "";
            }
        }
    }
    //根据字节长度，编码方式以及正则过滤字符串
    public static String filter(String str,int length,String regEx) {
        if (isBlank(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            Pattern p = Pattern.compile(regEx);
            char[] chars = str.toCharArray();
            char[] var1 = chars;
            int var2 = chars.length;
            for (int var3 = 0; var3 < var2; var3++) {
                char c = var1[var3];
                if (p.matcher(String.valueOf(c)).find()) {
                    sb.append(c);
                }
            }
            try {
                byte[] tempBytes = sb.toString().getBytes("GBK");
                byte[] myBytes = Arrays.copyOf(tempBytes, tempBytes.length > length ? length : tempBytes.length);
                chars = (new String(myBytes, "GBK")).toCharArray();
                sb = new StringBuilder();
                char[] var4 = chars;
                int var5 = chars.length;
                for (int var6 = 0; var6 < var5; ++var6) {
                    char c = var4[var6];
                    if (p.matcher(String.valueOf(c)).find()) {
                        sb.append(c);
                    }
                }
                return sb.toString();
            } catch (UnsupportedEncodingException var7) {
                logger.error("filter error", var7);
                return "";
            }
        }
    }
}
