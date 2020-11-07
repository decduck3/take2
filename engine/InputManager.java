package engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InputManager extends KeyAdapter{
    public char keyPressed;
    public boolean currentlyPressed = false;

    @Override
    public void keyPressed(KeyEvent e){
        keyPressed = e.getKeyChar();
        currentlyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e){
        currentlyPressed = false;
    }

    public boolean GetKeyDown(char character){
        return keyPressed == character && currentlyPressed;
    }
}

