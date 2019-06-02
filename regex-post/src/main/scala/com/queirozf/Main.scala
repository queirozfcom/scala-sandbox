package com.queirozf

object Main extends App{
  // these are the strings we want to check
  val dateNoDay = "2016-08"
  val dateWithDay = "2016-08-20"

  // these are the patterns (note the starting capital letter)
  val YearAndMonth = """(\d{4})-([01][0-9])""".r
  val YearMonthAndDay = """(\d{4})-([01][0-9])-([012][0-9])""".r

  // this prints: "day provided: it is 20"
  dateWithDay match{
    case YearAndMonth(year,month) => println("no day provided")
    case YearMonthAndDay(year,month,day) => println(s"day provided: it is $day")
  }
}