package cn.goldlone.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CN on 2018/2/22.
 */
public class IOUtil {


    public static String streamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        int len;
        byte[] buf = new byte[1024];
        try {
            while((len = inputStream.read(buf)) != -1)
                sb.append(new String(buf, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
