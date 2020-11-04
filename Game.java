import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rendering.GameRenderer;
import rendering.classes.FramePart;
import rendering.classes.Pixel;

class Game{
    public static GameRenderer renderer;
    public static void main(String[] args){
        
        renderer = new GameRenderer();

        renderer.StartRenderCycle();

        FramePart background = background();

        while(true){

            renderer.AddRender(background);
            renderer.AddRender(createCube());
            renderer.PushRender();
            
        }
        
    }
    public static FramePart background(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < renderer.getWidth(); x++){
            for(int y = 0; y < renderer.getHeight(); y++){
                results.add(new Pixel(x, y,  Color.YELLOW));
            }
        }
        return new FramePart(results);
    }


    public static FramePart createCube(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < new Random().nextInt(renderer.getWidth()); x++){
            for(int y = 0; y < new Random().nextInt(renderer.getHeight());y++){
                results.add(new Pixel(x, y,  Color.BLACK));
            }
        }
        return new FramePart(results);
    }
}