
public class NoArvore {
	Integer		valor; //alterado para permitir o valor igual a null (estava sendo adicionado o valor zero na raiz da arvore.)
	NoArvore	direita;
	NoArvore	esquerda;
	
	//contrutor alterado para inicializar com null
	public NoArvore() {
		this.valor = null;
		this.direita = null;
		this.esquerda = null;
	}

	public NoArvore busca(NoArvore no, int valorprocurado) {
		if(no == null)
			return null;
		
		if(no.valor > valorprocurado)
			return busca(no.esquerda, valorprocurado);
		else if(no.valor < valorprocurado)
			return busca(no.direita, valorprocurado);
		else
			return no;
	}
	
	public NoArvore insere(NoArvore no, int novovalor) {
		if(no == null) {
			no = new NoArvore();
			no.valor = novovalor;
			no.esquerda = no.direita = null;
		}
		else if(novovalor < no.valor)
			no.esquerda = insere(no.esquerda, novovalor);
		else if(novovalor > no.valor)
			no.direita = insere(no.direita, novovalor);
	
		return no;
	}

	public void imprime(NoArvore raiz) {
		if (raiz != null) {
			imprime(raiz.esquerda);
			System.out.println(raiz.valor);
			imprime(raiz.direita);
		}
	}
	
	public NoArvore remove(NoArvore raiz, int valoraremover) {
		// faz a busca pelo valor a ser removido
		if (raiz == null)
			return null;
		else if (raiz.valor > valoraremover)
			raiz.esquerda = remove(raiz.esquerda, valoraremover);
		else if (raiz.valor < valoraremover)
			raiz.direita = remove(raiz.direita, valoraremover);
		else {	// passar por aqui significa que achou o nó com o
				// valora remover procurado e agora vai removê-lo
				// segundo as 4 situações a seguir:
			// Não ter filhos (esquerda e direita == null)
			if (raiz.esquerda == null && raiz.direita == null) {
				raiz = null;
			}
			// Ter filho apenas à direita (esquerda == null)
			else if (raiz. esquerda == null) {
				raiz = raiz.direita;
			}
			
			// Ter filho apenas à esquerda (direita == null)
			else if (raiz.direita == null) {
				raiz = raiz.esquerda;
			}
			// Ter dois filhos (esquerda e direita != null)
			else {
				
				//Pelo Antecessor
				/* 
				 * NoArvore sub_esquerda = raiz.esquerda; // encontrar o nó com maior valor na
				 * subárvore esquerda while (sub_esquerda.direita != null) { sub_esquerda =
				 * sub_esquerda.direita; } // aqui a sub_esquerda.valor tem o maior valor
				 * raiz.valor = sub_esquerda.valor; sub_esquerda.valor = valoraremover;
				 * raiz.esquerda = remove(raiz.esquerda, valoraremover);
				 */
				
				//Pelo Sucessor
				NoArvore sub_direita = raiz.direita;
				// encontrar o nó com menor valor na subárvore direita
				while (sub_direita.esquerda != null) {
					sub_direita = sub_direita.esquerda;
				}
				// aqui a sub_esquerda.valor tem o maior valor
				raiz.valor = sub_direita.valor;
				sub_direita.valor = valoraremover;
				raiz.direita = remove(raiz.direita, valoraremover);
			}
		}
		return raiz;		
	}
}



