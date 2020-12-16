package omorimap;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();

		//パラメータを取得
		String errMsg = (String)request.getAttribute("errMsg");	//OmorimapSubの全項目に記入がされなかった時のエラー表示用の変数
		String shopname =  request.getParameter("shopname");	//OmorimapSubの全項目に記入されなかった場合に店舗名を返す
		String comments =  request.getParameter("comments");	//OmorimapSubの全項目に記入されなかった場合にコメントを返す
		String strNumId = request.getParameter("numid");	//Omorimapで選択されたレコードのid
		String rshopname = (String)request.getAttribute("shopname");	//Omorimapで選択されたレコードのshopname
		String rcomments = (String)request.getAttribute("comments");	//Omorimapで選択されたレコードのcomments

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>omorimapSub</title>");
		out.println("	<meta charset=\"UTF-8\">");
		out.println("	<style>");
		out.println("		body {");
		out.println("			width :900px;");
		out.println("		}");
		out.println("		p.title {");
		out.println("			text-align:center;");
		out.println("		}");
		out.println("		p.shopname {");
		out.println("			text-align:center;");
		out.println("		}");
		out.println("		p.errmsg {");
		out.println("			text-align:center;");
		out.println("			color:#ff0000;");
		out.println("		}");
		out.println("		textarea.comments{");
		out.println("			width:300px;");
		out.println("			height:100px;");
		out.println("		}");
		out.println("		.cmntouter {");
		out.println("		text-align:center;");
		out.println("			height: 150px;");
		out.println("		}");
		out.println("		.cmntinner{");
		out.println("			vertical-align: top;");
		out.println("		}");
		out.println("		div.button {");
		out.println("			text-align:center;");
		out.println("	</style>");
		out.println("	<script type=\"text/javascript\">");
		out.println("	function btnsave(){");
		out.println("	  	ret = confirm(\"保存します。よろしいですか？\");");
		out.println("	  	if (ret == true){");
		out.println("		  	document.form1.submit();");
		out.println("	  	}");
		out.println("	}");
		out.println("	function btncancel(){");
		out.println("	  	ret = confirm(\"一覧画面に戻ります。よろしいですか？\");");
		out.println("	  	if (ret == true){");
		out.println("		  	location.href=\"/omorimap/Omorimap\";");
		out.println("	  	}");
		out.println("	}");
		out.println("	</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<p class=\"title\">");
		out.println("		新規投稿＆編集画面");
		out.println("	</p>");

		if(errMsg != null) {
			out.println("		<p class=\"errmsg\">");
			out.println(		errMsg);
			out.println("		</p>");
		}

		out.println("	<form name=\"form1\" action=\"/omorimap/EntryServlet\" method=\"post\">");

		//編集の場合、渡されたidをhidden型でインプット
		if(strNumId != null) {
			out.println("	<input type=\"hidden\" name=\"numid\" value=\"" + strNumId + "\">");
		}

		out.println("		<p class=\"shopname\">");
		out.println("			店舗名");
		out.println("			&nbsp;");
		out.println("			<input type = \"text\" name=\"shopname\" style=\"width:300px;\" maxlength=\"30\" value=\"");

		if(shopname != null) {
			out.println(shopname);
		} else if(rshopname != null) {
			out.println(rshopname);
		}

		out.println("\">");
		out.println("		</p>");

		out.println("		<div class=\"cmntouter\">");
		out.println("				<span class=\"cmntinner\">");
		out.println("					コメント");
		out.println("				</span>");
		out.println("				<span class=\"cmntinner\">");
		out.println("					<textarea name=\"comments\" class=\"comments\" maxlength=\"100\">");

		if(comments != null) {
			out.println(comments);
		} else if (rcomments != null) {
			out.println(rcomments);
		}

		out.println("</textarea>");
		out.println("				</span>");
		out.println("		</div>");
		out.println("		<div class=\"button\">");
		out.println("			<input type=\"button\" value=\"保存\" onClick=\"btnsave()\">");
		out.println("			&nbsp;");
		out.println("			<input type=\"button\" value=\"キャンセル\" onClick=\"btncancel()\">");
		out.println("		</div>");
		out.println("	</form>");
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
