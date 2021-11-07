package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest02 {

	public static void main(String[] args) {
		//패턴을 적용할 문자열
		//String str = "java programming";
		String str = "$ja1111aCva--@@-@@@@- 한글 -@@@@- progra556678mmFiJng";
		//적용할 패턴
		// String patternStr = "a|m|g"; // |기호는 or을 의미
		                             // => a이거나 m이거나 g인 문자 확인
		//String patternStr = "[amg]"; // 위와 동일
		//String patternStr = "[amg][ma]"; //  두 글자에 대해서 2번의 조건을 적용해서 확인
										  // => 첫 글자가 a,m,g이거나 두 번째 글자가 m,a인 문자
		//String patternStr = "[c-j]"; // c-j사이에 해당하는 문자(소문자) 확인
		                             // c,d,e,f,g,h,i,j
		//String patternStr = "[C-J]"; //대문자
		//String patternStr = "[C-Jc-j]";// 대문자 C에서 J, 소문자 c에서 j까지의 문자확인
		//String patternStr = "[4-8]"; // 숫자 4~8사이
		//String patternStr = "[^4-8]"; // ^가 []안에서 사용되면 부정의 의미
									  // => 숫자 4,5,6,7,8이 아닌 모든 문자
		//9. c~j사이의 영문자가 아닌것
		//String patternStr = "[^c-j]";
		//10. 영문자와 숫자만 추출
		//String patternStr = "[a-zA-Z1-9]";
		//11. 영문자와 숫자를 뺀 나머지 문자만 추출
		String patternStr = "[^a-zA-Z1-9]";
		//12. 한글만 추출 - 상상
		//String patternStr = "[가-핳]";
		
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
