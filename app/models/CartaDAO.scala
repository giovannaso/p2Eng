package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object CartaDAO{
    
    def getCarta(db: Database, id: Int): Carta = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from carta where id=?")
            ps.setInt(1,id)
            val res = ps.executeQuery()
            if(res.next())
                Carta(res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8))
            else
                Carta("","","",0,"","","","")
        }
    }
    
    def create(db: Database, disc: Carta): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("insert into carta(nome,sexo,idade,tamRoupa,tamSap,pedido,resp) values (?,?,?,?,?,?,?)")
            ps.setString(2,disc.nome)
            ps.setString(3,disc.sexo)
            ps.setInt(4,disc.idade)
            ps.setString(5,disc.tamRoupa)
            ps.setString(6,disc.tamSap)
            ps.setString(7,disc.pedido)
            ps.setString(8,disc.resp)
            ps.execute()
        }
    }
    
    def listagem(db: Database): MutableList[Carta] = {
    
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
            list.+=(Carta(//res.getInt(1)
              res.getString(2)
               ,res.getString(3)
               ,res.getInt(4)
               ,res.getString(5)
               ,res.getString(6)
               ,res.getString(7)))
          }
    }
        list
    }
}