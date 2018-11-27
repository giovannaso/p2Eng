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
    
   /** def confAdm(db: Database, login: Login): Boolean ={
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from usuario where email=? and senha=?")
            ps.setString(1,login.email)
            ps.setString(2,login.senha)
            val rs = ps.executeQuery()
            rs.next()
            if(login.email == "admin@carta.com"){
               return true
            }
        }
    } **/
}