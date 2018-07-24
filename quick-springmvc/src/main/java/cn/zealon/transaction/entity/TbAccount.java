package cn.zealon.transaction.entity;


/**
 * 
 * 
 * @author zealon
 * @date 2018-07-23 11:28:57
 * 
 */
public class TbAccount {
	private static final long serialVersionUID = 1L;
	private String uid;
	private Integer account;

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}

	public Integer getAccount() {
		return account;
	}
}
