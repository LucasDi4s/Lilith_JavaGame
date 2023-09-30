package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

public class Mouseinputes implements MouseListener, MouseMotionListener {

    private GamePanel gamepanel;

    public Mouseinputes(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      if(e.getButton() == MouseEvent.BUTTON1)
          gamepanel.getGameclass().getPlayer().setAttacking(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
   
    }

}
