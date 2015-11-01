package ua.vladgolubev;

import java.util.ArrayList;

public class BinaryTree {
    Node root;

    public void addNode(double value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;

            while (true) {
                parent = focusNode;
                if (value < focusNode.value) {
                    focusNode = focusNode.leftChild;

                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public ArrayList<Node> inOrderTraverseTree() {
        return inOrderTraverseTree(root);
    }

    public ArrayList<Node> inOrderTraverseTree(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null) {
            nodes.addAll(inOrderTraverseTree(focusNode.leftChild));
            nodes.add(focusNode);
            nodes.addAll(inOrderTraverseTree(focusNode.rightChild));
        }
        return nodes;
    }

    public ArrayList<Node> preOrderTraverseTree() {
        return preOrderTraverseTree(root);
    }

    public ArrayList<Node> preOrderTraverseTree(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null) {
            nodes.add(focusNode);
            nodes.addAll(preOrderTraverseTree(focusNode.leftChild));
            nodes.addAll(preOrderTraverseTree(focusNode.rightChild));
        }
        return nodes;
    }

    public ArrayList<Node> postOrderTraverseTree() {
        return postOrderTraverseTree(root);
    }

    public ArrayList<Node> postOrderTraverseTree(Node focusNode) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (focusNode != null) {
            nodes.addAll(postOrderTraverseTree(focusNode.leftChild));
            nodes.addAll(postOrderTraverseTree(focusNode.rightChild));
            nodes.add(focusNode);
        }
        return nodes;
    }

    public Node findNode(double value) {
        Node focusNode = root;

        while (focusNode.value != value) {
            if (value < focusNode.value) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }

            if (focusNode == null) {
                return null;
            }
        }

        return focusNode;
    }

    public boolean remove(double value) {
        Node focusNode = root;
        Node parent = root;
        boolean isItALeftChild = true;

        while (focusNode.value != value) {
            parent = focusNode;

            if (value < focusNode.value) {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;

            } else {
                isItALeftChild = false;

                focusNode = focusNode.rightChild;
            }

            if (focusNode == null) {
                return false;
            }

        }

        if (focusNode.leftChild == null && focusNode.rightChild == null) {
            if (focusNode == root) {
                root = null;
            } else if (isItALeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (focusNode.rightChild == null) {
            if (focusNode == root) {
                root = focusNode.leftChild;
            } else if (isItALeftChild) {
                parent.leftChild = focusNode.leftChild;
            } else {
                parent.rightChild = focusNode.leftChild;
            }
        } else if (focusNode.leftChild == null) {
            if (focusNode == root) {
                root = focusNode.rightChild;
            } else if (isItALeftChild) {
                parent.leftChild = focusNode.rightChild;
            } else {
                parent.rightChild = focusNode.leftChild;
            }
        } else {
            Node replacement = getReplacementNode(focusNode);
            if (focusNode == root) {
                root = replacement;
            } else if (isItALeftChild) {
                parent.leftChild = replacement;
            } else {
                parent.rightChild = replacement;
            }
            replacement.leftChild = focusNode.leftChild;
        }

        return true;
    }

    private Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.rightChild;

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }

        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }

        return replacement;
    }

    public void printTree() {
        printTreePart(root, 0);
    }

    private void printTreePart(Node root, int level) {
        if (root == null) {
            return;
        }
        printTreePart(root.rightChild, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + root.value);
        } else {
            System.out.println(root.value);
        }
        printTreePart(root.leftChild, level + 1);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 +
                    Math.max(height(node.leftChild),
                            height(node.rightChild));
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        int size = 1;
        if (node.leftChild != null) {
            size += size(node.leftChild);
        }
        if (node.rightChild != null) {
            size += size(node.rightChild);
        }
        return size;
    }
}

class Node {
    double value;
    Node leftChild;
    Node rightChild;

    public Node(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Вузол{" +
                "value=" + value +
                (leftChild != null ? ", left=" + leftChild.value : ", left=null") +
                (rightChild != null ? ", right=" + rightChild.value : ", right=null") +
                '}';
    }
}
