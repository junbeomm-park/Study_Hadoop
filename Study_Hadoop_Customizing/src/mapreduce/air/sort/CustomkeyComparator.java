package mapreduce.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

//그룹키비교기를 이용해서 분류된 데이터들이 내부에서 비교될 세부 조건을 정의하는 키
public class CustomkeyComparator extends WritableComparator {
	protected CustomkeyComparator() {
		super(CustomKey.class,true);
	}
	//WritableComparable의 타입이 정확하지 않기 때문에 warning이 발생
	//타입에 대한 부분을 무시하고 체크하지 않고 처리하겠다는 의미
	@SuppressWarnings("rawtypes")
	@Override
	public int compare( WritableComparable obj1, WritableComparable obj2) {
		CustomKey key1 = (CustomKey) obj1;
		CustomKey key2 = (CustomKey) obj2;
		
		return key1.compareTo(key2);
	}

}
