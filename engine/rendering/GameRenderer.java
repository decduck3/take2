package engine.rendering;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import engine.rendering.classes.FramePart;

public class GameRenderer {
    public JFrame frame;

    public WindowRenderer wRenderer;

    public List<FramePart> toPush;

    public GameRenderer(){
        wRenderer = new WindowRenderer();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        frame.setVisible(true);
        wRenderer.window = frame;
        frame.setContentPane(wRenderer);
    }

    public void AddRender(FramePart toAdd){
        //System.out.println("addrender");
        if(toPush == null){
            toPush = new ArrayList<FramePart>();
        }
        toPush.add(toAdd);
    }

    public void PushRender(){
        wRenderer.buffer.add(toPush);
        if(wRenderer.buffer.size() > 10){
            wRenderer.buffer.remove(0);
        }
        toPush = null;
    }

    public void StartRenderCycle(){
        new AsyncRenderController(wRenderer, 60).start();
    }

    public int getWidth(){
        return frame.getWidth();
    }
    public int getHeight(){
        return frame.getHeight();
    }
}