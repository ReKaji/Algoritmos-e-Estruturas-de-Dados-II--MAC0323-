# Exercício:

Temos uma coleção de N
 parafuso e N
 porcas de tamanhos variados. Sabemos que existe um pareamento entre os N
 parafusos e as N
 porcas de forma que os elementos pareados encaixam-se perfeitamente. Isto é, se um parafuso p
 está pareado com uma porca p′
, então p
 e p′
 encaixam-se perfeitamente. Podemos chamar tal pareamento de "pareamento completo" ou "pareamento perfeito", pois todos os N
 parafusos e todas as N
 porcas estão pareados.

Para encontrar um tal pareamento, a única operação que podemos fazer é escolher um parafuso p
 e uma porca p′
 e experimentar. Há três possíveis resultados para esse experimento: (1) p
 e p′
 encaixam-se perfeitamente, (2) p
 é muito grande para p′
, (3) p
 é muito pequeno para p′
. Queremos encontrar um pareamento perfeito fazendo "poucos" experimentos como esse. É fácil ver que podemos resolver esse problema executando no máximo N2
 experimentos.  (Esta solução quadrática está implementada no arquivo MatchSlow.java fornecido abaixo, que pode ser usado conjuntamente com GenerateAndMatchSlow.java.)

Queremos algo bem melhor: sua tarefa é encontrar e implementar um algoritmo linearítmico para este problema (seu algoritmo pode ser probabilístico). Detalhes sobre a implementação seguem abaixo.

Classes já implementadas.  São dadas abaixo as classes

GenerateAndMatch.java
NutsAndBolts.java
pieces/Nut.java
pieces/Bolt.java
Por um certo motivo técnico, Nut.java e Bolt.java estão organizados em um "package".

Objetos do tipo Bolt representam parafusos; objetos do tipo Nut representam porcas. Um objeto do tipo NutsAndBolts representa uma coleção de N
 parafusos e N
 porcas, para algum N
.

Dado N
, o programa GenerateAndMatch.java gera uma coleção de aleatória de N
 parafusos e N
 porcas que admite um pareamento perfeito, e encontra um pareamento perfeito entre eles, chamando o método match() de Match.java, que não é dado abaixo.

O usuário de GenerateAndMatch.java também fornece um outro parâmetro t
 ao programa: esse parâmetro especifica quantos tipos diferentes de porcas e parafusos existem no universo dos parafusos e porcas do qual GenerateAndMatch.java escolhe os N
 pares aleatórios de parafusos e porcas.

Por exemplo, se t=1
, então só há um tipo de parafuso e um tipo de porca, de forma que qualquer pareamento entre os N
 parafusos e as N
 porcas é um pareamento perfeito. Se t=2
, há somente dois tipos de parafusos e porcas, e assim por diante.

Sua tarefa.  O programa GenerateAndMatch.java depende da classe Match.java, que está faltando. Neste exercício, você deve escrever Match.java para que GenerateAndMatch.java funcione. Para entender em detalhe o que a função match() de Match.java deve fazer, você precisa ler as classes fornecidas. A seguir, descrevemos sucintamente como deve ser o comportamento de match(). A assinatura de match() deve ser

public static int[] match(NutsAndBolts nab)

Ao receber o objeto nab, sua função deve devolver um pareamento perfeito para aquele objeto.  Tal pareamento é dado por um vetor de inteiros, digamos p
, de N
 elementos, que é uma permutação de 0,…,N−1
.  O vetor p
 deve ter a seguinte propriedade: o método

public int check(int[] p)

de NutsAndBolts.java deve devolver −1
 quando executamos nab.check(p).

Sua função match() deve produzir o pareamento p
 executando chamadas aos métodos compareTo() em Bolt.java e Nut.java para comparar as porcas e os parafusos.

Exemplo.  Suponha que as porcas e parafusos no objeto nab têm as dimensões dadas abaixo:

 0  1  2  3  4  5  6  7  8  9
93 82  5 80 96 73 47 77 99  0
99 77 73 80 47  0 96 82  5 93

Assim, a porca 0
 tem dimensão 93
, a porca 1
 tem dimensão 82
, etc. O parafuso 0
 tem dimensão 99
, o parafuso 1
 tem dimensão 77
, etc. Para esta coleção de 10
 parafusos e 10
 porcas, seu match() deve devolver o vetor

p = { 9, 7, 8, 3, 6, 2, 4, 1, 0, 5 }

De fato, a porca 0
 e o parafuso p[0]=9
 têm a mesma dimensão, a porca 1
 e o parafuso p[1]=7
 têm a mesma dimensão, etc, de forma que nab.check(p) devolverá −1
.

Desempenho.  Seu match() deve ter eficiência grosso modo comparável com o que você pode ver nos exemplos de execução em experiments.txt.
