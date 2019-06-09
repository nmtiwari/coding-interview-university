package dataStructure.trees.bst;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Objects;

class Node<T extends Comparable<T>> {
    @Nullable
    private Node<T> left;
    @Nullable
    private Node<T> right;
    @NotNull
    private T val;

    public Node(@NotNull T val) {
        this.val = Objects.requireNonNull(val);
    }

    public void preOrderTraversal(Action<T> action) {
        action.action(this);

        if (left != null) {
            left.preOrderTraversal(action);
        }
        if (right != null) {
            right.preOrderTraversal(action);
        }
    }

    public void inOrderTraversal(Action<T> action) {
        if (left != null) {
            left.inOrderTraversal(action);
        }
        action.action(this);
        if (right != null) {
            right.inOrderTraversal(action);
        }
    }

    public void postOrderTraversal(Action<T> action) {
        if (left != null) {
            left.postOrderTraversal(action);
        }
        if (right != null) {
            right.postOrderTraversal(action);
        }
        action.action(this);
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    public void insertNode(@NotNull T value) {
        if (val.compareTo(Objects.requireNonNull(value)) >= 0) {
            if (left == null) {
                left = new Node<>(value);
            } else {
                left.insertNode(value);
            }
        } else if (right == null) {
            right = new Node<>(value);
        } else {
            right.insertNode(value);
        }
    }

    public Node<T> searchInSubTree(@NotNull T value) {
        Objects.requireNonNull(value);
        int compared = val.compareTo(value);
        if (compared == 0) {
            return this;
        } else if (compared < 0 && right != null) {
            return right.searchInSubTree(value);
        } else if (compared > 0 && left != null) {
            return left.searchInSubTree(value);
        }
        return null;
    }

    public interface Action<T extends Comparable<T>> {
        void action(Node<T> node);
    }
}
