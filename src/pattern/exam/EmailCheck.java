package pattern.exam;


public class EmailCheck {
	public static void main(String[] args) {
		//x{n}=>x를 n번 반복한 문자를 찾는다는 의미로 해석
		String emailReg="^[A-z]+\\.?[A-z0-9]+@[A-z]+(\\.[a-zA-Z]+){1,2}$";//이메일패턴정의
		String[] user = {"heaves@hanmail,net","heaves@hanmail.net",
					"테스트heaves@hanmail.net","sc.com@hanmail.net",
					",heaves@hanmail.net","@hanmail.net"
					,"heaves@hanmail.co.kr"};
		//user에 입력된 email의 형식이 맞는지 true|false로 출력할 수 있도록 작업	
		//String클래스의 matches메소드 활용
		//"heaves@hanmail,net => false
		//"heaves@hanmail.net" => true
		//"테스트heaves@hanmail.net" =>false
		//"sc.com@hanmail.net" => true
		//"heaves@hanmail.net" => flase
		//"@hanmail.net" => false
		//"heaves@hanmail.co.kr" => true
//		System.out.println(user[0].matches("[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[1].matches("[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[2].matches("[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[3].matches("[A-z\\.]+[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[4].matches("[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[5].matches("[A-z0-9]+@[0-9A-z\\.]+"));
//		System.out.println(user[6].matches("[A-z0-9]+@[0-9A-z\\.]+"));
		
		for(int i=0;i<user.length;i++) {
			System.out.println(user[i]+"=====>"+user[i].matches(emailReg));
		}
	}
}
