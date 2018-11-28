package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;


object UsuarioDAO{
    
    def create(db: Database, usu: Usuario): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("insert into usuario(nome,email,senha) values (?,?,?)")
            ps.setString(1,usu.nome)
            ps.setString(2,usu.email)
            ps.setString(3,usu.senha)
            ps.execute()
        }
    }
    
    def autenticar(db: Database, login: Login): Boolean ={
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("select * from usuario where email=? and senha=?")
            ps.setString(1,login.email)
            ps.setString(2,login.senha)
            val rs = ps.executeQuery()
            rs.next()
        }
    }
    
    def updateUsu(db: Database, usu: UpdateUsu): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("update usuario set nome= ?, email= ?, senha= ? where id= ?")
            ps.setString(1,usu.nome)
            ps.setString(2,usu.email)
            ps.setString(3,usu.senha)
            ps.setInt(4,usu.id)
            ps.execute()
        }
    }
    
    def deleteUsu(db: Database, usu: Delete): Unit = {
        db.withConnection{ conn =>
            val ps = conn.prepareStatement("delete from usuario where id= ?")
            ps.setInt(1,usu.id)
            ps.execute()
        }
    }
    
}