# Exerício:

Introdução. Alguns parâmetros relevantes de uma árvore rubro-negra esquerdista (ARNE) são sua altura (altura biternária e altura como AB), seu IPL e EPL, o número total ou a fração de nós vermelhos e o número médio de nós vermelhos nos caminhos até seus nós externos.

O programa RedBlackBSTMod.java (uma versão levemente modificada de RedBlackBST.java de S&W), dado abaixo, contém funções para determinar os parâmetros mencionados acima:

heightP() devolve a altura da ARNE como uma árvore binária;
height23P() devolve a altura biternária da ARNE;
iplP() devolve o IPL da ARNE;
sizeRP() devolve o número de nós vermelhos na ARNE;
aveRedLinksP() devolve o número médio de nós vermelhos nos caminhos até nós externos.
Leia o unit test de RedBlackBSTMod.java. Eis um exemplo de execução:

$ echo "S E A R C H X M P L" | java-algs4 RedBlackBSTMod
A 2
C 4
E 1
H 5
L 9
M 7
P 8
R 3
S 0
X 6
N = 10
2-3 height = 2
Plain height = 3
IPL = 19
No. red links = 3
Average no. of red links to an external node = 0.5455
$

Evolução de ARNEs. Suponha que k0,…,kN−1
 seja uma permutação de 0,…,N−1
. Suponha que começamos com uma ARNE vazia, e inserimos as chaves k0,k1,…
, uma por uma, nessa ordem. Obtermos assim uma sequência de N+1
 ARNEs, começando com a ARNE vazia e terminando com uma ARNE com N
 nós. Podemos pensar que uma ARNE "nasceu e evoluiu" até chegar a uma ARNE com N
 nós. Estamos interessados em saber como os vários parâmetros dessa ARNE evoluem neste processo. Estamos interessados no caso em que a permutação k0,…,kN−1
 é escolhida uniformemente ao acaso dentre todas as N!
 permutações possíveis.

Os programas GetStatsP.java e PlotStatsP.java. O programa GetStatsP.java pode ser usado para simular o processo acima e listar os parâmetros de interesse:

$ java-algs4 GetStatsP 10000 1 88888888
1000:
8.494
14
7
25.5
2.4835164835164836
2000:
9.3435
15

[...]

10000:
11.71
17
10
25.6
2.708629137086291
$

O programa PlotStatsP.java pode ser usado para visualizar a simulação executada por GetStatsP.java:

$ java-algs4 PlotStatsP 1000000 1 88888888
$ java-algs4 PlotStatsP 1000000 10 88888888

Na imagem gerada por PlotStatsP.java, duas funções são representadas com gráficos vermelhos. Essas funções parecem aproximar o comportamento da evolução da altura da ARNE e o comportamento da profundidade média dos nós da ARNE.

Complexidade das funções heightP() etc. As funções heightP(), height23P(), etc, estão implementadas em RedBlackBSTMod.java de forma direta: o valor correspondente é calculado cada vez que a função é executada (a letra "P" em seus nomes vem de "plain"). Todas essas funções têm complexidade linear no número de nós da ARNE, exceto por uma delas (qual?).

Percebemos que o tempo de execução de GetStatP.java cresce consideravelmente com N
:

$ time java-algs4 GetStatsP 1000000 1 88888888 > /dev/null

real 0m2.486s
user 0m2.670s
sys 0m0.039s
$ time java-algs4 GetStatsP 2000000 1 88888888 > /dev/null

real 0m8.042s
user 0m8.458s
sys 0m0.060s
$ time java-algs4 GetStatsP 4000000 1 88888888 > /dev/null

real 0m38.339s
user 0m40.337s
sys 0m0.115s
$

Objetivo. Neste exercício, você deve implementar as funções

height()
height23()
ipl()
sizeR()
aveRedLinks()
que devolvem, respectivamente, os mesmos valores que as funções "com P", mas de forma que elas tenham complexidade de tempo constante. Para tanto, você pode usar mais memória em sua ARNE: você pode adicionar a cada nó 3 novas variáveis de instância do tipo int. Você pode também adicionar mais uma variável de instância do tipo int à classe da ARNE (atualmente a classe só tem a variável root como variável de instância).

Você deve implementar suas funções height() etc na classe RedBlackBSTMod.java, de forma que os clientes GetStats.java e PlotStats.java, dados abaixo, possam ser executados sem nenhuma modificação. Note que, por exemplo, as saídas de GetStats.java e GetStatsP.java devem ser idênticas quando executadas com as mesmas entradas.

Desempenho esperado. Espera-se o seguinte tipo de comportamento de GetStats.java:

$ time java-algs4 GetStats 1000000 1 88888888 > /dev/null

real 0m0.697s
user 0m0.895s
sys 0m0.044s
$ time java-algs4 GetStats 2000000 1 88888888 > /dev/null

real 0m1.726s
user 0m2.132s
sys 0m0.070s
$ time java-algs4 GetStats 4000000 1 88888888 > /dev/null

real 0m4.277s
user 0m5.253s
sys 0m0.122s
$ time java-algs4 GetStats 8000000 1 88888888 > /dev/null

real 0m10.416s
user 0m14.441s
sys 0m0.270s
$ time java-algs4 GetStats 16000000 1 88888888 > /dev/null

real 0m25.735s
user 0m47.187s
sys 0m0.458s
$

Podemos usar o utilitário md5 (md5sum no GNU/Linux) para verificar se as saídas de GetStats.java e GetStatsP.java coincidem:

$ java-algs4 GetStatsP 2000000 1 77777777 | md5
d3a80a508d8a8150f9bee45e84b1a125
$ cd FASTER/
$ java-algs4 GetStats 2000000 1 77777777 | md5
d3a80a508d8a8150f9bee45e84b1a125
$ cd ..
$ java-algs4 GetStatsP 4000000 1 77777777 | md5
75f444b12049087210e25c7b7a09b7ff
$ cd FASTER/
$ java-algs4 GetStats 4000000 1 77777777 | md5
75f444b12049087210e25c7b7a09b7ff
$

O programa PlotStats.java. Com sua classe RedBlackBSTMod.java contendo implementações de height() etc, você poderá gerar gráficos com PlotStats.java de forma mais rápida.

