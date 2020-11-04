package engine.rendering;

public class AsyncRenderController extends Thread {
    public WindowRenderer renderer;
    public int frameRate;

    public AsyncRenderController(WindowRenderer renderer, int frameRate) {
        this.renderer = renderer;
        this.frameRate = frameRate;
    }

    public void run() {
        renderer.Render();
        try {
            Thread.sleep(1000 / frameRate);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new AsyncRenderController(renderer, frameRate).start();
    }
}