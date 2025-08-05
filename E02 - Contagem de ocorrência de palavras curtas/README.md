# Exercício:

Considere o alfabeto Σ={a,b,c,d,e}
 e considere o conjunto Σ∗
 das palavras
 constituídas de letras
 de Σ
, isto é, as sequências finitas que podemos formar com os elementos de Σ
. Assim, Σ∗={ε,a,b,…,e,aa,ab,…,ee,aaa,…}
, onde ε
 é a palavra vazia.

Considere a palavra p=abc∈Σ∗
. Dado s∈Σ∗
, queremos saber quantas ocorrências
 de p
 há em s
, ou seja, quantas subpalavras
 (isto é, subsequências) de s
 são iguais a p
: queremos saber quantas triplas ordenadas (i,j,k)
 com 0≤i<j<k<N
 existem tais que sisjsk=abc
, onde s=s0…sN−1
.

Exemplo. Se s=aaeaddececbdccd
, então p
 ocorre 6
 vezes em s
.

O função countABCPlain() em CountABC.java (dado abaixo) resolve o problema em que estamos interessados de forma elementar. Esta função tem complexidade de tempo cúbica, e portanto não é adequada para palavras s
 longas.

Neste exercício, você deve implementar em CountABC.java uma função de assinatura

public static long countABC(String s)

que determina o número de ocorrências de p
 na palavra dada s
 de forma muito mais eficiente: seu algoritmo deve ter complexidade de tempo de pior caso O(N(logN)2)
, onde N
 é o comprimento de s
. Ademais, exige-se que seu algoritmo não use mais que O(logN)
 de espaço adicional.

Observação. Há um algoritmo de complexidade de tempo O(N)
 se podemos usar espaço adicional O(N)
. (Dica: basta usar programação dinâmica.) Dada a restrição de espaço especificada acima, este algoritmo não vale como solução deste problema.

Exemplos de execução. Com seu algoritmo O(N(logN)2)
 implementado, o programa E02.java (dado abaixo) terá o seguinte tipo de comportamento:

$ java-algs4 E02 10000 323
Fast counting:
8: 0 [time: 0.01]
16: 13 [time: 0.0]
32: 26 [time: 0.0]
64: 220 [time: 0.0]
128: 3120 [time: 0.001]
256: 23295 [time: 0.0]
512: 240813 [time: 0.001]
1024: 1201797 [time: 0.001]
2048: 11211785 [time: 0.001]
4096: 82169488 [time: 0.004]
8192: 702031556 [time: 0.006]
Plain counting:
8: 0 [time: 0.001]
16: 13 [time: 0.0]
32: 26 [time: 0.0]
64: 220 [time: 0.001]
128: 3120 [time: 0.004]
256: 23295 [time: 0.003]
512: 240813 [time: 0.027]
1024: 1201797 [time: 0.179]
2048: 11211785 [time: 1.433]
4096: 82169488 [time: 11.58]
8192: 702031556 [time: 95.953]
$ java-algs4 E02 1000000 323
Fast counting:
8: 0 [time: 0.01]
16: 13 [time: 0.0]
32: 26 [time: 0.0]
64: 220 [time: 0.0]
128: 3120 [time: 0.0]
256: 23295 [time: 0.0]
512: 240813 [time: 0.001]
1024: 1201797 [time: 0.001]
2048: 11211785 [time: 0.001]
4096: 82169488 [time: 0.002]
8192: 702031556 [time: 0.003]
16384: 5781493878 [time: 0.01]
32768: 48993065246 [time: 0.009]
65536: 387371390935 [time: 0.011]
131072: 3008076727686 [time: 0.024]
262144: 23785360688174 [time: 0.053]
524288: 192405414255629 [time: 0.112]
Plain counting:
8: 0 [time: 0.001]
16: 13 [time: 0.0]
32: 26 [time: 0.0]
64: 220 [time: 0.001]
128: 3120 [time: 0.004]
256: 23295 [time: 0.008]
512: 240813 [time: 0.022]
1024: 1201797 [time: 0.178]
2048: 11211785 [time: 1.438]
[abortado]
$

O ponto é que countABC() "escala bem" (scales well
) com o comprimento de s
, enquanto que countABCPlain() não.
