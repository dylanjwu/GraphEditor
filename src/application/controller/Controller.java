package application.controller;

public interface Controller {
	void createNew();
	void save();
	void changeMode();
	void close();

	ModeController currentMode();
	
	void importGraph();
	void undo();
	void redo();
	void copy();
	void paste();
}
