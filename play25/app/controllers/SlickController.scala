package controllers

import slick.driver.JdbcProfile
import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class User(id: Long, name: String)

class UsersTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def * = (id, name) <>(User.tupled, User.unapply)
}


@Singleton
class SlickController @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends Controller {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  case class User(id: Long, name: String)

  val users = TableQuery[UsersTable]

  def selectAllFromUserTable() = Action.async { implicit request =>
    dbConfig.db.run(users.map(_.name).result).map { results =>

      val resultsAsString = results.mkString(",")

      Ok(resultsAsString)
    }
  }

}
