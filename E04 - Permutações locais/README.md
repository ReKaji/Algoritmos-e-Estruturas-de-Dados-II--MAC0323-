# Exercício:

Neste exercício, vamos dizer que uma permutação é k
-local se cada elemento está a no máximo k
 posições de onde ele estaria se a permutação fosse ordenada em ordem não-decrescente. Por simplicidade, usaremos também o termo k
-permutação para nos referirmos a uma permutação k
-local. Vamos, também por simplicidade, considerar permutações dos inteiros 0,…,N−1
.

Exemplo. A permutação

0 1 5 2 4 3 8 6 9 7

é 3
-local. De fato, escrevendo junto com a permutação identidade,

0 1 2 3 4 5 6 7 8 9
0 1 5 2 4 3 8 6 9 7

vemos facilmente que cada elemento está a no máximo 3 posições de sua posição "correta" (o elemento que está mais deslocado é o 5, que está deslocado 3 posições para a esquerda).

Objetivo.  Neste exercício, você deve elaborar um algoritmo que ordena k
-permutações em tempo O(Nlogk)
, usando espaço adicional O(k)
. Mais precisamente, você deve implementar a função sortLocal() em SortLocal.java, fornecido abaixo. Sua função deve ter assinatura

public static void sortLocal(Comparable[] v, int k)

É responsabilidade do usuário de sortLocal() fornecer no argumento k
 um valor tal que a permutação no argumento v
 seja uma k
-permutação.

Sua função SortLocal.sortLocal() deve ter complexidade de pior caso O(Nlogk)
 e deve usar espaço adicional O(k)
. Uma vez implementado, você poderá comparar sua solução com vários outros algoritmos, usando SortingTimes.java, também fornecido abaixo.

Exemplos.  Com sua implementação de sortLocal() em SortLocal.java, espera-se o seguinte tipo de comportamento de SortingTimes.java (leia o programa SotingTimes.java para entender a saída dele):

$ java-algs4 SortingTimes InsertionX 1000000 1000 50 314
InsertionX: 19.62s
Local: 4.96s
Local / InsertionX ratio: 0.25
$ java-algs4 SortingTimes MergeX 1000000 1000 50 314
MergeX: 8.86s
Local: 4.93s
Local / MergeX ratio: 0.56
$ java-algs4 SortingTimes Heap 1000000 1000 50 314
Heap: 6.97s
Local: 4.98s
Local / Heap ratio: 0.71
$ java-algs4 SortingTimes QuickX 1000000 1000 50 314
QuickX: 10.78s
Local: 4.98s
Local / QuickX ratio: 0.46
$ java-algs4 SortingTimes QuickX 2000000 1000 50 314
QuickX: 25.81s
Local: 10.05s
Local / QuickX ratio: 0.39
$

Mais exemplos de execução de SortingTimes.java são dados no arquivo experiments.txt.

Restrições.  Neste exercício, você não pode usar Arrays.sort(). Por outro lado, use, obrigatoriamente, a estrutura de heap em sua solução. Há uma solução desse exercício que não usa heaps, mas este é um exercício para você se familiarizar com a implementação de heaps.

Desempenho.  Como já dito, sua função SortLocal.sortLocal() deve ter complexidade de pior caso O(Nlogk)
 e deve usar espaço adicional O(k)
. Para valores "pequenos" de k
 em relação a N
, o tempo de execução de seu SortLocal.sortLocal() deve ser claramente menor que o tempo de execução de MergeX.sort(), Heap.sort() e QuickX.sort(), como você pode ver nos exemplos fornecidos.
