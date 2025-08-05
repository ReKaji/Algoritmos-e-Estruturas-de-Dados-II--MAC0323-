# Exercício:

Introdução.  Vimos que tabelas de dispersão, também conhecidas como tabelas de espalhamento ou tabelas de hashing, podem ser usadas para implementar tabelas de símbolos de forma eficiente. Por exemplo, considere o problema de determinar o número de ocorrências de palavras em textos.

Conhecemos o programa FrequencyCounter.java, que resolve esse problema usando a tabela de símbolos implementada em ST.java (que usa a classe java.util.TreeMap.java, do Java, que é baseada em árvores rubro-negras). Os programas FrequencyCounterRB.java e FrequencyCounterLP.java usam as tabelas de símbolos implementadas em RedBlackST.java e LinearProbingHashST.java, respectivamente. As execuções abaixo indicam que FrequencyCounterLP.java é claramente mais rápido que FrequencyCounter.java e FrequencyCounterRB.java. Nos exemplos a seguir, usamos frequentemente o texto em

https://www.ime.usp.br/~yoshi/DATA/LEIPZIG/leipzig1m.txt

como entrada.

$ time java-algs4 FrequencyCounter 1 < DATA/LEIPZIG/leipzig1m.txt
the 1160105
distinct = 534580
words = 21191455

real 0m12.650s
user 0m13.798s
sys 0m0.216s
$ time java-algs4 FrequencyCounterRB 1 < DATA/LEIPZIG/leipzig1m.txt
the 1160105
distinct = 534580
words = 21191455

real 0m14.377s
user 0m15.564s
sys 0m0.193s
$ time java-algs4 FrequencyCounterLP 1 < DATA/LEIPZIG/leipzig1m.txt
the 1160105
distinct = 534580
words = 21191455

real 0m5.102s
user 0m6.050s
sys 0m0.218s
$

Análise idealizada de probing linear.  Tabelas de dispersão com resolução de colisões por probing linear são analisadas usando-se um modelo idealizado, que supõe válida a hipótese de hashing uniforme (uniform hashing hypothesis, UHH). O resultado é como pode ser visto na página 33 de

https://www.ime.usp.br/~yoshi/Sedgewick/Algs4th/Slides/34HashTables.pdf

O teorema de Knuth apresentado na página mencionada acima diz que, por exemplo, se o fator de carregamento α
 de uma tabela é de 50%
 (isto é, α=1/2
), então o número médio de posições que examinamos em uma busca com sucesso em tal tabela é 1.5
. Ademais, o número médio de posições que examinamos em uma busca sem sucesso em tal tabela é 2.5
.

Por conveniência, vamos escrever ANPH (Average Number of Probes in a search Hit) para o número médio de posições examinadas em uma busca com sucesso. Analogamente, vamos escrever ANPM (Average Number of Probes in a search Miss) para o número médio de posições examinadas em uma busca sem sucesso, que também coincide com o número médio de posições que são examinadas em uma inserção de um novo elemento.

Um simulador do modelo idealizado.  O programa LPPseudoRandom.java, disponível em

https://www.ime.usp.br/~yoshi/2024i/mac0323/sandbox/2024.04.25/NO_COMPARES_PROBES_ST/

simula a criação de uma tabela de dispersão com probing linear no modelo idealizado, isto é, sob UHH, e determina o ANPH e ANPM da tabela resultante. Seguem alguns exemplos de execução de LPPseudoRandom.java.

$ java-algs4 LPPseudoRandom 88888888 1000000 500000 - -
Average displacement: 0.5004
Largest cluster: 46
Ave. no. probes in a search hit: 1.5004
Ave. no. probes in a search miss: 2.5087
Load factor: 0.5000
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.5000
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 2.5000

$ java-algs4 LPPseudoRandom 88888888 1000000 250000 - -
Average displacement: 0.1696
Largest cluster: 15
Ave. no. probes in a search hit: 1.1696
Ave. no. probes in a search miss: 1.3909
Load factor: 0.2500
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.1667
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.3889

$ java-algs4 LPPseudoRandom 88888888 1000000 950000 - -
Average displacement: 9.7501
Largest cluster: 3197
Ave. no. probes in a search hit: 10.7501
Ave. no. probes in a search miss: 206.9636
Load factor: 0.9500
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 10.5000
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 200.5000
$

Versão estendida de LinearProbingHashST.java.  Neste exercício, você deve escrever um programa chamado LinearProbingHashSTX.java, incluindo novos métodos à classe LinearProbingHashST.java. Com esses métodos, o usuário poderá determinar coisas como o ANPH e o ANPM da tabela corrente.

O programa LPXClient.java ilustra o uso de LinearProbingHashSTX.java:

$ echo "S E A R C H E X A M P L E" | java-algs4 LPXClient
Size [number of clusters of that size]:
2 [2]
1 [6]
Table size: 32
Largest cluster: 2
Ave. no. probes in a search hit: 1.0000
Ave. no. probes in a search miss: 1.3750
Load factor: 0.3125
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.2273
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.5579

$ java-algs4 LPXClient - - < DATA/LEIPZIG/leipzig1m.txt
Table size: 2097152
Largest cluster: 1129
Ave. no. probes in a search hit: 1.4906
Ave. no. probes in a search miss: 1.8321
Load factor: 0.2549
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.1711
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.4006
$

As execuções acima de LPXClient.java ilustram que o comportamento de uma tabela de dispersão com probing linear "real" é razoavelmente bem modelado pelo caso em que vale UHH.

Seu programa LinearProbingHashSTX.java.  Para escrever LinearProbingHashSTX.java, você deve adicionar os métodos abaixo à classe LinearProbingHashST.java. Para cada método, descrevemos sua assinatura e seu comportamento esperado.


1. hitProbes() deve devolver o ANPH da tabela corrente. Sua assinatura deve ser

    public double hitProbes()

2. missProbes() deve devolver o ANPM da tabela corrente. Assinatura:

    public double missProbes()

3. clusters() deve devolver um int[] cujas entradas são o número de elementos em cada cluster da tabela corrente. Assinatura:

    public int[] clusters()

4. maxClusterSize() deve devolver o tamanho máximo de um cluster na tabela corrente. Assinatura:

    public int maxClusterSize()

5. table() deve devolver um boolean[] tal que sua entrada de índice i
 é true se e só se a i
-ésima entrada da tabela corrente está ocupada. Ademais, o boolean[] devolvido por table() deve ter a mesma dimensão que a dimensão da tabela corrente. Assinatura:

    public boolean[] table()

6. tableSize() deve devolver a dimensão da tabela corrente. Assinatura:

    public int tableSize()

7. loadFactor() deve devolver o fator de carregamento da tabela atual. Assinatura:

    public double loadFactor()

Entradas maldosas.  Sabemos que é muitas vezes possível escolher entradas maldosas para tabelas de dispersão. Veja as páginas 38 a 40 de

https://www.ime.usp.br/~yoshi/Sedgewick/Algs4th/Slides/34HashTables.pdf

Veja também os programas Collision.java e GeneratorAaBB.java em

https://www.ime.usp.br/~yoshi/2024i/mac0323/sandbox/2024.04.25/

Outra forma de se gerar entradas maldosas.  O programa Generator.java, dado abaixo, pode ser usado para gerar entradas maldosas para tabelas baseadas em probing linear:

$ java-algs4 Generator 1000000 5 QRSTUVWXYZ0123456789 7777777 | java-algs4 LPXClient - -
Table size: 2097152
Largest cluster: 12718
Ave. no. probes in a search hit: 183.5567
Ave. no. probes in a search miss: 306.1876
Load factor: 0.4097
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.3470
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.9348
$ java-algs4 Generator 2000000 5 QRSTUVWXYZ0123456789 7777777 | java-algs4 LPXClient - -
Table size: 4194304
Largest cluster: 12993
Ave. no. probes in a search hit: 183.6794
Ave. no. probes in a search miss: 216.5757
Load factor: 0.3545
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.2746
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.6999
$ java-algs4 Generator 4000000 5 QRSTUVWXYZ0123456789 7777777 | java-algs4 LPXClient - -
Table size: 8388608
Largest cluster: 15035
Ave. no. probes in a search hit: 623.9492
Ave. no. probes in a search miss: 797.9337
Load factor: 0.2721
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.1869
Theoretical / ave. probes search miss / under uniform hashing
hypothesis: 1.4436
$

As estatísticas ruins que vemos acima têm reflexos ruins nos tempos de execução:

$ java-algs4 Generator 1000000 5 QRSTUVWXYZ0123456789 7777777 > tmp; time java-algs4 LPXClient - - < tmp; rm tmp
Table size: 2097152
Largest cluster: 12718
Ave. no. probes in a search hit: 183.5567
Ave. no. probes in a search miss: 306.1876
Load factor: 0.4097
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.3470
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.9348

real 0m2.362s
user 0m2.612s
sys 0m0.068s
$ java-algs4 Generator 2000000 5 QRSTUVWXYZ0123456789 7777777 > tmp; time java-algs4 LPXClient - - < tmp; rm tmp
Table size: 4194304
Largest cluster: 12993
Ave. no. probes in a search hit: 183.6794
Ave. no. probes in a search miss: 216.5757
Load factor: 0.3545
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.2746
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.6999

real 0m7.279s
user 0m7.668s
sys 0m0.114s
$ java-algs4 Generator 4000000 5 QRSTUVWXYZ0123456789 7777777 > tmp; time java-algs4 LPXClient - - < tmp; rm tmp
Table size: 8388608
Largest cluster: 15035
Ave. no. probes in a search hit: 623.9492
Ave. no. probes in a search miss: 797.9337
Load factor: 0.2721
Theoretical / ave. probes search hit / under uniform hashing hypothesis: 1.1869
Theoretical / ave. probes search miss / under uniform hashing hypothesis: 1.4436

real 0m29.456s
user 0m29.923s
sys 0m0.216s
$

Como vemos acima, se há perigo de haver entradas maldosamente escolhidas, é prudente não usar tabelas de dispersão.
