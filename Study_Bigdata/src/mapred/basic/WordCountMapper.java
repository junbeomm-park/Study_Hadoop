package mapred.basic;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//                                             KEYIN    VALUEIN KEYOUT VALUEOUT
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	//output데이터의 키와 value를 mapper의 실행결과로 내보낼 수 있도록 정의
	//output데이터의 key를 저장할 변수
	// - 문자열이므로 Text(하둡에서 문자열을 저장할때 사용하는 타입 - String과 동일한 개념) 타입으로 정의
	Text outputKey = new Text();
	//output데이터의 결과는 무조건 1
	static final IntWritable outputval = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//value가 입력데이터의 한 라인 => ex)read a book
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			//output키를 셋팅
			outputKey.set(token);
			//Context객체의 write메소드를 통해서 output으로 내보낼 데이터의 키와 value를 셋팅
			context.write(outputKey, outputval);
		}
	}

}
