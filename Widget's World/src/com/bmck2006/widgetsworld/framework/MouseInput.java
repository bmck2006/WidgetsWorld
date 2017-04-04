package com.bmck2006.widgetsworld.framework;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.bmck2006.widgetsworld.window.Game;
import com.bmck2006.widgetsworld.window.Menu;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		/**
		 	public Rectangle playButton = new Rectangle(Game.WIDTH/3 -30, 250, 100, 50);
			public Rectangle helpButton = new Rectangle(Game.WIDTH/3, 350, 100, 50);
			public Rectangle quitButton = new Rectangle(Game.WIDTH/3 + 30, 450, 100, 50);
		 */
		
		//Play Button
		if (mx >= Game.WIDTH/3 - 30 && mx <= Game.WIDTH/3 + 70) {
			if (my >= 250 && my <= 300) {
				//Press Play Button
				Game.state = Game.STATE.GAME;
			}
		}
		//Quit Button
		if (mx >= Game.WIDTH/3 + 30 && mx <= Game.WIDTH/3 + 130) {
			if (my >= 450 && my <= 500) {
				//Press Quit Button
				System.exit(1);
			}
		}
		//Help Button
				if (mx >= Game.WIDTH/3 && mx <= Game.WIDTH/3 + 100) {
					if (my >= 350 && my <= 400) {
						//Press Quit Button
						JOptionPane.showMessageDialog((Component)e.getSource(), Menu.getHelpString(), "Help", JOptionPane.PLAIN_MESSAGE );
					}
				}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
