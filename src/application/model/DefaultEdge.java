package application.model;

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