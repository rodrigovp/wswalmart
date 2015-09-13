Aplicação criada na linguagem java 1.8 para o sistema de entrega do Walmart.

Utiliza o MongoDB como base de dados.

Inicialmente (sabendo que se usaria o algoritmo de Dijkstra para o cálculo do menor caminho) busquei uma implementação que suportasse a modelagem que fiz sem que para isso houvessem muitas alterações. Optou-se pela implementação presente em:

https://github.com/mburst/dijkstras-algorithm/blob/master/Dijkstras.java, com adaptações.

Em seguida, parti para testes de integração do sistema, já utilizando o Spring REST e Spring Data, com o MongoDB.
Por último, escrevi as classes de domínio e seus respectivos testes, além das adaptações no código encontrado que implementa o algoritmo de Dijkstra.

Para rodar a aplicação: mvn spring-boot:run
É necessário também que o MongoDB esteja instalado na máquina de teste, bem como o database test.

OBS: para que o teste do controller funcione corretamente, é necessário que a aplicação esteja no ar. Os demais testes rodam sem essa necessidade.

É possível também rodar a aplicação como um executável, bastando executar os comandos abaixo:
mvn clean package -DskipTests
java -jar target/wswalmart-1.0.0.jar

Caso queira rodar a aplicação num servidor, basta incluir as linhas abaixo no pom.xml:
	<packaging>war</packaging>

	<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>

Para carregar as malhas, basta escrever um arquivo no formato pedido no requisito e realizar um post para o sistema para o endereço http://localhost:8080/carregarMalhaLogistica O nome do arquivo será o nome do mapa.

Para realizar as consultas, deve-se utilizar uma URL semelhante a abaixo:
http://localhost:8080/buscarRotaOtimizada/mapa/mapaComum/origem/A/destino/B/autonomia/5/combustivel/3.09

O sistema retornará um JSON semelhante ao abaixo:
{"retornoRest":"SUCESSO","rota":"[A, B]","custo":6}

Foram também criados tratamentos para:

 * arquivo inválido (que não esteja no formato exibido no requisito);
 * arquivo vazio;
 * busca por rota em mapa inexistente;


