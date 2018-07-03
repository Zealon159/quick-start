package cn.zealon.mqtt;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

public class Subscribe {
	private static String HOST = "tcp://127.0.0.1:61613";
	private static String USERNAME = "admin";
	private static String PASSWORD = "1Q2w3e4r";
	private final static boolean CLEAN_START = true;
	private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
	public static Topic[] topics = { new Topic("mqtt/fusesource/callback", QoS.EXACTLY_ONCE) };
	public final static long RECONNECTION_ATTEMPT_MAX = 6;
	public final static long RECONNECTION_DELAY = 2000;

	public final static int SEND_BUFFER_SIZE = 2 * 1024 * 1024;// 发送最大缓冲为2M

	public static void main(String[] args) throws Exception {
		// 创建MQTT对象
		MQTT mqtt = new MQTT();
		mqtt.setClientId("CallbackClient");
		// 设置mqtt broker的ip和端口
		mqtt.setHost(HOST);
		mqtt.setUserName(USERNAME);
		mqtt.setPassword(PASSWORD);
		// 连接前清空会话信息
		mqtt.setCleanSession(CLEAN_START);
		// 设置重新连接的次数
		mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
		// 设置重连的间隔时间
		mqtt.setReconnectDelay(RECONNECTION_DELAY);
		// 设置心跳时间
		mqtt.setKeepAlive(KEEP_ALIVE);
		// 设置缓冲的大小
		mqtt.setSendBufferSize(SEND_BUFFER_SIZE);
		// 获取mqtt的连接对象CallbackConnection
		CallbackConnection connection = mqtt.callbackConnection();

		//连接
		connection.connect(new Callback<Void>() {
			//可以在connect的onSuccess方法中发布或者订阅相应的主题
			public void onSuccess(Void oid) {
				//进入该方法表示连接成功连接成功 
				System.out.println("连接成功："+oid);
			}
			//onFailure方法中作相应的断开连接等操作
			public void onFailure(Throwable cause) {
				//进入该方法表示连接失败
				System.out.println("连接失败");
			}
		});

		//监听
		connection.listener(new Listener() {
			//表示成功，可以获取到订阅的主题和订阅的内容（UTF8Buffer topicmsg表示订阅的主题, 
			//Buffer msg表示订阅的类容），一般的可以在这个方法中获取到订阅的主题和内容然后进行相应的判断和其他业务逻辑操作；
			public void onPublish(UTF8Buffer topicmsg, Buffer msg, Runnable ack) {
				 //utf-8 is used for dealing with the garbled  
		        String topic = topicmsg.utf8().toString();  
		        String payload = msg.utf8().toString();  
				System.out.println("topic:"+topic);
				System.out.println("message:"+payload);
				//表示监听成功
				ack.run();
			}
			//表示监听失败，这里可以调用相应的断开连接等方法；
			public void onFailure(Throwable arg0) {
				//表示监听失败  
			}
			//表示监听到连接建立，该方法只在建立连接成功时执行一次，
			//表示连接成功建立，如果有必要可以在该方法中进行相应的订阅操作；
			public void onDisconnected() {
				 //表示监听到断开连接  
			}
			//表示监听到连接断开，该方法只在断开连接时执行一次，如有必要可以进行相应的资源回收操作。
			public void onConnected() {
				  //表示监听到连接成功  
			}
		});

		//订阅
		connection.subscribe(topics, new Callback<byte[]>() {
			//Topic[] topics表示定于的主题数组，注意只有在改方法订阅的主题，才能够在监听方法中接收到。
			public void onSuccess(byte[] qoses) {
				//主题订阅成功 
				System.out.println("订阅成功:"+new String(qoses));
			}
			public void onFailure(Throwable arg0) {
				 //状态主题订阅失败
				System.out.println("订阅失败");
			}
		});

		//断开连接
		/*connection.disconnect(new Callback<Void>() {
			public void onSuccess(Void value) {
				//与服务器断开连接成功 
			}
			
			public void onFailure(Throwable value) {
				 //与服务器断开连接失败 
			}
		});*/

		//回调将执行与连接相关联的调度队列，以便可以安全使用从回调的连接，但你绝不能在回调中执行任何阻塞操作，否则会改变执行的顺序，
		//这样可能出错。如果可能存在阻塞时，最好是在连接的调度队列中执行另外一个线程
		connection.getDispatchQueue().execute(new Runnable() {
			public void run() {
				//在这里进行相应的订阅、发布、停止连接等等操作 
			}
		});
	}
}
