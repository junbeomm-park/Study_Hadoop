package mapred.exam.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//                                             KEYIN    VALUEIN KEYOUT VALUEOUT
public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	
	static final IntWritable outputval = new IntWritable(1);
	//입력데이터로 line하나가 전달되고 line하나를 어떻게 처리할 것인지 명시
	//하둡프레임워크 내부에서 전체적인 상황을 판단하고 스케쥴링되어 자동으로 반복실행
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String[] line = value.toString().split(","); //한 라인을 ,를 기준으로 단어 분리
			if(line!=null & line.length>0) {//분리된 결과가 null이 아니거나 결과가 있을때만 작업
				//년도별 상승마감
				//년데이터로 키값을 셋팅 - 날짜데이터에서 4자리만 추출해서 키로 셋팅
				outputKey.set(line[2].substring(0, 4));
				//상승마감을 추출하기 - 종가에서 시가를 뺀 결과가 양수
				//split된 결과가 String배열이므로 각 단어는 String타입인 상태
				//숫자로 변환해야 계산할 수 있다
				//소숫점이 있으므로 Double클래스의 parseDouble을 이용해서 더블형의 숫자로 변환 
				
				double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
				
				System.out.println(line[2].substring(0, 4)+",,,,,,"+result);
				if(result>0) { // 상승마감인 경우만
					//리듀서로 전달하기
					context.write(outputKey, outputval);
				}
			}
		}
	}
}


