
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.time.Instant
import scala.collection.JavaConverters._

import com.amazonaws.services.kinesis.AmazonKinesisClient
import com.amazonaws.services.kinesis.model.{PutRecordRequest, PutRecordsRequest, PutRecordsRequestEntry}
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.Serialization._



import scala.collection.immutable.IndexedSeq
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.control.NonFatal

/**
  * Created by felipe.almeida@vtex.com.br on 20/12/16.
  */
object Main {


  def mkObj(idx: Int)(implicit formats: Formats): String = {

    val timestamp = Instant.now().toEpochMilli

    val mod = idx % 100

    // an obj more or less as large as those we see regularly
    val obj =
      ("accountName" -> "foo") ~
        ("namespace" -> "stresstest") ~
        ("name" -> "dummy") ~
        ("count" -> 1) ~
        ("sum" -> mod) ~
        ("min" -> mod) ~
        ("max" -> mod) ~
        ("unit" -> "numevts") ~
        ("version" -> "1.0.0") ~
        ("timestamp" -> timestamp) ~
        ("data" ->
          ("foo" -> "bar") ~
            ("baz" -> s"quux $idx bar baz") ~
            ("nested" ->
              ("other" ->
                ("bu" -> "báás") ~
                  ("fu" -> "faaa"))))



    //    println(write(obj))

    write(obj)

  }

  def main(args: Array[String]) {

    if(args.length != 4) throw new RuntimeException("""usage: sbt "run <nameStream> <numBatches> <numRecordsPerBatch> <threadWaitAfterEachBatchMillis>" """)

    if(args(3).toInt >= 500) throw new RuntimeException("""maximum number of records per PutRecords entry is 500""")


    print("Got args: ")
    args.foreach(println)

    val streamName = args.head

    val Array(numBatches,numRecordsPerBatch,threadWaitAfterEachBatchMillis) = args.tail.map(_.toLong)

    implicit val formats = DefaultFormats

    val cores = Runtime.getRuntime().availableProcessors()

    println(s"number of available cores: $cores")

    println(s"total num records to be sent: ${numBatches*numRecordsPerBatch}")

    println(s"total batches needed for all data (25M records) ${ (25000000.0 / (numRecordsPerBatch)).toLong}")

    println("Starting now...")
    Thread.sleep(2000)

    implicit val kinesisClient: AmazonKinesisClient = new AmazonKinesisClient()

    val md5 = MessageDigest.getInstance("MD5")

    val indices = 0.to(numBatches.toInt).foreach { batchIdx =>

      val req = new PutRecordsRequest

      val records = 0.to(numRecordsPerBatch.toInt).map{ innerIdx =>
        val entry = new PutRecordsRequestEntry
        val data = mkObj(innerIdx)
        val dataBytes = data.getBytes("UTF-8")

        val bytebuf = ByteBuffer.wrap(dataBytes)

        entry.setData(bytebuf)
        entry.setPartitionKey(new String(md5.digest(dataBytes)))
        entry
      }.asJavaCollection

      req.setStreamName(streamName)
      req.setRecords(records)

      Future(kinesisClient.putRecords(req)).map { res =>

        println(s"${Thread.currentThread().getName} - failcount: ${res.getFailedRecordCount} (batch: ${batchIdx})")

        //      if(res.getFailedRecordCount != 0){
        //        res.getRecords.asScala.foreach(println)
        //      }else{
        //        println("")
        //      }

        Thread.sleep(threadWaitAfterEachBatchMillis)
        //      println(Thread.currentThread().getName + ":" + res.toString)
      }.recover {
        case NonFatal(nf) => throw nf
      }


    }


    val tenMinuteMillis = 10*60*1000

    Thread.sleep(tenMinuteMillis)

  }

}

