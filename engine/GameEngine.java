package engine;

import engine.rendering.AsyncRenderController;
import engine.rendering.GameRenderer;

public class GameEngine {
    public GameRenderer renderer;
    public InputManager inputManager;
    public boolean isRunning = true;

    public GameEngine(){
        renderer = new GameRenderer();
        renderer.engine = this;
        inputManager = new InputManager();
        renderer.frame.addKeyListener(inputManager);
    }
}
