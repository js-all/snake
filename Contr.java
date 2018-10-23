package com.snake.pouloulou;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Contr extends KeyAdapter {
	public Snake s;
	public Fen f;
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 38:
			this.s.up();
		break;
		case 39:
			this.s.right();
		break;
		case 40:
			this.s.down();
		break;
		case 37:
			this.s.left();
		break;
		}
	}
	Contr(int s, int m, int g, Fen ff,Snake ss) {
		ss.m = m;
		this.f = ff;
		ff.goTo("menu");
		this.s = ss;


		ff.open();
	}
}
