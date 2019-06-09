package dataStructure.trees.bst;

import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.Stack;

class BST<T extends Comparable<T>> {
    private Node<T> root;

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insertNode(6);
        tree.insertNode(3);
        tree.insertNode(9);
        tree.insertNode(7);
        tree.insertNode(4);
        tree.insertNode(5);
        tree.insertNode(11);
        tree.insertNode(1);
        tree.insertNode(2);
        tree.testTraversals();
    }

    public void insertNode(@NotNull T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root.insertNode(value);
        }
    }

    public void inOrderTraversalR(Node.Action<T> action) {
        if (root != null) {
            root.inOrderTraversal(action);
        }
    }

    public void preOrderTraversalR(Node.Action<T> action) {
        if (root != null) {
            root.preOrderTraversal(action);
        }
    }

    public void postOrderTraversalR(Node.Action<T> action) {
        if (root != null) {
            root.postOrderTraversal(action);
        }
    }

    public void preOrderTraversalI(Node.Action<T> action) {
        if (root == null) {
            return;
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            action.action(node);
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public void inOrderTraversalI(Node.Action<T> action) {
        if (root == null) {
            return;
        }
        Stack<Node<T>> stack = new Stack<>();
        boolean shouldTraverseLeft = true;
        Node<T> temp = root;

        while (temp != null || !stack.isEmpty()) {
            if (temp == null) {
                temp = stack.pop();
                shouldTraverseLeft = false;
            }

            if (shouldTraverseLeft && temp.getLeft() != null) {
                stack.push(temp);
                temp = temp.getLeft();
                continue;
            }

            action.action(temp);
            shouldTraverseLeft = true;

            if (temp.getRight() != null) {
                stack.push(temp.getRight());
                temp = temp.getRight().getLeft();
            } else {
                temp = null;
            }
        }
    }

    public Node<T> searchInSubTree(@NotNull T value) {
        Objects.requireNonNull(value);
        return root.searchInSubTree(value);
    }

    private void testTraversals() {
        System.out.print("In Order: ");
        inOrderTraversalR(new Node.Action<T>() {
            @Override
            public void action(Node<T> node) {
                System.out.print(node.getVal() + " ");
            }
        });

        System.out.print("\n In Order Iterative: ");
        inOrderTraversalI(new Node.Action<T>() {
            @Override
            public void action(Node<T> node) {
                System.out.print(node.getVal() + " ");
            }
        });

        System.out.print("\nPre Order: ");
        preOrderTraversalR(new Node.Action<T>() {
            @Override
            public void action(Node<T> node) {
                System.out.print(node.getVal() + " ");
            }
        });

        System.out.print("\nPre Order Iterative: ");
        preOrderTraversalI(new Node.Action<T>() {
            @Override
            public void action(Node<T> node) {
                System.out.print(node.getVal() + " ");
            }
        });


        System.out.print("\nPost Order: ");
        postOrderTraversalR(new Node.Action<T>() {
            @Override
            public void action(Node<T> node) {
                System.out.print(node.getVal() + " ");
            }
        });
    }
}
