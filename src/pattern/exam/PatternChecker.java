package pattern.exam;

import java.util.regex.Pattern;
 
/** 문자열의 형식을 검사하는 기능을 갖는 클래스 */
public class PatternChecker {
     
    /** 숫자 모양에 대한 형식 검사 */
    public static boolean isNum(String str) {
    	String num="[0-9]+";
		return Pattern.matches(num, str);
   
    }
     
   //** 영문으로만 구성되었는지에 대한 형식 검사 *//*
    public static boolean isEng(String str) {
    	String eng="[A-z| ]+";
		return Pattern.matches(eng, str);

    }
   //** 한글로만 구성되었는지에 대한 형식 검사 *//*
    public static boolean isKor(String str) {
    	String kor="[가-힣| |ㄱ-ㅎ]+";
		return Pattern.matches(kor, str);
    }
     
    //** 영문과 숫자로만 구성되었는지에 대한 형식 검사 *//*
    public static boolean isEngNum(String str) {
    	String engnum="[A-z0-9 ]+";
		return Pattern.matches(engnum, str);
    }
     
    //** 한글과 숫자로만 구성되었는지에 대한 형식 검사 *//*
    public static boolean isKorNum(String str) {
    	String kornum="[ㄱ-ㅎ가-힣0-9 ]+";
		return Pattern.matches(kornum, str);
    }
     
   //** 이메일 형식인지에 대한 검사 --> "아이디@도메인"의 형식을 충족해야 한다. *//*
    public static boolean isEmail(String str) {
    	String email="[A-z0-9]+@[0-9A-z\\.]+";
		return Pattern.matches(email, str);
    }
 


   


    public static void main(String[] args){
    	System.out.println("***********isNum테스트*************");
    	System.out.println(isNum("2345"));//true
    	System.out.println(isNum("2345한글"));//false
    	System.out.println(isNum("2345****"));//false
    	System.out.println(isEng("aasdfgasdf"));//true
    	System.out.println(isEng("aasdfgas df"));//true
    	System.out.println(isEng("aasdfgas**df"));//false
    	System.out.println(isEng("aasdfgas1221df"));//false
    	System.out.println(isEng("aasdfgas한글df"));//false
    	System.out.println("***********isKor테스트*************");
    	System.out.println(isKor("aasdfgas한글df"));//false
    	System.out.println(isKor(" 한글"));//true
    	System.out.println(isKor("한글 한긆 ㄴㅇㄻㅁㄹ"));//true
    	System.out.println(isKor("123한글"));//false
    	
    	System.out.println("***********isEngNum테스트*************");
    	System.out.println(isEngNum("aasdfgas한글df"));//false
    	System.out.println(isEngNum(" 한글"));//false
    	System.out.println(isEngNum("   111   asdfa"));//true
    	System.out.println(isEngNum("dfgad998"));//true
    	System.out.println("***********isKorNum테스트*************");
    	System.out.println(isKorNum("aasdfgas한글df"));//false
    	System.out.println(isKorNum(" 한글"));//false
    	System.out.println(isKorNum("   111   asdfa"));//true
    	System.out.println(isKorNum("한글998"));//true

    	System.out.println("***********isEmail테스트*************");
    	System.out.println(isEmail("heaves@hanmail.net")); //true
    	System.out.println(isEmail("heaveshanmail.net")); //false
    	
    	
    
    	
    }
}
