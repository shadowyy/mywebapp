package spark;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

/**
 * @author yuyue
 * @version 2017-10-27 0027 10:53
 */
public class HelloSpark {

    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("HelloSpark");

        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("---")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<Integer> distData = jsc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
        distData.reduce((a, b) -> a + b);

        JavaRDD<String> distFile = jsc.textFile("c:/documents/1.txt");
        System.out.println("distFileXXXXXXXXXXXXX"+distFile.map(s->s+"xx").first());








    }
}
