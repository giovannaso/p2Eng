#p2
Carlos Kalam Siu, Giovanna Silva de Oliveira e Felipe Shitinoe

# Prova 2 - Play Framework

## Problematização:
As sacolinhas de Natal ou Sacolinha do bem são formas de ajudar aquelas pessoas que mais necessitam, seja com dinheiro para ajudar com os custos, alimentos ou até mesmo com brinquedos para trazer sorrisos para as crianças.
O nosso projeto tem como foco as crianças, dessa forma os voluntários ficam responsáveis por uma ou mais criança, dessa forma eles se tornam padrinhos. 
As sacolinhas podem ser montadas de várias formas, como uma lista de itens contendo brinquedos, materiais escolares, higiene, um valor monetário ou apenas um desses itens, dependendo muito da organizadora.

## O Projeto:
O nosso projeto se baseia nas sacolinhas de Natal, justamente por estarmos proximos dessa data comemorativa, utilizando o modelo MVC através da Play Framework. Primeiramente levantamos os componentes de uma sacolinha de natal e estruturamos
o banco de dados.



## Banco de Dados
Na tabela Carta ficará armazenada informações das cartas que serão designadas paras as crianças. 
Nela temos os seguintes campos: 
•	id (identificador numérico),
•	nome (tipo VARCHAR, nome da criança que receberá a carta),
•	sexo (tipo VARCHAR, sexo da criança que receberá a carta),
•	idade (tipo int, idade da criança que receberá a carta),
•	tamroupa (tipo VARCHAR, tamanho de roupa da criança que receberá a carta),
•	tamsap  (tipo VARCHAR, tamanho de roupa da criança que receberá a carta),
•	pedido (tipo VARCHAR, descrição do pedido da criança que receberá a carta),
•	email_u (tipo VARCHAR, email do padrinho que irá presentear a criança)
Na tabela Usuario ficara armazenada informações dos usuários, sendo o principal o Administrador, responsável pela inserção dos dados das crianças e padrinhos.
Nela temos os seguintes campos:
•	id (identificador numérico),
•	nome (tipo VARCHAR, nome do padrinho que presenteará a(s) criança(s)),
•	email_u (tipo VARCHAR, email do padrinho que irá presentear a criança),
•	senha (tipo VARCHAR, senha de usuário do padrinho que irá presentear a criança)

###### Script: 
CREATE TABLE carta(
    id serial PRIMARY KEY,
    nome VARCHAR (60) NOT NULL,
    sexo VARCHAR (20) NOT NULL,
    idade INT NOT NULL,
    tamRoupa VARCHAR (3) NOT NULL,
    tamSap VARCHAR (2) NOT NULL,
    pedido VARCHAR(100),
    resp VARCHAR (60)
);
CREATE TABLE usuario(
    id serial PRIMARY KEY
    nome VARCHAR (60),
    email varchar(70) UNIQUE NOT NULL,
    senha varchar(80) NOT NULL
);
Situações:
Originalmente o campo ‘tamsap’ era do tipo INT mas tivemos problemas apply e modificamos para o tipo VARCHAR, para isso tentamos usar os comandos 
•	“alter table carta alter column tamsap VARCHAR”
•	“alter table carta modify column tamsap VARCHAR”
•	“alter table carta modify tamsap VARCHAR”
Mas nenhum comando funcionou, decidimos utilizar o comando “drop table” e refazer a tabela.



## Controllers
 O Projeto conta com 3 Controllers:
    CartaController - responsável por gerenciar as requisições do usuário referentes a Carta. Contem os métodos que serão executados.
    HomeController - responsável pelo index.
    LoginController - Assim como o CartaController é responsável pelas requisições, mas referentes ao Login e gerenciamento de Usuários.

## Models
 Conta com 4 modelos, responsáveis pela validação e manipulação de dados:
    Carta.scala
    CartaDAO.scala
    Usuario.scala
    UsuarioDAO.scala
Tendo em vista que diferentes requisições trabalham com diferentes campos do banco de dados (tuplas) foram criadas diferentes classes e metodos nos modelos,
de forma com que eles só trabalhassem com os campos necessários (como por exemplo o delete trabalhar apenas com o ID, ou o cadastro não solicitar o ID, já que este
é serial.)

## Views
 O projeto contou com 10 telas, sendo elas categorizadas em:
    ###### Exibição
        Index - Pagina inicial
        Adm - Lista de funções do Administrador
        listaCriancas - Exibição da lista de cartas
    
    ###### Formularios
        adotar - Definir o padrinho de uma criança
        cadastrarUsu -  Cadastrar Usuário
        cartaDel - Deletar carta
        cartaForm - Cadastrar carta
        cartaUp - Atualizar carta
        delUsu - Excluir Usuario
        login - Logar no site

 O contratempo que tivemos com as Views foi a importação do CSS e o tratamento do HTML, já que alguns caracteres utilizados na importação são exclusivos para uso do Scala (@),
além de problemas com imagens relacionado a rotas e limitações por funções da framework (helper).