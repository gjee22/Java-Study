package testPractice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSum {

	static int stringSum(String str) {
		if (str == null || str == "") return 0;
		
		Pattern pattern = Pattern.compile("//(.)\n(.*)");
		Matcher matcher = pattern.matcher(str);
		
		StringBuilder delimiters = new StringBuilder();
		delimiters.append("[,");
		delimiters.append(":");
		if (matcher.find()) {
			delimiters.append(matcher.group(1));
			str = matcher.group(2);
			
		}
		delimiters.append("]");
		String[] separated = str.split(delimiters.toString());
		
		int sum = 0;
		for (String s : separated) {
			int cur = 0;
			try {
				cur = Integer.parseInt(s);
			} catch (Exception e) {
				throw new IllegalArgumentException("Invalid input format.");
			}
			
			if (cur < 0) {
				throw new RuntimeException("Input number cannot be negative.");
			} 
			sum += cur;
		}
		
		return sum;
		
	}
}
