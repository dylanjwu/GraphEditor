package application.controller;

/**
 * Controller interface implemented by MainController class
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface Controller {
	void createNew();
	void save();
	void changeMode(String mode);
	void close();
	ModeController currentMode();
	void importGraph();
	void undo();
	void redo();
	void copy();
	void paste();
	void simulateAlgorithm() throws InterruptedException;
}
