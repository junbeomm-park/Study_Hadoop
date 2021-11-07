package mapreduce.sort.exam;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyKeyComparator extends WritableComparator{
	protected MyKeyComparator() {
		super(MyKey.class,true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable anotherKey1, WritableComparable anotherKey2) {
		MyKey key1 = (MyKey)anotherKey1;
		MyKey key2 = (MyKey)anotherKey2;
		return key1.compareTo(key2);
	}
	
}
