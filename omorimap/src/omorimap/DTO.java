package omorimap;

import java.sql.Date;

//listテーブルの各レコードを格納するbean
public class DTO {
	private int id;				//レコードのインデックス
	private int no;				//一覧表の番号
	private String shopname;	//ショップ名
	private String comments;	//コメント
	private Date dt;			//登録日時
	private String ip;			//クライアントIP
	private int categoryno;		//カテゴリー番号
	private int star;			//評価の星の数
	private double latitude;	//マーカーの緯度
	private double longitude;	//マーカーの経度

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

	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categeryno) {
		this.categoryno = categeryno;
	}

	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
