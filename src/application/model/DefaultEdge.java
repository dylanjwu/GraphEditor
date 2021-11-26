package application.model;

import java.util.Objects;

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
	@Override
	public String toString() {
		return "DefaultEdge [src=" + src.getId() + ", dst=" + dst.getId() + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dst, src);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DefaultEdge))
			return false;
		DefaultEdge other = (DefaultEdge) obj;
		return Objects.equals(dst, other.dst) && Objects.equals(src, other.src);
	}
	
}