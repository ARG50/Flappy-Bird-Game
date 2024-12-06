import javax.swing.*;
import java.awt.*;

public class FlappyBird extends JPanel{

    protected int frameWidth = 360;
    protected int frameHeight = 640;
    ImageIcon gameBackground;
    ImageIcon flappyBirdImg;
    ImageIcon topPipeImg;
    ImageIcon bottomPipeImg;

    FlappyBird(){
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(new Color(243, 205, 77 ));


    }

}
