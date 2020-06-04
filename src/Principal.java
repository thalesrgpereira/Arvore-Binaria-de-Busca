import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Principal {

	public static void main(String[] args) {
		
		NoArvore abb = new NoArvore();
		int numero;
		
		/*
		abb.insere(abb, 6);
		abb.insere(abb, 8);
		abb.insere(abb, 4);
		abb.insere(abb, 5);
		abb.insere(abb, 2);
		abb.insere(abb, 3);
		abb.insere(abb, 1);
		abb.insere(abb, 9);
		abb.insere(abb, 7);
		*/
		
		insereDeArquivo(abb,"valores.txt");
		
		System.out.print("Informe um no a ser localizado: ");
		Scanner ent = new Scanner(System.in);
		try {
			numero = ent.nextInt();
			NoArvore resultado = abb.busca(abb, numero);
			
			if(resultado == null)
				System.out.println("Não encontrei o no "+numero);
			else
				System.out.println("Encontrei o nó: "+resultado.valor);
			
			abb.imprime(abb);
			ArvoreGrafica.imprimirArvore(ArvoreGrafica.geraArvore(abb));
			System.out.print("Informe um no a ser removido: ");
			numero = ent.nextInt();
			abb.remove(abb, numero);
		}
		finally {
			ent.close();
		}
		abb.imprime(abb);
		ArvoreGrafica.imprimirArvore(ArvoreGrafica.geraArvore(abb));
	}
	
	public static void insereDeArquivo( NoArvore abb, String Arquivo) {
	try {
	      File myObj = new File(Arquivo);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String [] texto = data.split(",");
        	for (int i = 0; i < texto.length; i++) {
        		try {
        			if(abb.valor == null) {
        				abb.valor = Integer.valueOf(texto[i].toString().trim());
        			}
        			else {
        			  abb.insere(abb, Integer.valueOf(texto[i].toString().trim()));
        			}
        		}catch (Exception e){
      	        }
        	}
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("Não foi possível ler o arquivo.");
	    }
	}

}
