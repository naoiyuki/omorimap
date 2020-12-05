package omorimap;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntryServlet
 */
@WebServlet("/EntryServlet")
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

		//OmorimapSubから受け取ったデータをDTOに登録
		DTO objDto = new DTO();

		//requestにてshopnameを取得
		String shopname = request.getParameter("shopname");
		//requestにてcommentsを取得
		String comments = request.getParameter("comments");
		//現在日時を取得
		Date dt = NowTime.nowSqlTime();
        //requestにてホストのIPアドレスを取得
		String ip = GetIp.getClientIp(request);

		//objDto.setNo(Integer.parseInt(request.getParameter("no")));
		objDto.setShopname(shopname);
		objDto.setComments(comments);
		objDto.setDt(dt);
		objDto.setIp(ip);

		//上記の値をDBに更新
		ConDb.UpdateDb(objDto.getShopname(),objDto.getComments(),objDto.getDt(),objDto.getIp());
//		ConDb.UpdateDb(shopname,comments,dt,ip);

		//Omorimapに画面遷移
		String disp = "/omorimap/Omorimap";
		//RequestDispatcher dispatch = request.getRequestDispatcher(disp);
		response.sendRedirect(disp);

	}

}
