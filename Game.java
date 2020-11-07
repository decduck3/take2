import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        FramePart cube = cube();
        
        System.out.println(engine.renderer.wRenderer.panel.isOptimizedDrawingEnabled());

        while(engine.isRunning){

            engine.renderer.AddRender(background);
            engine.renderer.AddRender(framerate());

            if(engine.inputManager.GetKeyDown('y')){
                engine.renderer.AddRender(cube);
            }
            
            engine.renderer.PushRender();
        }
        
    }
    public static FramePart framerate(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < new Random().nextInt(100); x++){
            for(int y = 0; y < new Random().nextInt(100); y++){
                results.add(new Pixel(x, y, Color.GREEN));
            }
        }
        return new FramePart(results);
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
    public static FramePart cube(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < engine.renderer.getWidth()/2; x++){
            for(int y = 0; y < engine.renderer.getHeight()/2; y++){
                results.add(new Pixel(x, y,  Color.BLUE));
            }
        }
        return new FramePart(results);
    }
    public static <T> List<T> fromArrayToList(T[] a) { 
	    return Arrays.stream(a).collect(Collectors.toList());
	}
}