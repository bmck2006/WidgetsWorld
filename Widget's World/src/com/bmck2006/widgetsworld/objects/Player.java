package com.bmck2006.widgetsworld.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.bmck2006.widgetsworld.framework.GameObject;
import com.bmck2006.widgetsworld.framework.ObjectId;
import com.bmck2006.widgetsworld.framework.Texture;
import com.bmck2006.widgetsworld.window.Animation;
import com.bmck2006.widgetsworld.window.Game;
import com.bmck2006.widgetsworld.window.Handler;

public class Player extends GameObject{
	
	private float width = 60;
	private float height = 60;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private boolean setFace = true; 
	//true = left face, false = right face in idol position (animation)
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalk, playerWalkRev;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		playerWalk = new Animation(3, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5], tex.player[6]);
		playerWalkRev = new Animation(3, tex.player[8], tex.player[9], tex.player[10], tex.player[11], tex.player[12], tex.player[13]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {	
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
			
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		
		playerWalk.runAnimation(); 
		playerWalkRev.runAnimation();
	}
	
	private void Collision(LinkedList<GameObject> objectList) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getId() == ObjectId.Block) {
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height/2;
					velY = 0;
				}
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
				//Right Collision
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;	
				}
				//Left Collision
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		
		// PLAYER JUMPING
		if (jumping) {
			if (velX < 0) { // Left Jumping
				if (velY == 0)
					graphics.drawImage(tex.playerJump[1], (int)x, (int)y, 90, 58, null);
				else if (velY < 0)
					graphics.drawImage(tex.playerJump[0], (int)x, (int)y, 90, 58, null);
				else
					graphics.drawImage(tex.playerJump[2], (int)x, (int)y, 90, 58, null);
				setFace = true; //Left facing
			}
			else if (velX > 0) { //Right Jumping
				if (velY == 0)
					graphics.drawImage(tex.playerJump[4], (int)x, (int)y, 90, 58, null);
				else if (velY < 0)
					graphics.drawImage(tex.playerJump[3], (int)x, (int)y, 90, 58, null);
				else
					graphics.drawImage(tex.playerJump[5], (int)x, (int)y, 90, 58, null);
				setFace = false; //Right facing
			}
			else { //in place jumping
				if (setFace && velY > 0) 
					graphics.drawImage(tex.playerJump[1], (int)x, (int)y, 90, 58, null);
				else if (!setFace && velY > 0)
					graphics.drawImage(tex.playerJump[4], (int)x, (int)y, 90, 58, null);
				else if (setFace)
					graphics.drawImage(tex.playerJump[0], (int)x, (int)y, 90, 58, null);
				else
					graphics.drawImage(tex.playerJump[3], (int)x, (int)y, 90, 58, null);
				}
			}
			
		//PLAYER WALKING
		else{
			
			
			//True equals left face, false equals right face. 
			graphics.setColor(Color.blue);
			if(velX < 0) {
				playerWalk.drawAnimation(graphics, (int)x, (int)y, 90, 58);
				setFace = true;
			}
			else if (velX > 0) {
				playerWalkRev.drawAnimation(graphics,  (int)x, (int)y, 90, 58);
				setFace = false;
			}
			else {
				if (setFace) {
					graphics.drawImage(tex.player[0],(int)x,(int)y,null); 
				}
				else {
				graphics.drawImage(tex.player[7],(int)x, (int)y, null);
				}
			}
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + (height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width -5), (int)y +5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	

}
