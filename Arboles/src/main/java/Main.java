import BST.BSTreeInterface;
import BST.Tree;
import BTree.*;
import Graphics.GraphicsTree;

public class Main {
    public static void main(String[] args) {


        BTree<Integer> st = new BTree<Integer>(1);
        st.add(109);
        st.add(74);
        st.add(8);
        st.add(0);
        st.add(16);
        st.add(47);
        st.add(80);
        st.add(75);
        st.add(140);
        st.add(222);
        st.add(211);
//        st.add(149);
        st.add(220);
        st.add(248);
        st.add(242);
        st.add(254);
//        for(int rec= 0; rec < 20; rec++) {
//            st.add(rec);
            System.out.println(st.toString());

            System.out.println("....");

//        }


    }
}
