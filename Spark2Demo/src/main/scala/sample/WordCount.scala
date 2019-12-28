package sample
import org.apache.spark.sql.{Row, SaveMode, SparkSession}
object WordCount {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .enableHiveSupport()
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    val textFile = spark.sparkContext.textFile("C:\\data\\data-master\\retail_db\\order_items")
    val counts = textFile.flatMap(line => line.split(",")).map(word => (word, 1)).reduceByKey(_ + _)
    counts.saveAsTextFile("C:\\data\\data-master\\retail_db\\order_items\\output")
  }
}
