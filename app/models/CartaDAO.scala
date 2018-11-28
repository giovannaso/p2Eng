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
                Carta(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8))
            else
                Carta(0,"","",0,"","","","")
        }
    }
    
    def create(db: Database, disc: Carta): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("insert into carta(nome,sexo,idade,tamRoupa,tamSap,pedido,resp) values (?,?,?,?,?,?,?)")
            ps.setString(1,disc.nome)
            ps.setString(2,disc.sexo)
            ps.setInt(3,disc.idade)
            ps.setString(4,disc.tamRoupa)
            ps.setString(5,disc.tamSap)
            ps.setString(6,disc.pedido)
            ps.setString(7,disc.resp)
            ps.execute()
        }
    }
    
     def delete(db: Database, c: Delete): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("delete from carta where id= ?")
            ps.setInt(1,c.id)
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
        list
    }
    
    def update(db: Database, disc: Update): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("update carta set nome= ?, sexo= ?, idade= ?,tamRoupa= ?,tamSap= ?,pedido= ?,resp= ? where id= ?")
           ps.setString(1,disc.nome)
            ps.setString(2,disc.sexo)
            ps.setInt(3,disc.idade)
            ps.setString(4,disc.tamRoupa)
            ps.setString(5,disc.tamSap)
            ps.setString(6,disc.pedido)
            ps.setString(7,disc.resp)
            ps.setInt(8,disc.id)
            ps.execute()
        }
    }
    
    def adotar(db: Database, disc: Update): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("update carta set resp= ? where id= ?")
            ps.setString(1,disc.resp)
            ps.setInt(2,disc.id)
            ps.execute()
        }
    }
    
}