import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

//def badF: Future[Int] = Future{
//  Thread.sleep(1000)
//  throw new Exception("foo")
//}
//
//def goodF: Future[Int] = Future{
//  Thread.sleep(2000)
//  Random.nextInt(10)
//}
//
//val res = for{
//  a1 <- goodF
//  a2 <- badF
//} yield(a1+a2)
//
//val synRes = Await.result({
//  res
//},1.minute)
//
//println(synRes)

val futureoflists: Future[List[Int]] = Future{
  Thread.sleep(4000)
  List(1,2,3,4)
}

def nextValue: Future[List[Int]] = Future{
  Thread.sleep(1000)
  val num1 = Random.nextInt(10)
  val num2 = Random.nextInt(10)
  println("will return list now")
  List(num1,num2)
}

//Await.result({
//  nextValue.flatMap{ value1 =>
//    nextValue.map{ value2 =>
//      value1 ++ value2
//    }
//  }
//},1.minute)

Stream.from(1).foldLeft(Future(List.empty[Int])){
  case (future,i) => future.map{ num =>
    num + i
  }
}

//Await.result({
//
//  for {
//    each <- for{
//      i <- List(1,2,3)
//    } yield i
//
//  }
//
//},1.minute)
//
//
//Future.


