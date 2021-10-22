package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
 *  	hadoop의 hdfs를 api로 제어
 *  	- hdfs에 저장된 파일을 읽어서 콘솔에 출력하기
 */
public class HDFSTest02 {
	public static void main(String[] args) {
		// 1. 설정파일을 객체로 정의
		Configuration conf = new Configuration();
		// 2. HDFS에 접근하기 위해 제공되는 객체를 생성
		FileSystem hdfs = null;
		// 3. HDFS로 부터 input하기 위해 스트림객체 생성
		FSDataInputStream hdfsIn = null;
		try {
			hdfs = FileSystem.get(conf);
			Path inPath = new Path(args[0]);
			hdfsIn = hdfs.open(inPath);
			// 4. 입력스트림을 통해서 데이터를 읽는다.
			//String data = hdfsIn.readUTF(); // 입력스트림에서 문자열을 읽어서 리턴
			int data = hdfsIn.read();
			System.out.println("hdfs에서 읽은 데이터 : "+(char)data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
