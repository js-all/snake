package com.snake.pouloulou;

import javax.swing.JLabel;

public class RunAtDeath implements Runnable {
	int p;
	int m;
	int td;
	Fen ff;
	JLabel[] sc;
	private Snake s;
	public RunAtDeath(int p, int m, int td, Fen ff, Snake s, JLabel[] sc) {
		this.p = p;
		this.m = m;
		this.td = td;
		this.ff = ff;
		this.s = s;
		this.sc = sc;
	}
	public void run() {
		if (this.p > this.m) {
			this.m = this.p;
		}
		sc[0].setText(Integer.toString(this.p -5));
		sc[1].setText(Integer.toString(this.m -5));
		Contr c =new Contr(this.p, this.m, this.td, ff, s);
		c.f.f.removeKeyListener(c);
	}
}
