package mapreduce.sort.exam;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import mapreduce.sort.exam.MyKey;

public class PageViewReducer extends Reducer<MyKey, Text, Text, Text>{
	//reduce작업의 결과를 저장할 변수
	Text resultVal = new Text();
	Text resultKey = new Text();//로그파일로 출력할 값을 저장할 변수
	@Override
	protected void reduce(MyKey key, Iterable<Text> values,
			Reducer<MyKey, Text, Text, Text>.Context context) 
					throws IOException, InterruptedException {
		int sum = 0;
		int user_count = 0;
		String beforeUser="";
		for(Text value : values) {
			String currentUser = value.toString();
			if(!beforeUser.equals(currentUser)) {
				user_count++;//사용자가 다른 경우 증가
			}
			sum++;//하나의 상품에 접속한 모든 횟수
			beforeUser = currentUser;
				
			
		}
		//상품코드가 바뀔때마다 출력
		resultKey.set(key.getProductId());
		StringBuffer data = new StringBuffer();
		data.append(user_count).append("\t").append(sum);
		resultVal.set(data.toString());
		
		context.write(resultKey, resultVal);
	}

}
