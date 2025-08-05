# Exercício:

Este exercício é uma versão expandida do Web Exercise 2.5.15, Sorting a linked list, de

https://algs4.cs.princeton.edu/25applications/

A classe Bag.java. O tipo de dado Bag é útil para armazenar coleções de objetos de um dado tipo, com elementos repetidos permitidos.

https://algs4.cs.princeton.edu/13stacks/

Ao examinar a implementação em Bag.java, você verá que os objetos são simplesmente armazenados em uma lista ligada.

Neste exercício, você deve escrever uma variante de Bag.java, chamada BagVariant.java, que contém o método adicional

public void sort()

O método sort() deve ser tal que, se executamos sort() e imediatamente iteramos sobre os objetos no BagVariant, os objetos são percorridos em ordem não-decrescente.

Observação. Se executamos sort() seguido de um ou mais add() e então iteramos, a iteração não tem a obrigação de varrer os elementos do BagVariant em ordem não-decrescente. Exige-se que a varredura seja em ordem não-decrescente somente no caso em que iteramos depois de executar sort(), sem nenhum add() entre essas operações.

Para implementar sort(), você deve, obrigatoriamente, rearranjar as células da lista ligada que armazenam os objetos em BagVariant em ordem não-decrescente.

Os objetos em um BagVariant devem ser ordenáveis. Naturalmente, para sort() fazer sentido, os objetos em um BagVariant devem vir de um universo totalmente ordenado. Para impor tal restrição sobre o tipo dos objetos em um BagVariant, o título da classe deve ser

public class BagVariant<Item extends Comparable<Item>> implements Iterable<Item>

O título da classe Bag.java é

public class Bag<Item> implements Iterable<Item>

Assim, "Item" deve ser substituído por "Item extends Comparable<Item>" no parâmetro de tipo. Faça o mesmo na definição da classe encaixada estática Node.

O cliente BagVariantClient.java ilustra (minimamente) um uso de BagVariant.

BagSort.java. Note que, com BagVariant.java, você pode implementar uma rotina de ordenação genérica:

public static void sort(Comparable[] a) {</span><br /><span style="font-family: 'courier new', courier, monospace;">    BagVariant b = new BagVariant();</span><br /><span style="font-family: 'courier new', courier, monospace;">    for (Comparable x : a) b.add(x); // foreach works for arrays</span><br /><span style="font-family: 'courier new', courier, monospace;">    b.sort();</span><br /><span style="font-family: 'courier new', courier, monospace;">    int i = 0;</span><br /><span style="font-family: 'courier new', courier, monospace;">    for (Object x : b) a[i++] = (Comparable) x;</span><br /><span style="font-family: 'courier new', courier, monospace;">}

O programa BagSort.java abaixo contém o código acima. Não deixe de ver o unit test de BagSort.java também.

Comparação de rotinas de ordenação. O programa SortCompare.java abaixo será usado para comparar algumas implementações de algoritmos de ordenação. Em particular, para comparar a velocidade de BagSort.sort() e, digamos, a implementação básica do mergesort (Merge.sort()), podemos executar SortCompare.java da seguinte forma:

$ java-algs4 SortCompare BagSort Merge 100000 200
For 100000 random Doubles
BagSort is 1.0 times faster than Merge
[3.21 vs 3.32]
$ java-algs4 SortCompare BagSort Merge 200000 200
For 200000 random Doubles
BagSort is 1.0 times faster than Merge
[6.98 vs 6.82]
$ java-algs4 SortCompare BagSort Merge 400000 200
For 400000 random Doubles
BagSort is 1.4 times faster than Merge
[20.01 vs 27.37]
$

Vemos acima que BagSort.sort() é comparável a Merge.sort() em termos de velocidade. Note que, claramente, este resultado depende fundamentalmente do algoritmo implementado em BagVariant.sort().

Importante. Neste exercício, espera-se que sua implementação de BagVariant.sort() seja tal que seu BagSort.java dê resultados parecidos com os resultados acima. Seu BagSort.sort() (que depende essencialmente de seu BagVariant.sort()) não deve ser substancialmente mais lento que Merge.sort().

Restrições de tempo e memória.  Expandindo a observação anterior, note que o Web Exercise 2.5.15, Sorting a linked list, impõe as seguintes duas restrições: a complexidade de sua solução (BagVariant.sort()) deve ser O(NlogN)
 e você não deve usar mais que O(logN)
 de memória adicional.  Em particular, seu algoritmo não deve fazer uma cópia dos objetos a serem ordenados.  Por exemplo, uma abordagem seria (a) copiar os dados para um array, (b) ordenar o array e (c) montar uma lista ligada a partir do array.  Esta solução viola a condição de O(1)
 de memória adicional para os objetos a serem ordenados e assim não é válida.
