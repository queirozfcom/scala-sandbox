import com.typesafe.config.ConfigFactory

/**
  * Created by felipe on 12/12/16.
  */
object Main extends App{

  val conf = ConfigFactory.load()

  val regexp = conf.getString("my-regexp")

  println(regexp)

  val str1 = "foobar.vtexcommercestable.com.br"

  println(if(str1.matches(regexp)) "true" else "false")

}
