package omorimap;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntryServlet
 */
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//URL用の変数
		String disp = "";

		//requestパラメータを取得
		String shopname = request.getParameter("shopname");
		String comments = request.getParameter("comments");

		//OmorimapSubの登録処理
		if(shopname != null && comments != null) {
			//requestパラメータをチェック
			String errMsg = "";
			if(shopname == null || "".equals(shopname.trim())) {
				errMsg += "店舗名を入力して下さい。<br>";
			}
			if(comments == null || "".equals(comments.trim())) {
				errMsg += "コメントを入力して下さい。<br>";
			}
	
			//入力不備がないかエラーチェック
			if(errMsg != "") {
				request.setAttribute("errMsg",errMsg);
				//OmorimapSubに画面遷移
				disp = "/OmorimapSub";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		        dispatch.forward(request, response);
			}
			//エラーメッセージ無し
			else
			{
			//現在日時を取得
			Date dt = NowTime.nowSqlTime();
	        //requestにてホストのIPアドレスを取得
			String ip = GetIp.getClientIp(request);
	
			//上記の値をDBに更新
			ConDb.UpdateDb(shopname,comments,dt,ip);
	
			//Omorimapに画面遷移
			disp = "/omorimap/Omorimap";
			response.sendRedirect(disp);
			}
		}
	}

}
