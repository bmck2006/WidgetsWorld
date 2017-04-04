package com.bmck2006.widgetsworld.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.bmck2006.widgetsworld.framework.GameObject;
import com.bmck2006.widgetsworld.framework.ObjectId;
import com.bmck2006.widgetsworld.framework.Texture;
import com.bmck2006.widgetsworld.window.Game;

public class Block extends GameObject {
	
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics graphics) {
		if (type ==0) { // dirt block
			graphics.drawImage(tex.block[0], (int)x, (int)y, null);
		}
		if (type ==1) { // grass block
			graphics.drawImage(tex.block[1], (int)x, (int)y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
