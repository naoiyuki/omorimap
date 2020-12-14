package omorimap;

import java.util.ArrayList;		//dbに登録されたlist

public class ListDTO {
	private static ArrayList<DTO> list;

	public static ArrayList<DTO> getList() {
		return list;
	}

	public static void setList(ArrayList<DTO> list) {
		ListDTO.list = list;
	}

}
