package cn.zealon.client;


import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * @Author: zealon
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args){

        String host = "localhost";
        int port = 9000;
        try {
            Socket socket = new Socket(host,port);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("nfud");
            pw.flush();

            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            String data = null;
            while ((data=br.readLine())!=null){
                System.out.println("服务器响应："+data);
            }

            br.close();
            reader.close();
            is.close();

            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void url(){
        try {
            URL url = new URL("https://zealon.cn");
            System.out.println(url.toString());
            System.out.println(url.getProtocol());
            InputStream is = url.openStream();
            InputStreamReader isReader = new InputStreamReader(is,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader((isReader));

            String data = bufferedReader.readLine();
            while (data!=null){
                System.out.println(data);
                data = bufferedReader.readLine();
            }

            bufferedReader.close();
            isReader.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
