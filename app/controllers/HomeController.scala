package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import play.api.data._
import play.api.data.Forms._
import scala.collection.mutable.MutableList
import models.Carta

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents) extends AbstractController(cc) {
  
  def index = Action {implicit request: Request[AnyContent] =>
    Ok(views.html.index()).withSession("teste" -> "123")
  }
  
  def t2 = Action{implicit request =>
    val teste = request.session.get("teste")
    teste match {
      case Some(str) => Ok(str)
      case None => NotFound("No session")
    }
  }
  
  def t3 = Action {implicit request =>
    Ok("deleted").withSession(request.session - "teste")
  }
  
  def sucesso = Action{implicit request =>
    import play.api.Logger
    Logger.debug(request.session.toString())
    val logado = request.session.get("usuario")
    Logger.debug(logado.toString())
    logado match {
      case Some(str) => Ok(views.html.sucesso())
      case None => Redirect("/login")
    }
  }
  
}