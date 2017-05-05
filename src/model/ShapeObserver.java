package model;

import controller.Controller;
public class ShapeObserver implements IShapeObserver {

	@Override
	public void update() {
		Controller ctrl = Controller.getInstance();
		//ctrl.refreshObserver();
	}

	

}
