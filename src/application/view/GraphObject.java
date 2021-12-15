package application.view;

import application.view.visitor.GraphVisitor;

public interface GraphObject {
	void accept(GraphVisitor visitor);
}
