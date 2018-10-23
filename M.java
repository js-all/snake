package com.snake.pouloulou;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class M {
	static public void main(String[] args) {
			JLabel S1T = new JLabel("votre score est de :");
			JLabel S1V = new JLabel("0");
			JLabel S2T = new JLabel("votre meilleure score est de :");
			JLabel S2V = new JLabel("0");
			JLabel[] sc = {S1V, S2V};
			int X = 832;
			int Y = 842;
			Fen f =  new Fen(X, Y, Color.DARK_GRAY, true, "Snake || menu", null, false);
			Contr c = new Contr(0, 0, 2, f, new Snake( 32, 32, 20, Color.DARK_GRAY, Color.WHITE, Color.RED, 100, 0, f, sc));
			JButton b = new JButton("Play !");
			JButton b1 = new JButton("parametre");
			JButton b2 = new JButton("menu");
			JButton b3 = new JButton("AI");
			
			b.addActionListener(e -> {
				c.s.start();
				c.s.f.f.addKeyListener(c);
			});
			b1.addActionListener(e -> {
				c.f.goTo("param");
				c.f.f.setTitle("Snake || parametre");
			});
			b2.addActionListener(e -> {
				c.f.goTo("menu");
				c.f.f.setTitle("Snake || menu");
			});
			b3.addActionListener(e -> {
				c.f.f.removeKeyListener(c);
				new AI(c.s);
				c.s.start();
			});
			
			int PY = 90;
			int SY = 3;
			JPanel score = new JPanel();
			S1T.setPreferredSize(new Dimension(X/2, (Y/SY-((PY/100)*Y/SY))));
			S1V.setPreferredSize(new Dimension(X/2, (PY/100)*Y/SY));
			S2T.setPreferredSize(new Dimension(X/2, (Y/SY-((PY/100)*Y/SY))));
			S2V.setPreferredSize(new Dimension(X/2, (PY/100)*Y/SY));
			S1T.setForeground(Color.white);
			S2T.setForeground(Color.white);
			S1V.setForeground(Color.white);
			S2V.setForeground(Color.white);
			Font font = new Font("Arial",Font.PLAIN, 85);
			S1V.setFont(font);
			S2V.setFont(font);
			S2T.setVerticalAlignment(SwingConstants.TOP);
			S1T.setVerticalAlignment(SwingConstants.TOP);
			S2V.setVerticalAlignment(SwingConstants.TOP);
			S1V.setVerticalAlignment(SwingConstants.TOP);
			S1V.setHorizontalAlignment(SwingConstants.CENTER);
			S2V.setHorizontalAlignment(SwingConstants.CENTER);
			JPanel S1 = new JPanel();
			JPanel S2 = new JPanel();
			S1.setPreferredSize(new Dimension(X/2-10,Y/SY));
			S2.setPreferredSize(new Dimension(X/2-10,Y/SY));
			S1.setLayout(new GridLayout(2,1));
			S2.setLayout(new GridLayout(2,1));
			score.setLayout(new BorderLayout());
			score.add(S1, BorderLayout.WEST);
			score.add(S2, BorderLayout.EAST);
			S1.add(S1T);
			S1.add(S1V);
			S2.add(S2T);
			S2.add(S2V);
			S1.setBackground(Color.DARK_GRAY);
			S2.setBackground(Color.DARK_GRAY);
			JPanel bu = new JPanel();
			bu.setPreferredSize(new Dimension(832,500));
			score.setPreferredSize(new Dimension(X, Y/SY));
			score.setBackground(Color.DARK_GRAY);
			c.f.m.setLayout(new BorderLayout());
			c.f.m.add(score, BorderLayout.NORTH);
			c.f.m.add(bu, BorderLayout.CENTER);
			b.setPreferredSize(new Dimension(X/2,Y/15));
			GridLayout gl = new GridLayout(2,1);

			bu.setLayout(gl);
			bu.add(b);
			bu.add(b1);
			bu.add(b3);
			
			
			
			JPanel abd = new JPanel();
			JButton abdb = new JButton("allow");
			
			
			c.f.p.add(b2);
	}
}
