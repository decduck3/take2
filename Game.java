import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;

import engine.GameEngine;
import engine.rendering.GameRenderer;
import engine.rendering.classes.FramePart;
import engine.rendering.classes.Pixel;

class Game {
    public static GameEngine engine;

    public static int playerX = 10;
    public static int playerY = 10;

    public static void main(String[] args) {
        engine = new GameEngine();

        //engine.renderer.StartRenderCycle();

        System.out.println(engine.renderer.getWidth() + ":" + engine.renderer.getHeight());

        FramePart background = background();

        System.out.println(engine.renderer.wRenderer.panel.isOptimizedDrawingEnabled());

        while (engine.isRunning) {

            engine.renderer.AddRender(background);
            //engine.renderer.AddRender(framerate());

            if (engine.inputManager.GetKeyDown('w')) {
                playerY += 30;
            }
            if (engine.inputManager.GetKeyDown('s')) {
                playerY -= 30;
            }
            if (engine.inputManager.GetKeyDown('a')) {
                playerX -= 30;
            }
            if (engine.inputManager.GetKeyDown('d')) {
                playerX += 30;
            }

            engine.renderer.AddRender(player());

            engine.renderer.PushRender();

            engine.renderer.wRenderer.Render();
        }

    }

    public static FramePart framerate() {
        List<Pixel> results = new ArrayList<Pixel>();
        for (int x = 0; x < new Random().nextInt(100); x++) {
            for (int y = 0; y < new Random().nextInt(100); y++) {
                results.add(new Pixel(x, y, Color.GREEN));
            }
        }
        return new FramePart(results);
    }

    public static FramePart background() {
        File bg = new File("./assets/bg.jpg");
        BufferedImage bufferedImage = new BufferedImage(240, 240, BufferedImage.TYPE_INT_ARGB);
        try {
            bufferedImage = ImageIO.read(bg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < engine.renderer.getWidth(); x++){
            for(int y = 0; y < engine.renderer.getHeight(); y++){
                results.add(new Pixel(x, y, new Color(bufferedImage.getRGB(x, y), true)));
            }
        }
        return new FramePart(results);
    }
    public static FramePart player(){
        List<Pixel> results = new ArrayList<Pixel>();
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                results.add(new Pixel(x + playerX, y + playerY,  Color.BLUE));
            }
        }
        return new FramePart(results);
    }
    public static <T> List<T> fromArrayToList(T[] a) { 
	    return Arrays.stream(a).collect(Collectors.toList());
	}
}