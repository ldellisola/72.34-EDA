package BST;

import java.util.function.Function;

public class Tree<T extends Comparable<? super T>>implements BSTreeInterface<T>{

    NodeTreeInterface<T> Root;

    @Override
    public void insert(T myData) {
        if(Root == null)
            Root = new Node(myData);
        else
            Insert(getRoot(),myData);
    }

    private void Insert(NodeTreeInterface<T> node , T data){

        if(node.getLeft() == null && node.getData().compareTo(data) >= 0) {
            node.setLeft(new Node(data));
            return;
        }

        if(node.getRight() == null && node.getData().compareTo(data) < 0) {
            node.setRight(new Node(data));
            return;
        }

        if (node.getData().compareTo(data) >= 0){
            Insert(node.getLeft(),data);
        }else{
            Insert(node.getRight(),data);
        }


    }

    @Override
    public void delete(T myData) {

        Root = delete(getRoot(),myData);

    }

    private NodeTreeInterface<T> delete(NodeTreeInterface<T> root, T data){
        if (root == null)
            return null;

        /* Otherwise, recur down the tree */
        if ( data.compareTo(root.getData()) < 0)
            root.setLeft(delete(root.getLeft(), data));
        else if (data.compareTo(root.getData()) > 0)
            root.setRight(delete(root.getRight(), data));

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.setData(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(delete(root.getRight(), root.getData()));
        }

        return root;
    }

    private T minValue(NodeTreeInterface<T> root)
    {
        T minv = root.getData();
        while (root.getLeft() != null)
        {
            minv = root.getLeft().getData();
            root = root.getLeft();
        }
        return minv;
    }



    @Override
    public boolean contains(T myData) {
        return FindNode(getRoot(),myData) != null;
    }

    private NodeTreeInterface<T> FindNode(NodeTreeInterface<T> root,T myData){
        NodeTreeInterface<T> node = null;

        if(root != null) {
            int comp = root.getData().compareTo(myData);

            if (comp == 0) {
                return root;
            } else if (comp > 0) {
                node = FindNode(root.getLeft(), myData);

            } else {
                node = FindNode(root.getRight(), myData);
            }
        }

        return node;
    }

    private Function<NodeTreeInterface<T>,Integer> preOrderRecursive = (t)->{
        if(t == null)
            return 0;

        System.out.printf(" %d ", t.getData());

        this.preOrderRecursive.apply(t.getLeft());
        this.preOrderRecursive.apply(t.getRight());
        return 0;
    };

    private Function<NodeTreeInterface<T>,Integer> inOrderRecursive = (t)->{
        if(t == null)
            return 0;

        this.inOrderRecursive.apply(t.getLeft());
        System.out.printf(" %d ", t.getData());
        this.inOrderRecursive.apply(t.getRight());
        return 0;
    };

    private Function<NodeTreeInterface<T>,Integer> postOrderRecursive = (t)->{
        if(t == null)
            return 0;
        this.postOrderRecursive.apply(t.getLeft());
        this.postOrderRecursive.apply(t.getRight());
        System.out.printf(" %d ", t.getData());
        return 0;
    };
    @Override
    public void preOrder() {

        preOrderRecursive.apply(getRoot());
        System.out.println("");
    }

    @Override
    public void postOrder() {
        postOrderRecursive.apply(getRoot());
        System.out.println("");
    }

    @Override
    public void inOrder() {
        inOrderRecursive.apply(getRoot());
        System.out.println("");
    }

    @Override
    public NodeTreeInterface<T> getRoot() {
        return Root;
    }

    public int getHeight() {
        return FindHeight(getRoot());
    }

    private int FindHeight(NodeTreeInterface<T> node){
        if(node == null)
            return 0;

        int left = 0, right = 0;
        if(node.getLeft() != null){
            left = FindHeight(node.getLeft());
        }

        if(node.getRight() != null){
            right = FindHeight(node.getRight());
        }

        return 1 + (left > right ? left : right);
    }


    private class Node implements NodeTreeInterface<T>{
        private T data;
        private NodeTreeInterface<T> Left;
        private NodeTreeInterface<T> Right;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return Left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return Right;
        }

        @Override
        public void setLeft(NodeTreeInterface<T> node) {
            this.Left = node;
        }

        @Override
        public void setRight(NodeTreeInterface<T> node) {
            this.Right = node;

        }

        @Override
        public void setData(T data) {
            this.data = data;
        }
    }
}
