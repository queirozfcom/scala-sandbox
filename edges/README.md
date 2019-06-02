edges
=====

### Instalação / Testes Execução:

- `$ git clone https://github.com/queirozfcom/edges.git`

- `$ cd edges`

- `$ sbt test` // run tests

- `$ sbt run`  // run main class (takes about 10 seconds, outputs to STDOUT)

### Suposições

- Eu assumi que as arestas indicadas pelo arquivo texto são do tipo "não-direcionadas", pois não havia qualquer indicação
que elas fossem direcionadas.

### Tradeoffs

- Há um tradeoff entre complexidade em tempo de construção VS complexidade em tempo de consulta.

 - Depende se o caso de uso é de leitura frequente e escrita infrequente ou vice-versa.

- Há também um tradeoff entre ler todos os dados para a memória ou ler o arquivo como um stream de linhas, cada
uma representando uma aresta nova.

 - O caso de leitura como stream é mais escalável (o dataset não precisa caber todo em memória) porém mais complexo.

Eu optei por fazer do jeito mais simples, que é o suficiente para o caso em questão (carregar os dados em memória e
deixar a computação para o tempo de consulta).

### Estrutura

- Eu representei cada aresta não direcionada como duas arestas direcionadas (uma em cada direção), para deixar minha
lógica mais simples.

- O comportamento foi quebrado em partes para facilitar a composição e a testagem do mesmo. 

- Os testes incluem casos simples e casos que eu considero limítrofes como arestas que induzem ciclos no grafo.

- A chamada recursiva não é tail-recursive, mas pode ser que dê para otimizá-la assim.

- Certamente há muito espaço para otimização e melhoria da eficiência do código, se for necessário.