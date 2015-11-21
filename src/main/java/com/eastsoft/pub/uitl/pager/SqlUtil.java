package com.eastsoft.pub.uitl.pager;

import org.apache.commons.lang.StringUtils;

/**
* @ClassName: SqlUtil
* @Description: 工具类-原生sql分页辅助类
* @author zjs
* @date Dec 24, 2013 3:01:27 PM
*
*/
public class SqlUtil {

	public static String CS_SQL_COUNT = "select count(*) ";

	//	public static String CS_SQL_LIMIT = " offset :aoffset limit :alimit";

	public static String getSqlCount(String sql) {
		if (StringUtils.isEmpty(sql)) {
			return null;
		}
		int start = StringUtils.indexOfIgnoreCase(sql, "from");
		if (start < 0) {
			return null;
		}
		int end = StringUtils.lastIndexOfIgnoreCase(sql, "order by");
		end = end < 0 ? sql.length() - 1 : end;
		return CS_SQL_COUNT + sql.substring(start, end);
	}

}
