
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
object Main extends App {

  implicit val formats = DefaultFormats

  def mkObj(idx: Int): String = {

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



    println(write(obj))

    write(obj)

  }


  val streamName = SET ME

  implicit val kinesisClient: AmazonKinesisClient = new AmazonKinesisClient()

  val md5 = MessageDigest.getInstance("MD5")

  val indices = 0.to(1).foreach { outerIdx =>

    val req = new PutRecordsRequest

    val records = 0.to(200).map{ innerIdx =>
      val entry = new PutRecordsRequestEntry
      val data = mkObj(innerIdx)
      val dataBytes = data.getBytes("UTF-8")

      val bytebuf = ByteBuffer.wrap(dataBytes)

      entry.setData(bytebuf)
      entry.setPartitionKey(new String(md5.digest(dataBytes)))
      entry
    }.asJavaCollection


    print("numbytes: "+records.size()*250 )

    req.setStreamName(streamName)
    req.setRecords(records)

    Future(kinesisClient.putRecords(req)).map { res =>

      println("failed: "+res.getFailedRecordCount)
      println(Thread.currentThread().getName + ":" + res.toString)
    }.recover {
      case NonFatal(nf) => throw nf
    }


  }


  val tenMinuteMillis = 10*60*1000

  Thread.sleep(tenMinuteMillis)

}

