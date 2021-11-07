package mapred.exam.air;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//                                             KEYIN    VALUEIN KEYOUT VALUEOUT
public class AirMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	
	static final IntWritable outputval = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String NA = "NA";
			String[] line = value.toString().split(","); 
			if(line!=null & line.length>0) {
				if(!line[15].equals(NA) && Integer.parseInt(line[15])>0) {
					outputKey.set(line[1]+"월");
					context.write(outputKey, outputval);
				}
			}
		}
	}
}




