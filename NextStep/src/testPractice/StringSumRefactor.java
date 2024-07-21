package testPractice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSumRefactor {

	/**
	 * 입력된 문자열을 구분자로 분리한 후 각 숫자의 합을 구한다.
	 * 기본 구분자는 ','와 ':'이다.
	 * 그 외의 커스텀 구분자는 문자열 앞에 //[구분자]\n를 삽입하여 설정한다.
	 * @param str 문자열
	 * @return 문자열의 합
	 */
	static int stringSum(String str) {
		if (str == null || str == "") return 0;
		
		String delimiters = findDelimitersString(str);
		String[] separated = str.split(delimiters);
		
		int sum = 0;
		for (String s : separated) {
			sum += getNumber(s); 
		}
		
		return sum;
		
	}

	/**
	 * 문자를 숫자로 변환한다.
	 * 변환이 불가하면 IllegalArgumentException이 반환된다.
	 * 숫자가 음수이면 RuntimeException이 반환된다.
	 * @param s 문자로 된 숫자
	 * @return 숫자로 변환된 s
	 */
	private static int getNumber(String s) throws IllegalArgumentException, RuntimeException {
		int cur = 0;
		try {
			cur = Integer.parseInt(s);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid input format.");
		}
		
		if (cur < 0) {
			throw new RuntimeException("Input number cannot be negative.");
		}
		return cur;
	}

	/**
	 * 구분자를 지정한다.
	 * @param str 구분자를 찾을 문자열
	 * @return 커스텀 구분자가 있을 시엔 커스텀 구분자가 포함된 졍규 표현식을 반환한다. 커스텀 구분자가 없으면 기본 구분자인 ','랑 ':'를 포함한 regex를 반환한다.
	 */
	private static String findDelimitersString(String str) {
		Pattern pattern = Pattern.compile("//(.)\n(.*)");
		Matcher matcher = pattern.matcher(str);
		
		StringBuilder delimiters = new StringBuilder();
		delimiters.append("[,:");
		if (matcher.find()) {
			delimiters.append(matcher.group(1));
			str = matcher.group(2);
		}
		delimiters.append("]");
		
		return delimiters.toString();
	}
}
