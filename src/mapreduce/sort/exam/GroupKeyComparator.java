package mapreduce.sort.exam;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator{
	protected GroupKeyComparator() {
		super(MyKey.class,true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable anotherKey1,
									WritableComparable anotherKey2) {
		MyKey key1 = (MyKey)anotherKey1;
		MyKey key2 = (MyKey)anotherKey2;
		return key1.getProductId().compareTo(key2.getProductId());
	}
	

}
