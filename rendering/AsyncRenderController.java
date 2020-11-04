package rendering;

public class AsyncRenderController extends Thread {
    public WindowRenderer renderer;

    public AsyncRenderController(WindowRenderer renderer) {
        this.renderer = renderer;
    }

    public void run() {
        renderer.Render();
        new AsyncRenderController(renderer).start();
    }
}