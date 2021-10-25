package mapreduce.air.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirSortPatitioner extends Partitioner<CustomKey, IntWritable>{
	//getPartition메소드는 어떤 리듀서로 보낼지 정의하는 메소드
	//세 번째 매개변수로 전달되는 numPartitions는 리듀스태스크의 갯수
	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartitions) {

		return key.getYear().hashCode() % numPartitions;
	}

}
