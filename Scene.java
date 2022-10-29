import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Scene extends JFrame implements ActionListener, KeyListener, MouseInputListener {
  public MyCanvas c;
  private Timer t = new Timer(1000 / 20, this);
  boolean keepGoing = true;
  boolean paused;
  int width;
  int height;
  public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
  public ArrayList<Text> labels = new ArrayList<Text>();
  public boolean[] keysDown = new boolean[256];
  public boolean mouseDown = false;
  public int mouseX;
  public int mouseY;

  // keyboard constants
  int K_A = 65; int K_B = 66; int K_C = 67; int K_D = 68; int K_E = 69; int K_F = 70; int K_G = 71;
    int K_H = 72; int K_I = 73; int K_J = 74; int K_K = 75; int K_L = 76; int K_M = 77; int K_N = 78;
    int K_O = 79; int K_P = 80; int K_Q = 81; int K_R = 82; int K_S = 83; int K_T = 84;int  K_U = 85;
    int K_V = 86; int K_W = 87; int K_X = 88; int K_Y = 89; int K_Z = 90;
    int K_LEFT = 37; int K_RIGHT = 39; int K_UP = 38;int K_DOWN = 40; int K_SPACE = 32;
    int K_ESC = 27; int K_PGUP = 33; int K_PGDOWN = 34; int K_HOME = 36; int K_END = 35;
    int K_0 = 48; int K_1 = 49; int K_2 = 50; int K_3 = 51; int K_4 = 52; int K_5 = 53;
    int K_6 = 54; int K_7 = 55; int K_8 = 56; int K_9 = 57; 

  public Scene() {
    c = new MyCanvas(this);
    this.add(c, BorderLayout.CENTER);
    this.setSceneSize(800, 600);
    this.setTitle("Default Title");
    this.setVisible(true);
    setResizable(true);

    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);

    // close frame when user exits out
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    for (int i = 0; i < 256; i++) {
      keysDown[i] = false;
    }

  }

  // takes an int and sets a timer to do that many frames per second
  public void setFrameRate(int fps) {
    t = new Timer(1000 / fps, this);
  }

  // is called when timer goes off
  public void actionPerformed(ActionEvent e) {
    if (keepGoing) {
      c.repaint();
      mainLoop();
    }
  }

  // starts timer
  public void start() {
    t.start();
  }
  // stops timer
  public void stop() {
    t.stop();
  }

  // clears screen
  public void clear() {
    c.clear(getGraphics(), width, height);
  }

  // sets size of screen, updating variables and resetting border
  public void setSceneSize(int newWidth, int newHeight) {
    width = newWidth;
    height = newHeight;
    this.setSize(width+15, height+40);
    c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    //c.setSize(newWidth, newHeight);
  }

  // calls update and repaint. then updates sprite position acording to their speed if they are active
  public void mainLoop() {
    update();
    for (Sprite s : sprites) {
      if (s.getActive()) {
        s.update();
      }
    }
    c.repaint();
  }

  // method for game maker to overwrite
  public void update() {
    // Should be overwritten
  }

  // sets title for JFrame --working
  public void setGameTitle(String t) {
    this.setTitle(t);
  }

  // hides cursor --working
  public void hideCursor() {
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        cursorImg, new Point(0, 0), "blank cursor");
    this.getContentPane().setCursor(blankCursor);
  }

  // show cursor --working
  public void showCursor() {
    this.getContentPane().setCursor(Cursor.getDefaultCursor());
  }

  // allow or prevent user to resize the windowe
  public void allowResize() {
    setResizable(true);
  }
  public void preventResize() {
    setResizable(false);
  }

  // update keystate when key is pressed
  public void keyPressed(KeyEvent e) {
    keysDown[e.getKeyCode()] = true;
  }
  // update keystate when key is released
  public void keyReleased(KeyEvent e) {
    keysDown[e.getKeyCode()] = false;
  }

  public void keyTyped(KeyEvent e) {
    // System.out.println("test");
  }

  @Override
  public void mouseClicked(java.awt.event.MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mousePressed(java.awt.event.MouseEvent e) {
    mouseDown = true;
  }
  
  @Override
  public void mouseReleased(java.awt.event.MouseEvent e) {
    mouseDown = false;
    
  }

  @Override
  public void mouseEntered(java.awt.event.MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(java.awt.event.MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseDragged(java.awt.event.MouseEvent e) {
    // TODO Auto-generated method stub
    
  }


  @Override // update mouse position when mouse is moved
  public void mouseMoved(java.awt.event.MouseEvent e) {
    Point p = MouseInfo.getPointerInfo().getLocation();  
    mouseX = p.x - 8;
    mouseY = p.y - 30;
  }

  // public void togglePause() {
  //   // handles pausing --working
  //   if (paused) {
  //     t.start();
  //     paused = false;
  //   } else {
  //     t.stop();
  //     paused = true;
  //     Graphics g = getGraphics();
  //     g.setFont(new Font("Arial", 1, 40));
  //     g.drawString("Game is paused", width / 2 - 150, height / 2);
  //   }
  // }
}
