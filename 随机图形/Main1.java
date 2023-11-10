import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class MyShape{
	int x = 0;
	int y = 0;
	int z = 0;
	int k = 0;

	public MyShape(){
		;
	}

	public abstract void draw(Graphics g);
}

class MyLine extends MyShape{
	Random rd = new Random();

	@Override
	public void draw(Graphics g){
		g.drawLine(x,y,z,k);
	}

	public MyLine(){
		int _x = rd.nextInt(200);
		int _y = rd.nextInt(200);
		int _z = rd.nextInt(200);
		int _k = rd.nextInt(200);

		x = _x;
		y = _y;
		z = _z;
		k = _k;

	}
}

class MyRectangle extends MyShape{
	Random rd = new Random();

	@Override
	public void draw(Graphics g){
		g.drawRect(x,y,z,k);
	}

	public MyRectangle(){
		int _x = rd.nextInt(200);
		int _y = rd.nextInt(200);
		int _z = rd.nextInt(200);
		int _k = rd.nextInt(200);

		x = _x;
		y = _y;
		z = _z;
		k = _k;
	}
}

class MyOval extends MyShape{
	Random rd = new Random();
	
	@Override
	public void draw(Graphics g){
		g.drawOval(x,y,z,k);
	}

	public MyOval(){
		int _x = rd.nextInt(200);
		int _y = rd.nextInt(200);
		int _z = rd.nextInt(200);
		int _k = rd.nextInt(200);

		x = _x;
		y = _y;
		z = _z;
		k = _k;
	}
}

class DrawComponent extends JComponent
{
	@Override
	public void paintComponent(Graphics g)
	{
		for(int i=0;i<20;i++) {
			if(i<6) {
				MyOval mo = new MyOval();
				mo.draw(g);
			}
			else if(i<12) {
				MyRectangle mr = new MyRectangle();
				mr.draw(g);
			}
			else {
				MyLine ml = new MyLine();
				ml.draw(g);
			}
		}
	}
}


class Game extends JFrame{
	Random rd = new Random();

	public Game(){
		this.add(new DrawComponent());
		this.setSize(600,600);
		this.setTitle("Graphics");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
}

public class Main1{
	public static void main(String [] args){
		new Game();
	}
}