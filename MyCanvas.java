import java.awt.*;
import javax.swing.*;

// // class to make canvas
class MyCanvas extends JPanel {
  Scene scene;
  int width = 800;
  int height = 600;
  // class constructor
  public MyCanvas(Scene s) {
    super();
    scene = s;
    //this.setSize(width, height);
    //this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    // x = 0;
  }

  // change size variables
  public void setSize(int w, int h){
    width = w;
    height = h;
  }

  // clear screen and draw border
  public void clear(Graphics g, int width, int height) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(0, 0, width, height);
  }

  // paints on the screen
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(0, 0, width, height);
    
    // draws all of the sprites
    for (Sprite s : scene.sprites){
      g.drawImage(s.getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
    }
    // draws all of the text objects
    for (Text t : scene.labels){
      if(t.active){
        g.setFont(new Font("Verdana", Font.ITALIC, t.size));
        g.drawString(t.msg, t.x, t.y);
      }
    }
  }

}