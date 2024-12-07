import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    private int frameWidth = 360;
    private int frameHeight = 640;
    private Image gameBackground;
    private Image flappyBirdImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

//    Making Bird Object
    private int birdX = frameWidth/8;
    private int birdY = frameHeight/2;
    private int birdWidth = 34;
    private int birdHeight = 24;

    protected class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image birdImg;

        Bird(Image img){
            this.birdImg = img;
        }
    }

    // Pipes
    private int pipeX = frameWidth;
    private int pipeY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;

    protected class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image pipeImg;
        // Check if bird passed between pipes.
        boolean passed = false;

        Pipe(Image img){
            this.pipeImg = img;
        }
    }

    // Game Logic
    Bird bird;
    // Movement in X direction(To show that pipes are passing at left side and bird is moving towards right side.)
    private int velocityX = -4;
    // Movement of bird in Y direction (Negative for Upwards, Positive for Downwards)
    private int velocityY = 0; // At the start, the bird is falling.
    private int gravityFall = 1;
    // Game Timer Loop --> 60 frames per second
    Timer gameLoop;
    // Timer for placing pipes.
    Timer placePipesTimer;

    ArrayList<Pipe> pipesArrayList;
    Random random = new Random();

    FlappyBird(){
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(new Color(243, 205, 77 ));
        this.setFocusable(true);
        this.addKeyListener(this);

        // Load and converting ImageIcon to Image by getImage() method.
        gameBackground= new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        flappyBirdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        // Construct bird
        bird = new Bird(flappyBirdImg);
        // Construction of Pipes list
        pipesArrayList = new ArrayList<Pipe>();


        // Game Loop for repeating of paintComponent() method.
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    public void placePipes(){
        // (0 - 1) * pipeHeight/2 --> (0 - 256)
        // 0 - 128 - (0 - 256) ---> 1/4 pipeHeight ---> 3/4 pipeHeight

        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipesArrayList.add(topPipe);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        draw(g);
        System.out.println("check draw");
    }

    public void draw(Graphics g){
        // drawing the background of the game.
        g.drawImage(gameBackground, 0, 0, frameWidth, frameHeight, null);
        // drawing the bird
        g.drawImage(bird.birdImg, bird.x, bird.y, bird.width, bird.height, null);

        for(int i = 0; i<pipesArrayList.size(); i++){
            Pipe pipe = pipesArrayList.get(i);
            g.drawImage(pipe.pipeImg, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

    }

    // Movement method of Bird
    public void move()
    {
        // Bird
        velocityY += gravityFall; // Velocity decreases by Gravity in successive frames.
        bird.y += velocityY; // Bird falls towards down in every frame by velocityY position.
        bird.y = Math.max(bird.y, 0); // Bird can not exceed the range of top frame boundary.

        //Pipes
        for (int i = 0; i<pipesArrayList.size(); i++){
            Pipe pipe = pipesArrayList.get(i);
            pipe.x += velocityX;
        }


    }

}
