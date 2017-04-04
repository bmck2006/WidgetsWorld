package com.bmck2006.widgetsworld.framework;

import java.awt.image.BufferedImage;

import com.bmck2006.widgetsworld.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps, ps2;
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	private BufferedImage playerSheet2 = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] playerJump = new BufferedImage[6];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			blockSheet = loader.loadImage("/Textures.png");
			playerSheet = loader.loadImage("/Sprites.png");
			playerSheet2 = loader.loadImage("/SpritesRev.png");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);
		ps2 = new SpriteSheet(playerSheet2);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(25, 3, 32, 32); //dirt block
		block[1] = bs.grabImage(25, 2, 32, 32); //grass block
		
		//Player walking images
		player[0] = ps.grabImage(1, 1, 90, 58); //Character idle
		player[1] = ps.grabImage(1, 2, 90, 58); //Character walking
		player[2] = ps.grabImage(2, 2, 90, 58); //Character walking
		player[3] = ps.grabImage(3, 2, 90, 58); //Character walking
		player[4] = ps.grabImage(4, 2, 90, 58); //Character walking
		player[5] = ps.grabImage(5, 2, 90, 58); //Character walking
		player[6] = ps.grabImage(6, 2, 90, 58); //Character walking
		player[7] = ps2.grabImage(6, 1, 90, 58); //Character idle REVERSE
		player[8] = ps2.grabImage(6, 1, 90, 58); //Character walking REVERSE
		player[9] = ps2.grabImage(5, 2, 90, 58); //Character walking REVERSE
		player[10] = ps2.grabImage(4, 2, 90, 58); //Character walking REVERSE
		player[11] = ps2.grabImage(3, 2, 90, 58); //Character walking REVERSE
		player[12] = ps2.grabImage(2, 2, 90, 58); //Character walking REVERSE
		player[13] = ps2.grabImage(1, 2, 90, 58); //Character walking REVERSE
		
		//Player jumping images
		playerJump[0] = ps.grabImage(4, 3, 90, 58); //Character takeoff LEFT
		playerJump[1] = ps.grabImage(5, 3, 90, 58); //Character midair LEFT
		playerJump[2] = ps.grabImage(1, 3, 90, 58); //Character landing LEFT
		playerJump[3] = ps2.grabImage(3, 3, 90, 58); //Character takeoff RIGHT
		playerJump[4] = ps2.grabImage(2, 3, 90, 58); //Character midair RIGHT
		playerJump[5] = ps2.grabImage(6, 3, 90, 58); //Character landing RIGHT
	}
}
