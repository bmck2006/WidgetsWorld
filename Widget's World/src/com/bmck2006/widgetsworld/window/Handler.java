package com.bmck2006.widgetsworld.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.bmck2006.widgetsworld.framework.GameObject;

public class Handler {
	
	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() {
		
		for (int i =0; i < objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.tick(objectList);
		}
	}
	
	public void render (Graphics g) {
		
		for (int i =0; i < objectList.size(); i++) {
			tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}

	public void addObject (GameObject object) {
		objectList.add(object);
	}
	
	public void removeObject (GameObject object) {
		objectList.remove(object);
	}
}
