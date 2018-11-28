package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.CartaDAO
import models.Carta
import models.Update
import models.Delete
import play.api.data._
import play.api.data.Forms._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class CartaController @Inject()(db: Database, cc: ControllerComponents) 
  extends AbstractController(cc) with play.api.i18n.I18nSupport {
  
  val form: Form[(String,String,Int,String,String,String,String)] = Form (
        mapping(
            "nome" -> text,
            "sexo" -> text,
            "idade" -> number,
            "tamRoupa" -> text,
            "tamSap" -> text,
            "pedido" -> text,
            "resp" -> text
        )(Carta.applyCarta)(Carta.unapplyCarta)
    )
  
  val delForm: Form[Delete] = Form (
        mapping(
            "id" -> number
        )(Delete.apply)(Delete.unapply)
    )
    
  /**  
    val upForm: Form[Update] = Form (
        mapping(
            "id" -> number,
            "nome" -> text,
            "sexo" -> text,
            "idade" -> number,
            "tamRoupa" -> text,
            "tamSap" -> text,
            "pedido" -> text,
            "resp" -> text
        )(Update.apply)(Update.unapply)
    )
    **/
  
  
   def create = Action {implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.cartaForm(formWithErrors))
      },
      carta => {
        CartaDAO.create(db,Carta.criarCarta(carta._1,carta._2,carta._3,carta._4,carta._5,carta._6,carta._7))
        Redirect("/carta")
      }
    )
  }
  
  
 def delete = Action {implicit request =>
    delForm.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.cartaDel(formWithErrors))
      },
      delete => {
        CartaDAO.delete(db,delete)
        Redirect("/carta")
      }
    )
  }
  
  
    /**
  def update = Action {implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        println(formWithErrors)
        BadRequest(views.html.cartaupdate(formWithErrors))
      },
      carta => {
        cartaDAO.update(db,carta)
        Redirect("/carta")
      }
    )
  }
  
  def info(id: Int) = Action {
    val carta = cartaDAO.getcarta(db,id)
    Ok(views.html.info(carta))
  }
  */
 
  def formcarta = Action {implicit request =>
    Ok(views.html.cartaForm(form))
  }
  
  /**
  def formup = Action {implicit request =>
    Ok(views.html.cartaupdate(form))
  }
  */
  def formDel = Action {implicit request =>
    Ok(views.html.cartaDel(delForm))
  }
  
  
   def lista = Action {
    val list = MutableList[Carta]()
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""
      select 
         * 
      from 
         carta 
      order by 
          carta.nome 
      limit 10""")
      while (res.next()) {
            list.+=(Carta(res.getInt(1)
              ,res.getString(2)
               ,res.getString(3)
               ,res.getInt(4)
               ,res.getString(5)
               ,res.getString(6)
               ,res.getString(7)
               ,res.getString(8)))
          }
    }
 
    Ok(views.html.listaCriancas(list))
  }
  
  
  
  
  
}
 