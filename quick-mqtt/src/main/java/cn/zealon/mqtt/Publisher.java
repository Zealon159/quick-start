package cn.zealon.mqtt;

import java.util.LinkedList;
import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class Publisher {

	public static void main(String[] args) throws Exception {

		String user = env("APOLLO_USER", "admin");
		String password = env("APOLLO_PASSWORD", "1Q2w3e4r");
		String host = env("APOLLO_HOST", "localhost");
		int port = Integer.parseInt(env("APOLLO_PORT", "61613"));
		final String destination = arg(args, 0, "mqtt/fusesource/callback");

		int messages = 10;
		int size = 256;

		String DATA = "abcdefghijklmnopqrstuvwxyz";
		String body = "哈";
		for (int i = 0; i < size; i++) {
			body += DATA.charAt(i % DATA.length());
		}
		Buffer msg = new AsciiBuffer(body);

		MQTT mqtt = new MQTT();
		mqtt.setHost(host, port);
		mqtt.setUserName(user);
		mqtt.setPassword(password);

		FutureConnection connection = mqtt.futureConnection();
		connection.connect().await();

		final LinkedList<Future<Void>> queue = new LinkedList<Future<Void>>();
		UTF8Buffer topic = new UTF8Buffer(destination);
		for (int i = 1; i <= messages; i++) {

			// Send the publish without waiting for it to complete. This allows
			// us
			// to send multiple message without blocking..
			queue.add(connection.publish(topic, msg, QoS.AT_LEAST_ONCE, false));

			// Eventually we start waiting for old publish futures to complete
			// so that we don't create a large in memory buffer of outgoing
			// message.s
			if (queue.size() >= 1000) {
				queue.removeFirst().await();
			}

		}

		queue.add(connection.publish(topic, new AsciiBuffer("SHUTDOWN"), QoS.AT_LEAST_ONCE, false));
		while (!queue.isEmpty()) {
			queue.removeFirst().await();
		}

		connection.disconnect().await();

		System.exit(0);
	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}
}
