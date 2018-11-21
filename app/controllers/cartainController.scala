package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.cartaDAO
import models.carta
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class cartainController @Inject()(db: Database, cc: ControllerComponents) 
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
  
  val form: Form[carta] = Form (
        mapping(
           // "id" -> number,
            "nome" -> text,
            "sexo" -> text,
            "idade" -> number,
            "tamRoupa" -> text,
            "tamSap" -> text,
            "pedido" -> text,
        )(carta.apply)(carta.unapply)
    )
  
  def create = Action {implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.filmForm(formWithErrors))
      },
      carta => {
        cartaDAO.create(db,carta)
        Redirect("/cartas")
      }
    )
  }
  
  def formcarta = Action {implicit request =>
    Ok(views.html.cartaForm(form))
  }
  
  def lista = Action {
 
    val list = MutableList[carta]()
    //conn representa a conexao de fato com o bd
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""
      select 
         * 
      from 
         cartas 
      order by 
          cartas.nome 
      limit 10""")
      while (res.next()) {
        list.+=(carta(//res.getInt(1)
              res.getString(2)
               ,res.getString(3)
               ,res.getInt(4)
               ,res.getString(5)
               ,res.getString(6)
               ,res.getString(7)))
      }
    }
 
    Ok(views.html.carta(list))
  }
  
  
}
