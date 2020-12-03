package oomorimap;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // 配列宣言
    // SampleDTO型のオブジェクトを格納するArrayList
    private ArrayList<DTO>list = null;


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
		out.println("	</style>");
		out.println("	");
		out.println("</head>");
		out.println("<body>");
		out.println("	<div class=\"main\">");
		out.println("<h4 align=\"right\">");
		Calendar cal1 = Calendar.getInstance();
		Date date1 = cal1.getTime();
		SimpleDateFormat sdformat
		= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fdate1 = sdformat.format(date1);


		out.println(fdate1);
		out.println("</h4>");
		out.println("		<h1>大森周辺マップ</h1>");
		out.println("		<div align =\"right\">");
		out.println("			<button>");
		out.println("			新規投稿");
		out.println("			</button>");
		out.println("		</div>");
		out.println("		<p> </p>");
		out.println("		<table border=\"1\">");
		out.println("            <tr>");
		out.println("                <th class>");
		out.println("                No");
		out.println("                </th>");
		out.println("                <th>");
		out.println("                店名");
		out.println("                </th>");
		out.println("                <th>");
		out.println("                最終更新");
		out.println("                </th>");
		out.println("            </tr>");


//		Connection conn = null;
//	    String url = "jdbc:mysql://localhost/mysql?serverTimezone=JST";
//	    String user = "root";
//	    String password = "naoi";

	    try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//	  		conn = DriverManager.getConnection(url, user, password);
//	     	Statement stmt = conn.createStatement();
//	      	String sql = "SELECT * FROM list";
//	      	ResultSet rs = stmt.executeQuery(sql);

	    	// DAOオブジェクト化
	    	ConDb objCndb = new ConDb();
	    	list = objCndb.selectAll();

	    	for(int i = 0;i < list.size();i++){
	    		DTO Dto = list.get(i);

//	    		int no = rs.getInt("no");
//	    		String shopname = rs.getString("shopname");
//	    		String comments = rs.getString("comments");
//	    		Date dt = rs.getDate("dt");
//	    		String ip = rs.getString("ip");

	    		int no = Dto.getNo();
	    		String shopname = Dto.getShopname();
	    		String comments = Dto.getComments();
	    		Date dt = Dto.getDt();
	    		String ip = Dto.getIp();

	    		out.println("            <tr>");
	    		out.println("                <td>");
	    		out.println(no);
	    		out.println("                </td>");
	    		out.println("                <td>");
	    		out.println(shopname);
	    		out.println("                <div>");
	    		out.println(comments);
	    		out.println("                </div>");
	    		out.println("                </td>");
	    		out.println("                <td>");
	    		out.println(dt);
	    		out.println("                <div>");
	    		out.println(ip);
	    		out.println("                </div>");
	    		out.println("                </td>");
	    		out.println("            </tr>");

	    	}

	    }
//	    catch (SQLException e){
//	      out.println("SQLException:" + e.getMessage());
//	    }
	    catch (Exception e){
	      out.println("Exception:" + e.getMessage());
	    }
//	    finally{
//	      try{
//	        if (conn != null){
//	          conn.close();
//
//	        }else{
//	          out.println("コネクションがありません");
//	        }
//	      }catch (SQLException e){
//	        out.println("SQLException:" + e.getMessage());
//	      }
//	    }




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
