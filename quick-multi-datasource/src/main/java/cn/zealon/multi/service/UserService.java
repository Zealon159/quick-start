package cn.zealon.multi.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	public List<Map<String,Object>> selectAllByMasterDB();
	
	public List<Map<String,Object>> selectAllByOtherDB();
	
	public String selectAllDB();
}
