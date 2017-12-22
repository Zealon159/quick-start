package zealon.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;
import zealon.redis.RedisUtil;
import zealon.redis.entity.Room;

public class App {
	private static Jedis jedis;
	public static void main(String[] args) {
		jedis = RedisUtil.getJedis();
		
		Room room = new Room();
		room.setRoomDesc("安和厅");
		room.setRoomNumber("201");
		
//		String roomStr = SerializeUtil.objectSerialiable(room);
//		jedis.set("myroom", roomStr);
		
		Room room1 = (Room)SerializeUtil.objectDeserialization(jedis.get("myroom"));
		System.out.println(room1);
		
//		Serialize(room);
//
//		Room rRoom = Deserialize();
//		System.out.println(rRoom.toString());
	}

	/**
	 * 序列化Room对象
	 * 
	 * @param room
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void Serialize(Room room) {
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File("D:/Room.txt")));
			oo.writeObject(room);
			System.out.println("Room对象序列化成功！");
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Room Deserialize() {
		ObjectInputStream ois;
		Room room = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("D:/Room.txt")));
			room = (Room) ois.readObject();
			System.out.println("room对象反序列化成功！");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}

}
