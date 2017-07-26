package com.queirozf.models

/**
  * Created by felipe on 25/07/17.
  */
case class Person(firstName: String, lastName: String, age: Integer) {

  require(age >= 0, "age should be a positive integer")

  def getFullName = firstName.toLowerCase.capitalize + " " + lastName.toLowerCase.capitalize
}
