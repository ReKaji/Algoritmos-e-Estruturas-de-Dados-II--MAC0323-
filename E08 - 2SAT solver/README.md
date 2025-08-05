# Exercício:

Introdução.  Neste exercício, você resolverá o Creative Problem 4.2.34, 2-Satisfiability, de S&W.

Satisfatibilidade.  Seja ϕ
 uma fórmula booleana, com variáveis x1,…,xN
. Dizemos que ϕ
 é satisfatível se existe uma atribuição de valores-verdade ("true" ou "false" ou 1
 ou 0
) às variáveis xi
 de forma que ϕ
 torna-se verdadeira com esses valores.

Problema SAT. Dada uma fórmula booleana, decidir se ela é satisfatível.

O SAT é um problema de natureza fundamental em computação: ele é um problema NP-completo. Assim, acredita-se que não há algoritmo polinomial para SAT, e sabe-se que todo problema NP admite um algoritmo polinomial caso SAT admita tal algoritmo. Você estudará essas coisas (em geral, brevemente) em MAC0338, Análise de Algoritmos, e caso você queira estudar essas coisas mais a fundo, faça MAC0414, Autômatos, Computabilidade e Complexidade, ou MAC0430, Algoritmos e Complexidade de Computação.

Mesmo restringindo a entrada a certas fórmulas de formato especial, o Problema SAT continua difícil (NP-completo). Seja k
 um inteiro positivo. Dizemos que uma fórmula booleana ϕ
 é uma fórmula k
SAT se ϕ
 é uma conjunção de disjunções, onde cada disjunção tem exatamente k
 literais (um literal é uma variável ou sua negação). As disjunções em ϕ
 são chamadas de cláusulas. Assim, ϕ
 é uma fórmula k
SAT se ϕ
 é um "e" de cláusulas, com cada cláusula contendo k
 variáveis (ou suas negações). Por exemplo, eis um 3
SAT:

ϕ=(x1∨x2∨¬x3)∧(x2∨x3∨¬x4)∧(x3∨x4∨x1)∧(x4∨¬x1∨x2)∧(¬x1∨¬x2∨x3)∧(¬x2∨¬x3∨x4)∧(¬x3∨¬x4∨¬x1)∧(¬x4∨x1∨¬x2).

 A ϕ
 acima é constituída de 8
 cláusulas.

Problema k
SAT. Dada uma fórmula booleana k
SAT, decidir se ela é satisfatível.

O Problema k
SAT para k=3
 é também NP-completo. Entretanto, o 2
SAT admite algoritmo polinomial; de fato, 2
SAT admite um algoritmo linear. Neste exercício, você encontrará e implementará tal algoritmo.

Grafos de implicação.  Sabemos que a∨b
 é equivalente a ambos ¬a⇒b
 e ¬b⇒a
. Assim, um 2
SAT pode ser representado como um grafo dirigido, como vimos no Exercício T10. Aliás, você deve fazer o T10 antes de fazer este exercício.

O programa ImplicationGraph.java fornecido abaixo cria o grafo de implicações de um 2
SAT dado.

Sua tarefa.  Neste exercício, você deve escrever o programa TwoSAT.java, que implementa a seguinte API:

public class TwoSAT
-------------------------------------------------------------------------------------
              TwoSAT(ImplicationGraph impgr) ... resolve o 2SAT codificado em imprgr
      boolean hasSolution()                  ... true se e só se satisfatível
    boolean[] assignment()                   ... um satisfying assignment
       String unSATProof()                   ... certificado de não-satisfatibilidade

O construtor TwoSAT() deve ter complexidade O(N+M)
 quando o 2
SAT codificado em impgr tem N
 variáveis e M
 cláusulas. Os métodos hasSolution(), assignment() e unSATProof() devem ter complexidade constante.  De fato, as "respostas" para as chamadas de hasSolution(), assignment() e unSATProof() devem ser computadas quando o construtor TwoSAT() for chamado; hasSolution(), assignment() e unSATProof() devem apenas devolver os valores computados (mais precisamente, assignment() e unSATProof() devem devolver referências aos objetos computados pelo construtor).

Quando o 2
SAT fornecido não é satisfatível, unSATProof() deve devolver um certificado para este fato. Isto é discutido mais abaixo.

O programa TwoSATClient.java ilustra o uso de TwoSAT.java. Exemplos de execução de TwoSATClient.java seguem abaixo.

2
SAT aleatórios.  Para verificar seus programas, você pode achar conveniente usar o gerador de instâncias InstanceGenerator.java. Este programa recebe N
, M
 e seed
 e produz um 2
SAT aleatório com N
 variáveis e M
 cláusulas, usando seed
 como a semente de StdRandom.java.

Execuções de TwoSATClient.java.  Considere as seguintes duas execuções:

$ java-algs4 InstanceGenerator 4 9 7777 | java-algs4 TwoSATClient
Satisfying assignment:
1: true
2: false
3: false
4: false

Check the assignment:

Assignment: true false false false
4 variables
18 implications
1 [true]: -4 [true] -3 [true]
-1 [false]: 4 [false] 3 [false]
2 [false]: 4 [false] -3 [true] 2 [false]
-2 [true]: -3 [true] -2 [true]
3 [false]: -2 [true] 2 [false] -1 [false]
-3 [true]: -4 [true] 1 [true]
4 [false]: 3 [false] -1 [false]
-4 [true]: -2 [true] 1 [true]
Truth value of 2SAT: true
$ java-algs4 InstanceGenerator 4 10 7777 | java-algs4 TwoSATClient
Not satisfiable:

1 => -3 => -1
-1 => 3 => -2 => -3 => 1

Check the graph:

4 variables
20 implications
1: -4 -3 3
-1: 4 3
2: 4 -3 2
-2: -3 -2
3: -2 2 -1
-3: -4 -1 1
4: 3 -1
-4: -2 1
$

No primeiro caso, InstanceGenerator.java gerou uma instância satisfatível, e TwoSATClient.java encontrou uma solução. No segundo caso, a instância gerada (que contém apenas uma cláusula a mais que a instância anterior) não é satisfatível. TwoSATClient.java, não apenas disse que a instância não é satisfatível, mas também forneceu um certificado para essa afirmação: o grafo de implicações contém as implicações x1⇒¬x2⇒¬x1
 e ¬x1⇒x4⇒x2⇒x1
 e portanto x1⇔¬x1
, que é uma contradição.

No caso de instâncias não satisfatíveis, o método unSATProof() de TwoSAT.java deve devolver dois caminhos dirigidos no grafo de implicações: um de uma variável xi
 para sua negação ¬xi
 e outro de ¬xi
 para xi
 (como no exemplo acima).

(Leia o código de TwoSATClient.java para entender a saída acima em detalhe.)

Limiar de 2
SAT. Consideremos instâncias aleatórias de 2
SAT, como aquelas geradas por InstanceGenerator.java. Sabe-se que, quando o número de cláusulas M
 é consideravelmente menor que N
, o número de variáveis, os 2
SATs aleatórios são satisfatíveis com probabilidade alta. Por outro lado, quando M
 é consideravelmente maior que N
, os 2
SATs aleatórios são insatisfatíveis com probabilidade alta. Para observar este fato experimentalmente, você pode usar o programa Estimate.java, que depende de TwoSAT.java.

Exemplos de execução

$ java-algs4 Estimate 1000 1000 10000 888
Simulation time: 10.633 seconds
Fraction satisfiable: 0.9335
$ java-algs4 Estimate 1000 1150 10000 888
Simulation time: 10.999 seconds
Fraction satisfiable: 0.498
$ java-algs4 Estimate 1000 1250 10000 888
Simulation time: 10.38 seconds
Fraction satisfiable: 0.1194
$ java-algs4 Estimate 1000 1300 10000 888
Simulation time: 10.693 seconds
Fraction satisfiable: 0.0392
$

Desempenho.  Exemplos adicionais de execução são dados no arquivo experiments.txt. Espera-se que seu programa tenha desempenho não muito diferente do que pode ser visto em exemplos.txt.

Transição de fase. Suponha que estamos tratando de 2
SATs aleatórios com N
 variáveis. Como comentado acima, conforme M
 varia de valores menores que N
 para valores maiores que N
, a probabilidade de 2
SATs aleatórios serem satisfatíveis passa de próximo de 1
 a próximo de 0
. De fato, essa transição é "brusca". Você pode observar essa fato colocando o resultado de Estimate.java em um gráfico. Isso é feito pelo programa SATPlot.java. Experimente executar SATPlot.java como segue:

$ java-algs4 SATPlot  1000 1000 88888888 &
$ java-algs4 SATPlot 20000  400 88888888 &
$ java-algs4 SATPlot 40000  500 88888888 &

A execução

$ java-algs4 SATPlot N T seed

gera um gráfico produzido da seguinte forma. São gerados 2
SATs aleatórios com N
 variáveis e M
 cláusulas, onde M
 varia (potencialmente) de 0
 a 2N
. Para cada M
 fixo, são geradas T
 instâncias aleatórias e a probabilidade de satisfatibilidade correspondente é estimada. Os valores de M
 para os quais os experimentos são executados são escolhidos por um método "adaptativo", útil quando a geração de cada ponto do gráfico é custoso. Para entender como funciona essa método adaptativo, veja "Adaptive plot" em

https://introcs.cs.princeton.edu/java/24percolation/

SAT solvers.  Apesar de SAT ser um problema NP-completo, há "SAT solvers" que resolvem eficientemente problemas muito grandes que surgem em aplicações práticas. Se você quiser ler sobre SAT solvers, veja, por exemplo,

https://www.amazon.com/Art-Computer-Programming-Fascicle-Satisfiability/dp/0134397606
https://github.com/aaw/sat
https://www-cs-faculty.stanford.edu/~knuth/programs.html
