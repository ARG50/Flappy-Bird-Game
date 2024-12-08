import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
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
    private int gravityFall = 1; // Gravity fall

    // Timers
    Timer gameLoop; // Game Timer Loop --> 60 frames per second
    Timer placePipesTimer; // Timer for placing pipes.

    // Is Game Over ?
    boolean gameOver = false; // Check if collision occurs between the pipes or the bird is fallen out of the frame.

    // Checking score
    double score = 0;

    // Making a list of pipes with random position
    ArrayList<Pipe> pipesArrayList; // list of holding top Pipes and bottom Pipes.
    Random random = new Random();  // for shifting pipes upwards with random values

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


        bird = new Bird(flappyBirdImg); // Construct bird
        pipesArrayList = new ArrayList<Pipe>(); // Construction of Pipes list

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
        else if(gameOver && e.getKeyCode() == KeyEvent.VK_SPACE){
            bird.y = birdY; // Resetting Bird's Y position
            velocityY = 0; // VelocityY reset
            pipesArrayList.clear(); // All pipes are reset
            score = 0; // score reset to 0
            // Restart new game
            gameOver = false;
            gameLoop.start();
            placePipesTimer.start();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) { // This action-performed method id for gameLoop
        move();
        repaint();

        if (gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }

    // Collision of Bird only if the bird is collided with pipes.
    public boolean collision(Bird a, Pipe b){
        return a.x < b.x + b.width &&  // a's top left corner doesn't reach to b's top right corner
                a.x + a.width > b.x &&   // a's top right corner passes b's top left corner.
                a.y < b.y + b.height &&  // a's top left corner doesn't reach b's bottom left corner.
                a.y + a.height > b.y; // a's bottom left corner passes b's top left corner.
    }

    public void placePipes(){
        // (0 - 1) * pipeHeight/2 --> (0 - 256)
        // 0 - 128 - (0 - 256) ---> 1/4 pipeHeight ---> 3/4 pipeHeight
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));

        // Creating space for passing the bird between the pipes. Also for shifting bottom pipes.
        int passingSpace = frameHeight/4;

        // Top Pipe
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY; // Top Pipe starts with random shift from upwards
        pipesArrayList.add(topPipe);

        // Bottom Pipe
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + topPipe.height + passingSpace; // From where bottom pipe starts
        pipesArrayList.add(bottomPipe);
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

        //For draw pipes in the frame from ArrayList<Pipe>
        for(int i = 0; i<pipesArrayList.size(); i++){
            Pipe pipe = pipesArrayList.get(i);
            g.drawImage(pipe.pipeImg, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // Score Board
        g.setColor(new Color(44, 62, 80));
        g.setFont(new Font("Cambria", Font.PLAIN, 30));
        if(gameOver){
            g.setColor(new Color(189, 35, 35));
            g.drawString("Score: "+String.valueOf((int) score), 10, 35);
            g.drawString("Game Over!", frameWidth/4, frameHeight/2);


            g.setFont(new Font("Cambria", Font.PLAIN, 26));
            g.drawString("Click 'Space-key' to Restart", frameWidth/15, 6*(frameHeight/10));
            g.drawString("Click 'Esc' to Exit game", frameWidth/7, 6*(frameHeight/8));

        }
        else{
            g.drawString(String.valueOf((int) score), 10, 35);
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

            if(!pipe.passed && bird.x > pipe.x + pipe.width){ // Score Board
                pipe.passed = true;
                score += 1; // If the bird is passed between 2 pipes (top and bottom) then score will be 1 for each pipe.
            }

            if(collision(bird, pipe)) { // Collision
                gameOver = true;
            }
        }

        // Bird is fallen out of the frame then game is over.
        if(bird.y > frameHeight){
            gameOver = true;
        }
    }

}
