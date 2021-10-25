package mapreduce.sort.exam;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class MyKeyPartitioner extends Partitioner<MyKey, Text> {
	
	@Override
	public int getPartition(MyKey key, Text val, int numPartitions) {
		int hash = key.getProductId().hashCode();
		return hash%numPartitions;
	}

}
