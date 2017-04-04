package com.bmck2006.widgetsworld.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.bmck2006.widgetsworld.framework.EffectsPlayer;
import com.bmck2006.widgetsworld.framework.KeyInput;
import com.bmck2006.widgetsworld.framework.MouseInput;
import com.bmck2006.widgetsworld.framework.ObjectId;
import com.bmck2006.widgetsworld.framework.SoundPlayer;
import com.bmck2006.widgetsworld.framework.Texture;
import com.bmck2006.widgetsworld.objects.Block;
import com.bmck2006.widgetsworld.objects.Player;


public class Game extends Canvas implements Runnable {
	
	public static enum STATE {
		MENU,
		GAME
	};
	
	public static STATE state = STATE.MENU;
	private Menu menu;
	
	private static final long serialVersionUID = -5864677762951382432L;
	
	public static SoundPlayer soundPlayer;
	public static EffectsPlayer effectsPlayer;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null;
	private BufferedImage clouds = null;
	
	//Object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		menu = new Menu();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1.png"); //Loads the level
		clouds = loader.loadImage("/clouds.png");
		
		handler = new Handler();
		
		cam = new Camera(0,0);
		
		//soundPlayer = new SoundPlayer();
		//effectsPlayer = new EffectsPlayer();
		//soundPlayer.playMusic();
		
		loadImageLevel(level);
		
		//handler.addObject(new Player(100,100, handler, ObjectId.Player));
		
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());
	}
	
	public synchronized void start() {
		
		if(running) 
			return; // fail safe to make sure start method isn't called twice, which would start another thread causing problems.
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		
		//Game Loop
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	private void tick(){
		
		if (state == STATE.GAME) {
			
			handler.tick();
			
			for (int i = 0; i < handler.objectList.size(); i++) {
				if(handler.objectList.get(i).getId() == ObjectId.Player) {
					cam.tick(handler.objectList.get(i));
				}
			}
		}
	}
	
	private void render() {
		
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) graphics;
		
		/*
		 * ***********DRAW GAME************************
		 */
		graphics.setColor(new Color(0,181,255)); //Use this for standard cyan background, no clouds.
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		//graphics.drawImage(clouds, 0, 0, this); Use this for static cloud background
		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam
		
		for(int xx = 0; xx < clouds.getWidth() * 4; xx += clouds.getWidth()) {// static clouds that move with the camera, 4 is amount of images (adjustable).
			graphics.drawImage(clouds, xx, 0, this);
		} 
		
		if (state == STATE.GAME) {
			handler.render(graphics);
		}else if (state == STATE.MENU) {
			menu.render(graphics);
		}
		
		
		
		g2d.translate(-cam.getX(), -cam.getY());//end of cam
		
		/*
		 * ********************************************
		 */
		
		graphics.dispose();
		bufferStrategy.show();
		
	}
	
	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy =0; yy < w; yy++) {
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Block(xx*32, yy*32, 0,ObjectId.Block));
				}
				if(red == 128 && green == 128 && blue == 128) {
					handler.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
				}
				if(red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
				}
			}
		}
	}
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String args[]) {
		
		new Window(800, 600, "Widget's World Prototype", new Game());
	}

}
