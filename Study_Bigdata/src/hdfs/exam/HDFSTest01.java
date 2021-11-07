package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
 * 		hadoop hdfs를 api로 제어
 * 		- hadoop hdfs에 api를 이용해서 파일을 생성
 * 		- 사용자가 원하는 경로에 파일을 생성
 */
public class HDFSTest01 {
	public static void main(String[] args) {
		//	1. HDFS를 제어하기 위해서 설정 파일을 읽어서 사용해야 한다.
		//	   hadoop설치 폴더의 설정 파일을 접근하기 위해서 제공되는 클래스
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		//	4. HDFS에 저장하는 기능을 처리하는 output스트림객체를 정의
		FSDataOutputStream hdfsout = null;
		try {
            //	2. HDFS를 접근하기 위해서 HDFS를 정의한 객체를 생성
			hdfs = FileSystem.get(conf);
			//	3. HDFS의 경로를 표현할 수 있는 객체
			//	=> HDFS에 저장할(출력할) 파일의 경로를 명령행매개변수로 받아서 적용하겠다는 의미
			Path path = new Path(args[0]);
			//	5. HDFS에 파일을 저장하기 위한 출력스트림을 생성
			hdfsout = hdfs.create(path);
			//	6. 데이터를 출력해서 HDFS에 파일 저장하기
			hdfsout.writeUTF(args[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
