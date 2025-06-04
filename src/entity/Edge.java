public class Edge {

	private Node from; // Node asal
    private Node to;   // Node tujuan
    private int weight; // Bobot edge

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}