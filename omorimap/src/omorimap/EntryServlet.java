package omorimap;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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
	//Omorimap,OmorimapSubから受け取った値に応じて処理を行う
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//URL用の変数
		String disp = "";

		//パラメータを取得
		String strNumId = request.getParameter("numid");
		String strShopname = request.getParameter("shopname");
		String strComments = request.getParameter("comments");
		String strDltNumId = request.getParameter("dltnumid");
		//listを取得
		ArrayList<DTO> list = ListDTO.getList();
		//insert用　一覧表のNoの最後の値を取得する為の変数
		int intListLastIndex = list.size() - 1;	//size()-1してListDTOの最終行の引数を取得
		//int intNo = ListDTO.getList().get(intListLastIndex).getNo();	//listDTOの最終行のNoを取得
		int intLastNo  = 0;	//最後のNo用変数を初期化
		//現在日時を取得
		Date dt = NowTime.nowSqlTime();
        //requestにてホストのIPアドレスを取得
		String ip = GetIp.getClientIp(request);

		//OmorimapSubの項目をDBへ追加処理
		if(strShopname != null && strComments != null) {
			//requestパラメータをチェック
			String errMsg = "";
			if("".equals(strShopname.trim())) {
				errMsg += "店舗名を入力して下さい。<br>";
			}
			if("".equals(strComments.trim())) {
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
			//エラー無し→追加
			else
			{
				//最後のNoを取得
				for(int i = intListLastIndex;i > 0;i--) {
					if(list.get(i).getNo() > 0) {
						intLastNo = list.get(i).getNo();
						break;
					}
				}
				//追加するNoの為、最後のNoに+1する
				intLastNo++;
				//上記の値をDBに更新
				DAO.insertRcd(intLastNo,strShopname,strComments,dt,ip);

				//Omorimapに画面遷移
				disp = "/omorimap/Omorimap";
				response.sendRedirect(disp);
			}
		}

		//Omorimapから受け取ったdltnumidに基づいて削除処理
		if(strDltNumId != null) {
			int intDltNumId = Integer.parseInt(strDltNumId);
			DAO.hideRcd(intDltNumId);

			//Omorimapに画面遷移
			disp = "/omorimap/Omorimap";
			response.sendRedirect(disp);
		}

		//Omorimapからnumidを受け取ってパラメーターをOmorimapSubで再表示
		if(strNumId != null) {

		}
	}

}
