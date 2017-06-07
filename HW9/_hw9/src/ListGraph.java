import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A ListGraph is an extension of the AbstractGraph abstract class
 *  that uses an array of lists to represent the edges.
 *  @author Koffman and Wolfgang
 */
public class ListGraph extends AbstractGraphExtended {

    // Data Field
    /** An array of Lists to contain the edges that
     *  originate with each vertex.
     */
    private List<Edge>[] edges;

    /** Construct a graph with the specified number of
     *  vertices and directionality.
     *  @param numV The number of vertices
     *  @param directed The directionality flag
     */
    public ListGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new List[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList<Edge>();
        }
    }

    /** Determine whether an edge exists.
     *  @param source The source vertex
     *  @param dest The destination vertex
     *  @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source].contains(new Edge(source, dest));
    }

    /** Insert a new edge into the graph.
     *  @param edge The new edge
     */
    @Override
    public void insert(Edge edge) {
  //     System.err.println("_____" + edge.getSource() + " " + edge.getDest());
        edges[edge.getSource()].add(edge);
        if (!isDirected()) {
            edges[edge.getDest()].add(new Edge(edge.getDest(),
                    edge.getSource(),
                    edge.getWeight()));
        }
      // else
      //      edges[edge.getDest()].add(new Edge(edge.getSource(),edge.getDest(),           edge.getWeight()));

    }

    /** Return an iterator to the edges connected
     *  to a given vertex.
     *  @param source The source vertex
     *  @return An Iterator<Edge> to the vertices
     *          connected to source
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
		// linked list has already implements iterator so just call it
        return edges[source].iterator();
    }

    /** Get the edge between two vertices. If an
     *  edge does not exist, an Edge with a weight
     *  of Double.POSITIVE_INFINITY is returned.
     *  @param source The source
     *  @param dest The destination
     *  @return the edge between these two vertices
     */
    @Override
    public Edge getEdge(int source, int dest) {
        Edge target =
                new Edge(source, dest, Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source]) {
            if (edge.equals(target)) {
                return edge; // Desired edge found, return it.
            }
        }
        // Assert: All edges for source checked.
        return target; // Desired edge not found.
    }

    public static void main(String[] args)
    {
        System.out.println("This section for undirected graph testing");
        ListGraph lg = new ListGraph(10,false);
        System.out.println("List graph is testing...");
       try {
           System.out.println("addRandomEdgesToGraph method is working..");
           lg.addRandomEdgesToGraph(10);
           System.out.println("writeGraphToFile method is working and write all imformations to addRandomForListGraphUnDirected file.");
           lg.writeGraphToFile("addRandomForListGraphUnDirected.txt");
        }catch (Exception e)
        {
            System.err.println(e);
        }
        System.out.println("breadthFirstSearch method is working..Results are below..");
        int [] arr = lg.breadthFirstSearch(1);
        for(int a:arr)
            System.out.println(a);

        //********BU KISIM getConnectedComponentUndirectedGraph  VE isBipartiteUndirectedGraph METODLARI ICIN ********//
        ListGraph lg3 = new ListGraph(10,false);
        System.out.println("getConnectedComponentUndirectedGraph method is working..Results are writed to files.");
        lg3.getConnectedComponentUndirectedGraph();

        ListGraph lg4 = new ListGraph(10,false);
        if(lg4.isBipartiteUndirectedGraph ())
            System.out.println("Graph is bipartite.");
        else
            System.out.println("Graph is not bipartite.");
        //*****************************************************************************************************************//

        System.out.println("\n\n\nThis section for directed graph testing");
        ListGraph lg2 = new ListGraph(10,true);
        System.out.println("List graph is testing...");
        try {
            System.out.println("addRandomEdgesToGraph method is working..");
            lg2.addRandomEdgesToGraph(5);
            System.out.println("writeGraphToFile method is working and write all imformations to addRandomForListGraphDirected file.");
            lg2.writeGraphToFile("addRandomForListGraphDirected.txt");
        }catch (Exception e)
        {
            System.err.println(e);
        }



    }
}
/*</listing>*/
