package com.algz.platform.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ADateUtils {

	/**
	 * 获得当前日期。（按照格式）
	 * @param dataFormate 值为null或""，则默认为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateTime(String dataFormate) {
		Date date = new Date( );
		if(dataFormate==null||dataFormate.equals("")) {
			dataFormate="yyyy-MM-dd HH:mm:ss";
		}
	    SimpleDateFormat sdf = new SimpleDateFormat (dataFormate);
	    return sdf.format(date);
	}
	
	/**
	 * 获得当前日期。(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getCurrentDateTime() {
	    return getCurrentDateTime(null);
	}
}
