import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private final List<String> results;

    public Experiment() {
        this.results = new ArrayList<>();
    }

    public void runTraversals(Graph graph) {
        System.out.println("Graph size: " + graph.getVertexCount() + " vertices, " + graph.getEdgeCount() + " edges");

        long bfsStart = System.nanoTime();
        graph.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;

        long dfsStart = System.nanoTime();
        graph.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        results.add(graph.getVertexCount() + " vertices | BFS: " + bfsTime + " ns | DFS: " + dfsTime + " ns");
        System.out.println("BFS time: " + bfsTime + " ns");
        System.out.println("DFS time: " + dfsTime + " ns");
        System.out.println();
    }

    public void runMultipleTests() {
        Graph smallGraph = createGraph(10);
        Graph mediumGraph = createGraph(30);
        Graph largeGraph = createGraph(100);

        System.out.println("SMALL GRAPH STRUCTURE");
        smallGraph.printGraph();
        System.out.println();

        System.out.println("SMALL GRAPH TRAVERSAL OUTPUT");
        runTraversals(smallGraph);

        System.out.println("MEDIUM GRAPH PERFORMANCE OUTPUT");
        runTraversals(mediumGraph);

        System.out.println("LARGE GRAPH PERFORMANCE OUTPUT");
        runTraversals(largeGraph);
    }

    public void printResults() {
        System.out.println("FINAL PERFORMANCE COMPARISON");
        for (String result : results) {
            System.out.println(result);
        }
    }

    private Graph createGraph(int size) {
        Graph graph = new Graph(false);

        for (int i = 0; i < size; i++) {
            graph.addVertex(new Vertex(i));
        }

        for (int i = 0; i < size - 1; i++) {
            graph.addEdge(i, i + 1);
        }

        for (int i = 0; i < size - 2; i += 2) {
            graph.addEdge(i, i + 2);
        }

        for (int i = 0; i < size - 5; i += 5) {
            graph.addEdge(i, i + 5);
        }

        return graph;
    }
}
