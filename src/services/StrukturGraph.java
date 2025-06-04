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
            System.out.println("Node maksimum tercapai.");
            return;
        }

        Node newNode = new Node(name);

        for (int i = 0; i < nodeCount; i++) {
            if (nodes[i].equals(newNode)) {
                System.out.println("Node sudah ada.");
                return;
            }
        }

        nodes[nodeCount] = newNode;

        // Menambahkan edge ke dirinya sendiri dengan bobot 0
        adjacencyMatrix[nodeCount][nodeCount] = new Edge(newNode, newNode, 0);

        nodeCount++;
        System.out.println("Node " + name + " berhasil ditambahkan.");
    }
}