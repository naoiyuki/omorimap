package omorimap;

//category_masterテーブルの各レコードを格納するbean
public class Category_masterDTO {
	private int categoryno;				//カテゴリー番号
	private String categoryname;		//カテゴリー名

	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}

	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
}
