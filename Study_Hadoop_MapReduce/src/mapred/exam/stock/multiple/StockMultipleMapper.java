package mapred.exam.stock.multiple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class StockMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
//  											KEYIN    VALUEIN KEYOUT VALUEOUT
	Text outputKey = new Text();
	
	static final IntWritable outputval = new IntWritable(1);


	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String[] line = value.toString().split(","); 
			if(line!=null & line.length>0) {
				//key => 구분값,년도
				String year = line[2].substring(0,4);
				double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
				if(result>0) {
					outputKey.set("up,"+year);//키설정
					context.write(outputKey, outputval);
				}else if(result<0) {
					outputKey.set("down,"+year);
					context.write(outputKey, outputval);
				}else {
					outputKey.set("equal,"+year);
					context.write(outputKey, outputval);
				}
			}
		}
	}
}



