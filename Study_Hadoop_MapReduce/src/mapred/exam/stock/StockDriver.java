package mapred.exam.stock;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//맵리듀스를 실행하기 위한 클래스
public class StockDriver {
	public static void main(String[] args) 
			throws IOException, 
				   ClassNotFoundException, 
			       InterruptedException {
		// 1. job생성
		Configuration conf = new Configuration();
		Job job = new Job(conf, "stock");
		
		// 2. job을 처리하기 위해 필요한 클래스를 등록
		job.setMapperClass(StockMapper.class);
		job.setReducerClass(StockReducer.class);
		job.setJarByClass(StockDriver.class);
		
		// 3. hdfs에서의 input/output
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 4. 리듀서결과 정보 설정
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 5. hdfs에 파일로 쓰고 파일을 읽을 수 있도록 path와 함께 설정
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 6. 1번~5번까지 설정한 내용을 기반으로 job이 실행
		job.waitForCompletion(true);
		
	}
	
}
