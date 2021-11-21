package application.model;

public interface Edge {
	Vertex getSrc();
	Vertex getDst();
}

class DefaultEdge implements Edge {
	private Vertex src, dst;

	public DefaultEdge(Vertex src, Vertex dst) {
		this.src = src;
		this.dst = dst;
	}
	@Override
	public Vertex getSrc() {
		return src;
	}
	@Override
	public Vertex getDst() {
		return dst;
	}
}

class WeightedEdge implements Edge {
	private Edge edge;
	private int weight;
	public WeightedEdge(Edge edge, int weight) {
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public Vertex getSrc() {
		return edge.getSrc();
	}

	@Override
	public Vertex getDst() {
		return edge.getDst();
	}
	
	public int getWeight() {
		return weight;
	}
}

