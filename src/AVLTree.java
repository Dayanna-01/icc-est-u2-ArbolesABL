public class AVLTree {
    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private class Node {
        int value;
        Node left, right;
        int height;

        Node(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    public void insert(int value) {
        System.out.println("Nodo a insertar: " + value);
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }

        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }

        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }
}
