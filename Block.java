/**
 * Created by Jerrydream on 2016-09-24.
 */

import java.awt.*;

public class Block extends Rectangle {
    Image pic;
    int dx = 3;
    int dy = -3;
    Boolean destroyed, powerup;
    Rectangle left, right;
    Block(int a, int b, int width, int height, String s) {
        // a, b indicates the location of the block, s indicates which image we should use for this block
        this.x = a;
        this.y = b;
        this.width = width;
        this.height = height;
        left = new Rectangle(a-1, b, 1, height);
        right = new Rectangle(a+width+1, b, 1, height);
        pic = Toolkit.getDefaultToolkit().getImage(s);
        destroyed = false;
        powerup = false;
    }
    public void draw(Graphics g, Component c) {
        if (!destroyed)
            g.drawImage(pic, x, y, width, height, c);
    }

}
