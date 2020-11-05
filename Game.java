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
            engine.renderer.AddRender(createCube());
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

    public static FramePart createCube(){
        final Pixel[] results = new Pixel[0];
        final int xLimit = new Random().nextInt(engine.renderer.getWidth());
        final int yLimit = new Random().nextInt(engine.renderer.getHeight());
        final Pixel pixel = new Pixel(0, 0,  Color.BLUE);
        final int[] x = new int[1];
        final int[] y = new int[1];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                while(x[0] < xLimit){
                    while(y[0] < yLimit){
                        pixel.x = x[0];
                        pixel.y = y[0];
                        results[results.length] = pixel;
                        x[0] += 1;
                        y[0] += 1;
                    }
                }
            }
        };

        Range range = Range.create(xLimit * yLimit);

        kernel.execute(range);

        return new FramePart(fromArrayToList(results));
    }
}