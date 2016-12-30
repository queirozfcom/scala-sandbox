import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

val s3Path = "s3://vtex-simpledb/felipe.csv"

val customSchema = StructType(Array(
  StructField("InstanceId", StringType, nullable = true),
  StructField("origin", StringType, nullable = true),
  StructField("authorizedDate", StringType, nullable = true),
  StructField("orderId", StringType, nullable = true),
  StructField("totalValue", StringType, nullable = true),
  StructField("Sellers", StringType, nullable = true),
  StructField("SellerNames", StringType, nullable = true),
  StructField("salesChannel", StringType, nullable = true),
  StructField("SellerOrderId", StringType, nullable = true),
  StructField("SalesChannelName", StringType, nullable = true),
  StructField("totalItems", StringType, nullable = true),
  StructField("Items", StringType, nullable = true)
))

/**
  * Created by felipe.almeida@vtex.com.br on 29/12/16.
  */
class LoadFromS3AndInsertToKinesis {

  def main(args: Array[String]) {

    val s3Path = args.head

    // schema: InstanceId,origin,authorizedDate,orderId,totalValue,Sellers,SellerNames,salesChannel,SellerOrderId,SalesChannelName,totalItems,Items

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .getOrCreate()

    val customSchema = StructType(Array(
      StructField("InstanceId", StringType, nullable = true),
      StructField("origin", StringType, nullable = true),
      StructField("authorizedDate", StringType, nullable = true),
      StructField("orderId", StringType, nullable = true),
      StructField("totalValue", StringType, nullable = true),
      StructField("Sellers", StringType, nullable = true),
      StructField("SellerNames", StringType, nullable = true),
      StructField("salesChannel", StringType, nullable = true),
      StructField("SellerOrderId", StringType, nullable = true),
      StructField("SalesChannelName", StringType, nullable = true),
      StructField("totalItems", StringType, nullable = true),
      StructField("Items", StringType, nullable = true)
    ))

    val df = spark.sqlContext.read.schema(customSchema).option("mode", "DROPMALFORMED").option("header","true").option("escape","""""""). csv(s3Path).repartition(10)




  }

}
