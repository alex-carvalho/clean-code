# Clean Code

O termo Clean Code é o título do livro de Robert C. Martin onde ele descreve os princípios que devem ser seguidos para se manter um código limpo com qualidade, fácil de ser mantido.
 
Mas qual a importância de se ter um código limpo? 

Pense no dia dia do desenvolvedor, ele passa o dia todo codificando ou boa parte dele lendo código já escrito?

A manutenção de um software é um grande problema para a maioria dos projetos, pois um código mal escrito dificulta muito seu entendimento. As práticas de Clean Code ajudam a evitar este problema.

Os exemplos tem como base o curso https://www.pluralsight.com/courses/writing-clean-code-humans


Clean Code é a base para um software de qualidade:
<br>

![Imagem](clean-code.png)


### Princípios do Clean Code:

* **Nomes significativos**

    O nome das variáveis, métodos e classes devem dizer claramente o que faz ou o que representa, procurar não abreviar palavras. Exemplo:

    | Ruim          | Melhor             |
    | ------------- |-------------       |
    | int p;        |int preco;          |
    | vDados()      |validaDados()       |    

 * **Métodos**

    Devem ter responsabilidade única.

    - Tamanho

        Não existe um número máximo de linhas que pode ter, mas lembre-se, se ele for muito extenso provavelmente está fazendo mais e uma coisa. 

        Um método deve poder ser visto completamente sem precisar usar o scroll.

    - Número de Parâmetros 

        Não existe um número máximo de parâmetros.

        Métodos que recebem muitos parâmetros provavelmente fazem mais de uma coisa, além de tornar sua utilização mais difícil. Quanto menos parâmetros melhor, e se possível evitar métodos com mais de 3.
 

* **Comentários**

    Comentários redundantes são totalmente desnecessários, exemplo:
    ```
    /*
    * Faz a validação dos dados
    */
    validaDados()
    ```
    O tempo gasto com comentários de justificativa pelo código ruim poderia ser gasto refatorando o mesmo.

    Não deixar código comentado, use versionamento git!

    Os comentários podem ser utilizados para documentação, marcação de TODO, alertar sobre um problema em algum framework e por isso fez desta forma.

* **Formatação**

    A formatação do código é algo que deve ser padronizado, pois facilita a leitura. As regras podem ser definidas pela equipe ou seguir a convenção da linguagem.

* **Classes**

    Devem ter responsabilidade única.

    Use nomes específicos eles levam a classes menores e mais coesas.

<br>
<br>
Commit de refactor: 

https://github.com/alex-carvalho/clean-code/commit/1f4e12ed4ca32fdfa31ff641eac5e526485c4ddc