/**
 * Created by Jerrydream on 2016-09-24.
 */

import java.awt.*;

public class Block extends Rectangle {
    Image pic;
    Block(int a, int b, int width, int height, String s) {
        // a, b indicates the location of the block, s indicates which image we should use for this block
        this.x = a;
        this.y = b;
        this.width = width;
        this.height = height;
        pic = Toolkit.getDefaultToolkit().getImage(s);

    }
    public void draw(Graphics g, Component c) {
        g.drawImage(pic, x, y, width, height, c);
    }

}
