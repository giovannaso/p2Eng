package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.UsuarioDAO
import models.Login
import models.UpUsu
import models.Delete
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
    
    val upForm: Form[UpdateUsu] = Form (
        mapping(
            "id" -> number,
            "nome" -> text,
            "email" -> text,
            "senha" -> text,
        )(UpdateUsu.apply)(UpdateUsu.unapply))
    
    val delForm: Form[Delete] = Form (
        mapping(
            "id" -> number
        )(Delete.apply)(Delete.unapply))
  
  
  def form = Action {implicit request =>
    Ok(views.html.login(loginForm))
  }
  
  def formUp = Action {implicit request =>
    Ok(views.html.usuUp(upForm))
  }
  
  def formDel = Action {implicit request =>
    Ok(views.html.usuDel(delForm))
  }
  
  def auth = Action {implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors))
      },
      login => {
        val estaLogado = UsuarioDAO.autenticar(db,login)
        if(estaLogado){
          if(login.email == "admin@carta.com"){
           Redirect("/adm").withSession("cartas" -> login.email)
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
  def updateUsu = Action {implicit request =>
    upForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.usuUp(formWithErrors))
      },
      updateUsu => {
        UsuarioDAO.updateUsu(db,updateUsu)
        Redirect("/")
      }
    )
  }
  
  def deleteUsu = Action {implicit request =>
    delForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.usuDel(formWithErrors))
      },
      deleteUsu => {
        GamesDAO.deleteUsu(db,deleteUsu)
        Redirect("/")
      }
    )
  }

  def logout = Action {implicit request =>
    Redirect("/").withSession(request.session - "cartas")
  }
  
}
