# Exercício:

Seja G
 um grafo. Há dois tipos de vértices em G
: aqueles que pertencem a algum circuito e aqueles que não pertencem a nenhum circuito.

O programa Classify.java, dado abaixo, pode ser usado para classificar os vértices de um dado grafo. No exemplo a seguir, o programa InstanceGenerator.java é usado para gerar instâncias de grafos.

$ java-algs4 InstanceGenerator 6 6 8888
6
6
0 5
0 4
1 3
2 4
2 5
3 5

6 vertices, 6 edges
0: 5 4
1: 3
2: 4 5
3: 5 1
4: 2 0
5: 0 3 2

$ java-algs4 InstanceGenerator 6 6 8888 > tmp.txt
$ java-algs4 Classify tmp.txt
Number of vertices in cycles: 4
Number of vertices not in cycles: 2
Ratio (in cycles/total): 0.6666666666666666
$ java-algs4 Classify tmp.txt +
Vertices in cycles: 0 2 4 5
Vertices not in cycles: 1 3
Number of vertices in cycles: 4
Number of vertices not in cycles: 2
Ratio (in cycles/total): 0.6666666666666666
$

Note que o argumento adicional dado ao programa Classify.java faz com que ele imprima a classificação dos vértices explicitamente (não apenas o número de vértices de cada tipo).

O programa Classify.java é baseado na classe Cycle.java. Em particular, para cada vértice i
 do grafo de interesse G
, ele cria um objeto da classe Cycle, chamado finder. Uma vez criado esse objeto, finder.hasCycle() vale true se e só se G
 contém um circuito que contém o vértice i
. Ademais, se finder.hasCycle() vale true, então finder.cycle() é um iterador que varre os vértices de um circuito que contém o vértice i
 (começando com i
 e terminando com i
). Por exemplo, no grafo em tmp.txt acima, se fizermos

  Cycle finder = new Cycle(G, 0);
  for (int v : finder.cycle())
      StdOut.print(v + " ");
  StdOut.println();

obtemos

0 4 2 5 0

Sua tarefa.  Neste exercício, você deve acrescentar código à classe

https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Cycle.html
https://algs4.cs.princeton.edu/41graph/Cycle.java.html

para que Classify.java funcione, isto é, você deve adicionar um construtor de assinatura

public Cycle(Graph G, int s)

com comportamento adequado. Você pode adicionar outras funções a Cycle.java para que o construtor acima funcione como pretendido. Não mude o nome da classe.

Observação.  Você pode supor que sua versão de Cycle.java só será usada com grafos simples, isto é, sem arestas paralelas e sem laços.

Exemplos de execução

$ java-algs4 InstanceGenerator 100 120 88888888 > tmp.txt; java-algs4 Classify tmp.txt +
Vertices in cycles: 0 1 2 6 7 8 9 10 13 14 15 16 17 18 20 21 22 24 25 26 28 29 31 32 36 37 40 41 42 43 47 48 49 51 53 54 55 56 58 59 61 62 63 64 69 70 71 72 73 74 75 76 77 79 81 83 84 85 87 88 89 90 93 94 95 96 97 98 99
Vertices not in cycles: 3 4 5 11 12 19 23 27 30 33 34 35 38 39 44 45 46 50 52 57 60 65 66 67 68 78 80 82 86 91 92
Number of vertices in cycles: 69
Number of vertices not in cycles: 31
Ratio (in cycles/total): 0.69
$ java-algs4 InstanceGenerator 10000 11000 88888888 > tmp.txt; time -p java-algs4 Classify tmp.txt
Number of vertices in cycles: 5016
Number of vertices not in cycles: 4984
Ratio (in cycles/total): 0.5016
real 1.68
user 1.75
sys 0.03
$ java-algs4 InstanceGenerator 20000 21000 88888888 > tmp.txt; time -p java-algs4 Classify tmp.txt
Number of vertices in cycles: 7668
Number of vertices not in cycles: 12332
Ratio (in cycles/total): 0.3834
real 7.39
user 7.60
sys 0.04
$ java-algs4 InstanceGenerator 40000 41000 88888888 > tmp.txt; time -p java-algs4 Classify tmp.txt
Number of vertices in cycles: 11373
Number of vertices not in cycles: 28627
Ratio (in cycles/total): 0.284325
real 36.42
user 36.66
sys 0.12
$ java-algs4 InstanceGenerator 40000 45000 7777777 > tmp.txt; time -p java-algs4 -Xss4m Classify tmp.txt
Number of vertices in cycles: 21983
Number of vertices not in cycles: 18017
Ratio (in cycles/total): 0.549575
real 27.79
user 28.01
sys 0.15
$ java-algs4 InstanceGenerator 40000 60000 7777777 > tmp.txt; time -p java-algs4 -Xss8m Classify tmp.txt
Number of vertices in cycles: 33707
Number of vertices not in cycles: 6293
Ratio (in cycles/total): 0.842675
real 13.60
user 13.87
sys 0.09
$

Entrega.  Entregue apenas sua classe Cycle.java.

MAC0328 Algoritmos em grafos.  Classify.java executa múltiplas buscas em profundidade. É possível resolver o problema que Classify.java resolve com uma única busca em profundidade (ou melhor, com uma única busca em profundidade por componente conexa do grafo). Essa solução é mais sofisticada e é muitas vezes estudada na disciplina MAC0328 Algoritmos em grafos. O programa FastClassify.java implementa essa solução (compare os tempos de execução):

$ java-algs4 InstanceGenerator 10000 11000 88888888 > tmp.txt; time -p java-algs4 FastClassify tmp.txt
Number of vertices in cycles: 5016
Number of vertices not in cycles: 4984
Ratio (in cycles/total): 0.5016
real 0.13
user 0.17
sys 0.03
$ java-algs4 InstanceGenerator 20000 21000 88888888 > tmp.txt; time -p java-algs4 FastClassify tmp.txt
Number of vertices in cycles: 7668
Number of vertices not in cycles: 12332
Ratio (in cycles/total): 0.3834
real 0.14
user 0.23
sys 0.02
$ java-algs4 InstanceGenerator 40000 41000 88888888 > tmp.txt; time -p java-algs4 FastClassify tmp.txt
Number of vertices in cycles: 11373
Number of vertices not in cycles: 28627
Ratio (in cycles/total): 0.284325
real 0.18
user 0.31
sys 0.02
$ java-algs4 InstanceGenerator 40000 45000 7777777 > tmp.txt; time -p java-algs4 -Xss4m FastClassify tmp.txt
Number of vertices in cycles: 21983
Number of vertices not in cycles: 18017
Ratio (in cycles/total): 0.549575
real 0.20
user 0.34
sys 0.02
$ java-algs4 InstanceGenerator 40000 60000 7777777 > tmp.txt; time -p java-algs4 -Xss8m FastClassify tmp.txt
Number of vertices in cycles: 33707
Number of vertices not in cycles: 6293
Ratio (in cycles/total): 0.842675
real 0.22
user 0.39
sys 0.02
$
