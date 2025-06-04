package services;

import entity.Node;
import entity.Edge;

public class StrukturGraph {
    private final int MAX_NODES = 100; // Batas maksimum node
    private Edge[][] adjacencyMatrix;  // Matriks adjacency untuk menyimpan objek Edge
    private Node[] nodes;              // Menyimpan objek Node
    private int nodeCount;             // Jumlah node saat ini

    public StrukturGraph() {
        adjacencyMatrix = new Edge[MAX_NODES][MAX_NODES];
        nodes = new Node[MAX_NODES];
        nodeCount = 0;
        initializeMatrix();
    }

    private void initializeMatrix() {
        for (int i = 0; i < MAX_NODES; i++) {
            for (int j = 0; j < MAX_NODES; j++) {
                adjacencyMatrix[i][j] = null; // Tidak ada edge
            }
        }
    }

    public void addNode(String name) {
        if (nodeCount >= MAX_NODES) {
            System.out.println("Lokasi yang dapat ditambahkan mencapai jumlah maksimum.");
            return;
        }

        Node newNode = new Node(name);

        for (int i = 0; i < nodeCount; i++) {
            if (nodes[i].equals(newNode)) {
                System.out.println("Lokasi sudah ditambahkan sebelumnya.");
                return;
            }
        }

        nodes[nodeCount] = newNode;

        // Menambahkan edge ke dirinya sendiri dengan bobot 0
        adjacencyMatrix[nodeCount][nodeCount] = new Edge(newNode, newNode, 0);

        nodeCount++;
        System.out.println("Lokasi " + name + " berhasil ditambahkan.");
    }

    public void addEdge(String from, String to, int weight) {
        int fromIndex = getNodeIndex(from);
        int toIndex = getNodeIndex(to);

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Salah satu lokasi tidak ditemukan.");
            return;
        }

        if (fromIndex == toIndex) {
            System.out.println("Tidak dapat menambahkan jalur ke lokasi yang sama.");
            return;
        }

        if (adjacencyMatrix[fromIndex][toIndex] != null) {
            System.out.println("Jalur sudah ada. Silakan update jika ingin mengubah bobot.");
            return;
        }

        adjacencyMatrix[fromIndex][toIndex] = new Edge(nodes[fromIndex], nodes[toIndex], weight);

        System.out.println("Jalur dari " + from + " ke " + to + " dengan jarak " + weight + " berhasil ditambahkan.");
    }

    public void updateEdge(String from, String to, int newWeight) {
        int fromIndex = getNodeIndex(from);
        int toIndex = getNodeIndex(to);

        if (fromIndex == -1 && toIndex == -1) {
            System.out.println("Kedua lokasi tidak ditemukan.");
            return;    
        }
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Salah satu lokasi  tidak ditemukan.");
            return;
        }

        Edge edge = adjacencyMatrix[fromIndex][toIndex];
        if (edge == null) {
            System.out.println("Jalur belum ada.");
            return;
        }

        // Gunakan setter untuk memperbarui bobot
        edge.setWeight(newWeight);
        System.out.println("lokasi dari " + from + " ke " + to + " berhasil diperbarui dengan jarak " + newWeight + ".");
    }

    public void printGraph() {
        System.out.println("Peta Jalur Antar Lokasi:");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print(nodes[i].getName() + ": ");
            for (int j = 0; j < nodeCount; j++) {
                if (adjacencyMatrix[i][j] == null) {
                    System.out.print("Tidak diketahui ");
                } else {
                    System.out.print(adjacencyMatrix[i][j].getWeight() + " ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Jarak antar lokasi:");
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (adjacencyMatrix[i][j] != null) {
                    System.out.println("[Lokasi " + nodes[i].getName() + " -> Lokasi " + nodes[j].getName() + ", jarak: " + adjacencyMatrix[i][j].getWeight() + " KM]");
                }
            }
        }
    }

    public void shortestPath(String from, String to) {
        int fromIndex = getNodeIndex(from);
        int toIndex = getNodeIndex(to);

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Salah satu lokasi tidak ditemukan.");
            return;
        }

        int[] dist = new int[nodeCount];
        boolean[] visited = new boolean[nodeCount];
        int[] prev = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            prev[i] = -1;
        }
        dist[fromIndex] = 0;

        for (int count = 0; count < nodeCount - 1; count++) {
            int u = findMinDistance(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < nodeCount; v++) {
                Edge edge = adjacencyMatrix[u][v];
                if (!visited[v] && edge != null && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + edge.getWeight() < dist[v]) {
                    dist[v] = dist[u] + edge.getWeight();
                    prev[v] = u;
                }
            }
        }

        if (dist[toIndex] == Integer.MAX_VALUE) {
            System.out.println("Tidak ada jalur dari lokasi " + from + " ke lokasi " + to + ".");
            return;
        }

        System.out.println("Jarak total dari lokasi " + from + " ke lokasi " + to + ": " + dist[toIndex]);

        StringBuilder path = new StringBuilder();
        int currentIndex = toIndex;

        while (currentIndex != -1) {
            path.insert(0, nodes[currentIndex].getName());

            if (prev[currentIndex] != -1) {
                Edge edge = adjacencyMatrix[prev[currentIndex]][currentIndex];
                path.insert(0, " --" + edge.getWeight() + "-- ");
            }

            currentIndex = prev[currentIndex];
        }

        System.out.println("Jalur terpendeknya ialah:");
        System.out.println(path);
    }
    
    private int getNodeIndex(String name) {
        Node targetNode = new Node(name);
        for (int i = 0; i < nodeCount; i++) {
            if (nodes[i].equals(targetNode)) {
                return i;
            }
        }
        return -1;
    }

    private int findMinDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < nodeCount; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}

