package omorimap;

import java.util.ArrayList;

//listテーブルのデータを格納するbean
public class ListDTO {
	private static ArrayList<DTO> list;

	public static ArrayList<DTO> getList() {
		return list;
	}

	public static void setList(ArrayList<DTO> list) {
		ListDTO.list = list;
	}

}
