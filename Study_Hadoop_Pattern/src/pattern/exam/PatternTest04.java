package pattern.exam;

import java.util.regex.Pattern;

public class PatternTest04 {
	public static void main(String[] args) {
		System.out.println(Pattern.matches("[0-9]+", "12345java")); //false
		System.out.println(Pattern.matches("[0-9]+", "java")); //false
		System.out.println(Pattern.matches("[0-9]+", "12345")); //true
		System.out.println(Pattern.matches("[0-9]+", "1")); //true
		System.out.println(Pattern.matches("[0-9]+", " ")); //false
		System.out.println("===========================================");
		System.out.println(Pattern.matches("[0-9]?", "12345java")); //false
		System.out.println(Pattern.matches("[0-9]?", "java")); //false
		System.out.println(Pattern.matches("[0-9]?", "12345")); //false
		System.out.println(Pattern.matches("[0-9]?", "1")); //true
		System.out.println(Pattern.matches("[0-9]?", "")); //true
		
		//String클래스의 matches메소드이용
		String str = "12345java"; //패턴을 검사할 문자열
		System.out.println(str.matches("[0-9]?")); //false
	}

}
