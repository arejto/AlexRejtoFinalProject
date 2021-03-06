package tictactoctoe;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Canvas;

public class GameBoard extends Canvas implements MouseListener
{
	private int mouseX, mouseY;
	private boolean mouseClicked, gameOver;
	private int mouseButton, prevMouseButton;
	private Grid board;
	
	private final static int WIDTH=250;
	private final static int HEIGHT=250;
	private final static int SCALE = 50;
	private final static int BOARDSIZE = 5;

	public GameBoard()
	{
		mouseClicked = false;
		mouseX = mouseY = 0;
		mouseButton = 0;
		prevMouseButton = MouseEvent.BUTTON2;
		
		board = new Grid(BOARDSIZE,BOARDSIZE);
		
		addMouseListener(this);
		setBackground(Color.WHITE);
	}

	public void mouseClicked(MouseEvent e)
	{
		mouseClicked = true;
		mouseX=e.getX();
		mouseY=e.getY();
		mouseButton = e.getButton();
		repaint();
	}

	public void paint(Graphics window)
	{
		window.setColor(Color.white);
		window.fillRect(0,0,640,480);
		window.setFont(new Font("TAHOMA",Font.BOLD,12));
		window.setColor(Color.blue);
		window.drawString("TIC TAC TOE", 420,55);
		window.drawString("left mouse click == [X]", 420,85);
		window.drawString("right mouse click == [O]", 420,105);
		window.drawString("mouse wheel click == [Y]", 420 , 125);

		window.drawRect(50,50,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(50,100,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(50,150,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(50,200,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(50,250,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		
		window.drawRect(100,50,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(100,100,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(100,150,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(100,200,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(100,250,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);

		window.drawRect(150,50,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(150,100,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(150,150,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(150,200,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(150,250,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);

		window.drawRect(200,50,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(200,100,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(200,150,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(200,200,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(200,250,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		
		window.drawRect(250,50,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(250,100,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(250,150,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(250,200,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);
		window.drawRect(250,250,WIDTH/BOARDSIZE,HEIGHT/BOARDSIZE);

		if(mouseClicked)
		{
			markBoard();
			board.drawGrid(window);

			if(determineWinner(window))
			{
			  //make a new board	
			  board = new Grid(BOARDSIZE,BOARDSIZE);
				
			  //clear the screen
				setBackground(Color.WHITE);

			}	
			mouseClicked = false;
		}
	}

	public void markBoard()
	{
		if(mouseX>=WIDTH/BOARDSIZE &&mouseX<=WIDTH+50&&mouseY>=HEIGHT/BOARDSIZE &&mouseY<=HEIGHT+50)
		{
			int r = mouseY/50-1;
			int c = mouseX/50-1;
			Piece piece = (Piece)board.getSpot(r,c);
			//if BUTTON1 was pressed and BUTTON1 was not pressed last mouse press
			if(mouseButton==MouseEvent.BUTTON1 && prevMouseButton== MouseEvent.BUTTON2)		//left mouse button pressed
			{
				if(piece==null)
				{
					board.setSpot(r,c,new Piece(5+c*50+50,5+r*50+50,WIDTH/BOARDSIZE-10,HEIGHT/BOARDSIZE-10,"X",Color.GREEN));
				}
				//save the current button pressed to compare to next button pressed
				prevMouseButton=mouseButton;
			}
			//if BUTTON3 was pressed and BUTTON3 was not pressed last mouse press  --- button 3 is right click
			if(mouseButton == MouseEvent.BUTTON3 && prevMouseButton == MouseEvent.BUTTON1)
			{
				if(piece ==null)
				{
					board.setSpot(r,c,new Piece(5+c*50+50,5+r*50+50,WIDTH/BOARDSIZE-10,HEIGHT/BOARDSIZE-10,"O",Color.RED));
					
				}
				prevMouseButton=mouseButton;				
			}
				//save the current button pressed to compare to next button pressed
			
			if(mouseButton == MouseEvent.BUTTON2 && prevMouseButton== MouseEvent.BUTTON3)
			{
				if(piece ==null)
				{
					board.setSpot(r,c,new Piece(5+c*50+50,5+r*50+50,WIDTH/BOARDSIZE-10,HEIGHT/BOARDSIZE-10,"Y",Color.BLUE));
					
				}
				prevMouseButton=mouseButton;				
			}
				//save the current button pressed to compare to next button pressed
				
				
		
		}
	}
	
	public boolean determineWinner(Graphics window)
	{
		String winner="";
		for (int r = 0; r<board.getNumRows(); r++)
		{
			Piece row0 = (Piece)board.getSpot(r,0);
			Piece row1 = (Piece)board.getSpot(r,1);
			Piece row2 = (Piece)board.getSpot(r,2);
			Piece row3 = (Piece)board.getSpot(r,3);
			Piece row4 = (Piece)board.getSpot(r,4);
			
			if(row0==null||row1==null||row2==null||row3==null||row4==null) continue;
			
			if(row0.getName().equals(row1.getName())&&row0.getName().equals(row2.getName())&&row0.getName().equals(row3.getName())&&row0.getName().equals(row4.getName()))
			{
				winner=row0.getName()+" wins horizontally!";
				break;
			}
		}
		
		//check for vertical winner
		
		if(winner.equals(""))
		{
			for(int c=0 ; c< board.getNumCols();c++){
				
				Piece col0 = (Piece)board.getSpot(0,c);
				Piece col1 = (Piece)board.getSpot(1,c);
				Piece col2 = (Piece)board.getSpot(2,c);
				Piece col3 = (Piece)board.getSpot(3,c);
				Piece col4 = (Piece)board.getSpot(4,c);
				
				if(col0 ==null || col1 == null || col2 == null|| col3 == null|| col4 == null)continue;
				
				if(col0.getName().equals(col1.getName()) && col0.getName().equals(col2.getName())&& col0.getName().equals(col3.getName())&& col0.getName().equals(col4.getName())){
					
					winner = col0.getName() + " wins vertically";
					break;
				}
			}
			
			
		}
		//check for diagonal winner
		
		if(winner.equals(""))
		{			
				Piece num0 = (Piece)board.getSpot(0,0);
				Piece num1 = (Piece)board.getSpot(1,1);
				Piece num2 = (Piece)board.getSpot(2,2);
				Piece num3 = (Piece)board.getSpot(3,3);
				Piece num00 = (Piece)board.getSpot(4,4);
				
				Piece num4 = (Piece)board.getSpot(4,0);
				Piece num5 = (Piece)board.getSpot(3,1);
				Piece num6 = (Piece)board.getSpot(2,2);
				Piece num7 = (Piece)board.getSpot(1,3);
				Piece num8 = (Piece)board.getSpot(0,4);
						
			if(num0 != null&& num1 != null&& num2 != null&& num3 != null&& num00 != null){
				if(num0.getName().equals(num1.getName()) && num0.getName().equals(num2.getName())&& num0.getName().equals(num3.getName())&& num0.getName().equals(num00.getName()))
				{
					winner = num0.getName() + " wins diagonally";
				}
			}			
			
			if(num4 != null && num5 != null && num6 != null && num7 != null){	
				
				if(num4.getName().equals(num5.getName()) && num4.getName().equals(num6.getName())&& num4.getName().equals(num7.getName())&& num4.getName().equals(num8.getName()))
				{
							
					winner = num4.getName() + " wins diagonally";
					
				}
			}		
					
		}

		if(winner.indexOf("no name")>-1){
		   board.drawGrid(window);
		   return false;
		}
		else if(board.drawGrid(window)&&winner.length()==0){
		   winner =  "cat's game - no winner!\n\n";
		}
				
		if(winner.length()>0)
		{
			window.setFont(new Font("TAHOMA",Font.BOLD,22));
			window.setColor(Color.orange);
			window.drawString(winner, 320,355);	
			try{
			   Thread.currentThread().sleep(1500);
			}
			catch(Exception e){
			}
			repaint();
			return true;
		}
		return false;
	}

	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}