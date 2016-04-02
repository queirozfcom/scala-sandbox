package controllers

import javax.inject._

import play.api.data.Forms._
import play.api.data._
import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  case class UserData(name: String, age: Int)

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }


  def createUser() = Action {

    val data1 = Map("name" -> "john", "age" -> "25")

    val positive = Constraint[Int] { num: Int =>
      if (num > 0) Valid else Invalid(Seq(ValidationError("error.nonpositive")))
    }

    val even = Constraint[Int] { num: Int =>
      if (num % 2 == 0) Valid else Invalid(Seq(ValidationError("error.uneven")))
    }

    val userForm: Form[UserData] = Form(
      mapping(
        "name" -> text,
        "age" -> number.verifying(positive, even)
      )(UserData.apply)(UserData.unapply)
    )

    userForm.bind(data1).fold(
      hasErrors => BadRequest(hasErrors.errorsAsJson),
      userData => Ok("no errors")
    )


  }

}
