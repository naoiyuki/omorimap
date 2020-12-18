package omorimap;

import java.util.ArrayList;

//category_masterテーブルのデータを格納するbean
public class Category_masterListDTO {
	private static ArrayList<Category_masterDTO> category_master;

	public static ArrayList<Category_masterDTO> getCategory_master() {
		return category_master;
	}

	public static void setCategory_master(ArrayList<Category_masterDTO> category_master) {
		Category_masterListDTO.category_master = category_master;
	}

}
