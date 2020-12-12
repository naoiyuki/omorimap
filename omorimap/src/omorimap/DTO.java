package omorimap;

import java.sql.Date;

public class DTO {
	private int no;				//一覧表の番号
	private String shopname;	//ショップ名
	private String comments;	//コメント
	private Date dt;			//登録日時
	private String ip;			//登録IP

	private int id;				//レコードのインデックス
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}


}
