package com.queirozf.sandbox

import com.sksamuel.elastic4s.ElasticClient
import org.elasticsearch.common.settings.Settings
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms
import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by felipe on 03/12/15.
 */
object Elastic4s {

  def main(args: Array[String]) {
    val client = ElasticClient.remote("localhost",9300)

    Await.result(
      client.execute {
        search in "movies" query "*" aggs {
          aggregation terms "count_by_director" field "director"
        }
      } map { result =>
        result.aggregations.asScala.foreach { agg =>
          // agg is something that implements org.elasticsearch.search.aggregations.Aggregation
          // lots of classes implement it, depending upon the type of aggregation you have got
          println(s"\n ${agg.getClass} \n")
          // the above will print org.elasticsearch.search.aggregations.bucket.terms.StringTerms

          agg match{
            case st:StringTerms => {
              st.getBuckets.asScala.foreach{ bucket =>
                println(s"doc_count: ${bucket.getDocCount}  ")
                println(s"key: ${bucket.getKey}  ")
              }
            }
            case _ => println("Something else")
          }

        }
      }, 10.seconds)
  }

}
