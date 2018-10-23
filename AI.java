package com.snake.pouloulou;

public class AI {
	Snake s;
	boolean iss;
	AI(Snake s) {
		this.iss = true;
		this.s = s;
		new Thread(() -> {
			try {
				Thread.sleep((int)this.s.speed);
				this.move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	private void move() {
		if (iss) {
		int[] fp = this.s.getFoodPos();
		int[] hp = this.s.getHeadPos();
		int[] pc = new int[2];
		if (hp[1] != fp[1]) {
			int f = fp[1];
			int h = hp[1];
			if (f - h > 0) {
				this.go("right");
			} else if (f - h < 0) {
				this.go("left");
			}
			
			
		} else {
			if (hp[0] != fp[0]) {
				int f = fp[0];
				int h = hp[0];
				if (f - h > 0) {
					this.go("bottom");
				} else if (f - h < 0) {
					this.go("top");
				}
			}
		}
		new Thread(() -> {
			try {
				Thread.sleep((int)this.s.speed);
				this.move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		}
	}
	private void go(String d) {
		if (iss) {
		if (d == "top") {
			if (this.s.d == 2) {
				this.s.left();
				this.setTimeout(() -> {
					this.s.up();
				}, this.s.speed);
			} else {
				this.s.up();
			}
		} else if (d == "right") {
			if (this.s.d == 3) {
				this.s.down();
				this.setTimeout(() -> {
					this.s.right();
				}, this.s.speed);
			} else {
				this.s.right();
			}
			
		} else if (d == "bottom") {
			if (this.s.d == 0) {
				this.s.right();
				this.setTimeout(() -> {
					this.s.down();
				}, this.s.speed);
			} else {
				this.s.down();
			}
		} else if (d == "left") {
			if (this.s.d == 1) {
				this.s.up();
				this.setTimeout(() -> {
					this.s.left();
				}, this.s.speed);
			} else {
				this.s.left();
			}
		}
	}}
	private void setTimeout(Runnable run, int n) {
		new Thread(() -> {
			try {
				Thread.sleep((int)n);
				run.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
}
