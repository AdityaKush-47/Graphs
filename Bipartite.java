import java.util.*;

public class Bipartite {
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 1));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
    }

    public static boolean isBipartite(ArrayList<Edge> graph[]) {
        int color[] = new int[graph.length];
        Arrays.fill(color, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1) {
                q.add(i);
                color[i] = 0;
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);
                        if (color[curr] == color[e.dest]) {
                            return false;
                        } else if (color[e.dest] == -1) {
                            color[e.dest] = (color[e.src] + 1) % 2;
                            q.add(e.dest);
                        }
                    }

                }
            }
        }
        return true;

    }

    // Bipartite 2
    // public static boolean isBipartite2(ArrayList<Edge>[] graph) {
    //     if(!detectCycle(graph)) {
    //         return true;
    //     } else {
            
    //     }

    // }

    // Cycle Detection Code for Bipartite 2
    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (detectCycleUtil(graph, i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, int curr, int par, boolean[] visited) {
        visited[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[i].get(i);
            if (!visited[e.dest]) {
                if (detectCycleUtil(graph, e.dest, curr, visited)) {
                    return true;
                }
            } else if (visited[e.dest] && e.dest != par) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 5;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        System.out.println(isBipartite(graph));
    }
}
