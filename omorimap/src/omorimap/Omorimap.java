package omorimap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Omorimap
 */
//一覧画面の表示
public class Omorimap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Omorimap() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 変数宣言
    // DTO型,Category_masterDTO型のオブジェクトを格納するArrayList
    private ArrayList<DTO> list = null;
    private ArrayList<Category_masterDTO> category_master = null;
    //現在時刻の取得
    String fdate1 = NowTime.nowTime();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>omorimap</title>");
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	");
		out.println("	<style>");
		out.println("		.main {");
		out.println("		width :1200px;");
		out.println("		}");
		out.println("		table {");
		out.println("		border-collapse: collapse;");
		out.println("		width :1200px;");
		out.println("		}");
		out.println("		table th {/*table内のthに対して*/");
		out.println("		padding: 10px;/*上下左右10pxずつ*/");
		out.println("		background: #fff5e5;/*背景色*/");
		out.println("		}");
		out.println("		table th.shpnm{");
		out.println("		width :600px;");
		out.println("		}");
		out.println("		table td {/*table内のtdに対して*/");
		out.println("		padding: 3px 10px;/*上下3pxで左右10px*/");
		out.println("		}");
		out.println("		table td.num{");
		out.println("		text-align:center;");
		out.println("		}");
		out.println("		table td.dlt{");
		out.println("		text-align:center;");
		out.println("		}");
		out.println("		table td.ctgry{");
		out.println("		text-align:center;");
		out.println("		}");
		out.println("		table td.star{");
		out.println("		text-align:center;");
		out.println("		}");
		out.println("		/*星用のcss*/");
		out.println("		.star5_rating{");
		out.println("		    position: relative;");
		out.println("		    z-index: 0;");
		out.println("		    display: inline-block;");
		out.println("		    white-space: nowrap;");
		out.println("		    color: #CCCCCC; /* グレーカラー 自由に設定化 */");
		out.println("		    /*font-size: 30px; フォントサイズ 自由に設定化 */");
		out.println("		}");
		out.println("		.star5_rating:before, .star5_rating:after{");
		out.println("		    content: '★★★★★';");
		out.println("		}");
		out.println("		.star5_rating:after{");
		out.println("		    position: absolute;");
		out.println("		    z-index: 1;");
		out.println("		    top: 0;");
		out.println("		    left: 0;");
		out.println("		    overflow: hidden;");
		out.println("		    white-space: nowrap;");
		out.println("		    color: #ffcf32; /* イエローカラー 自由に設定化 */");
		out.println("		}");
		out.println("		.star5_rating[data-rate=\"5\"]:after{ width: 100%; } /* 星5 */");
		out.println("		.star5_rating[data-rate=\"4\"]:after{ width: 80%; } /* 星4 */");
		out.println("		.star5_rating[data-rate=\"3\"]:after{ width: 60%; } /* 星3 */");
		out.println("		.star5_rating[data-rate=\"2\"]:after{ width: 40%; } /* 星2 */");
		out.println("		.star5_rating[data-rate=\"1\"]:after{ width: 20%; } /* 星1 */");
		out.println("	</style>");
		out.println("	<!-地図のコードの追記->");
		out.println("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("	<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.3.0/dist/leaflet.css\" />");
		out.println("	<script src=\"https://unpkg.com/leaflet@1.3.0/dist/leaflet.js\"></script>");
		out.println("	<script>");
		out.println("		function btndlt(){");
		out.println(" 		 	ret = confirm(\"この行の情報を削除します。よろしいですか？\");");
		out.println("  			if (ret == false) {");
		out.println("  				return false;");
		out.println("  			}");
		out.println("		}");
		out.println("		function init() {");
		out.println("			//地図を表示するdiv要素のidを設定");
		out.println("			var map = L.map('mapcontainer');");
		out.println("			//中心座標の指定:大森駅");
		out.println("			var mpoint = [35.589249385284106, 139.7278683];");
		out.println("			//地図の中心とズームレベルを指定");
		out.println("			map.setView(mpoint, 17);");
		out.println("			//表示するタイルレイヤのURLとAttributionコントロールの記述を設定して、地図に追加する");
		out.println("			L.tileLayer('http://tile.openstreetmap.jp/{z}/{x}/{y}.png'");
		out.println("			,{attribution: \"<a href='http://osm.org/copyright' target='_blank'>OpenStreetMap</a> contributors\" }).addTo(map);");
		out.println("			//マーカーを地図に追加");

	    try {
	    	// DAOオブジェクト化
	    	//全レコードを取得
	    	DAO objDao = new DAO();

	    	objDao.selectAllRcd();

	    	list = ListDTO.getList();
	    	category_master = Category_masterListDTO.getCategory_master();

	    	//各レコードをfor文を使って取得
	    	for(int i = 0;i < list.size();i++){
	    		DTO Dto = list.get(i);

	    		//レコードの各パラメーターを取得
	    		int intDtoNo = Dto.getNo();
	    		String strDtoShopname = Dto.getShopname();
	    		double doubleDtoLatitude = Dto.getLatitude();
	    		double doubleDtoLongitude = Dto.getLongitude();

	    		//Noカラムの値が0なら表示しない　一覧表から削除されたレコードのNoは0にしてある為
	    		if(intDtoNo > 0) {
	    			out.println("			L.marker([" + doubleDtoLatitude + "," + doubleDtoLongitude + "]).bindTooltip(\"" + strDtoShopname + "\").addTo(map);");
	    		}
	    	}
			out.println("	    }");
			out.println("	</script>");
			out.println("	");
			out.println("</head>");
			out.println("<body onload=\"init()\">");
			out.println("<body>");
			out.println("	<div class=\"main\">");
			out.println("	<div id=\"mapcontainer\" style=\"height:600px\"></div>");
			out.println("<h4 align=\"right\">");
			out.println(fdate1);
			out.println("</h4>");
			out.println("		<h1>大森周辺マップ</h1>");
			out.println("		<div align =\"right\">");
			out.println("			<button  onclick=\"location.href='/omorimap/OmorimapSub'\">");
			out.println("			新規投稿");
			out.println("			</button>");
			out.println("		</div>");
			out.println("		<p> </p>");
			out.println("		<table border=\"1\">");
			out.println("            <tr>");
			out.println("                <th>");
			out.println("                No");
			out.println("                </th>");
			out.println("                <th class=\"shpnm\">");
			out.println("                店名");
			out.println("                </th>");
			out.println("                <th>");
			out.println("                カテゴリー");
			out.println("                </th>");
			out.println("                <th>");
			out.println("                評価");
			out.println("                </th>");
			out.println("                <th>");
			out.println("                最終更新");
			out.println("                </th>");
			out.println("                <th>");
			out.println("                削除");
			out.println("                </th>");
			out.println("            </tr>");

	    	//各レコードをfor文を使って取得
	    	for(int i = 0;i < list.size();i++){
	    		DTO Dto = list.get(i);

	    		//レコードの各パラメーターを取得
	    		int intDtoId = Dto.getId();
	    		int intDtoNo = Dto.getNo();
	    		String strDtoShopname = Dto.getShopname();
	    		String strDtoComments = Dto.getComments();
	    		Date dtDtoDt = Dto.getDt();
	    		String strDtoIp = Dto.getIp();
	    		String strCDtoCategryname = category_master.get(Dto.getCategoryno() - 1).getCategoryname();
	    		int intDtoStar = Dto.getStar();

	    		//Noカラムの値が0なら表示しない　一覧表から削除されたレコードのNoは0にしてある為
	    		if(intDtoNo > 0) {
		    		out.println("            <tr>");
		    		out.println("                <td class=\"num\">");
		    		out.println("                	<form method=\"post\" action=\"/omorimap/EntryServlet\">");
		    		out.println("	                <button type=\"submit\" name=\"numid\" value=\"" + intDtoId + "\">");
		    		out.println(intDtoNo);
		    		out.println("	                </button>");
		    		out.println("                	</form>");
		    		out.println("                </td>");
		    		out.println("                <td>");
		    		out.println("                	<font size = \"4\">");
		    		out.println(strDtoShopname);
		    		out.println("                	</font>");
		    		out.println("                <div>");
		    		out.println(strDtoComments);
		    		out.println("                </div>");
		    		out.println("                </td>");
		    		out.println("                <td class=\"ctgry\">");
		    		out.println(strCDtoCategryname);
		    		out.println("                </td>");
		    		out.println("                <td class=\"star\">");
		    		out.println("                <span class=\"star5_rating\" data-rate=\"" + intDtoStar + "\"></span>");
		    		out.println("                </td>");
		    		out.println("                <td>");
		    		out.println(dtDtoDt);
		    		out.println("                <div>");
		    		out.println(strDtoIp);
		    		out.println("                </div>");
		    		out.println("                </td>");
		    		out.println("                <td class=\"dlt\">");
		    		out.println("                	<form method=\"post\" action=\"/omorimap/EntryServlet\" onsubmit = \"return btndlt();\">");
		    		out.println("		            	<button type=\"image\" name=\"dltnumid\" value=\"" + intDtoId + "\">");
		    		out.println("		            	<image src=\"file:///C:/Users/naoiyuki/git/omorimap/omorimap/trashbox.png\" width=\"30\" height=\"30\" >");
		    		out.println("	                </form>");
		    		out.println("                </td>");
		    		out.println("            </tr>");
	    		}
	    	}

	    }
	    catch (Exception e){
	      out.println("Exception:" + e.getMessage());
	    }

		out.println("        </table>");
		out.println("");
		//out.println("		</p>");
		out.println("	</div>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
