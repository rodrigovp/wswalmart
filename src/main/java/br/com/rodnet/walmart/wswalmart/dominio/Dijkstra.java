package br.com.rodnet.walmart.wswalmart.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	//Variável que recebe os vértices pertencentes ao menor caminho
	Local verticeCaminho = new Local ();

	//Variável que guarda o vértice que está sendo visitado 
	Local localAtual = new Local ();

	//Variável que marca o vizinho do vértice atualmente visitado 
	Local verticeVizinho = new Local ();

	//Corte de vértices que já tiveram suas distâncias marcadas e cujos vizinhos não foram visitados
	List<Local> fronteira = new ArrayList<Local>();


	//Algoritmo de Dijkstra
	public List<Local> encontrarMenorCaminhoDijkstra(Mapa mapa, Local umLocal, Local outroLocal){
		List<Local> ret = new ArrayList<Local>();
		
		//No início, todos os vértices do grafo não foram visitados
		int numeroDeLocaisNaoVisitados = mapa.quantidadeDeLocais();

		//O primeiro nó a ser visitado é o da origem do caminho
		localAtual = umLocal;
		//Adiciona o primeiro nó no corte
		fronteira.add(localAtual);
		//Adiciona a origem na lista do menor caminho
		ret.add(localAtual);

		//Colocando a distancias iniciais
		//for (int i=0;i< grafo.getVertices().size();i++){
		for(Local local : mapa){

			//Nó atual tem distância zero, e todos os outros, 9999(infinita)
			if (local.ehChamadoDe(localAtual.getDescricao())){
				local.setDistancia(0);
			} else{
				local.setDistancia(9999);
			}
		}

		//O algoritmo continua até que todos os vértices sejam visitados
		while (numeroDeLocaisNaoVisitados != 0){

			//Toma-se sempre o vértice com menor distância, que é o primeiro da lista do corte
			localAtual = this.fronteira.get(0);
			/*Para cada vizinho (cada aresta), calcula-se a sua possível distância,
                    somando a distância do vértice atual com a da aresta correspondente.
                    Se essa distância for menor que a distância do vizinho, esta é atualizada.
			 */ 
			//for (int i=0; i<atual.getArestas().size();i++){
			for(Trecho trecho : localAtual){

				verticeVizinho = trecho.getDestino();                               
				if (!verticeVizinho.verificarVisita()){

					verticeVizinho.setPai(localAtual);

					//Comparando a distância do vizinho com a possível distância
					if (verticeVizinho.getDistancia() > (localAtual.getDistancia() + trecho.getPeso())){

						verticeVizinho.setDistancia(localAtual.getDistancia() + trecho.getPeso());

						/*Se o vizinho é o vértice procurado, e foi feita uma mudança na distância,
						 * a lista com o menor caminho anterior é apagada, pois existe um caminho menor ainda.
						 * Cria-se a nova lista do menor caminho, com os vértices pais, até o vértice origem.
						 * */
						if (verticeVizinho.equals(outroLocal)){
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
			localAtual.visitar();
			numeroDeLocaisNaoVisitados--;
			this.fronteira.remove(localAtual);
			/*Ordena a lista do corte, para que o vértice com menor distância fique na primeira
			 * posição*/

			Collections.sort(fronteira);

		}

		return ret;
	}
}
