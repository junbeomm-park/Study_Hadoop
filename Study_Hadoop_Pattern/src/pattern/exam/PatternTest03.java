package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest03 {

	public static void main(String[] args) {
		//패턴을 적용할 문자열
		//String str = "java programmIngm";
		String str = "ja1111aCva--@@-@@@@- 한글 -@@@@- progra556678mmFiJng-@-vma";
		//적용할 패턴
		//String patternStr = ".*"; // *앞의 패턴문자가 0이거나 1이거나 여러 개 있거나
		//String patternStr = "-@+-"; // +기호 앞의 패턴문자가 1이거나 여러 개 있거나
		//String patternStr = "-@?-"; // ?기호 앞의 패턴문자가 없거나 1이거나
		//String patternStr = "[^ ]"; // 공백이 아닌 문자
		//String patternStr = ".{5}"; // .{n} 기호는 .은 임의의 한 문자를 의미 n은 글자수를 의미
		//String patternStr = "[amv]{1,3}"; // XXXX{1,3} - 패턴문자의 반복횟수
		                                  // a,m,v가 1회,2회,3회인 문자 1이상 3이하의 자릿수
									      // a,m,v,aa,am,av,mv,aaa,aam,aav........
		//String patternStr = "[a-z]{3,}"; // 영문자 소문자가 3글자 이상인 글자 추출
		                                   // {3, } => 3이상
		//String patternStr = "\\W"; // 대문자,소문자, 숫자 뺀 모든 문자
		//String patternStr = "\\w"; // 대문자,소문자, 숫자 
		//String patternStr = "\\D"; // 숫자를 뺀 모든 문자
		String patternStr = "\\d"; // 숫자만
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
