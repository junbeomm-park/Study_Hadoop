package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest05 {

	public static void main(String[] args) {
		//패턴을 적용할 문자열
		//String str = "java programmIngm";
		String str = "asdfasdf2323 a1 b1 d525 d5s s5d 5dd5";
		//적용할 패턴
		
		String patternStr = "[a-z][0-9]"; 
		equalsPattern(str, patternStr);
	}
	//패턴을 적용해서 처리할 메소드
	public static void equalsPattern(String str, String patternStr) {
		// 1. 패턴을 인식
		//Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		Pattern pattern = Pattern.compile(patternStr);
		// 2. 패턴을 적용하여 문자열을 관리하는 클래스
		Matcher matcher = pattern.matcher(str); //패턴을 적용할 문자열
		
		// 3. Matcher의 메소드를 적용해서 패턴이 일치하는 문자열을 추출할 수 있다.
//		System.out.println("matcher.find()=>"+matcher.find());
//		System.out.println("matcher.start()=>"+matcher.start());
//		System.out.println("matcher.end()=>"+matcher.end());
//		System.out.println("matcher.group()=>"+matcher.group());
//		System.out.println("===============================================");
		while(matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.start()+":"+(matcher.end()-1));
		}
	}
}
