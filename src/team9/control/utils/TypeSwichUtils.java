package team9.control.utils;

public class TypeSwichUtils {
	public static int stringToInt(String str) {
		char[] c = str.toCharArray();
		int[] i = new int[c.length];
		int count = 0;
		for(int j = 0; j < c.length; j++) {
			i[j] = c[j] - '0';
			count =count * 10 + i[j];
		}
		return count;
	}
}