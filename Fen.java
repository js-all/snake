package com.snake.pouloulou;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Fen {
	JFrame f;
	JPanel c;
	JPanel m;
	JPanel p;
	JPanel cc;
	private CardLayout cl;
	public int sizeX;
	public int sizeY;
	public Fen(int sizeX, int sizeY, Color c1, boolean t, String tt, String Path, boolean b) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.cl = new CardLayout();
		this.f = new JFrame();
		this.f.setTitle(tt);
		this.f.setUndecorated(b);
		this.f.setSize(this.sizeX,this.sizeY);
		this.f.setLocationRelativeTo(null);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.c = new JPanel();
		this.p = new JPanel();
		this.m = new JPanel();
		this.cc = new JPanel();
		this.c.setBackground(c1);
		this.p.setBackground(c1);
		this.m.setBackground(c1);
		this.cc.setBackground(c1);
		this.c.setPreferredSize(new Dimension(this.sizeX, this.sizeY));
		this.p.setPreferredSize(new Dimension(this.sizeX, this.sizeY));
		this.m.setPreferredSize(new Dimension(this.sizeX, this.sizeY));
		this.cc.setPreferredSize(new Dimension(this.sizeX, this.sizeY));
		this.cc.setLayout(this.cl);
		this.cc.add(this.m, "menu");
		this.cc.add(this.c, "game");
		this.cc.add(this.p, "param");
		this.cl.show(this.cc, "menu");
		this.f.setContentPane(this.cc);
		this.f.setResizable(t);
		if (Path != null) {
			ImageIcon ico = new ImageIcon(Path);
			this.f.setIconImage(ico.getImage());
		}
	}
	public void open() {
		this.f.setVisible(true);
	}
	public void goTo(String g) {
		this.cl.show(this.cc, g);
	}
	}
