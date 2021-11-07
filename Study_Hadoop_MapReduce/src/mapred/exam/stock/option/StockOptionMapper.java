package mapred.exam.stock.option;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//                                             KEYIN    VALUEIN KEYOUT VALUEOUT
public class StockOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	
	static final IntWritable outputval = new IntWritable(1);
	String jobType;
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		jobType = context.getConfiguration().get("jobType");
	}
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		
		if(key.get()>0) { //0번 라인 제외
			String[] line = value.toString().split(","); //한 라인을 ,를 기준으로 단어 분리
			if(line!=null & line.length>0) {//분리된 결과가 null이 아니거나 결과가 있을때만 작업
				if(jobType.equals("high")) {
					
					outputKey.set(line[2].substring(0, 4));
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result>0) {
						context.write(outputKey, outputval);
					}
					
				}else if(jobType.equals("row")) {
					outputKey.set(line[2].substring(0, 4));
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result<0) {
						context.write(outputKey, outputval);
					}
				
				}else if(jobType.equals("equal")) {
					outputKey.set(line[2].substring(0, 4));
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result==0) {
						context.write(outputKey, outputval);
						
					}
				
				}
			}
		}
	}
}



