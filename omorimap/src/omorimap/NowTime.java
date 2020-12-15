package omorimap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//現在時刻を取得するクラス
public class NowTime {
	//サーブレット用　現在時刻
	public static String nowTime() {
		Calendar cal1 = Calendar.getInstance();
		Date date1 = cal1.getTime();
		SimpleDateFormat sdformat
		= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String fdate1 = sdformat.format(date1);
		return fdate1;
	}

	//sql用　現在時刻
	public static java.sql.Date nowSqlTime(){
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sDate = new java.sql.Date(utilDate.getTime());

        return sDate;
	}

}
