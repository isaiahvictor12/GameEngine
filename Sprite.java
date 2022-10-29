import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Sprite {
    private Scene scene; // the scene where the sprite can be drawn
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;
    private boolean active; // should the sprite be drawn
    private BufferedImage image; // the current image to draw
    private BufferedImage originalImage; // the original image for the sprite
    private int rotationAngle; // in degrees
    private int boundsType;


    public Sprite(Scene s, String _imagePath) {
        scene = s;
        x = 0;
        y = 0;
        dx = 0;
        dy = 0;
        width = 100;
        height = 100;
        active = true;
        boundsType = 0;
        rotationAngle = 0;

        image = null;
        // attempt to load the image from a file
        try {
            image = ImageIO.read(new File(_imagePath));
            originalImage = image;
        } catch (Exception e) {
            System.out.println("file error");
        }
    }
    
    // changes variable and image
    public void setRotation(int angle){
        rotationAngle = angle;
        rotate();
    }
    // returns the angle taht the image is rotated at
    public int getRotation(){
        return rotationAngle;
    }
    // sprite will rotate rotationAngle degrees
    public void rotate(){    
        BufferedImage newImage = new BufferedImage(
            originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
 
        Graphics2D g2 = newImage.createGraphics();
 
        g2.rotate(Math.toRadians(rotationAngle), originalImage.getWidth() / 2,
                  originalImage.getHeight() / 2);
        g2.drawImage(originalImage, null, 0, 0);
 
        image = newImage;
    }

    // updates position based on speed
    public void update() {
        x += dx;
        y += dy;
        checkBounds();
    }

    // bounds check
    // 0 is nothung. 1 is wrap. 2 is bounce.
    public void checkBounds() {
        if (boundsType == 1) {
            if (x > scene.width) {
                x = 0;
            } else if (x + width < 0) {
                x = scene.width;
            }
            if (y > scene.height) {
                y = 0;
            } else if (y + height < 0) {
                y = scene.height;
            }
        } else if (boundsType == 2) {
            if (x + width >= scene.width) {
                x = scene.width - width;
                dx = -dx;
            } else if (x <= 0){
                x = 0;
                dx = -dx;
            }
            if (y + height >= scene.height) {
                y = scene.height - height;
                dy = -dy;
            } else if (y <= 0){
                y = 0;
                dy = -dy;
            }
        }
    }

    // rectangle collision check
    // checks if sprite is touching another sprites
    public boolean collidesWith(Sprite s) {
        boolean collides = true;
        if (x > s.getX() + s.getWidth() ||
                x + width < s.getX() ||
                y > s.getY() + s.getHeight() ||
                y + width < s.getY()) {
            collides = false;
        }

        return collides;
    }

    // sprite goes to a random position
    public void goToRandomPosition() {
        setX((int) (Math.random() * (scene.width - getWidth())));
        setY((int) (Math.random() * (scene.height - getHeight())));
    }

    // rectangle click detection
    public boolean mouseOnSprite() {
        return (scene.mouseX >= x && scene.mouseX <= x + width && scene.mouseY >= y && scene.mouseY <= y + height);
    }

    public BufferedImage getImage() {
        return image;
    }

    // getters and setters for x, y, dx, dy, width, height, and active
    public void setX(int newX) {
        x = newX;
    }

    public int getX() {
        return x;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getY() {
        return y;
    }

    public void setDX(int newDX) {
        dx = newDX;
    }

    public void setDY(int newDY) {
        dy = newDY;
    }

    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }

    public void setWidth(int newW) {
        width = newW;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int newH) {
        height = newH;
    }
    public int getHeight() {
        return height;
    }

    public void setActive(Boolean b) {
        active = b;
    }
    public Boolean getActive() {
        return active;
    }

    public void setBoundsType(int t) {
        boundsType = t;
    }
    public int getBoundsType() {
        return boundsType;
    }


}