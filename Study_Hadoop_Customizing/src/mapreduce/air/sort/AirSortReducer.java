package mapreduce.air.sort;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirSortReducer extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable>{
	//reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	CustomKey resultKey = new CustomKey();
	Text appenddata = new Text();//로그파일로 출력할 값을 저장할 변수
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context) 
					throws IOException, InterruptedException {
		int sum = 0;
		Integer beforeMonth = key.getMonth();
		int count = 0;
		//appenddata.set("reduce호출");
		//한 개씩 비교하면서 month가 같으면 집계를 하고 month가 달라지면 기존의 집계한 내용을 내보내기하고
		//다시 초기화
		for(IntWritable value : values) {
			if(count<=10) {//log파일에 출력해서 보기위한 목적으로 만든 코드이므로 리듀서에 영향을 주지 않는다.
				System.out.println("reduce=>"+key);
				count++;
			}
			if(beforeMonth!=key.getMonth()) {//최초 month값과 month값이 달라지는 시점
				//결과를 내보내기
				resultKey.setYear(key.getYear());
				resultKey.setMonth(beforeMonth);
				resultVal.set(sum);
				context.write(resultKey, resultVal);
				sum=0;//다시 집계해야 하므로 초기화
				
			}
			sum = sum+value.get();
			beforeMonth = key.getMonth();
		}//end for
		//마지막월인 경우 내보내기 코드를 직접 넣기
		if(key.getMonth()==beforeMonth) {
			resultKey.setYear(key.getYear());
			resultKey.setMonth(beforeMonth);
			resultVal.set(sum);
			context.write(resultKey, resultVal);
		}
	}

}
