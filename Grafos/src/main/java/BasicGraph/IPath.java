package BasicGraph;

import java.util.List;

public interface IPath<V> extends Comparable<IPath>{

    void addVertex(V element);

    void removeLastVertex();

    Boolean hasCycle();

    int getDistance();

    List<V> getVertices();

    IPath<V> clone();

    @Override
    default int compareTo(IPath o) {
        return getDistance() - o.getDistance();
    }
}
