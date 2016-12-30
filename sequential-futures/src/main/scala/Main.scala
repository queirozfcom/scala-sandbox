import scala.collection.immutable.IndexedSeq
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

/**
  * Created by felipe.almeida@vtex.com.br on 30/06/16.
  */
object Main extends App {

  case class Sku(id: Int)

  def getSkus: Future[List[Sku]] = Future {

    // retrieving skus takes time...
    Thread.sleep(1000)

    val num1 = Random.nextInt(1000)
    val num2 = Random.nextInt(1000)


    if (Random.shuffle(List(1, 2, 3, 4, 5, 6)).head == 1) {
      // this is meant to simulate the cases where we have no more skus to provide
      println("no more skus...")
List.empty[Sku]
    } else {
      println("here have some skus...")
      List(Sku(num1), Sku(num2))
    }
  }

  // do not call catalog more than X times
  val maximumNumberOfCalls = 1000

  val res = 1.to(maximumNumberOfCalls).foldLeft(getSkus) { (previousFuture, next) =>

    val foo = "bar"

    for {
      previousResults <- previousFuture
      skus <- getSkus
    } yield {
      if(skus.nonEmpty) {
        println(s"yielding stuff.., current results is: $previousResults")
        previousResults ++ skus
      } else {
        println("yielding self..")
        previousResults
      }
    }
  }

  res.onComplete { r =>
    println(r)
  }

  val concreteResult = Await.result({
    res
  }, 1.minute)

  println(concreteResult)

}



