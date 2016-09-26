import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jerrydream on 2016-09-24.
 */
public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> ball = new ArrayList<Block>();
    ArrayList<Block> powerup = new ArrayList<Block>();
    Block paddle;
    Thread thread;
    Animate animate;
    int blockSize;
    Random random;

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
        // initially, we add one ball to the game
        ball.add(new Block(237, 437, 25, 25, "img/ball.png"));
        blockSize = 25;
        random = new Random();
        for (int i =0; i < 7; i++)
            blocks.get(random.nextInt(32)).powerup = true;

    }

    public void paintComponent(Graphics g) {
        // this method is basically letting print be as if we have not overwritten painComponent function
        // http://stackoverflow.com/questions/28724609/what-does-super-paintcomponentg-do
        super.paintComponent(g);
        for (Block b: blocks)
            b.draw(g, this);
        paddle.draw(g, this);
        for (Block b: ball)
            b.draw(g, this);
        for (Block b: powerup)
            b.draw(g, this);
    }

    public void update() {
        for (Block p: powerup) {
            p.y ++;
            if (p.intersects(paddle) && !p.destroyed) {
                ball.add(new Block(paddle.dx + 25, 437, 25, 25, "img/ball.png"));
                p.destroyed =true;
            }
        }
        for (Block ba: ball) {
            if (ba.x <=0 || (ba.x >= getWidth()- blockSize))
                ba.dx *= -1;
            if (ba.y <= 0 || ba.intersects(paddle))
                ba.dy *= -1;
            ba.x += ba.dx;
            ba.y += ba.dy;
            for (Block b: blocks) {
                if ((ba.intersects(b.left) || ba.intersects(b.right) || ba.intersects(b)) && !b.destroyed) {
                    b.destroyed =true;
                    ba.dx *= -1;
                    if (b.powerup)
                        powerup.add(new Block(b.x, b.y, 25, 19, "img/extra.png"));
                }
            }
        }
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
