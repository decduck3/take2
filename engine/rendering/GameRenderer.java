package engine.rendering;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.GameEngine;
import engine.rendering.classes.FramePart;

public class GameRenderer {
    public JFrame frame;

    public WindowRenderer wRenderer;

    public List<FramePart> toPush;

    public GameEngine engine;

    public GameRenderer() {
        wRenderer = new WindowRenderer();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        frame.setVisible(true);
        wRenderer.frame = frame;
        wRenderer.panel = new JPanel();
        wRenderer.frame.add(wRenderer.panel);
        wRenderer.gameRenderer = this;
        frame.setContentPane(wRenderer);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
                engine.isRunning = false;
            }
            @Override
            public void windowClosed(WindowEvent e) {
                engine.isRunning = false;
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
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