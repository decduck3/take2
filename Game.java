import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.aparapi.*;

import engine.GameEngine;
import engine.rendering.GameRenderer;
import engine.rendering.classes.FramePart;
import engine.rendering.classes.Pixel;

class Game{
    public static GameEngine engine;
    public static boolean isRunning = true;
    public static void main(String[] args){
        engine = new GameEngine();

        engine.renderer.StartRenderCycle();

        FramePart background = background();

        while(isRunning){

            engine.renderer.AddRender(background);
            engine.renderer.PushRender();

            isRunning = engine.renderer.frame != null;
            
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
    public static <T> List<T> fromArrayToList(T[] a) { 
	    return Arrays.stream(a).collect(Collectors.toList());
	}
}