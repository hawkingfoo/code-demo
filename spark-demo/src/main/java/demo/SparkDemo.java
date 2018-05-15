package demo;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.File;
import java.util.Arrays;

public class SparkDemo {
    private static JavaSparkContext context;

    public static void init(String appName, String master) {
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        context = new JavaSparkContext(conf);
    }

    public static void wordCount(String srcPath, String dstPath) {
        FileUtils.deleteQuietly(new File(dstPath));

        JavaRDD<String> file = context.textFile(srcPath);
        JavaRDD<String> words = file.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });

        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer x, Integer y) throws Exception {
                return x + y;
            }
        });

        counts.saveAsTextFile(dstPath);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("error");
            System.exit(0);
        }
        SparkDemo.init("demo", "local");
        SparkDemo.wordCount(args[0], args[1]);
    }
}
