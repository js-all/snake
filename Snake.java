package com.snake.pouloulou;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Snake {
	public boolean abd;
	public Fen f;
	public Color bgc;
	public Color snc;
	public Color foc;
	public int sizeX;
	public int sizeY;
	public int sizeC;
	public int speed;
	public boolean dead = false;
	public int m;
	private int td;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ArrayList<ArrayList<JPanel>> ps = new ArrayList<ArrayList<JPanel>>();
	private ArrayList<int[]> s = new ArrayList<int[]>();
	private int l;
	public int d;
	private int[] food = new int[2];
	private int[] last = new int[2];
	private JLabel[] sc;
	/*
	 * direction, d:
	 *   0 : top
	 *   1 : right
	 *   2 : bottom
	 *   3 : left
	 */
	
	public Snake(int sizeX, int sizeY, int sizeC, Color bg, Color sn, Color fo, int ss, int m,Fen F, JLabel[] sc) {
		this.abd = false;
		this.bgc = bg;
		this.snc = sn;
		this.foc = fo;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeC = sizeC;
		this.speed = ss;
		this.sc = sc;
		this.m = m;
		this.l = 5;
		this.d = 0;
		this.food[0] = (int)(Math.random() * 32);
		this.food[1] = (int)(Math.random() * 32);
		for (int h = 0;h < this.l;h++) {
			int[] a = {((int)(this.sizeY / 2) + h),(int)(this.sizeX / 2)};
			this.s.add(this.s.size(), a);
		}
		this.last[0] = this.s.get(l - 1)[0];
		this.last[1] = this.s.get(l - 1)[1];
		this.f = F;
		this.f.c.setBackground(this.bgc.brighter());
		this.f.c.setBorder(BorderFactory.createLineBorder(bg.brighter().brighter()));
		for (int i = 0;i < this.sizeY;i++) {
			this.ps.add(new ArrayList<JPanel>());
			for (int j = 0;j < this.sizeX;j++) {
				JPanel jp = new JPanel();
				jp.setPreferredSize(new Dimension(this.sizeC, this.sizeC));
				jp.setBackground(this.bgc);
				this.ps.get(i).add(jp);
			}
		}
		
		this.init();
		
	}
	
	public void init() {
		int y = 0;
		for (ArrayList<JPanel> a : ps) {
			int x = 0;
			for(JPanel p : a) {
				this.gbc.gridheight = 1;
				if (x == this.sizeX) {
					this.gbc.gridwidth = GridBagConstraints.REMAINDER;
				} else {
					this.gbc.gridwidth = 1;
				}
				this.gbc.gridheight = y;
				this.gbc.gridx = x;
				p.setBackground(this.bgc);
				this.f.c.add(p, this.gbc);
				x++;
				
			}
			y++;
		}
		this.render();
	}
	
	public void up() {
		if (d != 2) {
			this.d = 0;
		}
	}
	public void right() {
		if (d != 3) {
			this.d = 1;
		}
	}
	public void down() {
		if (d != 0) {
			this.d = 2;
		}
	}
	public void left() {
		if (d != 1) {
			this.d = 3;
		}
	}
	
	public int[] getFoodPos() {
		return this.food;
	}
	
	public int[] getHeadPos() {
		return this.s.get(0);
	}
	
	public ArrayList<int[]> getSnakePos() {
		return this.s;
	}
	
	public void start() {
		this.dead = false;
		this.s.clear();
		this.l = 5;
		this.d = 0;
		for (int h = 0;h < this.l;h++) {
			int[] a = {((int)(this.sizeY / 2) + h),(int)(this.sizeX / 2)};
			this.s.add(this.s.size(), a);
		}
		this.last[0] = this.s.get(l - 1)[0];
		this.last[1] = this.s.get(l - 1)[1];
		this.f.f.requestFocusInWindow();
		this.f.f.setTitle("Snake || score : 0");
		this.f.goTo("game");
		new Thread(() -> {
			try {
				Thread.sleep((int)this.speed);
					this.move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void death() {
		this.dead = true;
		this.f.f.setTitle("Snake || menu");
		this.f.goTo("menu");
			Thread d = new Thread(new RunAtDeath(this.l, this.m, this.td, this.f, this, this.sc));
			d.start();
	}
	
	private void render() {
		for(ArrayList<JPanel> ap : this.ps) {
			for( JPanel jp : ap) {
				jp.setBackground(this.bgc);
			}
		}
		for(int[] p : this.s) {
			this.ps.get(p[0]).get(p[1]).setBackground(this.snc);
		}
		this.ps.get(this.food[0]).get(this.food[1]).setBackground(this.foc);
	}
	
	private void move() {
		int[] snh = new int[2];
		int[] snl = new int[2];
		switch(d) {
		case 0:
			snh[0] = this.s.get(0)[0] - 1;
			snh[1] = this.s.get(0)[1];
		break;
		case 1:
			snh[0] = this.s.get(0)[0];
			snh[1] = this.s.get(0)[1]+ 1;
		break;
		case 2:
			snh[0] = this.s.get(0)[0] + 1;
			snh[1] = this.s.get(0)[1];
		break;
		case 3:
			snh[0] = this.s.get(0)[0];
			snh[1] = this.s.get(0)[1] - 1;
		break;
		}
		snl[0] = this.s.get(this.s.size() - 1)[0];
		snl[1] = this.s.get(this.s.size() - 1)[1];
		this.last[0] = snl[0];
		this.last[1] = snl[1];
		for(int j = (this.s.size() -1);j > 0;j--) {
			this.s.get(j)[0] = this.s.get(j - 1)[0];
			this.s.get(j)[1] = this.s.get(j - 1)[1];
		}
		this.s.get(0)[0] = snh[0];
		this.s.get(0)[1] = snh[1];
		if (snh[0] == this.food[0] && snh[1] == this.food[1]) {
			this.s.add(this.s.size(), snl);
			this.l++;
			this.food[0] = (int)(Math.random() * 32);
			this.food[1] = (int)(Math.random() * 32);
			this.f.f.setTitle("Snake || score : " + (this.l -5));
		}
		if (this.abd) {
		for(int[] gg : this.s) {
			if (snh[0] == gg[0] & snh[1] == gg[1]) {
				if (gg != this.s.get(0)) {
					this.td = 1;
					this.dead = true;
					System.out.println("body");
					this.death();
				}
			}
		}
		}
		if (snh[0] > 31 | snh[0] < 0 | snh[1] > 31 | snh[1] < 0) {
			this.td = 0;
			this.dead = true;
			System.out.println("border");
			this.death();
		}
		this.render();
		new Thread(() -> {
			try {
				Thread.sleep(this.speed);
				if (this.dead == false) {
					this.move();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				
			} catch (IndexOutOfBoundsException e) {
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
