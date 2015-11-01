package ua.vladgolubev;

public class Main {

    public static void main(String[] args) {
        System.out.println("Завдання 1: ");
        BinaryTree task1BinaryTree = new BinaryTree();
        task1BinaryTree.addNode(50.2);
        task1BinaryTree.addNode(25.32);
        task1BinaryTree.addNode(15.7);
        task1BinaryTree.addNode(30.4);
        task1BinaryTree.addNode(75.45);
        task1BinaryTree.addNode(85.35);

        //1.1 Обчислення середнього арифметичного всіх вершин дерева
        double averageTreeNodeValue = getAverageTreeNodeValue(task1BinaryTree);
        //1.2 Додає в дерево вершину зі значенням, обчисленим в попередній функції
        task1BinaryTree.addNode(averageTreeNodeValue);

        //1.2 Виведення вмісту дерева на екран
        task1BinaryTree.printTree();

        System.out.println("\nЗавдання 2: ");

        BinaryTree task2BinaryTree = new BinaryTree();
        //2.0 Додавання номерів в базу викрадених автомобілів
        for (int carNumber : new int[]{456, 124, 786, 435, 788, 444, 565, 127, 458, 322, 411, 531, 400, 546, 410}) {
            task2BinaryTree.addNode(carNumber);
        }

        //2.0 Виведення можливих номерів машин, що починаються на 4 у порядку зростання
        task2BinaryTree.inOrderTraverseTree()
                .stream()
                .filter(carNumberNode -> String.valueOf(carNumberNode.value).charAt(0) == '4')
                .map(carNumberNode -> (int) carNumberNode.value)
                .forEach(System.out::println);

    }

    private static double getAverageTreeNodeValue(BinaryTree binaryTree) {
        double sumOfTreeNodeValues = binaryTree.preOrderTraverseTree()
                .stream()
                .map(node -> node.value)
                .reduce(0.0, (num, memo) -> num + memo);
        return sumOfTreeNodeValues / binaryTree.size();
    }
}
