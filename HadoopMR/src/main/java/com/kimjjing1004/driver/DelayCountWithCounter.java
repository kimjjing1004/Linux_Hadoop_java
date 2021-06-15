package com.kimjjing1004.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.kimjjing1004.mapper.DelayCountMapperWithCounter;
import com.kimjjing1004.reducer.DelayCountReducer;

public class DelayCountWithCounter extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new DelayCountWithCounter(), args);
		System.out.println("MR-Job Result: " + res);
		// TODO Auto-generated method stub

	}
	
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// GenericOptionParser에서 제공하는 파라미터를 제외한 나머지 파라미터 가져오기
		String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: DelayCountWithCounter <in> <out>");
			System.exit(2);
		}
		Job job = Job.getInstance(getConf(), "DelayCount");
		
		
		job.setJarByClass(DelayCountWithCounter.class);
		job.setMapperClass(DelayCountMapperWithCounter.class);
		job.setReducerClass(DelayCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		job.waitForCompletion(true);
		return 0;
	}

}
