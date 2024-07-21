package testPractice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringSumTest {
	// Null or empty string
	@Test
	void nullOrEmpty() {
		assertEquals(0, StringSum.stringSum(null));
		assertEquals(0, StringSum.stringSum(""));
	}
	
	@Test
	void simpleTest() {
		// no delimiters
		assertEquals(1, StringSum.stringSum("1"));
		
		// using single default delimiters
		assertEquals(6, StringSum.stringSum("1:2:3"));
		assertEquals(6, StringSum.stringSum("1,2,3"));

		// using single custom delimiters
		assertEquals(3, StringSum.stringSum("//;\n1;2"));
	}
	
	@Test
	void complexTest() {
		// using all the default delimiters
		assertEquals(6, StringSum.stringSum("1:2,3"));
		
		// using all delimiters in addition to a custom
		assertEquals(11, StringSum.stringSum("//;\n1:2;3,5"));
//		assertEquals(11, StringSum.stringSum("//.\n1:2.3,5"));
	}
	
	@Test
	void exceptionTest() {
		// using a negative number  
		RuntimeException e1 = assertThrows(RuntimeException.class, () -> StringSum.stringSum("-1,2"));
		assertEquals("Input number cannot be negative.", e1.getMessage());
		RuntimeException e2 = assertThrows(RuntimeException.class, () -> StringSum.stringSum("//^\n1^-2:2"));
		assertEquals("Input number cannot be negative.", e2.getMessage());
		RuntimeException e3 = assertThrows(RuntimeException.class, () -> StringSum.stringSum("//.\n1.-2,2"));
		assertEquals("Input number cannot be negative.", e3.getMessage());
		
		// using an invalid format
		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class, () -> StringSum.stringSum("1;2"));
		assertEquals("Invalid input format.", e4.getMessage());
		IllegalArgumentException e5 = assertThrows(IllegalArgumentException.class, () -> StringSum.stringSum("1 2,3"));
		assertEquals("Invalid input format.", e5.getMessage());
	}
}
