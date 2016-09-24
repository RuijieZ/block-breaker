/**
 * Created by Jerrydream on 2016-09-24.
 */
public class Animate implements Runnable {
    // Runnable is an interface that can be put into a thread, and describe what the thread is supposed to do
    // http://stackoverflow.com/questions/13327571/in-a-simple-to-understand-explanation-what-is-runnable-in-java

    BlockBreakerPanel bp;
    Animate(BlockBreakerPanel b) {
        bp = b;
    }

    public void run() {
        while(true) {
            bp.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
