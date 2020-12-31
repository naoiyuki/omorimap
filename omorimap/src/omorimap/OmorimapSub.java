package omorimap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class OmorimapSub
 */
//登録画面の表示
public class OmorimapSub extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OmorimapSub() {
        super();
    }

 // DTO型,Category_masterDTO型のオブジェクトを格納するArrayList
    private ArrayList<Category_masterDTO> category_master = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();

		//パラメータを取得
		String errMsg = (String)request.getAttribute("errMsg");		//OmorimapSubの全項目に記入がされなかった時のエラー表示用の変数
		String shopname =  request.getParameter("shopname");		//記入されなかった場合に店舗名を返す
		String comments =  request.getParameter("comments");		//記入されなかった場合にコメントを返す
		String categoryno =  request.getParameter("categoryno");	//記入されなかった場合にカテゴリー番号を返す
		String star =  request.getParameter("star");				//記入されなかった場合に評価を返す
		String latitude =  request.getParameter("latitude");		//記入されなかった場合に緯度を返す
		String longitude =  request.getParameter("longitude");		//記入されなかった場合に経度を返す
		String strNumId = request.getParameter("numid");					//Omorimapで選択されたレコードのid
		String rshopname = (String)request.getAttribute("shopname");		//Omorimapで選択されたレコードのshopname
		String rcomments = (String)request.getAttribute("comments");		//Omorimapで選択されたレコードのcomments
		String rcategoryno = (String)request.getAttribute("categoryno");	//Omorimapで選択されたレコードのcategoryno
		String rstar = (String)request.getAttribute("star");				//Omorimapで選択されたレコードのstar
		String rlatitude = (String)request.getAttribute("latitude");		//Omorimapで選択されたレコードのstar
		String rlongitude = (String)request.getAttribute("longitude");		//Omorimapで選択されたレコードのstar
		category_master = Category_masterListDTO.getCategory_master();		//登録されたカテゴリーマスター


		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>omorimapSub</title>");
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	<style>");
		out.println("		.main{");
		out.println("			width :900px;");
		out.println("		}");
		out.println("		.sub{");
		out.println("			width :400px;");
		out.println("			margin:  0 auto;");
		out.println("		}");
		out.println("		.title {");
		out.println("			text-align:center;");
		out.println("		}");
		out.println("		.shopname {");
		out.println("			text-align:left;");
		out.println("		}");
		out.println("		.errmsg {");
		out.println("			text-align:center;");
		out.println("			color:#ff0000;");
		out.println("		}");
		out.println("		.category {");
		out.println("			text-align:left;");
		out.println("		}");
		out.println("		.star {");
		out.println("			text-align:left;");
		out.println("		}");
		out.println("		.comments{");
		out.println("			width:300px;");
		out.println("			height:100px;");
		out.println("			text-align:left;");
		out.println("		}");
		out.println("		.cmntouter {");
		out.println("			height: 150px;");
		out.println("			display:  table-cell;");
		out.println("			vertical-align:  middle;");
		out.println("		}");
		out.println("		.cmntinner{");
		out.println("			vertical-align: top;");
		out.println("		}");
		out.println("		div.button {");
		out.println("			text-align:center;");
		out.println("	</style>");
		out.println("	<!-地図のコードの追記->");
		out.println("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("	<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.3.0/dist/leaflet.css\" />");
		out.println("	<script src=\"https://unpkg.com/leaflet@1.3.0/dist/leaflet.js\"></script>");
		out.println("	<script type=\"text/javascript\">");
		out.println("	//保存ボタンの処理");
		out.println("	function btnsave(){");
		out.println("	  	ret = confirm(\"保存します。よろしいですか？\");");
		out.println("	  	if (ret == true){");
		out.println("		  	document.form1.submit();");
		out.println("	  	}");
		out.println("	}");
		out.println("	//キャンセルボタンの処理");
		out.println("	function btncancel(){");
		out.println("	  	ret = confirm(\"一覧画面に戻ります。よろしいですか？\");");
		out.println("	  	if (ret == true){");
		out.println("		  	location.href=\"/omorimap/Omorimap\";");
		out.println("	  	}");
		out.println("	}");
		out.println("	//地図表示の処理");
		out.println("	var marker;		//マーカー");
		out.println("	var lat;		//マーカー移動後の緯度");
		out.println("	var lng;		//マーカー移動後の緯度");
		out.println("	function init() {");
		out.println("		//地図を表示するdiv要素のidを設定");
		out.println("		var map = L.map('mapcontainer');");

		//マーカーの設置
		if(latitude != null) {
			String latitudeStripped = StringUtils.strip(latitude);
			if("".equals(latitudeStripped)) {
				//新規登録の際に移動しなかった場合、もう一度大森駅の座標を設定
				out.println("		var mpoint = [35.589249385284106, 139.7278683];");
			} else {
				//移動した座標の再表示する際の座標
				out.println("		var mpoint = [" + latitude + ", " + longitude + "];");
				out.println("    	document.getElementById('lat').value=" + latitude + ";");
				out.println("    	document.getElementById('lng').value=" + longitude + ";");
			}
		} else if(rlatitude != null) {
			//編集登録の初期の座標
			out.println("		var mpoint = [" + rlatitude + ", " + rlongitude + "];");
			out.println("    	document.getElementById('lat').value=" + rlatitude + ";");
			out.println("    	document.getElementById('lng').value=" + rlongitude + ";");
		} else {
			//初回の座標
			out.println("		//中心座標の指定:大森駅");
			out.println("		var mpoint = [35.589249385284106, 139.7278683];");
		}

		out.println("		//地図の中心とズームレベルを指定");
		out.println("		map.setView(mpoint, 17);");
		out.println("		//表示するタイルレイヤのURLとAttributionコントロールの記述を設定して、地図に追加する");
		out.println("		L.tileLayer('http://tile.openstreetmap.jp/{z}/{x}/{y}.png'");
		out.println("		,{attribution: \"<a href='http://osm.org/copyright' target='_blank'>OpenStreetMap</a> contributors\" }).addTo(map);");
		out.println("		//マーカーを地図に追加");
		out.println(" 		marker = L.marker(mpoint,{draggable:true}).bindTooltip(\"登録位置\").addTo(map);");
		out.println("    	//ドラッグイベント");
		out.println("		marker.on('dragend', function(e) {");
		out.println("		    //クリック位置経緯度取得");
		out.println("		    lat = e.target._latlng.lat;");
		out.println("    		lng = e.target._latlng.lng;");
		out.println("    		document.getElementById('lat').value= lat;");
		out.println("    		document.getElementById('lng').value= lng;");
		out.println("		} );");
		out.println("    }");
		out.println("	</script>");
		out.println("</head>");
		out.println("<body onload=\"init()\">");
		out.println("	<div class = \"main\">");
		out.println("	<div id=\"mapcontainer\" style=\"height:500px\"></div>");
		out.println("		<div class = \"sub\">");
		out.println("			<p class=\"title\">新規投稿＆編集画面</p>");

		//エラーメッセージの表示
		if(errMsg != null) {
			out.println("				<p class=\"errmsg\">"+ errMsg + "</p>");
		}

		out.println("			<form name=\"form1\" action=\"/omorimap/EntryServlet\" method=\"post\">");

		//編集の場合、渡されたidをhidden型でインプット
		if(strNumId != null) {
			out.println("			<input type=\"hidden\" name=\"numid\" value=\"" + strNumId + "\">");
		}
		out.println("				<p class=\"shopname\">店舗名&nbsp;");
		out.println("					<input type = \"text\" name=\"shopname\" style=\"width:300px;\" maxlength=\"30\" value=\"");

		//店名の再表示
		if(shopname != null) {
			out.println(shopname);
		} else if(rshopname != null) {
			out.println(rshopname);
		}

		out.println("\">");
		out.println("				</p>");
		out.println("				<div class=\"category\">");
		out.println("					<p>カテゴリー&nbsp;");
		out.println("						<select name=\"categoryno\">");
		out.println("							<option selected hidden></option>");

		//カテゴリーの再表示
		for(int i = 0;i < category_master.size();i++) {
			Category_masterDTO objCDto = category_master.get(i);

			out.println("							<option value=\"" + objCDto.getCategoryno() + "\"");
			if(String.valueOf(objCDto.getCategoryno()).equals(categoryno)
					|| String.valueOf(objCDto.getCategoryno()).equals(rcategoryno)) {
				out.println("							selected");
			}
			out.println(">" + objCDto.getCategoryname() + "</option>");
		}

		out.println("						</select>");
		out.println("					</p>");
		out.println("				</div>");
		out.println("				<div class=\"star\">評価");

		//評価の再表示
		for(int i = 1;i <= 5;i++) {
			out.println("					<input type=\"radio\" name=\"star\" value=\""
					+ i + "\"");
			if(String.valueOf(i).equals(star)
					|| String.valueOf(i).equals(rstar)) {
				out.println("					checked");
			}
			out.println("					>" + i);
		}

		out.println("				</div>");
		out.println("				<div class=\"cmntouter\">");
		out.println("						<span class=\"cmntinner\">コメント</span>");
		out.println("						<span class=\"cmntinner\">");
		out.println("							<textarea name=\"comments\" class=\"comments\" maxlength=\"100\">");


		//コメントの再表示
		if(comments != null) {
			out.println(comments);
		} else if (rcomments != null) {
			out.println(rcomments);
		}

		out.println("</textarea>");
		out.println("						</span>");
		out.println("				</div>");
		out.println("				<div>");
		out.println("					<input type=\"hidden\" name=\"latitude\" id=\"lat\">");
		out.println("					<input type=\"hidden\" name=\"longitude\" id=\"lng\">");
		out.println("				</div>");
		out.println("				<div class=\"button\">");
		out.println("					<input type=\"button\" value=\"保存\" onClick=\"btnsave()\">");
		out.println("					&nbsp;");
		out.println("					<input type=\"button\" value=\"キャンセル\" onClick=\"btncancel()\">");
		out.println("				</div>");
		out.println("			</form>");
		out.println("		</div>");
		out.println("	</div>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
