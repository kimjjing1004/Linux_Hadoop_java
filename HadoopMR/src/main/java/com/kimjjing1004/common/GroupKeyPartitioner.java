package com.kimjjing1004.common;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupKeyPartitioner extends Partitioner<DateKey, IntWritable> {

	@Override
	public int getPartition(DateKey key, IntWritable value, int numPartitions) {
		// TODO Auto-generated method stub
		int hash = key.getYear().hashCode();
		int partition = hash % numPartitions;
		return partition;
	}
	
	

}
