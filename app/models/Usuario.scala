package models

case class Login(email: String, senha: String)
case class upUsu(id: Int, nome: String, email: String, senha: String)
case class DeleteUsu(id: Int)

class Usuario(val _id: Int, val _nome: String, val _email: String, val _senha: String){
    def id = _id
    
    def nome = _nome
    
    def email = _email
    
    def senha = _senha
}

object Usuario{
     def upUsu(id: Int, nome: String, email: String, senha: String): upUsu = {
        return new upUsu(id,nome,email,senha)
    }
    
    def delUsu(id: Int): DeleteUsu = {
        return new DeleteUsu(id)
    }
    
    def apply(nome: String, email: String, senha: String): (String,String,String) = {
        return (nome,email,senha)
    }

    def unapply(usu: (String,String,String)): Option[(String,String,String)] = {
        return Some(usu._1,usu._2,usu._3)
    }
}