package omorimap;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	//接続用の情報をフィールドに定数として定義
		private static final String RDB_DRIVE="com.mysql.jdbc.Driver";
		private static final String URL="jdbc:mysql://localhost/mysql?serverTimezone=JST";
		private static final String USER="root";
		private static final String PASSWD="naoi";


		//DBに接続するためのメソッド
		public static Connection createConnection(){
			Connection conn = null;

		  //DB接続開始
		    try {
		    	//ドライバクラスをロード
		    	Class.forName(RDB_DRIVE).newInstance();
		    } catch (Exception e) {
	        }
		    try {
		    	//DBへのアクセス
		    	conn = DriverManager.getConnection(URL, USER, PASSWD);
		    } catch(Exception ex) {
	            ex.printStackTrace();
	        }
		    return conn;
		}

		//DBの接続を閉じるためのメソッド
		public static void disConnection(Connection conn) {
			try{
	            conn.close();
	            } catch (Exception ex){
	            ex.printStackTrace();
	        }
		}

		// DBのテーブルに登録された全てのデータをArrayList<DTO>型オブジェクトへ格納し、戻り値として返す
		public static ArrayList<DTO> selectAllDb(){
			// 変数宣言
	        Connection conn = null;  // DBコネクション
	        Statement stmt = null;   // SQLステートメント

	        // 配列宣言
	        ArrayList<DTO> list = new ArrayList<DTO>();

	        // SQL文作成
	        String sql = "SELECT * FROM list";

			try {
				conn= createConnection();
				stmt = conn.createStatement();

				// SQL文発行
		    	ResultSet rs = stmt.executeQuery(sql);

		    	// 検索結果をArrayListに格納
		    	while(rs.next()) {
		    		DTO objDto = new DTO();
		    		objDto.setNo(rs.getInt("no"));
		    		objDto.setShopname(rs.getString("shopname"));
		    		objDto.setComments(rs.getString("comments"));
		    		objDto.setDt(rs.getDate("dt"));
		    		objDto.setIp(rs.getString("ip"));
		    		list.add(objDto);
		    	}

		    	//接続などを閉じる
		    	rs.close();
		    	stmt.close();
		    	disConnection(createConnection());


			}catch(SQLException e){
	            System.out.println("Errorが発生しました！\n"+e);
	        }finally{
	            // リソースの開放
	            if(stmt != null){
	                try{stmt.close();}catch(SQLException ignore){}
	            }
	            if(conn != null){
	                try{conn.close();}catch(SQLException ignore){}
	            }
	        }

			return list;
		}

		//カラムをDBに追加
		public static void updateDb(String shopname,String comments,Date dt,String ip){
			// 変数宣言
	        Connection conn = null;  // DBコネクション
	        PreparedStatement pstmt = null;   // SQLステートメント

	        // SQL文作成
	        String sql = "INSERT INTO list (shopname,comments,dt,ip) VALUES (?,?,?,?)";

			try {
				conn= createConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1,shopname);
				pstmt.setString(2,comments);
				pstmt.setDate(3,dt);
				pstmt.setString(4,ip);

				//SQLをDBへ発行
				pstmt.executeUpdate();

				//接続などを閉じる
		    	pstmt.close();
		    	disConnection(createConnection());


			}catch(SQLException e){
	            System.out.println("Errorが発生しました！\n"+e);
	        }finally{
	            // リソースの開放
	            if(pstmt != null){
	                try{pstmt.close();}catch(SQLException ignore){}
	            }
	            if(conn != null){
	                try{conn.close();}catch(SQLException ignore){}
	            }
	        }
		}

		//カラムをDBから削除
		public static void deleteDb(int no) {
			// 変数宣言
	        Connection conn = null;  // DBコネクション
	        PreparedStatement pstmt = null;   // SQLステートメント

	        // SQL文作成
	        String sql = "DELETE FROM list WHERE no = \"" + no + "\"";

	        try {
	        	conn= createConnection();
				pstmt = conn.prepareStatement(sql);

				//SQLをDBへ発行
				pstmt.executeUpdate();

				//接続などを閉じる
		    	pstmt.close();
		    	disConnection(createConnection());

	        }catch(SQLException e){
	            System.out.println("Errorが発生しました！\n"+e);
	        }finally{
	            // リソースの開放
	            if(pstmt != null){
	                try{pstmt.close();}catch(SQLException ignore){}
	            }
	            if(conn != null){
	                try{conn.close();}catch(SQLException ignore){}
	            }
	        }
		}


}
