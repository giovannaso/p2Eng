package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.UsuarioDAO
import models.Login
import play.api.data._
import play.api.data.Forms._

@Singleton
class LoginController @Inject()(db: Database, cc: ControllerComponents) 
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
  
  val loginForm: Form[Login] = Form (
        mapping(
            "email" -> text,
            "senha" -> text
    )(Login.apply)(Login.unapply))
  
  def form = Action {implicit request =>
    Ok(views.html.loginForm(loginForm))
  }
  
  def auth = Action {implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.loginForm(formWithErrors))
      },
      login => {
        val estaLogado = UsuarioDAO.autenticar(db,login)
        if(estaLogado){
           Redirect("/").withSession("cartas" -> login.email)   
        }else{
           Redirect("/login")
        }
      }
    )
  }
  
  def logout = Action {implicit request =>
    Redirect("/").withSession(request.session - "cartas")
  }
}
