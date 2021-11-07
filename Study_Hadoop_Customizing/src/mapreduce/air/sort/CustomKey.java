package mapreduce.air.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomKey implements WritableComparable<CustomKey>{
	private String year;
	private Integer month;
	
	public CustomKey() {
		
	}
	
	public CustomKey(String year, Integer month) {
		super();
		this.year = year;
		this.month = month;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	//네트워크로 전송되는 복합키클래스(사용자정의키를 정의한 클래스(객체))를 역직렬화할때 내부에서 호출되는 메소드
	@Override
	public void readFields(DataInput in) throws IOException {
		year = WritableUtils.readString(in);
		month = in.readInt();
	}
	
	@Override
	public String toString() {
		return (new StringBuffer()).append(year).append(",").append(month).toString();
	}

	//네트워크로 복합키를 전송하기 위해 직렬화할때 호출되는 메소드
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, year);
		out.writeInt(month);
	}
	//사용자가 만들어 놓은 키를 기준으로 정렬하기 위해서 값들을 비교하도록 처리하는 메소드
	//year로 비교하고 year가 같으면 month로 비교
	@Override
	public int compareTo(CustomKey obj) {
		int result = year.compareTo(obj.year);
		if(result==0) { //year와 대상 year가 같다.
			result = month.compareTo(obj.month);
		}
		
		return result;
	}

}
