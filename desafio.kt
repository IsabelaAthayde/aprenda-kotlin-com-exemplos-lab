// [Abra o projeto no Kotlin Playground](https://pl.kotl.in/goXTp6Ktw)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String)

data class ConteudoEducacional(var nome: String, val nivel: Nivel, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()   
    

	//função que matricula os alunos 
    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            println("Usuário ${usuario.nome} já possui matricula na formação $nome")
            return
        }
        
        inscritos.add(usuario)
        println("Usuário ${usuario.nome} matriculado na formação $nome")
    }
    
    fun remover(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            inscritos.remove(usuario)
        	println("O usuário ${usuario.nome} teve sua matricula removida da formação $nome")
        }
    }
    
}



data class ConteudosParaAprender(var conteudos: List<ConteudoEducacional>) {
    //Iniciar listas de cada nivel 
    val basicos = mutableListOf<ConteudoEducacional>()    
    val intermediarios = mutableListOf<ConteudoEducacional>()
    val dificeis = mutableListOf<ConteudoEducacional>()
    
    //Armazenar os conteudos de acordo com o nível
    fun armazenarConteudosNosNiveis(conteudos: List<ConteudoEducacional>) {
       conteudos.forEach {
             if (it.nivel == Nivel.BASICO) {
                basicos.add(it)
            } else if (it.nivel == Nivel.INTERMEDIARIO){
                intermediarios.add(it)
            } else if (it.nivel == Nivel.DIFICIL) {
                dificeis.add(it)
            }
        }
    }
    
    //Chamar a função de armazenar como ação padrão da classe
    var armazenado = armazenarConteudosNosNiveis(conteudos)
   
   //mostrar conteudo de forma opcional e criar um filtro na busca de acordo com o argumento recebido.
    fun mostrarConteudo(escolhaNivel: String) {
        val opcoes = listOf("BASICO", "INTERMEDIARIO", "DIFICIL")
        
        if(escolhaNivel !in opcoes) {
            println('\n')
        	throw IllegalArgumentException("$escolhaNivel não aceito, escolha somente entre os três níveis disponíveis para continuar. \n (BASICO, INTERMEDIARIO OU DIFICIL)")
        }
        
        if (escolhaNivel == "BASICO") {
            println("\nOs contéudos Básicos são:")    

            //Criar Tratamento para caso a lista esteja vazia
            if (basicos.isEmpty()) {
                println("Página Vazia!")
                return
            }

            //Iterar pela lista e imprimir o conteudo
            basicos.forEach {
                println("Conteúdo: ${it.nome} de nível ${it.nivel} que possuí a duração de ${it.duracao} minutos")
            }
        } else if (escolhaNivel == "INTERMEDIARIO"){
           	println("\nOs contéudos Intermediários são:")

            if (intermediarios.isEmpty()) {
                println("Página Vazia!")
                return
            }
            
            intermediarios.forEach {
                println("Conteúdo: ${it.nome} de nível ${it.nivel} que possuí a duração de ${it.duracao} minutos")
            }
        } else if (escolhaNivel == "DIFICIL") {
            println("\nOs contéudos Difícies são:")
            if (dificeis.isEmpty()) {
                println("Página Vazia!\n")
                return
            }
           	dificeis.forEach {
                println("Conteúdo: ${it.nome} de nível ${it.nivel} que possuí a duração de ${it.duracao} minutos")
           	}
        }
    }
 
}

fun main() {
    // Criar alguns conteúdos educacionais
    val conteudo1 = ConteudoEducacional("Introdução à programação", Nivel.BASICO, 60)
    val conteudo2 = ConteudoEducacional("Algoritmos avançados", Nivel.INTERMEDIARIO, 90)
    val conteudo3 = ConteudoEducacional("Bancos de Dados", Nivel.INTERMEDIARIO, 75)
    
    //Armazenar os conteudos em uma só lista
    val conteudosList = mutableListOf(conteudo1, conteudo2, conteudo3)
    
    //Utilizar da classe e da função para mostrar os conteudos de acordo com o filtro de nivel

    ConteudosParaAprender(conteudosList).mostrarConteudo("BASICO") 
    
    ConteudosParaAprender(conteudosList).mostrarConteudo("INTERMEDIARIO") 
    
    ConteudosParaAprender(conteudosList).mostrarConteudo("DIFICIL")


    
    // Criar formações passando o nome, o nivel, e uma lista com os conteudos educacionais
    val formacao1 = Formacao("Desenvolvimento Web", Nivel.INTERMEDIARIO, listOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Ciência de Dados", Nivel.DIFICIL, listOf(conteudo1, conteudo3))
    val formacao3 = Formacao("Programação Básica", Nivel.BASICO, listOf(conteudo1))

    // Criar alguns usuários passando o nome
    val usuario1 = Usuario("Weberth")
    val usuario2 = Usuario("Isabela")
    val usuario3 = Usuario("Mario")    
    val usuario4 = Usuario("Julia")

    // Matricular usuários nas formações, não permitindo duplicatas
    formacao1.matricular(usuario1)
    
    //teste para duplicatas
    formacao1.matricular(usuario2)
    formacao1.matricular(usuario2) 
    
    formacao1.matricular(usuario3)
    formacao1.matricular(usuario4)
    formacao2.matricular(usuario2)
    formacao3.matricular(usuario3)
    formacao3.matricular(usuario4)

    
    //Remover usuários de uma formação
    formacao1.remover(usuario4)

    // Exibir os inscritos em cada formação
    println("\nInscritos na formação '${formacao1.nome}': ${formacao1.inscritos.map { it.nome }}")
    println("Inscritos na formação '${formacao2.nome}': ${formacao2.inscritos.map { it.nome }}")
    println("Inscritos na formação '${formacao3.nome}': ${formacao3.inscritos.map { it.nome }}\n")
}
