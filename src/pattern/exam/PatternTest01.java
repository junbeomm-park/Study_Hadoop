package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest01 {

	public static void main(String[] args) {
		//패턴을 적용할 문자열
		//String str = "java, hi ~~~~~~~ java";
		String str = "$100. .한 $20.0 ^^$";
		//적용할 패턴
		//String patternStr = "java";//  정확하게 일치하는지 확인
		//String patternStr = "^java";//  ^기호 뒤의 문자열로 시작하는지 확인
		//String patternStr = "java$";// $기호 앞의 문자열로 끝나는지 확인
		//String patternStr = "^\\$"; // 패턴에서 사용하는 기호는 일반 문자열로 인식하지 않는다.
		                          // 패턴에서 사용되는 기호를 문자열로 인식시키려면 \\와 함께 사용
		//String patternStr = "\\$$"; // $문자열로 끝나는지 확인
		//String patternStr = "."; // .기호는 글자 한자리를 의미
		                         // group메소드 때문에 한 글자씩 잘라서 리턴됨
		//String patternStr = "...."; // 4자리 글자를 의미
		//String patternStr = "\\.";
		String patternStr = "\\..\\."; // .과 .사이의 한 글자를 찾기
		equalsPattern(str, patternStr);
	}
	//패턴을 적용해서 처리할 메소드
	public static void equalsPattern(String str, String patternSrt) {
		// 1. 패턴을 인식
		Pattern pattern = Pattern.compile(patternSrt, Pattern.CASE_INSENSITIVE);
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
