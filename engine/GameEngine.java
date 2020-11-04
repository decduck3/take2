package engine;

import engine.rendering.GameRenderer;

public class GameEngine {
    public GameRenderer renderer;

    public GameEngine(){
        renderer = new GameRenderer();
    }
}
