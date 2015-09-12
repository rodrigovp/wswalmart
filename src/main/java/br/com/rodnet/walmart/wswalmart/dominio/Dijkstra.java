package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	//Variável que recebe os vértices pertencentes ao menor caminho
	Vertice verticeCaminho = new Vertice ();

	//Variável que guarda o vértice que está sendo visitado 
	Vertice verticeAtual = new Vertice ();

	//Variável que marca o vizinho do vértice atualmente visitado 
	Vertice verticeVizinho = new Vertice ();

	//Corte de vértices que já tiveram suas distâncias marcadas e cujos vizinhos não foram visitados
	List<Vertice> fronteira = new ArrayList<Vertice>();


	//Algoritmo de Dijkstra
	public List<Vertice> encontrarMenorCaminhoDijkstra(Grafo grafo, Vertice v1, Vertice v2){
		List<Vertice> ret = new ArrayList<Vertice>();
		
		//No início, todos os vértices do grafo não foram visitados
		int numeroDeVerticesNaoVisitados = grafo.quantidadeDeVertices();

		//O primeiro nó a ser visitado é o da origem do caminho
		verticeAtual = v1;
		//Adiciona o primeiro nó no corte
		fronteira.add(verticeAtual);
		//Adiciona a origem na lista do menor caminho
		ret.add(verticeAtual);

		//Colocando a distancias iniciais
		//for (int i=0;i< grafo.getVertices().size();i++){
		for(Vertice vertice : grafo){

			//Nó atual tem distância zero, e todos os outros, 9999(infinita)
			if (vertice.ehChamadoDe(verticeAtual.getDescricao())){
				vertice.setDistancia(0);
			} else{
				vertice.setDistancia(9999);
			}
		}

		//O algoritmo continua até que todos os vértices sejam visitados
		while (numeroDeVerticesNaoVisitados != 0){

			//Toma-se sempre o vértice com menor distância, que é o primeiro da lista do corte
			verticeAtual = this.fronteira.get(0);
			/*Para cada vizinho (cada aresta), calcula-se a sua possível distância,
                    somando a distância do vértice atual com a da aresta correspondente.
                    Se essa distância for menor que a distância do vizinho, esta é atualizada.
			 */ 
			//for (int i=0; i<atual.getArestas().size();i++){
			for(Aresta aresta : verticeAtual){

				verticeVizinho = aresta.getDestino();                               
				if (!verticeVizinho.verificarVisita()){

					verticeVizinho.setPai(verticeAtual);

					//Comparando a distância do vizinho com a possível distância
					if (verticeVizinho.getDistancia() > (verticeAtual.getDistancia() + aresta.getPeso())){

						verticeVizinho.setDistancia(verticeAtual.getDistancia() + aresta.getPeso());

						/*Se o vizinho é o vértice procurado, e foi feita uma mudança na distância,
						 * a lista com o menor caminho anterior é apagada, pois existe um caminho menor ainda.
						 * Cria-se a nova lista do menor caminho, com os vértices pais, até o vértice origem.
						 * */
						if (verticeVizinho.equals(v2)){
							ret.clear();
							verticeCaminho = verticeVizinho;
							ret.add(verticeVizinho);
							while (verticeCaminho.getPai() != null){

								ret.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();


							}
							//Ordena a lista do menor caminho, para que ele seja exibido da origem ao destino.
							Collections.sort(ret);

						}
					}
					//Cada vizinho, depois de visitado, é adicionado ao corte
					this.fronteira.add(verticeVizinho);
				}

			}
			//Marca o vértice atual como visitado e o retira do corte
			verticeAtual.visitar();
			numeroDeVerticesNaoVisitados--;
			this.fronteira.remove(verticeAtual);
			/*Ordena a lista do corte, para que o vértice com menor distância fique na primeira
			 * posição*/

			Collections.sort(fronteira);

		}

		return ret;
	}
}
