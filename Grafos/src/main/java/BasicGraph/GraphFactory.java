package BasicGraph;


public class GraphFactory<V>  {

    public enum GraphType { SPARSE, DENSE};

    private GraphFactory() 	{
    }

    public static <V> GraphService<V> create(GraphType graphType) {
        switch (graphType)
        {
		    case SPARSE: return new AdjacencyListGraph<V>();
            case DENSE: return new AdjacencyMatrixGraph<V>();
        }

        // default if null
        return new AdjacencyMatrixGraph<V>();
    }
}