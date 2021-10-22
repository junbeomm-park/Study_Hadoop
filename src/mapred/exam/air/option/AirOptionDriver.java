package mapred.exam.air.option;

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
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

//맵리듀스를 실행하기 위한 클래스
public class AirOptionDriver extends Configured implements Tool{
	@Override
	public int run(String[] optionlist) throws Exception {
		//run메소드는 사용자가 입력한 모든 옵션에 대한 정보를 String[]로 전달받는다.
		//-D와 함께 사용자가 입력한 옵션과 기존에 사용 되던 명령행매개변수를 분리해서 관리해야 한다.
		//이를 담당하는 객체가 GenericOptionParser
		//GenericOptionParser에 의해 사용자가 입력한 모든 옵션이 관리된다.
		//-D와 입력한 옵션 정보를 GenericOptionParser가 관리 하고 나머지 옵션(공통옵션)도 따로 관리한다.
		//getConf() 메소드를 통해서 받은 환경설정정보에 -D와 함께 입력한 사용자 정의 옵션을 등록
		//=> 이렇게 하면 Mapper의 setup메소드에서 환경설정정보에서 사용자가 입력한 옵션을 꺼낼 수 있다.
		GenericOptionsParser optionParser = 
				new GenericOptionsParser(getConf(), optionlist);
		
		// 나머지 일반옵션(공통옵션)도 관리 - input/output경로가 설정되므로 반드시 필요
		String[] otherArgs = optionParser.getRemainingArgs();
	
		// 1. 객체생성
		//Configuration conf = new Configuration();
		Job job = new Job(getConf(), "airoption");
		
		// 2. job을 처리하기 위해 필요한 클래스를 등록
		job.setMapperClass(AirOptionMapper.class);
		job.setReducerClass(AirOptionReducer.class);
		job.setJarByClass(AirOptionDriver.class);
		
		// 3. hdfs에서의 input/output
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// 4. 리듀서결과 정보 설정
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 5. hdfs에 파일로 쓰고 파일을 읽을 수 있도록 path와 함께 설정
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		// 6. 1번~5번까지 설정한 내용을 기반으로 job이 실행
		job.waitForCompletion(true);
		return 0;
	}
	public static void main(String[] args) throws Exception  {
		ToolRunner.run(new Configuration(), new AirOptionDriver(), args);
		
	}

	
	
}
