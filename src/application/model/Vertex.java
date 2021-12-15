package application.model;

import java.util.List;

/**
 *  Vertex interface
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface Vertex {

	boolean hasEdge(Vertex v);

	void removeEdge(Vertex v);

	void addEdge(Vertex v);

	List<Vertex> edges();

	int getId();
}

