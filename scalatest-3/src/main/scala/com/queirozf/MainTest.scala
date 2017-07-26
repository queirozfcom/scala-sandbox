package com.queirozf

import org.scalatest.Assertions._

/**
  * Created by felipe on 25/07/17.
  */
object MainTest extends App {

  // will pass
  assert("foo" == "foo")

  // will fail
  assertResult("foo") {
    "bar".toUpperCase
  }

}
