package mapreduce.sort.exam;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import mapreduce.sort.exam.MyKey;
//  하둡을 실행할때 사용자가 입력(쉘프롬포트에서 실행을 위해 사용자가 입력)하는 옵션을 Mapper내부에서
//	사용할 수 있도록 처리
//	=> 옵션이 어떤 값이 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
public class PageViewMapper extends Mapper<LongWritable, Text, MyKey, Text>{
//  											KEYIN    VALUEIN KEYOUT VALUEOUT
	MyKey outputKey = new MyKey();
	Text outputVal = new Text();
	
	static final IntWritable outputval = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, MyKey, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split("\\t");
		//data[2] - productId, data[9] - userID
		outputKey.setProductId(data[2]);
		outputKey.setUserId(data[9]);
		outputVal.set(data[9]);
		context.write(outputKey, outputVal);
		
	}
}




