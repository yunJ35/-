package team9.control.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QueryUtil {
	/**
	 * 	用户学籍信息查询
	 * @return map 学籍信息
	 */
	public static Map<String, Object> rollInfoQuery(String userName) {
		List<Map<String, Object>> list = DButils.studentMapListHandler();
		Iterator<Map<String, Object>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, Object> map;
			map = it.next();
			if (map.get("id").equals(userName)) {
				return map;
			}
		}
		return null;
	}
}