package com.queirozf.sandbox

import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by felipe on 26/12/15.
 */
object JVMConcurrency1 {

  def main(args: Array[String]) {

    val lastInt = new AtomicInteger

    def futureInt(step: Int) = Future {
      Thread.sleep(1000)
      println(step + " step: " + lastInt.incrementAndGet)
      println(Runtime.getRuntime.availableProcessors())
    }

    (1.to(10)).foreach { i =>
      synchronized {
        Thread.sleep(1000)
        futureInt(i)
      }
    }

    println("1 step: " + lastInt)

    Thread.sleep(10000)

  }

}
