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
    Ok(views.html.login(loginForm))
  }
  
  def auth = Action {implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors))
      },
      login => {
        val estaLogado = UsuarioDAO.autenticar(db,login)
       // val confAdm = UsuarioDAO.confAdm(db,login)
        if(estaLogado){
          if(login.email == "admin@carta.com"){
           Redirect("/carta/form").withSession("cartas" -> login.email)
           }else{
             Redirect("/carta").withSession("cartas" -> login.email)
           }
        }
        else{
           Redirect("/login")
      }
      }
    )
  }

  def logout = Action {implicit request =>
    Redirect("/").withSession(request.session - "cartas")
  }
  
}

