import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameWindow extends JFrame {

    protected int frameWidth = 360;
    protected int frameHeight = 640;

    FrameWindow()
    {
//        ImageIcon windowIcon =

        this.setTitle("Flappy Bird Game");
        this.setSize(new Dimension(frameWidth, frameHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        FlappyBird flappyBird = new FlappyBird();
        this.add(flappyBird);
        this.pack();
        flappyBird.requestFocus();

        this.setVisible(true);

    }
}
