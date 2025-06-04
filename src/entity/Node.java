package Entity;

//representasi simpul lokasi
public class Node {
	private String name;
	
	public Node(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Node)) return false;
		Node other = (Node) obj;
		return name.equalsIgnoreCase(other.name);
	}
	
	@Override 
	public int hashCode() {
		return name.toLowerCase().hashCode();
	}
	}