import java.io.*;
import java.util.*;

/**
 * Created by MSD on 19.05.2017.
 */
public abstract class AbstractGraphExtended extends AbstractGraph {
    private ArrayList <Edge> arr;//helper data structure for writeGraphToFile
    private final String fileName = "vertex.txt"; //file name for is bipartite and get connected component methods
    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
        arr = new ArrayList<>();
    }

    /*bu metod datafield kısmındaki fileName adındaki dosyadaki edge bilgilerini okur ve
    bipartie ise true değilse false döndürür

        This method returns true if calling graph instance is bipartite graph, false otherwise. A bipartite
    graph is a graph whose vertices can be divided into two independent sets, U and V such that every
    edge (u, v) either connects a vertex from U to V or a vertex from V to U.
    **/
    public boolean isBipartiteUndirectedGraph (){
        ArrayList<ArrayList> connectedArray = new ArrayList<ArrayList>();
        ArrayList<Edge> helperArraylist = new ArrayList<Edge>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            super.loadEdgesFromFile(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int y = 0; y < super.getNumV(); ++y) {
            int[] intArr = breadthFirstSearch(y);
            if (intArr[0] != -1) {
                ArrayList temp = new ArrayList();
                for (int x : intArr) {
                    if (x != -1)
                        temp.add(x);
                }
                Collections.sort(temp);
                boolean flag = false;

                if (!connectedArray.contains(temp)) {
                    connectedArray.add(temp);
                }

            }
            for (int i = 0; i < connectedArray.size(); ++i) {
                String fileName = "connectedComponentTest2" + (i + 1) + ".txt";
                BufferedWriter bf = null;
                try {
                    bf = new BufferedWriter(new FileWriter(fileName));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                PrintWriter pw = new PrintWriter(bf);
                for (int j = 0; j < connectedArray.get(i).size(); ++j) {
                    if (j == 0) pw.println(connectedArray.get(i).size());
                    int number = (Integer) connectedArray.get(i).get(j);
                    Iterator it = edgeIterator(number);
                    while (it.hasNext()) {
                        Edge tempE = (Edge) it.next();
                        pw.println(tempE.getSource() + " " + tempE.getDest());
                    }
                }
                pw.close();
            }
        }
        File file = null;
        for(int i = 0; i < connectedArray.size(); ++i)
        {
            try {
                file = new File("connectedComponentTest2" + (i + 1) + ".txt");

                scanner = new Scanner(file);
                int number = Integer.parseInt(scanner.nextLine());

                while (scanner.hasNextLine())
                {
                    String[] edge = scanner.nextLine().split(" ");
                    Edge edgeT = new Edge(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]));
                    helperArraylist.add(edgeT);
                }
                scanner.close();
                file.delete();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        int counter = 0;
        for(int i = 0; i < helperArraylist.size(); ++i)
        {
            int number = helperArraylist.get(i).getSource();
            counter = 0;
            for(int j = i; j < helperArraylist.size(); ++j)
            {
                if(number == helperArraylist.get(j).getSource())
                    ++counter;
                if(counter > 1)
                    return true;
            }
        }
        return false;

    }

    /*bu metod datafield kısmındaki fileName adındaki dosyadaki edge bilgilerini okur ve
    connected componentleri bulup farklı dosyalara basar

    This method finds connected components in a graph, creates ListGraph or MatrixGraph instances for each connected
    component and returns them in a Graph array. If this method is called from a ListGraph instance, then connected
    components will be ListGraph instances. Same thing will happen for MatrixGraph instance. The method will only work
    for undirected graphs.
    **/
    public Graph [] getConnectedComponentUndirectedGraph () {
        ArrayList<ArrayList> connectedArray = new ArrayList<ArrayList>();
        Graph[] graphs = null; //connected graflar buraya kaydedilcecek
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName)); //dosyayı scannere iliştirdik

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            super.loadEdgesFromFile(scanner); //okunan edge'leri grafa ekledik
        } catch (IOException e) {
            e.printStackTrace();
        }
        //burada breadth first search metodunu kullanarak conneceted componentleri tespit ediyor
        for (int y = 0; y < super.getNumV(); ++y) {
            int[] intArr = breadthFirstSearch(y);
            if (intArr[0] != -1) {
                ArrayList temp = new ArrayList();
                for (int x : intArr) {
                    if (x != -1)
                        temp.add(x);
                }
                Collections.sort(temp);
                boolean flag = false;

                if (!connectedArray.contains(temp)) {
                    connectedArray.add(temp);
                }

            }
            for (int i = 0; i < connectedArray.size(); ++i) {
                String fileName = "connectedComponent" + (i + 1) + ".txt";
                BufferedWriter bf = null;
                try {
                    bf = new BufferedWriter(new FileWriter(fileName));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                PrintWriter pw = new PrintWriter(bf);
                for (int j = 0; j < connectedArray.get(i).size(); ++j) {
                    if (j == 0) pw.println(connectedArray.get(i).size());
                    int number = (Integer) connectedArray.get(i).get(j);
                    Iterator it = edgeIterator(number);
                    while (it.hasNext()) {
                        Edge tempE = (Edge) it.next();
                        pw.println(tempE.getSource() + " " + tempE.getDest());
                    }
                }
                pw.close();
            }
        }
        String callerClass = new Exception().getStackTrace()[1].getClassName(); //cagıran class türünde graf oluşturulacak
        if (callerClass.equals("ListGraph"))
        {
            graphs = new ListGraph[connectedArray.size()];
        }
        else
        {
            graphs = new MatrixGraph[connectedArray.size()];

        }

        for(int i = 0; i < connectedArray.size(); ++i)
        {
            try {
                scanner = new Scanner(new File("connectedComponent" + (i + 1) + ".txt"));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            int number = Integer.parseInt(scanner.nextLine());

            if (callerClass.equals("ListGraph")) //oluşan graflarınn constructor cagırılması
            {
                graphs[i] = new ListGraph(getNumV(),false);
            }
            else
                graphs[i] = new MatrixGraph(getNumV(),false);

            while (scanner.hasNextLine())
            {
                String[] edge = scanner.nextLine().split(" ");
                Edge edgeT = new Edge(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]));
                graphs[i].insert(edgeT); //grafa elemanları ekleme kısmı
            }
        }

        scanner.close();
        return graphs;

    }
    /*
    This method selects a random number between 0 and edgeLimit then adds that much edges to calling graph.
    The source and destination vertices of edges will be random again. The inserted edges will be directed or
    undirected according to calling graph (If graph is directed, new edges will be directed vice versa).
    The weights of edges will be set to 1. It will return number of inserted edges.
    **/
    public int addRandomEdgesToGraph (int edgeLimit) throws Exception {
        Random rand = new Random();
        if(edgeLimit<2) throw new Exception("Un supported edge limit.");
        int nEdge = rand.nextInt(edgeLimit);
        if(nEdge<2) nEdge = 2; // değer basmama ihitmaline karşı eger tutulan sayı 2 den kucukce otomatik 2 olarak atanır.

        int [] numberArr = new int[nEdge];

        int i = 0;
        while(i < nEdge)
        {
            numberArr[i] = rand.nextInt(edgeLimit);
            ++i;
        }
        i = 0;
        while (i+1 < numberArr.length)
        {
            arr.add(new Edge(numberArr[i],numberArr[i+1]));
            insert(new Edge(numberArr[i],numberArr[i+1]));
            ++i;
        }
        return nEdge;
    }
    /*
    This method performs a breadth first search on calling graph starting from vertex start and returns an array
    which contains the predecessor of each vertex in the breadth-first search tree. It will work for both directed and undirected graphs.
    **/
    public int[] breadthFirstSearch(int start) {
        Queue < Integer > theQueue = new LinkedList < Integer > ();
        // Declare array parent and initialize its elements to –1.
        int[] parent = new int[getNumV()];
        for (int i = 0; i < getNumV(); i++) {
            parent[i] = -1;
        }
        // Declare array identified and
        // initialize its elements to false.
        boolean[] identified = new boolean[getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
        identified[start] = true;
        theQueue.offer(start);
    /* While the queue is not empty */
        int i = 0;

        while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
            int current = theQueue.remove();
      /* Examine each vertex, neighbor, adjacent to current. */
            Iterator < Edge > itr = edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                // If neighbor has not been identified
                if (!identified[neighbor]) {
                    if(i == 0)
                        parent[i++] = start;
                    // Mark it identified.
                    identified[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor)
             into the tree. */
         // System.err.println("qqqq" + neighbor);
                    parent[i] = neighbor;
                    i++;
                }
            }
            // Finished visiting current.
        }
        return parent;
    }

    public void writeGraphToFile (String fileName)
    {
        try {
            BufferedWriter bf= new BufferedWriter(new FileWriter(fileName));
            PrintWriter pw = new PrintWriter(bf);
            pw.println(super.getNumV());

            for(int i = 0; i < arr.size();++i) {
                Edge e = arr.get(i);
                pw.println(e.getSource() + " " + e.getDest());
            }
            pw.close();
        }catch(IOException e)
        {
            System.err.println(e);
        }


    }
    @Override
    public void insert(Edge edge) {

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return false;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }
}
