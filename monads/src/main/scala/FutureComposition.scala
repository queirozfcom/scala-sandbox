import java.time.ZonedDateTime

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._

/**
  * Created by felipe.almeida@vtex.com.br on 06/06/16.
  */
object FutureComposition extends App {


  val quickInt = Future {
    Thread.sleep(5000)
    10
  }

  val slowInt = Future {
    Thread.sleep(9000)
    8
  }

  val futureException = Future {
    Thread.sleep(2000)
    new RuntimeException("returned exception!")
  }

  val futureThrow = Future {
    Thread.sleep(3000)
    throw new RuntimeException("threw exception!")
  }

  Await.result({
    val initial = ZonedDateTime.now.toEpochSecond
    for {
      i1 <- quickInt
      i2 <- slowInt
    } yield {
      println(s"elapsed time: ${ZonedDateTime.now.toEpochSecond - initial} seconds")
      println(i1 + i2)
    }
  }, 20.seconds)


  val newQuickInt = Future {
    Thread.sleep(5000)
    10
  }

  val newSlowInt = Future {
    Thread.sleep(9000)
    8
  }

  Await.result({
    val initial = ZonedDateTime.now.toEpochSecond
    Future.firstCompletedOf(List(newQuickInt, newSlowInt)).map { theInt =>
      println(s"elapsed time: ${ZonedDateTime.now.toEpochSecond - initial} seconds")
      println(theInt)
    }
  }, 20.seconds)

  // propagating errors

  val futureThrow1 = Future {
    Thread.sleep(1000)
    Try(throw new RuntimeException("threw exception!"))
  }

  val futureTry = Future {
    Thread.sleep(3000)
    Try(2)
  }

  val futureThrow3 = Future {
    Thread.sleep(5000)
    Try(new RuntimeException("threw exception!"))
  }


  Await.result({
    val initial = ZonedDateTime.now.toEpochSecond

    futureThrow1.map{

    }

    for {
      a <- futureThrow1
      b <- futureThrow2
      c <- futureThrow3
    } yield {
      println(s"elapsed time: ${ZonedDateTime.now.toEpochSecond - initial} seconds")
      println(List(a, b, c))
    }
  }

    , 20.seconds)


}
