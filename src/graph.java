import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private final Map<Integer, Vertex> vertices;
    private final Map<Integer, List<Vertex>> adjacencyList;
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.putIfAbsent(vertex.getId(), vertex);
        adjacencyList.putIfAbsent(vertex.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        Vertex source = vertices.get(from);
        Vertex destination = vertices.get(to);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }

        adjacencyList.get(from).add(destination);

        if (!directed) {
            adjacencyList.get(to).add(source);
        }
    }

    public void printGraph() {
        for (int vertexId : adjacencyList.keySet()) {
            System.out.print("V" + vertexId + " -> ");
            for (Vertex neighbor : adjacencyList.get(vertexId)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        if (!vertices.containsKey(start)) {
            throw new IllegalArgumentException("Start vertex does not exist.");
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        visited.add(start);
        queue.add(vertices.get(start));

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.print(current + " ");

            // BFS visits all direct neighbors first, then moves level by level.
            for (Vertex neighbor : adjacencyList.get(current.getId())) {
                if (!visited.contains(neighbor.getId())) {
                    visited.add(neighbor.getId());
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) {
            throw new IllegalArgumentException("Start vertex does not exist.");
        }

        Set<Integer> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertices.get(start));

        while (!stack.isEmpty()) {
            Vertex current = stack.pop();

            if (!visited.contains(current.getId())) {
                visited.add(current.getId());
                System.out.print(current + " ");

                // DFS goes as deep as possible before returning to another branch.
                List<Vertex> neighbors = adjacencyList.get(current.getId());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    Vertex neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor.getId())) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Vertex> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return directed ? count : count / 2;
    }
}
