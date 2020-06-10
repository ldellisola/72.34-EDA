package BasicGraph;

import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyMatrixGraph<V> implements GraphService<V> {

    Matrix2D<Integer> matrix = new Matrix2D<Integer>();
    List<V> nodes = new ArrayList<V>();
    int edgesCount = 0;

    public boolean isEmpty() {
        return matrix.cantElements() == 0;
    }

    public boolean addVertex(V element) {

        if(nodes.contains(element)){
            return false;
        }

        nodes.add(element);
        matrix.append(0);
        return false;
    }

    public boolean removeVertex(V element) {
        int index = nodes.indexOf(element);
        if(index != -1) {
            matrix.remove(index);
            nodes.remove(index);
            return true;
        }
        return false;
    }

    public boolean addEdge(V element1, V element2) {
        int rowIndex = nodes.indexOf(element1);
        if(rowIndex == -1){
            nodes.add(element1);
            rowIndex = nodes.size() - 1;
            matrix.append(0);
        }

        int colIndex = nodes.indexOf(element2);
        if(colIndex == -1){
            nodes.add(element2);
            colIndex = nodes.size() - 1;
            matrix.append(0);
        }
        try {
            int assignedValue = rowIndex == colIndex ? 2 : 1;

            if (matrix.get(rowIndex,colIndex) == assignedValue){
                return false;
            }

            matrix.set(rowIndex, colIndex, assignedValue);
            matrix.set(colIndex, rowIndex, assignedValue);
            edgesCount++;
            return true;
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }

    }

    public boolean removeEdge(V element1, V element2) {
        int rowIndex = nodes.indexOf(element1);
        int colIndex = nodes.indexOf(element2);

        if(rowIndex >= 0  && colIndex >= 0){

            if (matrix.get(rowIndex,colIndex) == 0){
                return false;
            }

            matrix.set(rowIndex,colIndex,0);
            matrix.set(colIndex,rowIndex,0);
            edgesCount--;
            return true;
        }
        return false;
    }

    public void dump() {
        System.out.println("Vertex:");
        nodes.forEach(v -> System.out.println(v.toString()));
        matrix.dump();
    }

    public int vertexesSize() {
        return nodes.size();
    }

    public int edgesSize() {
        return edgesCount;
    }

    public int degree(V node) {
        int index = nodes.indexOf(node);

        if(index == -1){
            return -1;
        }
        int value = 0;
        for(int i = 0; i < nodes.size(); i++){
            value += matrix.get(index,i) >0 ? 1 : 0;
        }

        return value;
    }

    public Iterable<V> getVertices() {
        return nodes;
    }

    private List<V> generateBFSList(V startNode){
        List<Integer> visitedNodes = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        int startIndex = nodes.indexOf(startNode);

        if(startIndex < 0)
            return visitedNodes.stream().map(integer -> nodes.get(integer)).collect(Collectors.toList());

        queue.add(startIndex);

        while (!queue.isEmpty()){
            int selectedIndex = queue.poll();

            // Si ya visite el nodo, lo salto
            if(visitedNodes.contains(selectedIndex))
                continue;

            // Visito nodo
            visitedNodes.add(selectedIndex);

            // Cargo hijos
            for (int i = 0; i < nodes.size(); i++) {
                if(matrix.get(selectedIndex,i) == 1)
                    queue.add(i);
            }
        }
        return visitedNodes.stream().map(integer -> nodes.get(integer)).collect(Collectors.toList());
    }

    public void printBFS(V startNode) {
        List<V> list = generateBFSList(startNode);

        list.forEach(v -> System.out.printf(" -> %s ",v));
        System.out.println();
    }

    private List<V> generateDFSList(V startNode){
        List<Integer> visitedNodes = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int startIndex = nodes.indexOf(startNode);

        if(startIndex < 0)
            return visitedNodes.stream().map(integer -> nodes.get(integer)).collect(Collectors.toList());

        stack.push(startIndex);

        while (!stack.empty()){
            int selectedIndex = stack.pop();

            // Si ya visite el nodo, lo salto
            if(visitedNodes.contains(selectedIndex))
                continue;

            // Visito nodo
            visitedNodes.add(selectedIndex);

            // Cargo hijos
            for (int i = 0; i < nodes.size(); i++) {
                if(matrix.get(selectedIndex,i) == 1)
                    stack.push(i);
            }
        }

        return visitedNodes.stream().map(integer -> nodes.get(integer)).collect(Collectors.toList());
    }

    public void printDFS(V startNode) {
        List<V> list = generateDFSList(startNode);

        list.forEach(v -> System.out.printf(" -> %s ",v));
        System.out.println();
    }

    public Iterable<V> getBFS(V startNode) {
        return generateBFSList(startNode);
    }

    public Iterable<V> getDFS(V startNode) {
        return generateDFSList(startNode);
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

        for(V el : getAdjacentNodes(startNode)){
            if(!visited.contains(el)){
                path.addVertex(el);
                dfs(el,endNode,visited,path,paths);
                path.removeLastVertex();
                visited.remove(el);
            }
        }
    }

    private List<V> getAdjacentNodes(V node){
        List<V> adjacentNodes = new ArrayList<>();
        int nodeIndex = nodes.indexOf(node);

        for(int i = 0 ; i < matrix.cantElements() ; i++){
            if(matrix.get(nodeIndex, i) != 0){
                adjacentNodes.add(nodes.get(i));
            }
        }

        return adjacentNodes;
    }
}
