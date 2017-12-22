package zealon.redis.entity;

import java.io.Serializable;

public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String roomNumber;
	private String roomDesc;

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomDesc=" + roomDesc + "]";
	}
	
	
}
