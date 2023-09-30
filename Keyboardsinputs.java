package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static utilz.Constants.Directions.*;

public class Keyboardsinputs implements KeyListener {

    private GamePanel gamepanel;

    public Keyboardsinputs(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamepanel.getGameclass().getPlayer().setUp(false);
            case KeyEvent.VK_S:
                gamepanel.getGameclass().getPlayer().setDown(false);
            case KeyEvent.VK_A:
                gamepanel.getGameclass().getPlayer().setLeft(false);
            case KeyEvent.VK_D:
                gamepanel.getGameclass().getPlayer().setRight(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamepanel.getGameclass().getPlayer().setUp(true);
            case KeyEvent.VK_S:
                gamepanel.getGameclass().getPlayer().setDown(true);
            case KeyEvent.VK_A:
                gamepanel.getGameclass().getPlayer().setLeft(true);
            case KeyEvent.VK_D:
                gamepanel.getGameclass().getPlayer().setRight(true);
                break;
        }

    }
}
