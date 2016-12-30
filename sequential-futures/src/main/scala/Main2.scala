import scala.collection.immutable.IndexedSeq
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

/**
  * Created by felipe.almeida@vtex.com.br on 30/06/16.
  */
object Main2 {

  case class Sku(id:Int)

  def getSkus: Future[List[Sku]] = Future {

    // retrieving skus takes time...
    Thread.sleep(1000)

    val num1 = Random.nextInt(1000)
    val num2 = Random.nextInt(1000)


    if (Random.shuffle(List(1, 2, 3, 4, 5, 6)).head == 1) {
      // this is meant to simulate the cases where we have no more skus to provide
      println("no more skus...")
      throw new Exception
    } else {
      println("here have some skus...")
      List(Sku(num1), Sku(num2))
    }
  }

  val res = Await.result({
    Future.sequence(1.to(1000).map(_ => getSkus)).map(_.flatten)
  },1.minute)

  println(res)

}
