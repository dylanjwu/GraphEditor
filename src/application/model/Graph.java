package application.model;

import java.util.List;

/**
 * Graph interface that can be iterated upon
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface Graph extends Iterable<Vertex> {
	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	void clear();
	void addEdge(Vertex u, Vertex v);
	void removeEdge(Vertex u, Vertex v);
	List<Vertex> getVertices();
}
