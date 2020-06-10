package BasicGraph;

import java.util.ArrayList;
import java.util.List;

public class Path<V> implements IPath<V> {
    private List<V> list = new ArrayList<>();

    public Path() {
    }

    private Path(List<V> list){
        this.list.addAll(list);
    }

    @Override
    public void addVertex(V element) {
        list.add(element);
    }

    @Override
    public void removeLastVertex() {
        list.remove(list.size()-1);
    }

    @Override
    public Boolean hasCycle() {
        for (int i = 0; i < list.size() ; i++) {
            for(int j = 0 ; j < list.size() ;j++){
                if(j != j && list.get(i) == list.get(j)){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int getDistance() {
        return list.size();
    }

    @Override
    public List<V> getVertices() {
        return list;
    }

    @Override
    public IPath<V> clone() {
        return new Path<V>(list);
    }
}
