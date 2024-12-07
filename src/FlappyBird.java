import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyBird extends JPanel implements ActionListener{

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

    // Game Logic
    Bird bird;
    // Movement of bird in Y direction (Negative for Upwards, Positive for Downwards)
    private int velocityY = -9;
    private int gravityFall = 1;
    // Game Timer Loop --> 60 frames per second
    Timer gameLoop;

    FlappyBird(){
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(new Color(243, 205, 77 ));

        // Load and converting ImageIcon to Image by getImage() method.
        gameBackground= new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        flappyBirdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        // Construct bird
        bird = new Bird(flappyBirdImg);
        // Game Loop for repeating of paintComponent() method.
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
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

    }

    public void move()
    {
        // Velocity decreases by Gravity in successive frames.
        velocityY += gravityFall;
        // Bird falls towards down in every frame by velocityY position.
        bird.y += velocityY;
        // Bird can not exceed the range of top frame boundary.
        bird.y = Math.max(bird.y, 0);

    }

}
