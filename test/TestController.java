import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.controller.AddController;
import application.controller.DeleteController;
import application.controller.MainController;
import application.controller.MoveController;
import application.model.DefaultGraph;
import application.model.Graph;
import application.view.GraphEditorView;
import application.view.GraphEditorViewImpl;

public class TestController {

	@Test
	public void testChangeMode() {
		Graph model = new DefaultGraph();
		GraphEditorView view = new GraphEditorViewImpl(model);
		MainController controller = new MainController(view, model);
		controller.changeMode("add");
		assertTrue(controller.currentMode() instanceof AddController);

		controller.changeMode("move");
		assertTrue(controller.currentMode() instanceof MoveController);

		controller.changeMode("delete");
		assertTrue(controller.currentMode() instanceof DeleteController);

		controller.changeMode("move");
		assertTrue(controller.currentMode() instanceof MoveController);

		controller.changeMode("add");
		assertTrue(controller.currentMode() instanceof AddController);
	}

	public void testSimulateAlgorithm() {
		Graph model = new DefaultGraph();
		GraphEditorView view = new GraphEditorViewImpl(model);
		MainController controller = new MainController(view, model);
		try {
			controller.simulateAlgorithm();
			assertTrue(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
	}
}
