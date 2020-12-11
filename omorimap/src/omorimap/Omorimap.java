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
public class Omorimap extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Omorimap() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 配列宣言
    // SampleDTO型のオブジェクトを格納するArrayList
    private ArrayList<DTO> list = null;
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
		out.println("		width :900px;");
		out.println("		}");
		out.println("		table {");
		out.println("		border-collapse: collapse;");
		out.println("		width :900px;");
		out.println("		}");
		out.println("		table th {/*table内のthに対して*/");
		out.println("		padding: 10px;/*上下左右10pxずつ*/");
		out.println("		background: #fff5e5;/*背景色*/");
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
		out.println("	</style>");
		out.println("	<script>");
		out.println("		function btndlt(){");
		out.println(" 		 	ret = confirm(\"この行の情報を削除します。よろしいですか？\");");
		out.println("  			if (ret == false) {");
		out.println("  				return false;");
		out.println("  			}");
		out.println("		}");
		out.println("	</script>");
		out.println("	");
		out.println("</head>");
		out.println("<body>");
		out.println("	<div class=\"main\">");
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
		out.println("                <th>");
		out.println("                店名");
		out.println("                </th>");
		out.println("                <th>");
		out.println("                最終更新");
		out.println("                </th>");
		out.println("                <th>");
		out.println("                削除");
		out.println("                </th>");
		out.println("            </tr>");

	    try {
	    	// DAOオブジェクト化
	    	//全レコードを取得
	    	ConDb objCndb = new ConDb();
	    	list = objCndb.selectAllDb();

	    	//各レコードをfor文を使って取得
	    	for(int i = 0;i < list.size();i++){
	    		DTO Dto = list.get(i);

	    		//レコードの各カラムの値を取得
	    		int intDtoNo = Dto.getNo();
	    		String strDtoShopname = Dto.getShopname();
	    		String strDtoComments = Dto.getComments();
	    		Date dtDtoDt = Dto.getDt();
	    		String strDtoIp = Dto.getIp();

	    		out.println("            <tr>");
	    		out.println("                <td class=\"num\">");
	    		out.println("                	<form method=\"get\" action=\"https://www.google.com/\">");
	    		out.println("	                <input type=\"submit\" name=\"num\" value=\"" + intDtoNo + "\">");
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
	    		out.println("                <td>");
	    		out.println(dtDtoDt);
	    		out.println("                <div>");
	    		out.println(strDtoIp);
	    		out.println("                </div>");
	    		out.println("                </td>");
	    		out.println("                <td class=\"dlt\">");
	    		out.println("                	<form method=\"post\" action=\"/omorimap/EntryServlet\" onsubmit = \"return btndlt();\">");
	    		out.println("		            	<button type=\"image\" name=\"dltnum\" value=\"" + intDtoNo + "\">");
	    		out.println("		            	<image src=\"file:///C:/Users/naoiyuki/git/omorimap/omorimap/trashbox.png\" width=\"30\" height=\"30\" >");
	    		out.println("	                </form>");
	    		out.println("                </td>");
	    		out.println("            </tr>");
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
