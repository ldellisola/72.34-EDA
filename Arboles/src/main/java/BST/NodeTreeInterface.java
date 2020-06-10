package BST;

public interface NodeTreeInterface<T extends Comparable<? super T>> {

    T getData();

    NodeTreeInterface<T> getLeft();

    NodeTreeInterface<T> getRight();

    void setLeft(NodeTreeInterface<T> node);

    void setRight(NodeTreeInterface<T> node);

    void setData(T data);
}