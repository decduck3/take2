import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.GameEngine;
import engine.rendering.GameRenderer;
import engine.rendering.classes.FramePart;
import engine.rendering.classes.Pixel;

class Game{
    public static GameEngine engine;
    public static void main(String[] args){
        engine = new GameEngine();

        engine.renderer.StartRenderCycle();

        FramePart background = background();

        while(true){

            engine.renderer.AddRender(background);
            engine.renderer.AddRender(createCube());
            engine.renderer.PushRender();
            
        }
        
    }
    public static FramePart background(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < engine.renderer.getWidth(); x++){
            for(int y = 0; y < engine.renderer.getHeight(); y++){
                results.add(new Pixel(x, y,  Color.YELLOW));
            }
        }
        return new FramePart(results);
    }

    public static FramePart createCube(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < new Random().nextInt(engine.renderer.getWidth()); x++){
            for(int y = 0; y < new Random().nextInt(engine.renderer.getHeight());y++){
                results.add(new Pixel(x, y,  Color.BLUE));
            }
        }
        return new FramePart(results);
    }
}