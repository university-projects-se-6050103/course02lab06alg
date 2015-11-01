package ua.vladgolubev;

public class Main {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addNode(50.2);
        binaryTree.addNode(25.32);
        binaryTree.addNode(15.7);
        binaryTree.addNode(30.4);
        binaryTree.addNode(75.45);
        binaryTree.addNode(85.35);

        binaryTree.printTree();
    }
}
