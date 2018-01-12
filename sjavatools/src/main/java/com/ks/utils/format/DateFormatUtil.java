package com.ks.utils.format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
/**
 * 将特殊的日期格式的值转换整 Date 方便后续使用
 * @author Administrator
 *
 */
public class DateFormatUtil {
	private static final HashMap<String, String> dateRegFormat = new HashMap<String, String>();
	static {
		dateRegFormat.put("^\\d{1,2}[ /-]\\d{1,2}[ /-]\\d{4}[ ]\\d{1,2}[: ]\\d{1,2}[: ]\\d{1,2}[.].*$", "MM-dd-yyyy-HH-mm-ss");
		dateRegFormat.put("^\\d{4}[ /-]\\d{1,2}[ /-]\\d{1,2}[ ]\\d{1,2}[: ]\\d{1,2}[: ]\\d{1,2}[.].*$", "yyyy-MM-dd-HH-mm-ss");
		dateRegFormat.put("^\\d{1,2}[ /-]\\d{1,2}[ /-]\\d{4}[ ]\\d{1,2}[: ]\\d{1,2}[: ]\\d{1,2}$", "MM-dd-yyyy-HH-mm-ss");
		dateRegFormat.put("^\\d{4}[ /-]\\d{1,2}[ /-]\\d{1,2}[ ]\\d{1,2}[: ]\\d{1,2}[: ]\\d{1,2}$", "yyyy-MM-dd-HH-mm-ss");
		dateRegFormat.put("^\\d{1,2}[ /-]\\d{1,2}[ /-]\\d{4}[ ]\\d{1,2}[: ]\\d{1,2}$", "MM-dd-yyyy-HH-mm");
		dateRegFormat.put("^\\d{4}[ /-]\\d{1,2}[ /-]\\d{1,2}[ ]\\d{1,2}[: ]\\d{1,2}$", "yyyy-MM-dd-HH-mm");
		dateRegFormat.put("^\\d{4}[ /-]\\d{1,2}[ /-]\\d{1,2}$", "yyyy-MM-dd");
		dateRegFormat.put("^\\d{1,2}[ /-]\\d{1,2}[ /-]\\d{4}$", "MM-dd-yyyy");
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");
		dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");
		dateRegFormat.put("^\\d{8}$", "yyyyMMdd");
	}

	public static Date FormatDate(String dateStr) {
		SimpleDateFormat sdf;
		
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					sdf = new SimpleDateFormat(dateRegFormat.get(key));
					dateStr = dateStr.replaceAll("\\D+", "-");
					return sdf.parse(dateStr);
				}
			}
		} catch (Exception e) {
			System.err.println("日期格式无效:" + dateStr);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String str = "2017032915";
		Date d = DateFormatUtil.FormatDate(str);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
		
	}
}
