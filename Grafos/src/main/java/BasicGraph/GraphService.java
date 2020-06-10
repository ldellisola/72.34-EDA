package BasicGraph;

import java.util.ArrayList;
import java.util.List;

// T implements equals, hashCode
public interface GraphService<V> {

    // true si no tienen nodos
    boolean isEmpty();

    // Agrega un nodo al grafo, con la información dada y devuelve true
    // Si está repetido se ignora, y devuelve false
    boolean addVertex(V element);

    // Elimina un nodo al grafo, con la información dada
    // y todos los ejes incidentes y devuelve true. Si no existia devuelve false
    boolean removeVertex(V element);

    // if the edge is repeated is ignored (it is not a multigraph)
    // If necessary, element1 and element2 are nodes added automatically

    // Agrega un eje al grafo
    // Si alguno (o ambos) de los nodos indicados no existiera,
    // se agrega automáticamente y devuelve true. Si ya existía devuelve false
    boolean addEdge(V element1, V element2);

    // Borra un eje del grafo y devuelve true. Quizas deja los vértices aislados.
    // si no existia devuelve false.
    boolean removeEdge(V element1, V element2);


    // Imprime la lista de Nodos y la lista de Ejes en la salida estándard
    void dump();

    int vertexesSize();

    int edgesSize();

    // devuelve el grado del nodo
    int degree(V node);

    Iterable<V> getVertices();


    // recorrido BFS sin iterador
    void printBFS(V startNode);

    // recorrido DFS sin iterador
    void printDFS(V startNode);

    // devuelve iterados con los nodos alcanzables a partir de V, recorriendo en BFS
    Iterable<V> getBFS(V startNode);

    // devuelve iterados con los nodos alcanzables a partir de V, recorriendo en DFS
    Iterable<V> getDFS(V startNode);

    void printAllPathsBetween(V startNode, V endNode);

    IPath<V> getSortestPath(V startNode, V endNode);

    List<IPath<V>> getAllPaths(V startNode, V endNode);


}