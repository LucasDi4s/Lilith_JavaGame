package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

    // Array de botões do menu
    private MenuButton[] buttons = new MenuButton[3];

    // Imagens de fundo do menu
    private BufferedImage backgroundImg, backgroundImgPink;

    // Posição e dimensões do menu
    private int menuX, menuY, menuWidth, menuHeight;

    // Construtor da classe Menu
    public Menu(Game game) {
        super(game);
        loadButtons();       // Inicializa os botões do menu
        loadBackground();    // Carrega as imagens de fundo do menu
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    // Método para carregar a imagem de fundo do menu
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }

    // Método para carregar os botões do menu
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }

    // Implementação do método update da interface Statemethods
    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    // Implementação do método draw da interface Statemethods
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
    }

    // Implementação do método mouseClicked da interface Statemethods
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    // Implementação do método mousePressed da interface Statemethods
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    // Implementação do método mouseReleased da interface Statemethods
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }

        resetButtons();
    }

    // Método para redefinir o estado dos botões
    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }

    // Implementação do método mouseMoved da interface Statemethods
    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    // Implementação do método keyPressed da interface Statemethods
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    // Implementação do método keyReleased da interface Statemethods
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
