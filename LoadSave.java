package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
public static final String PLAYER_LILITH = "Lilith_player.png";
public static final String LEVEL_LILITH = "player.png";

    public static BufferedImage GetSpriteLilith(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+ filename); // Carrega a imagem sprite sheet
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}