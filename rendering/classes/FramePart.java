package rendering.classes;

import java.util.ArrayList;
import java.util.List;

public class FramePart {
    public List<Pixel> partList = new ArrayList<Pixel>();

    public FramePart(List<Pixel> pixelArray){
        partList = pixelArray;
    }

    public int[] getPartDimensions(){
        
        int rightMost = 0;
        for(Pixel p : partList){
            if(p.x > rightMost){
                rightMost = p.x;
            }
        }
        int leftMost = rightMost;
        for(Pixel p : partList){
            if(p.x < leftMost){
                leftMost = p.x;
            }
        }
        int upMost = 0;
        for(Pixel p : partList){
            if(p.y > upMost){
                upMost = p.y;
            }
        }
        int downMost = upMost;
        for(Pixel p : partList){
            if(p.y < downMost){
                downMost = p.y;
            }
        }
        int[] results = new int[4];
        results[0] = leftMost;
        results[1] = downMost;
        results[2] = rightMost - leftMost;
        results[3] = upMost - downMost;
        return results;
    }
}