package cn.zealon.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zealon
 * @Version: 1.0
 */
public class MyScoketServer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            while (true) {
                // 接收
                Socket socket = serverSocket.accept();
                SocketTask task = new SocketTask(socket);
                executorService.execute(task);
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Socket 任务处理
     */
    private static class SocketTask implements Runnable{

        private Socket socket;

        public SocketTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = this.socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);

                String data = null;
                while ((data = br.readLine()) != null) {
                    System.out.println("接收到请求：" + data);
                }

                // 关闭输入流
                this.socket.shutdownInput();

                // 响应请求
                OutputStream os = this.socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("处理时间:" + System.currentTimeMillis());
                pw.flush();

                // 关闭资源
                pw.close();
                os.close();
                br.close();
                is.close();
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
