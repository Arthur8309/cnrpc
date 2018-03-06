/**        
 * 类名称：DateSecret.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2017年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.tools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.ce.framework.core.tools.Base64;

/**
 * @author lenovo
 *
 */
public class DateSecret {


private static String keyCode="123456";//秘钥可以任意改，只要总长度是8个字节就行

private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };    

public static String encryptDES(String encryptString)

            throws Exception {

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));

        return Base64.encode(encryptedData);

    }

public static String decryptDES(String decryptString)

            throws Exception {

        byte[] byteMi = Base64.decode(decryptString);

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);

        byte decryptedData[] = cipher.doFinal(byteMi);

 

        return new String(decryptedData ,"utf-8");

    }

/**将二进制转换成16进制 

     * @param buf 

     * @return  String

*/  

    public static String parseByte2HexStr(byte buf[]) {  

            StringBuffer sb = new StringBuffer();  

            for (int i = 0; i < buf.length; i++) {  

                    String hex = Integer.toHexString(buf[i] & 0xFF);  

                    if (hex.length() == 1) {  

                            hex = '0' + hex;  

                    }  

                    sb.append(hex.toUpperCase());  

            }  

            return sb.toString();  

    }

}