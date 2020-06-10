package BasicGraph;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyListGraph<V> implements GraphService<V> {

    AdjacencyList<V> AdjacencyList = new AdjacencyList<>();

    @Override
    public boolean isEmpty() {
        return AdjacencyList.GetVertices().isEmpty();
    }

    @Override
    public boolean addVertex(V element) {

        if(AdjacencyList.ContainsVertex(element))
            return false;

        AdjacencyList.AddVertex(element, new ArrayList<>());
        return false;
    }

    @Override
    public boolean removeVertex(V element) {
        if(AdjacencyList.ContainsVertex(element)){
            AdjacencyList.RemoveVertex(element);

            AdjacencyList.GetVertices().forEach(v -> AdjacencyList.RemoveAdjacentVertex(v,element));

            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(V element1, V element2) {

        // Making sure both elements are in the List. If they are already then nothing will happen.
        addVertex(element1);
        addVertex(element2);

        AdjacencyList.AddAdjacentVertex(element1,element2);
        AdjacencyList.AddAdjacentVertex(element2,element1);

        return true;
    }

    @Override
    public boolean removeEdge(V element1, V element2) {

        if(AdjacencyList.AreVerticesAdjacent(element1,element2)){
            AdjacencyList.RemoveAdjacentVertex(element1, element2);
            AdjacencyList.RemoveAdjacentVertex(element2, element1);
            return true;
        }

        return false;
    }

    @Override
    public void dump() {
        AdjacencyList.Dump();
    }

    @Override
    public int vertexesSize() {
        return AdjacencyList.GetVertices().size();
    }

    @Override
    public int edgesSize() {
        // Because this is not a directed graph and whenever I'm adding an edge I'm adding to elements to my
        // list, I can get away with divinding the ammount of pairs vertex, adjacent by 2 to get the real
        // number
        return AdjacencyList.GetAllEdges().size() / 2;
    }

    @Override
    public int degree(V node) {
        if(AdjacencyList.ContainsVertex(node))
            return AdjacencyList.GetAdjacentVertices(node).size();
        else
            return -1;
    }

    @Override
    public Iterable<V> getVertices() {
        return AdjacencyList.GetVertices();
    }

    @Override
    public void printBFS(V startNode) {
        Iterable<V> list = getBFS(startNode);

        list.forEach(v -> System.out.printf(" -> %s ",v));
        System.out.println();
    }


    @Override
    public void printDFS(V startNode) {
        Iterable<V> list = getDFS(startNode);

        list.forEach(v -> System.out.printf(" -> %s ",v));
        System.out.println();
    }

    @Override
    public Iterable<V> getBFS(V startNode) {
        List<V> visitedNodes = new ArrayList<>();
        Queue<V> queue = new ArrayDeque<>();

        if(!AdjacencyList.ContainsVertex(startNode))
            return new ArrayList<>();


        queue.add(startNode);

        while (!queue.isEmpty()){
            V selectedNode = queue.poll();

            // Si ya visite el nodo, lo salto
            if(visitedNodes.contains(selectedNode))
                continue;

            // Visito nodo
            visitedNodes.add(selectedNode);

            // Cargo hijos
            AdjacencyList.GetAdjacentVertices(selectedNode).forEach(v -> queue.add(v));
        }

        return visitedNodes;
    }

    @Override
    public Iterable<V> getDFS(V startNode) {
        List<V> visitedNodes = new ArrayList<>();
        Stack<V> stack = new Stack<>();

        if(!AdjacencyList.ContainsVertex(startNode))
            return new ArrayList<>();


        stack.add(startNode);

        while (!stack.isEmpty()){
            V selectedNode = stack.pop();

            // Si ya visite el nodo, lo salto
            if(visitedNodes.contains(selectedNode))
                continue;

            // Visito nodo
            visitedNodes.add(selectedNode);

            // Cargo hijos
            AdjacencyList.GetAdjacentVertices(selectedNode).forEach(v -> stack.push(v));
        }

        return visitedNodes;
    }

    @Override
    public void printAllPathsBetween(V startNode, V endNode) {

        getAllPaths(startNode,endNode)
                .forEach(viPath -> {
                    System.out.printf("Path from %s to %s\n", startNode, endNode);
                    viPath.getVertices().forEach(v -> {
                        System.out.printf(" -> %s", v);
                    });
                    System.out.println();
                });

    }

    @Override
    public IPath<V> getSortestPath(V startNode, V endNode) {
        return getAllPaths(startNode,endNode).stream().min(IPath::compareTo).get();
    }

    @Override
    public List<IPath<V>> getAllPaths(V startNode, V endNode) {

        List<IPath<V>> paths = new ArrayList<>();
        List<V> visited = new ArrayList<>();

        Path<V> path = new Path<>();
        path.addVertex(startNode);

        dfs(startNode, endNode,visited, path, paths);

        return paths;
    }

    private void dfs(V startNode, V endNode, List<V> visited, IPath<V> path, List<IPath<V>> paths){
        visited.add(startNode);

        if(endNode.equals(startNode)){
            paths.add(path.clone());
            return;
        }

        for(V el : AdjacencyList.GetAdjacentVertices(startNode)){
            if(!visited.contains(el)){
                path.addVertex(el);
                dfs(el,endNode,visited,path,paths);
                path.removeLastVertex();
                visited.remove(el);
            }
        }

    }


    private class AdjacencyList<V>{
        private HashMap<V, List<V>> List;

        public AdjacencyList(){
            List = new HashMap<>();
        }

        public void AddVertex(V vertex, List<V> adjacent ) {

            if(!List.containsKey(vertex)){
                List.put(vertex,adjacent);
            }

        }

        public void RemoveVertex(V vertex){
            if(List.containsKey(vertex)){
                List.remove(vertex);
            }
        }

        public void AddAdjacentVertex(V vertex, V adjacent)  {
            if(!List.containsKey(vertex)){
                List<V> l = new ArrayList<>();
                l.add(adjacent);
                AddVertex(vertex,l);
                return;
            }

            List.get(vertex).add(adjacent);
        }

        public void RemoveAdjacentVertex(V vertex, V adjacent){
            if(List.containsKey(vertex)){
                List.get(vertex).remove(adjacent);
            }
        }

        public void Dump(){
            StringBuilder bld = new StringBuilder();

            List.forEach((v, vs) -> {

                bld.append("Node: ").append(v).append(" adjacent to:\n");

                vs.forEach(v1 -> bld.append("\t -> ").append(v1).append("\n"));
            });

            System.out.println(bld.toString());
        }

        public List<V> GetVertices(){
            return List.keySet().stream().collect(Collectors.toList());
        }

        public List<V> GetAdjacentVertices(V vertex){
            return List.getOrDefault(vertex,new ArrayList<>());
        }

        public Boolean AreVerticesAdjacent(V v1, V v2){

            if(List.containsKey(v1) && List.containsKey(v2)){
                return List.get(v1).contains(v2) && List.get(v2).contains(v1);
            }
            return false;
        }

        public Boolean ContainsVertex(V vertex){
            return List.containsKey(vertex);
        }

        public List<Tuple<V,V>> GetAllEdges(){

            List<Tuple<V,V>> tuples = new ArrayList<>();

            Map.Entry<V,List<V>>[] entries = new Map.Entry[2000];

            entries = List.entrySet().toArray(entries);

            for(int i = 0 ; entries[i] != null ;i++){

                List<V> values = entries[i].getValue();

                for(int j = 0 ; j < values.size() ; j++){
                    tuples.add(new Tuple<>(entries[j].getKey(),values.get(j)));
                }
            }

            return tuples;
        }


        private class Tuple<A,B>{
            private A firstValue;
            private B secondValue;

            public Tuple(A value1,B value2){
                firstValue = value1;
                secondValue = value2;
            }
        }
    }
}

















