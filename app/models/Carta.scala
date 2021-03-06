package models
 
case class Carta(id: Int, nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String)
 
case class Update(id: Int, nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String)

case class Adotar(id: Int, resp: String)

case class Delete(id:Int)


object Carta{
    
    def criarCartaID(id: Int, nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String) = {
        new Carta(id,nome,sexo,idade,tamRoupa,tamSap,pedido,resp)
    }
    
    def criarCarta(nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String): Carta= {
        return new Carta(0,nome,sexo,idade,tamRoupa,tamSap,pedido,resp)
    }
    
    def attCarta(id: Int, nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String): Update = {
        return new Update(id, nome, sexo, idade, tamRoupa, tamSap, pedido, resp)
    }
    
    def adotarCarta(id: Int, resp: String): Adotar = {
        return new Adotar(id, resp)
    }
    
    def delCarta(id: Int): Delete = {
        return new Delete(id)
    }
    
    def applyCarta(nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String): (String,String,Int,String,String,String,String) = {
        return (nome, sexo, idade, tamRoupa, tamSap, pedido, resp)
    }
    
    def unapplyCarta(c: (String,String,Int,String,String,String,String)): Option[(String,String,Int,String,String,String,String)] = {
        return Some(c._1,c._2,c._3,c._4,c._5,c._6,c._7)
    }
    
    def applyUp(id: Int, nome: String, sexo: String, idade: Int, tamRoupa: String, tamSap: String, pedido: String, resp: String): (Int,String,String,Int,String,String,String,String) = {
        return (id, nome, sexo, idade, tamRoupa, tamSap, pedido, resp)
    }
    
    def unapplyUp(c: (Int,String,String,Int,String,String,String,String)): Option[(Int,String,String,Int,String,String,String,String)] = {
        return Some(c._1,c._2,c._3,c._4,c._5,c._6,c._7,c._8)
    }
    
    def applyDel(id: Int): Int = {
        return (id)
    }
    
    def unapplyDel(id: Int): Option[Int] = {
        return Some(id)
    }
}