package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAPITest {
	public static void main(String[] args) {
		//형태소분석으로 처리해야 하나 지금은 패턴으로 제어
		String value ="배송이 너무 느려요 상품가 좋아서 예뻐요 좋아 좋아";
		Pattern p = Pattern.compile("은|는|이|가|요|서");
		Matcher m = p.matcher(value);
		//패턴이 일치하지 않는 문자들만 추출해서 저장하기
		StringBuffer sb = new StringBuffer();
		while(m.find()) {
			String data = m.group();
			System.out.println(data);
			//패턴에 만족하는 문자열을 ""로 치환하고 전체 문자열 StringBuffer에 저장 : \,$는 치환불가능
			m.appendReplacement(sb, "");
		}
		System.out.println(sb);
		//패턴검사를 만족하지 않은 이후 문자열도 추가하기
		m.appendTail(sb);
		
		System.out.println(sb);
		String[] result = sb.toString().split(" ");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
}
