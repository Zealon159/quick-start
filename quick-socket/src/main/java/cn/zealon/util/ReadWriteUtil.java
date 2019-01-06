package cn.zealon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zealon
 * @Version: 1.0
 */
public class ReadWriteUtil {
    public static String InputStreamToString(BufferedReader br){
        StringBuffer data = new StringBuffer();
        try {
            String line = br.readLine();
            while (line!=null){
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
