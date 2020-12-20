package omorimap;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;



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
		String strCategoryno = request.getParameter("categoryno");
		String strStar =  request.getParameter("star");
		String strDltNumId = request.getParameter("dltnumid");

		//list,category_masterを取得
		ArrayList<DTO> list = ListDTO.getList();
		//ListDTOの引数の最大値を取得
		int intListLastIndex = list.size() - 1;	//size()-1してListDTOの最終行の引数を取得

		//現在日時を取得
		Date dt = NowTime.nowSqlTime();
        //requestにてホストのIPアドレスを取得
		String ip = GetIp.getClientIp(request);

		//OmorimapSubの各値をDBへ追加処理
		//条件にstrShopname != nullを追加し、OmorimapSubから受け取ったパラメーターと判断
		if(strShopname != null) {
			//入力不備がないかエラーチェック
			String errMsg = "";
			String strCommentsStripped = StringUtils.strip(strComments);
			if("".equals(strShopname.trim())) {
				errMsg += "店舗名を入力して下さい。<br>";
			}
			if("".equals(strCommentsStripped)) {
				errMsg += "コメントを入力して下さい。<br>";
			}
			if("".equals(strCategoryno.trim())) {
				errMsg += "カテゴリーを選択して下さい。<br>";
			}
			if(strStar == null) {
				errMsg += "評価を選択して下さい。<br>";
			}
			if(errMsg != "") {
				request.setAttribute("errMsg",errMsg);

				//OmorimapSubに画面遷移
				disp = "/OmorimapSub";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		        dispatch.forward(request, response);
			}
			//エラー無し,Omorimapから送られてくるid無し(idはsqlで自動インクリメント)→新規追加
			else if (strNumId == null)
			{
				//表示されるNoの最大値用の変数を初期化
				int intMaxNo  = 0;

				//最後のNoを取得
				for(int i = intListLastIndex;i >= 0;i--) {
					if(list.get(i).getNo() > 0) {
						intMaxNo = list.get(i).getNo();
						break;
					}
				}
				//追加するNoの為、最後のNoに+1する
				intMaxNo++;

				//リクエストパラメーターの整数化
				int intCategoryno = Integer.parseInt(strCategoryno.trim());
				int intStar = Integer.parseInt(strStar.trim());

				//上記の値をDBに更新
				DAO.insertRcd(intMaxNo,strShopname,strComments,dt,ip,intCategoryno,intStar);

				//Omorimapに画面遷移
				disp = "/omorimap/Omorimap";
				response.sendRedirect(disp);
			}
			//エラー無し,Omorimapから送られてくるid有り→上書き
			else if (strNumId != null) {
				//リクエストパラメーターの整数化
				int intNumId = Integer.parseInt(strNumId.trim());
				int intCategoryno = Integer.parseInt(strCategoryno.trim());
				int intStar = Integer.parseInt(strStar.trim());
				strShopname.trim();
				strComments.trim();

				//上記の値をDBに更新
				DAO.updateRcd(intNumId,strShopname,strComments,dt,ip,intCategoryno,intStar);

				//Omorimapに画面遷移
				disp = "/omorimap/Omorimap";
				response.sendRedirect(disp);
			}
		}

		//Omorimapから受け取ったdltnumidに基づいて非表示処理
		if(strDltNumId != null) {
			int intDltNumId = Integer.parseInt(strDltNumId);
			DAO.hideRcd(intDltNumId);

			//Omorimapに画面遷移
			disp = "/omorimap/Omorimap";
			response.sendRedirect(disp);
		}

		//OmorimapSub編集用の画面遷移処理 Omorimapからnumidを受け取り該当するレコードのパラメーターをOmorimapSubで再表示
		//条件にstrShopname == nullを追加し、Omorimapから受け取ったパラメーターと判断
		if(strNumId != null && strShopname == null) {
			//編集するレコード(DTO)のid
			int intNumId = Integer.parseInt(strNumId);
			//編集するレコード(DTO)が格納されているlistDTOのインデックス
			int intListDTOIndex = intNumId - 1;
			DTO editDto = list.get(intListDTOIndex);
			//request.setAttribute("id", editDto.getId()); //idはOmorimapのものを転送するため不要
			request.setAttribute("shopname", editDto.getShopname());
			request.setAttribute("comments", editDto.getComments());
			request.setAttribute("categoryno", String.valueOf(editDto.getCategoryno()));
			request.setAttribute("star", String.valueOf(editDto.getStar()));
			//OmorimapSubに画面遷移
			disp = "/OmorimapSub";
			RequestDispatcher dispatch = request.getRequestDispatcher(disp);
	        dispatch.forward(request, response);

		}
	}

}
