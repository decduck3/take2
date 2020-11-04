package engine.rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.rendering.classes.FramePart;
import engine.rendering.classes.Pixel;

public class WindowRenderer extends JPanel {

    /**
     *
     */
    public List<List<FramePart>> buffer = new ArrayList<List<FramePart>>();

    public JFrame window;

    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g){
        List<FramePart> toDraw = buffer.get(0);
        //System.out.println("call");
        for(FramePart part : toDraw){
            //System.out.println("part");
            for(Pixel p : part.partList){
                g.setColor(p.color);
                g.drawLine(p.x, p.y, p.x, p.y);
                //System.out.println("draw");
            }
        }
    }

    public void Render(){
        if(buffer.size() > 0){
            //System.out.println(buffer.size());
            for(FramePart fp : buffer.get(0)){
                int[] dim = fp.getPartDimensions();
                //System.out.println(dim[0] + "-" + dim[1] + ":" + dim[2] + "-" + dim[3]);
                window.repaint(dim[0], dim[1], dim[2], dim[3]);
            }
            buffer.remove(0);
        }
    }
}