package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	//reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	Text resultKey = new Text();
	MultipleOutputs<Text, IntWritable> multiOut;
	
	
	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<Text, IntWritable>(context);
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) 
					throws IOException, InterruptedException {
		String[] data = key.toString().split(",");
		resultKey.set(data[1]+data[2]);
		
		
		if(data[0].equals("1980")) {
			int sum = 0;
			for(IntWritable value : values) {
				sum = sum+value.get();
		}
		resultVal.set(sum);// 계산된 결과를 IntWritable변수에 셋팅
		multiOut.write(data[0], resultKey, resultVal);
		
	}else if(data[0].equals("1990")) {
			int sum = 0;
			for(IntWritable value : values) {
			sum = sum+value.get();
	}
		resultVal.set(sum);// 계산된 결과를 IntWritable변수에 셋팅
		multiOut.write("1990", resultKey, resultVal);
		
	}else if(data[0].equals("2000")) {
			int sum = 0;
			for(IntWritable value : values) {
			sum = sum+value.get();
	}
		resultVal.set(sum);// 계산된 결과를 IntWritable변수에 셋팅
		multiOut.write("2000", resultKey, resultVal);
}
}
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut.close();
	}

	
}
