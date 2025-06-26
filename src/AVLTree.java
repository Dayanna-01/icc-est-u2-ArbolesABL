public class AVLTree {
    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    public void insert(int value) {
        System.out.println("valor a insertar: " + value);
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            newNode.setHeight(1);
            System.out.println("nodo insertado: " + newNode.getValue());
            System.out.println("balance al insertar: " + getBalance(newNode));
            return newNode;
        }

        if (value < node.getValue()) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRec(node.getRight(), value));
        } else {
            return node;
        }

        int altura = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(altura);

        int balance = getBalance(node);
        System.out.println("nodo actual: " + node.getValue() + " - altura: " + node.getHeight() + " - balance: " + balance);

        if (balance == -2) {
            System.out.println("cambio rotacion izquierda");
        } else if (balance == 2) {
            System.out.println("cambio rotacion derecha");
        }

        return node;
    }
}