package mapred.exam.stock.multiple;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

//맵리듀스를 실행하기 위한 클래스
public class StockMultipleDriver extends Configured implements Tool{
	@Override
	public int run(String[] optionlist) throws Exception {
		GenericOptionsParser optionParser = 
				new GenericOptionsParser(getConf(), optionlist);
		
		String[] otherArgs = optionParser.getRemainingArgs();
	
		// 1. 객체생성
		//Configuration conf = new Configuration();
		Job job = new Job(getConf(), "stock_multi");
		
		// 2. job을 처리하기 위해 필요한 클래스를 등록
		job.setMapperClass(StockMultipleMapper.class);
		job.setReducerClass(StockMultipleReducer.class);
		job.setJarByClass(StockMultipleDriver.class);
		
		// 3. hdfs에서의 input/output
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 4. 리듀서결과 정보 설정
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 5. hdfs에 파일로 쓰고 파일을 읽을 수 있도록 path와 함께 설정
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		//MultipleOutputs로 출력될 경로를 Path에 설정
		MultipleOutputs.addNamedOutput(job, "up", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "down", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "equal", TextOutputFormat.class, Text.class, IntWritable.class);
		
		
		// 6. 1번~5번까지 설정한 내용을 기반으로 job이 실행
		job.waitForCompletion(true);
		return 0;
	}
	public static void main(String[] args) throws Exception  {
		ToolRunner.run(new Configuration(), new StockMultipleDriver(), args);
		
	}

	
	
}
