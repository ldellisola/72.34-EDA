package BasicGraph;

import BasicGraph.GraphFactory.GraphType;

public class Main {


    static GraphService<Character> createModel1()
    {
        GraphService<Character> g = GraphFactory.create(GraphType.SPARSE);
        g.addEdge('C', 'G');
        g.addEdge('E', 'B');
        g.addEdge('A', 'F');
        g.addEdge('F', 'E');
        g.addEdge('B', 'F');
        g.addEdge('A', 'B');
        g.addVertex('T');
        g.addVertex('U');
        g.addEdge('T', 'U');
        g.addEdge('G', 'F');
        g.addEdge('G', 'U');

        return g;
    }

    public static void main2(String[] args) {
        GraphService<Character> g = createModel1();

        System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
        System.out.println(String.format("#edges: %d", g.edgesSize() ));

        // degree de cada nodo
        for(Character aV: g.getVertices()) {
            System.out.println(
                    String.format("vertex %s has degree %d", aV, g.degree(aV)));
        }

        g.dump();

        g.printBFS('E');

        System.out.println("Recorrido BFS desde E con iterador");
        for (Character aVertex : g.getBFS('E')) {
            System.out.println(aVertex);
        }


    }



    static GraphService<Character> createModel2()
    {
        GraphService<Character> g = GraphFactory.create(GraphType.SPARSE);
        g.addEdge('B', 'A');
        g.addEdge('A', 'C');
        g.addEdge('B', 'E');
        g.addEdge('C', 'E');
        g.addEdge('H', 'E');
        g.addEdge('E', 'F');
        g.addEdge('F', 'H');
        g.addEdge('H', 'G');
        g.addEdge('C', 'H');
        g.addEdge('D', 'T');

        g.addVertex('X');

        g.addEdge('B', 'X');
        g.addEdge('F', 'X');

        g.addEdge('X', 'X');
        g.addEdge('X', 'X');

        g.addVertex('M');		// isolated
        g.addVertex('F');


        return g;
    }

    static GraphService<Character> createModel3()
    {
        GraphService<Character> g = GraphFactory.create(GraphType.DENSE);
        g.addEdge('X', 'X');
        g.addEdge('X', 'B');
        g.addEdge('B', 'A');
        g.addEdge('A', 'C');
        g.addEdge('C', 'H');
        g.addEdge('H', 'E');
        g.addEdge('H', 'F');
        g.addEdge('H', 'G');
        g.addEdge('E', 'F');
        g.addEdge('D', 'T');

        g.addVertex('M');		// isolated


        return g;
    }

    public static void main(String[] args) {

        GraphService<Character> g = createModel3();

        System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
        System.out.println(String.format("#edges: %d", g.edgesSize() ));

        for(Character aV: g.getVertices()) {
            System.out.println(
                    String.format("vertex %s has degree %d", aV, g.degree(aV)));
        }

        g.dump();

//        g.removeEdge('E', 'H');
//        g.removeVertex('X');
//        g.removeVertex('M');
//        g.removeVertex('E');

        g.removeEdge('X',  'X');

        System.out.println(String.format("#vertices: %d", g.vertexesSize() ));
        System.out.println(String.format("#edges: %d", g.edgesSize() ));

        for(Character aV: g.getVertices()) {
            System.out.println(
                    String.format("vertex %s has degree %d", aV, g.degree(aV)));
        }
        g.dump();


        System.out.println("Recorrido BFS desde W");
        g.printBFS('W');

        System.out.println("Recorrido DFS desde W");
        g.printDFS('W');


        System.out.println("Recorrido BFS desde T");
        g.printBFS('T');


        System.out.println("Recorrido BFS desde C");
        g.printBFS('C');

        System.out.println("Recorrido DFS desde C");
        g.printDFS('C');

        System.out.println("Recorrido BFS desde C con iterador");
        for (Character aVertex : g.getBFS('C')) {
            System.out.println(aVertex);
        }

        g.printAllPathsBetween('E','G');
    }
}