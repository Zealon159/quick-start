package cn.zealon.mqtt;

import org.fusesource.hawtbuf.*;
import org.fusesource.mqtt.client.*;

public class Listener {
	public static void main(String []args) throws Exception {

        String user = env("APOLLO_USER", "admin");
        String password = env("APOLLO_PASSWORD", "1Q2w3e4r");
        String host = env("APOLLO_HOST", "localhost");
        int port = Integer.parseInt(env("APOLLO_PORT", "61613"));
        final String destination = arg(args, 0, "mqtt/edata");


        MQTT mqtt = new MQTT();
        mqtt.setHost(host, port);
        mqtt.setUserName(user);
        mqtt.setPassword(password);


        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new org.fusesource.mqtt.client.Listener() {
            long count = 0;
            long start = System.currentTimeMillis();

            public void onConnected() {
            }
            public void onDisconnected() {
            }
            public void onFailure(Throwable value) {
                value.printStackTrace();
                System.exit(-2);
            }
            public void onPublish(UTF8Buffer topic, Buffer msg, Runnable ack) {
                String body = msg.utf8().toString();
                System.out.println(body);
//                if( "SHUTDOWN".equals(body)) {
//                    long diff = System.currentTimeMillis() - start;
//                    System.out.println(String.format("Received %d in %.2f seconds", count, (1.0*diff/1000.0)));
//                    connection.disconnect(new Callback<Void>() {
//                        @Override
//                        public void onSuccess(Void value) {
//                            System.exit(0);
//                        }
//                        @Override
//                        public void onFailure(Throwable value) {
//                            value.printStackTrace();
//                            System.exit(-2);
//                        }
//                    });
//                } else {
//                    if( count == 0 ) {
//                        start = System.currentTimeMillis();
//                    }
//                    if( count % 1000 == 0 ) {
//                        System.out.println(String.format("Received %d messages.", count));
//                    }
//                    count ++;
//                }
            }
        });
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                Topic[] topics = {new Topic(destination, QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    public void onSuccess(byte[] qoses) {
                    }
                    public void onFailure(Throwable value) {
                        value.printStackTrace();
                        System.exit(-2);
                    }
                });
            }
            @Override
            public void onFailure(Throwable value) {
                value.printStackTrace();
                System.exit(-2);
            }
        });

        // Wait forever..
        synchronized (Listener.class) {
            while(true)
                Listener.class.wait();
        }
    }

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if( rc== null )
            return defaultValue;
        return rc;
    }

    private static String arg(String []args, int index, String defaultValue) {
        if( index < args.length )
            return args[index];
        else
            return defaultValue;
    }
}
