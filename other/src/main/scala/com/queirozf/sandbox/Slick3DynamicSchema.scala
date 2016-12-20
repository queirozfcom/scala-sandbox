package com.queirozf.sandbox

import slick.driver.MySQLDriver.api._
import slick.profile.SqlProfile.ColumnOption.SqlType
import scala.concurrent.ExecutionContext.Implicits.global
import java.sql.Timestamp

/**
 * Created by felipe on 25/12/15.
 */
object Slick3DynamicSchema {

  final case class User(id: Long, name: String, email: Option[String] = None, created: Timestamp, updated: Timestamp)

  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Long]("id", O.PrimaryKey)

    def name = column[String]("name")

    def email = column[Option[String]]("email")

    def created = column[Timestamp]("created", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def updated = column[Timestamp]("updated", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def * = (id, name, email, created, updated) <>(User.tupled, User.unapply)

  }


  def main(args: Array[String]) {

    val db = Database.forURL("jdbc:mysql://localhost:3306/slickexamples?user=root&password=Ghjkl2238")

    val users = TableQuery[Users]

    try {

      db.run(users.schema.create)

    } finally db.close()

  }
}
