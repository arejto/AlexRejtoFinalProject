package tictactoctoe;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Piece extends Cell implements Nameable
{
	private String name;
	private Color color;
	
	public Piece()
	{
		super(5,5,5,5);
		setName("empty");
		setColor(Color.BLUE);		
	}

	public Piece(String n)
	{
		this(5,5,5,5,n, Color.BLUE);
	}

	public Piece(int x, int y, String n)
	{
		this(x,y,5,5,n, Color.BLUE);
	}

	public Piece(int x, int y, int w, int h, String n)
	{

		this(x,y,w,h,n, Color.BLUE);
		
	}

	public Piece(int x, int y, int w, int h, String n, Color c)
	{

		super(x,y,w,h);
		setName(n);
		setColor(c);
	
	}

	public void setName(String n)
	{

		name=n;
	}

	public void setColor(Color c)
	{
		color=c;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void draw(Graphics window)
	{
		window.setFont(new Font("TAHOMA",Font.BOLD,28));
		window.setColor(getColor());
		window.drawRect(getX(),getY(),getWidth(),getHeight());
		window.drawString(getName(),getX()+10,getY()+30);

	}
	
	public String toString()
	{
		return getX() + " " + getY() + " " + getWidth() + " " +  getHeight() + " " + getName() + " " + getColor();
	}
}