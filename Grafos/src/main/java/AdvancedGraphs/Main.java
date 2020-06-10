package AdvancedGraphs;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public class Main {

    public static Graph<String, Integer> CreateGraph_Ej5() {

        Graph<String, Integer> g = new DefaultUndirectedWeightedGraph<>(Integer.class);

        g.addVertex("HNL");
        g.addVertex("LAX");
        g.addVertex("SFO");
        g.addVertex("ORD");
        g.addVertex("DFW");
        g.addVertex("PVD");
        g.addVertex("MIA");
        g.addVertex("LGA");

        g.addEdge("HNL","LAX",2555);

        g.addEdge("SFO","LAX",337);
        g.addEdge("DFW","LAX",1233);
        g.addEdge("ORD","LAX",1743);

        g.addEdge("ORD","SFO",1843);
        g.addEdge("ORD","DFW",802);
        g.addEdge("ORD","PVD",849);

        g.addEdge("MIA","PVD",1205);
        g.addEdge("LGA","PVD",1205);
        g.addEdge("LGA","DFW",1387);
        g.addEdge("LGA","MIA",1099);
        g.addEdge("DFW","MIA",1120);

        return g;
    }

    public static Graph<String, Integer> CreateGraph_Ej6() {

        Graph<String, Integer> g = new DefaultDirectedWeightedGraph<>(Integer.class);

        g.addVertex("HNL");
        g.addVertex("LAX");
        g.addVertex("SFO");
        g.addVertex("ORD");
        g.addVertex("DFW");
        g.addVertex("PVD");
        g.addVertex("MIA");
        g.addVertex("LGA");

        g.addEdge("HNL","LAX",2555);
        g.addEdge("LAX","HNL",2555);

        g.addEdge("SFO","LAX",100);
        g.addEdge("LAX","SFO",337);

        g.addEdge("DFW","LAX",1233);
        g.addEdge("LAX","LAX",1743);

        g.addEdge("ORD","SFO",100);
        g.addEdge("SFO","ORD",1843);
        g.addEdge("ORD","DFW",802);
        g.addEdge("ORD","PVD",849);

        g.addEdge("MIA","PVD",1205);
        g.addEdge("PVD","MIA",1205);

        g.addEdge("LGA","PVD",142);
        g.addEdge("PVD","LGA",999999);

        g.addEdge("DFW","LGA",1387);
        g.addEdge("MIA","LGA",1099);
        g.addEdge("MIA","DFW",1120);

        return g;
    }

    public static Graph<URI,DefaultEdge> CreateGraph_Ej7() throws URISyntaxException {
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        // add the vertices
        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);

        return g;
    }

    public static <V,E> void  printShortestPathFrom(V node, Graph<V,E> g){

        Set<V> vertices = g.vertexSet();

        vertices.forEach(s -> {
            GraphPath<V, E> path =  DijkstraShortestPath.findPathBetween(g,node,s);

            System.out.printf(String.format("Shortest path from %s to %s:",node,s));
            List<V> vertexs = path.getVertexList();
            StringBuilder bld = new StringBuilder();
            bld.append('[');
            for(int i = 1; i < vertexs.size() ; i++){
                bld.append('(')
                        .append(vertexs.get(i-1))
                        .append(":")
                        .append(vertexs.get(i))
                        .append(")");
            }

            bld.append(']');

            System.out.println(bld.toString());
            List<E> edges = path.getEdgeList();
            int total = 0;
            for (int i = 0; i < edges.size() ; i++) {
                total += ((Integer) edges.get(i));
            }
            System.out.println(total);
        });


    }

    public static void main(String[] args) {

        Graph g = CreateGraph_Ej5();

        printShortestPathFrom("",g);

    }
}
