import javax.swing.*;
import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;

public class FrameWindow extends JFrame{

    protected int frameWidth = 360;
    protected int frameHeight = 640;

    FlappyBird flappyBird;
//    GameManager gameManager;

    FrameWindow() {

        Image lord = new ImageIcon(getClass().getResource("SriRadha.png")).getImage();
        this.setIconImage(lord);

        this.setTitle("Flappy Bird Game");
        this.setSize(new Dimension(frameWidth, frameHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        flappyBird = new FlappyBird();
        this.add(flappyBird);
        this.addKeyListener(flappyBird);
        flappyBird.requestFocus();
        this.setVisible(true);

//        gameManager = GameManager.getInstance();

        this.pack();
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !gameManager.isRunning()){
//            System.out.println("Exit Protocol started ...");
//            this.dispose();
//            System.exit(0);
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

}
