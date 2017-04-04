package com.bmck2006.widgetsworld.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Menu {
	
	public JOptionPane helpMenu = new JOptionPane();
	private static String helpString = "- Use Left,Right arrows to move Widget and Tucson\n- Press Space to ump\n - Press ESC to quit";
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/3 -30, 250, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH/3, 350, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/3 + 30, 450, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.green);
		g.drawString("WIDGET'S WORLD", Game.WIDTH/3, 200);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
		g2d.draw(quitButton);
				
	}
	
	public JOptionPane getHelpMenu(){
		return helpMenu;
	}
	public static String getHelpString(){
		return helpString;
	}
}
