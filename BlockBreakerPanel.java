import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Jerrydream on 2016-09-24.
 */
public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<Block>();
    Block paddle;
    Thread thread;
    Animate animate;

    BlockBreakerPanel() {
        // constructor of BlockBreaker
        addKeyListener(this);
        // if a java object can gain focus or not. It is default to be true, we set it anyway just in case
        setFocusable(true);
        paddle = new Block(175, 480, 150, 25, "img/paddle.png");
        for (int i=0; i<8; i++) {
            blocks.add(new Block(i*60+2, 0, 60, 25, "img/blue.png"));
        }
        for (int i=0; i<8; i++) {
            blocks.add(new Block(i*60+2, 25, 60, 25, "img/red.png"));
        }
        for (int i=0; i<8; i++) {
            blocks.add(new Block(i*60+2, 50, 60, 25, "img/green.png"));
        }
        for (int i=0; i<8; i++) {
            blocks.add(new Block(i*60+2, 75, 60, 25, "img/yellow.png"));
        }
    }

    public void paintComponent(Graphics g) {
        // this method is basically letting print be as if we have not overwritten painComponent function
        // http://stackoverflow.com/questions/28724609/what-does-super-paintcomponentg-do
        super.paintComponent(g);
        for (Block b: blocks)
            b.draw(g, this);
        paddle.draw(g, this);
    }

    public void update() {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                paddle.x = paddle.x > 0 ? paddle.x-15 : paddle.x;
                break;
            case KeyEvent.VK_RIGHT:
                paddle.x = paddle.x < (getWidth() - paddle.width) ? paddle.x+15 : paddle.x;
                break;
            case KeyEvent.VK_ENTER:
                animate = new Animate(this);
                thread = new Thread(animate);
                thread.start();
            default:
                // do nothing, ignore all other keys
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
