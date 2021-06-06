import javax.swing.*;
import java.awt.event.*;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.*;
class test{
	static JFrame frame;
	static Container c;
	public static void main(String[] args) {
	frame=new JFrame("Pong");
	frame.setSize(530,800);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
   frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    c=frame.getContentPane();
    c.setLayout(null);
    make jpanel=new make();
    frame.addKeyListener(jpanel);
    c.add(jpanel);
	}
}
class make extends JPanel implements KeyListener{
Rectangle ai_pad,Player_pad,boll_rect;
int dx=1,dy=3;
boolean status=false;
int boll_x=242,boll_y=365;
int Player_x=222,Player_y=730;
int ai_x=222,ai_y=10;
int pad_height=70,pad_width=10;
int boll_height=20,boll_width=20;
boolean move_status=true;

public void paint(Graphics g)
   { 
   super.paint(g);
   g.setColor(Color.red);
   g.fillRect(ai_x,ai_y,pad_height,pad_width);//AI
   g.fillRect(Player_x,Player_y,pad_height,pad_width);//Player
   g.setColor(Color.white);
   g.drawLine(0,375,505,375);//mid
   g.drawOval(222,345,60,60);
   g.drawLine(0,10,505,10);
   g.drawLine(0,740,505,740);
   g.setColor(Color.cyan);
   g.fillOval(boll_x,boll_y,boll_height,boll_width);//boll
   
   }
make()
   {
	this.setBounds(5,6,505,750);
	this.setBackground(Color.black);
   }

class move extends Thread
   {
   public void run(){
    while(status){
    boll_x-=dx;
    boll_y-=dy;
    if(boll_x<=0 ||  boll_x>=485)
          dx*=-1; 
    if(boll_y>730 || boll_y<0){
          status=false;
          boll_x=242;
          boll_y=365;
        }   
    try{Thread.sleep(7);}catch(Exception e){} 
    boll_rect=new Rectangle(boll_x,boll_y,boll_height,boll_width);
    Player_pad=new Rectangle(Player_x,Player_y,pad_height,pad_width);
    ai_pad=new Rectangle(ai_x,ai_y,pad_height,pad_width);

    if(boll_rect.intersects(Player_pad) || boll_rect.intersects(ai_pad)){
                  dy*=-1;
                  move_status=true;
          }
    repaint();      
      }
      
     }
   }      
public void keyReleased(KeyEvent key){}
public void keyTyped(KeyEvent key){} 

public void keyPressed(KeyEvent key){
if(key.getKeyCode()==KeyEvent.VK_ENTER){
   move obj=new move();
    status=true;
    obj.start();
   new Thread(){
   	public void run(){
   	while(true){   		
   	AI();	
   	try{Thread.sleep(2);}catch(Exception e){}
   	}
   }
   }.start();
  }   
if(key.getKeyCode()==KeyEvent.VK_LEFT){
    Player_x-=25;
  }
if(key.getKeyCode()==KeyEvent.VK_RIGHT){
    Player_x+=25;
  }     
if(Player_x<=0)
   Player_x=0;  
if(Player_x>=435)
	Player_x=435;
repaint();  
   }

public void AI(){ 
  if(boll_y<=375 && boll_y>=80){
if(boll_x<=ai_x+pad_height/4)
   ai_x-=1;
 if(boll_x>=ai_x+pad_height/4)
   ai_x+=1;
  }
    repaint();
  }   
}
