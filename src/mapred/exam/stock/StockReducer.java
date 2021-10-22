package mapred.exam.stock;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StockReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	//reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) 
					throws IOException, InterruptedException {
		int sum = 0;
		//reduce메소드에 전달될 입력데이터의 value를 꺼내서 모두 더하기
		for(IntWritable value : values) {
			sum = sum+value.get();
		}
		resultVal.set(sum);// 계산된 결과를 IntWritable변수에 셋팅
		context.write(key, resultVal);
		
	}

}
