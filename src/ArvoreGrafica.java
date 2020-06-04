import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArvoreGrafica {
	public static Node<Integer> geraArvore(NoArvore abb) {
		Node<Integer> root = new Node<Integer>(abb.valor);
		criaNo(abb,root,0);
		return root;
	}
	
	private static void criaNo(NoArvore abb, Node<Integer> no, int direcao) {
		if (abb == null) return;
		
	    if (abb.esquerda != null) {
	    	Node<Integer> x = new Node<Integer>(abb.esquerda.valor);
	    	no.left = x;
	    	criaNo(abb.esquerda,no.left,-1);
	    }
	    if (abb.direita != null) {
	    	Node<Integer> x = new Node<Integer>(abb.direita.valor);
	    	no.right = x;
	    	criaNo(abb.direita,no.right,1);
	    }
	}

	
	public static Node<Integer> test2() {
        Node<Integer> root = new Node<Integer>(2);
        Node<Integer> n11 = new Node<Integer>(7);
        Node<Integer> n12 = new Node<Integer>(5);
        Node<Integer> n21 = new Node<Integer>(2);
        Node<Integer> n22 = new Node<Integer>(6);
        Node<Integer> n23 = new Node<Integer>(9);
        Node<Integer> n31 = new Node<Integer>(5);
        Node<Integer> n32 = new Node<Integer>(8);
        Node<Integer> n33 = new Node<Integer>(4);

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;

        n12.right = n23;
        n22.left = n31;
        n22.right = n32;

        n23.left = n33;

        return root;
    }
	
	public static <T extends Comparable<?>> void imprimirArvore(Node<T> root) {
        int maxLevel = ArvoreGrafica.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || ArvoreGrafica.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        ArvoreGrafica.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            ArvoreGrafica.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
            	ArvoreGrafica.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                	ArvoreGrafica.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                	ArvoreGrafica.printWhitespaces(1);

                ArvoreGrafica.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                	ArvoreGrafica.printWhitespaces(1);

                ArvoreGrafica.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(ArvoreGrafica.maxLevel(node.left), ArvoreGrafica.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}

class Node<T extends Comparable<?>> {
    Node<T> left, right;
    T data;

    public Node(T data) {
        this.data = data;
    }
}
