package mapreduce.air.sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//  하둡을 실행할때 사용자가 입력(쉘프롬포트에서 실행을 위해 사용자가 입력)하는 옵션을 Mapper내부에서
//	사용할 수 있도록 처리
//	=> 옵션이 어떤 값이 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
public class AirSortMapper extends Mapper<LongWritable, Text, CustomKey, IntWritable>{
//  											KEYIN    VALUEIN KEYOUT VALUEOUT
	CustomKey outputKey = new CustomKey();
	
	static final IntWritable outputval = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String NA = "NA";
			String[] line = value.toString().split(","); 
			if(line!=null & line.length>0) {
				
					//출발지연
					if(!line[15].equals(NA) && Integer.parseInt(line[15])>0) {
						outputKey.setYear(line[0]);
						outputKey.setMonth(Integer.parseInt(line[1]));
						context.write(outputKey, outputval);
					}
				
			}
		}
	}
}




