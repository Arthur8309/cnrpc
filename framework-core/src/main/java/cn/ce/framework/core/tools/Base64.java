/**        
 * 类名称：Base64.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2017年3月6日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @author Arthur
 *
 */
public class Base64 {


    private static final char[] legalChars = "ghijk67stuJKLM89rvwxyzAlmnOUV+/abcdefPQRSTopqCDEFG012345BHINWXYZ"

            .toCharArray();//字典内顺序任意更改，只要内容保证不缺就行

    private static HashMap<Character,Integer> hashDecode=new HashMap<Character,Integer>();

    /**

     * data[]进行编码

     * 

     * @param data

     * @return

     */

    public static String encode(byte[] data) {

        int start = 0;

        int len = data.length;

        StringBuffer buf = new StringBuffer(data.length * 3 / 2);


        int end = len - 3;

        int i = start;

        int n = 0;


        while (i <= end) {

            int d = ((((int) data[i]) & 0x0ff) << 16)

                    | ((((int) data[i + 1]) & 0x0ff) << 8)

                    | (((int) data[i + 2]) & 0x0ff);


            buf.append(legalChars[(d >> 18) & 63]);

            buf.append(legalChars[(d >> 12) & 63]);

            buf.append(legalChars[(d >> 6) & 63]);

            buf.append(legalChars[d & 63]);


            i += 3;


            if (n++ >= 14) {

                n = 0;

                buf.append(" ");

            }

        }


        if (i == start + len - 2) {

            int d = ((((int) data[i]) & 0x0ff) << 16)

                    | ((((int) data[i + 1]) & 255) << 8);


            buf.append(legalChars[(d >> 18) & 63]);

            buf.append(legalChars[(d >> 12) & 63]);

            buf.append(legalChars[(d >> 6) & 63]);

            buf.append("=");

        } else if (i == start + len - 1) {

            int d = (((int) data[i]) & 0x0ff) << 16;


            buf.append(legalChars[(d >> 18) & 63]);

            buf.append(legalChars[(d >> 12) & 63]);

            buf.append("==");

        }


        return buf.toString();

    }

    public static byte[] decode(String s) {

    

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {

            decode(s, bos);

        } catch (IOException e) {

            throw new RuntimeException();

        }

        byte[] decodedBytes = bos.toByteArray();

        try {

            bos.close();

            bos = null;

        } catch (IOException ex) {

            System.err.println("Error while decoding BASE64: " + ex.toString());

        }

        return decodedBytes;

    }

    private static void decode(String s, OutputStream os) throws IOException {

        int i = 0;


        int len = s.length();


        while (true) {

            while (i < len && s.charAt(i) <= ' ')

                i++;


            if (i == len)

                break;


            int tri = (decode(s.charAt(i)) << 18)

                    + (decode(s.charAt(i + 1)) << 12)

                    + (decode(s.charAt(i + 2)) << 6)

                    + (decode(s.charAt(i + 3)));


            os.write((tri >> 16) & 255);

            if (s.charAt(i + 2) == '=')

                break;

            os.write((tri >> 8) & 255);

            if (s.charAt(i + 3) == '=')

                break;

            os.write(tri & 255);


            i += 4;

        }

    }    

    private static int decode(char c) {

     

    if(hashDecode.size()==0)

    {

    for(int i =0;i<64;i++)

    { char ch = legalChars[i];

    hashDecode.put(ch, i);

    }

    }

    if(hashDecode.containsKey(c))

    {

    return hashDecode.get(c);

    }

    else if(c =='=')

    return 0;

    else

             throw new RuntimeException("unexpected code: " + c);

    }

}
