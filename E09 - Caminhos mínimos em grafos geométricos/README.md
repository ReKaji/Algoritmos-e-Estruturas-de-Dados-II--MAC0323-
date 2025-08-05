# Exercício:

Os programas em

https://www.ime.usp.br/~yoshi/2024i/mac0323/sandbox/2024.06.04/GEOMETRIC_GRAPHS/

ilustram como a computação de uma árvore de caminhos mínimos (ACM) evolui ao executarmos o algoritmos de Dijkstra para encontrar um caminho mínimo de um vértice S
 a um vértice T
 em grafos geométricos (veja, em particular, o programa SPGeoGraph.java).

O algoritmo de Dijkstra inclui vértices na ACM sendo computada na "ordem de distância" do vértice S
, sem prestar particular atenção ao vértice de destino T
. Neste exercício, veremos como podemos fazer com que nosso procedimento leve em conta o vértice T
.

Grafos geométricos. Recordemos inicialmente como definimos nossos grafos geométricos. Suponha que temos um conjunto X
 de pontos no plano e que d
 seja um número real. Com esses dados, definimos um grafo dirigido D=D(X,d)
 com V(D)=X
, colocando um arco (P,Q)
 em D
 sempre que P
 e Q
 são elementos de X
 que distam no máximo d
. Naturalmente se (P,Q)∈E(D)
, então (Q,P)∈E(D)
.

Vamos agora munir D=D(X,d)
 de uma função custo definida sobre os arcos de D
: consideramos c:E(D)→R
 tal que c(P,Q)=∥P−Q∥
 para todo arco (P,Q)
 em D
, isto é, o custo de (P,Q)
 é a distância euclidiana entre P
 e Q
. O grafo dirigido com pesos nos arcos em que temos interesse é (D,c)
. Em particular, dados dois vértices S
 e T
 em D
, queremos encontrar um caminho mais curto de S
 a T
 em (D,c)
.

Ajuste potencial. Seja (D,c)
 um grafo dirigido com custo nos arcos. O exercício teórico T11 Ajuste potencial sugere um procedimento para fazer a busca de um caminho mínimo de s
 a t
 em (D,c)
 levar em conta o vértice de destino t
, orientando a busca "na direção de t
". A sugestão é (a) procurar uma função potencial adequada h
, (b) definir uma nova função custo c′
 nos arcos de D
, e (c) executar o algoritmo de Dijkstra sobre (D,c′)
. O caminho mínimo encontrado no grafo ajustado (D,c′)
 é um caminho mínimo em (D,c)
, o grafo com a função custo original.

O exercício T11 diz que a função potencial ideal h
 é tal que h(v)=dD,c(v,t)
 para todo v∈V(D)
, isto é, h(v)
 deve ser a distância de v
 a t
 em (D,c)
. Naturalmente, não temos tal função à disposição. Assim, precisamos pensar em alternativas.

Função potencial para grafos geométricos. Suponha que queremos encontrar um caminho mínimo de S
 a T
 em um grafo geométrico (D,c)
. Para todo P∈V(D)
, tomamos

h(P)=∥P−T∥.

A função h:V(D)→R
 definida dessa forma é uma função potencial válida para (D,c)
: vale que

c′(P,Q)=c(P,Q)−h(P)+h(Q)≥0

para todo arco (P,Q)
 em D
. Verifique essa afirmação: você verá que é um exercício simples envolvendo a desigualdade triangular.

Sua tarefa. Neste exercício, você deve implementar o procedimento sugerido no T11 no caso específico de grafos geométricos usando a função potencial h
 definida acima.

A imagem dijk_plain.png, dada abaixo, ilustra o resultado de uma execução padrão do algoritmo de Dijkstra em um grafo geométrico (isto é, sem ajuste potencial). Esta imagem foi obtida executando-se

$ java-algs4 RandomPoints 10000 77 | java-algs4 SPGeoGraph .018 .1 .1 .9 .9
Number of vertices in shortest-paths tree: 9736
Length of path: 1.1833153451
$

Com ajuste potencial, o resultado é como na imagem dijk_potential.png, também dada abaixo. Note que a ACM é consideravelmente menor nessa busca ajustada. Esta imagem foi gerada executando-se

$ java-algs4 RandomPoints 10000 77 | java-algs4 SPGeoGraphA .018 .1 .1 .9 .9
Number of vertices in shortest-paths tree: 1512
Length of path: 1.1833153451
$

SPGeoGraphA.java é dado abaixo. São também dadas abaixo mais duas imagens geradas por SPGeoGraphA.java (dijk_5000_77_0181282.png e dijk_10000_77_015.png).

Implementação. O programa SPGeoGraph.java executa o algoritmo de Dijkstra no grafo subjacente ao grafo geométrico dado:

    EdgeWeightedDigraph ewdg = gg.ewDigraph();
    DijkstraSPX sp = new DijkstraSPX(ewdg, s, t);

(DijkstraSPX.java é uma variante simples de DijkstraSP.java, que termina a busca quando a distância ao vértice t
 fica determinada.)

Considere agora o programa SPGeoGraphA.java dado abaixo. Neste programa, a linha

    DijkstraSPX sp = new DijkstraSPX(gg, s, t);

executa, entre outras coisas, o algoritmo de Dijkstra no grafo geométrico gg, levando em conta o ajuste potencial descrito acima.

Neste exercício, você deve adicionar à classe DijkstraSPX.java um construtor de assinatura

    public DijkstraSPX(GeoGraph gg, int s, int t)

para que SPGeoGraphA.java funcione.

Tempos de execução.  O fato que a ACM produzida com o ajuste potencial tende a ser menor que a ACM produzida pelo algoritmo básico afeta o tempo de execução. Os programas SPGeoGraphT.java e SPGeoGraphAT.java são semelhantes aos programas SPGeoGraph.java e SPGeoGraphA.java, mas têm como saída adicional tempos de execução e não produzem imagens. Veja os seguintes exemplos:

$ java-algs4 RandomPoints 1000000 77 > tmp; java-algs4 SPGeoGraphT .003 .1 .1 .9 .9 tmp; rm tmp
Time to read data and produce geometric graph: 9.642
Time to build DijkstraSPX object: 2.183
Number of vertices in shortest-paths tree: 979172
Length of path: 1.1382615799
$ java-algs4 RandomPoints 1000000 77 > tmp; java-algs4 SPGeoGraphAT .003 .1 .1 .9 .9 tmp; rm tmp
Time to read data and produce geometric graph: 9.473
Time to build DijkstraSPX object: 1.352
Number of vertices in shortest-paths tree: 68683
Length of path: 1.1382615799
$ java-algs4 RandomPoints 2000000 77 > tmp; java-algs4 SPGeoGraphT .0015 .1 .1 .9 .9 tmp; rm tmp
Time to read data and produce geometric graph: 13.437
Time to build DijkstraSPX object: 3.267
Number of vertices in shortest-paths tree: 1958461
Length of path: 1.1586337363
$ java-algs4 RandomPoints 2000000 77 > tmp; java-algs4 SPGeoGraphAT .0015 .1 .1 .9 .9 tmp; rm tmp
Time to read data and produce geometric graph: 13.83
Time to build DijkstraSPX object: 1.519
Number of vertices in shortest-paths tree: 299558
Length of path: 1.1586337363
$

Espera-se que, com sua classe DijkstraSPX.java, ocorram reduções de tempo como as observadas acima.

Outros exemplos de grafos geométricos.  Uma vez que seu DijkstraSPX.java esteja pronto, você pode achar interessante executar SPGeoGraph.java e SPGeoGraphA.java das seguintes formas (é interessante ver como a ACM evolui):

$ java-algs4 Grid 63 | java-algs4 SPGeoGraph 0.03 .1 .1 .9 .9
Number of vertices in shortest-paths tree: 3866
Length of path: 1.1490485194
$ java-algs4 Grid 63 | java-algs4 SPGeoGraphA 0.03 .1 .1 .9 .9
Number of vertices in shortest-paths tree: 53
Length of path: 1.1490485194
$ java-algs4 Grid 63 | java-algs4 SPGeoGraph 0.03 .3 .1 .9 .9
Number of vertices in shortest-paths tree: 3896
Length of path: 1.0649113896
$ java-algs4 Grid 63 | java-algs4 SPGeoGraphA 0.03 .3 .1 .9 .9
Number of vertices in shortest-paths tree: 707
Length of path: 1.0649113896
$ java-algs4 Grid 63 | java-algs4 SPGeoGraphA 0.03 .45 .1 .55 .9
Number of vertices in shortest-paths tree: 422
Length of path: 0.8513325215
$ java-algs4 Grid 63 | java-algs4 SPGeoGraphA 0.03 .35 .1 .65 .9
Number of vertices in shortest-paths tree: 801
Length of path: 0.9419417382
$
