public class Game extends Scene {
    // initialize variables here
    Sprite background; 
    Sprite cat;
    int score;
    Text scoreLabel;
    int frameCount;
    int timer;
    Text timerLabel;
    Text gameOverLabel;

    public Game() {
        super();
        init();
    }

    public void init() { 
        // initialize variable values
        cat = new Sprite(this, "cat.jpg");
        score = 0;
        scoreLabel = new Text( "Score: " + Integer.toString(score), 20, 20, true, 20);
        frameCount = 0;
        timer = 10;
        timerLabel = new Text( "Timer: " + Integer.toString(timer), width - 100, 20, true, 20);
        gameOverLabel = new Text( "Game Over", 100, height/2, false, 100);
        background = new Sprite(this, "background.jpg");
        
        width = 800; // set the width of the scene
        height = 600; // set the height of the scene
        this.setSceneSize(width, height); // sets the scene size to the values you chose
        this.setTitle("Clicking Game"); // set game title 
        setFrameRate(15); // set the number of frames per second
        
        // write your own initialization code here
        
        background.setWidth(width); // set background size to size of the screen
        background.setHeight(height);
        sprites.add(background); // add background to sprites

        sprites.add(cat); // add cat to sprites
        cat.setX(400); // set position of cat
        cat.setY(300);
        cat.setBoundsType(2); // set bounds type to bounce

        labels.add(scoreLabel); // add Text objects to labels
        labels.add(timerLabel);
        labels.add(gameOverLabel);

        preventResize(); // dont let the user resize the window
        
        start(); // start the game
    }

    // happens every frame
    public void update() {
        frameCount++; // keep track of frames

        // when mouse is down, increase score and move cat to random position and rotation
        if(mouseDown){
            if(cat.mouseOnSprite()){
                score++;
                scoreLabel.msg = "Score: " + Integer.toString(score);
                cat.goToRandomPosition();
                cat.setRotation( (int) (Math.random() * 360));
            }
        }
        // every 15 frames (or 1 second) decrease timer by 1 and show game over if timer is at 0
        if(frameCount%15 == 0){
            timer--;
            timerLabel.msg = "Timer: " + Integer.toString(timer);
            if(timer == 0){
                gameOverLabel.active = true; // show game over label
                stop();
            } 
        }

        // changes velocity for the frame based on what keys are down
        int dx = 0;
        int dy = 0;
        int speed = 10;
        if(keysDown[K_A] == true){
            dx -= speed;
        } else 
        if(keysDown[K_D] == true){
            dx += speed;
        }
        if(keysDown[K_W] == true){
            dy -= speed;
        }
        if(keysDown[K_S] == true){
            dy += speed;
        }
        cat.setDX(dx);
        cat.setDY(dy);
        cat.checkBounds(); // check bounds
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start(); 
    }

}