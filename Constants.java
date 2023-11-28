package utilz;

import main.Game;

/**
 * Contém constantes utilizadas em diferentes partes do jogo.
 */
public class Constants {

    // Gravidade aplicada no jogo.
    public static final float GRAVITY = 0.04f * Game.SCALE;

    // Velocidade da animação (em milissegundos).
    public static final int ANI_SPEED = 25;

    /**
     * Contém constantes relacionadas a objetos no jogo.
     */
    public static class ObjectConstants {

        // Tipos de objetos.
        public static final int RED_POTION = 0;
        public static final int BLUE_POTION = 1;
        public static final int BARREL = 2;
        public static final int BOX = 3;
        public static final int SPIKE = 4;

        // Valores associados a poções.
        public static final int RED_POTION_VALUE = 15;
        public static final int BLUE_POTION_VALUE = 10;

        // Dimensões padrão dos contêineres.
        public static final int CONTAINER_WIDTH_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT_DEFAULT = 30;

        // Dimensões ajustadas dos contêineres.
        public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

        // Dimensões padrão das poções.
        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;

        // Dimensões ajustadas das poções.
        public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
        public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

        // Dimensões padrão dos espinhos.
        public static final int SPIKE_WIDTH_DEFAULT = 32;
        public static final int SPIKE_HEIGHT_DEFAULT = 32;

        // Dimensões ajustadas dos espinhos.
        public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
        public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);

        /**
         * Obtém a quantidade de sprites para um determinado tipo de objeto.
         *
         * @param object_type O tipo de objeto.
         * @return A quantidade de sprites.
         */
        public static int GetSpriteAmount(int object_type) {
            switch (object_type) {
                case RED_POTION, BLUE_POTION:
                    return 7;
                case BARREL, BOX:
                    return 8;
            }
            return 1;
        }
    }

    /**
     * Contém constantes relacionadas a inimigos no jogo.
     */
    public static class EnemyConstants {

        // Tipos de inimigos.
        public static final int CRABBY = 0;

        // Estados possíveis de um inimigo.
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        // Dimensões padrão do Crabby.
        public static final int CRABBY_WIDTH_DEFAULT = 140;
        public static final int CRABBY_HEIGHT_DEFAULT = 93;

        // Dimensões ajustadas do Crabby.
        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        // Deslocamento do desenho do Crabby.
        public static final int CRABBY_DRAWOFFSET_X = (int) (44 * Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int) (52 * Game.SCALE);

        /**
         * Obtém a quantidade de sprites para um determinado tipo e estado de
         * inimigo.
         *
         * @param enemy_type O tipo de inimigo.
         * @param enemy_state O estado do inimigo.
         * @return A quantidade de sprites.
         */
        public static int GetSpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }

            return 0;
        }

        /**
         * Obtém a quantidade máxima de vida para um determinado tipo de
         * inimigo.
         *
         * @param enemy_type O tipo de inimigo.
         * @return A quantidade máxima de vida.
         */
        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }

        /**
         * Obtém o dano causado por um determinado tipo de inimigo.
         *
         * @param enemy_type O tipo de inimigo.
         * @return O dano causado.
         */
        public static int GetEnemyDmg(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 15;
                default:
                    return 0;
            }

        }

    }

    /**
     * Contém constantes relacionadas ao ambiente do jogo.
     */
    public static class Environment {

        // Dimensões padrão das nuvens grandes.
        public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;

        // Dimensões ajustadas das nuvens grandes.
        public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

        // Dimensões padrão das nuvens pequenas.
        public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

        // Dimensões ajustadas das nuvens pequenas.
        public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
    }

    /**
     * Contém constantes relacionadas à interface do usuário (UI) do jogo.
     */
    public static class UI {

        /**
         * Contém constantes relacionadas a botões na UI.
         */
        public static class Buttons {

            // Dimensões padrão dos botões.
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;

            // Dimensões ajustadas dos botões.
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        /**
         * Contém constantes relacionadas a botões de pausa na UI.
         */
        public static class PauseButtons {

            // Dimensões padrão do botão de som.
            public static final int SOUND_SIZE_DEFAULT = 42;

            // Dimensões ajustadas do botão de som.
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        /**
         * Contém constantes relacionadas a botões URM na UI.
         */
        public static class URMButtons {

            // Dimensões padrão dos botões URM.
            public static final int URM_DEFAULT_SIZE = 56;

            // Dimensões ajustadas dos botões URM.
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        /**
         * Contém constantes relacionadas a botões de volume na UI.
         */
        public static class VolumeButtons {

            // Dimensões padrão dos botões de volume.
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            // Dimensões ajustadas dos botões de volume e do slider.
            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    /**
     * Contém constantes relacionadas a direções no jogo.
     */
    public static class Directions {

        // Constantes para direções.
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    /**
     * Contém constantes relacionadas ao jogador no jogo.
     */
    public static class PlayerConstants {

        // Estados possíveis do jogador.
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int LONG_JUMP = 4;
        public static final int GRAB_ATTACK = 5;
        public static final int ROLLING = 6;
        public static final int ATTACK = 7;
        public static final int ATTACK_JUMP_1 = 8;
        public static final int QUICK_ATTACK = 9;
        public static final int QUICK_ATTACK_2 = 10;
        public static final int QUICK_ATTACK_3 = 11;
        public static final int ULT_DEFENSE = 12;
        public static final int HIT = 13;
        public static final int ENEMY_HIT = 14;
        public static final int DEATH = 15;

        /**
         * Obtém a quantidade de sprites para um determinado estado do jogador.
         *
         * @param player_action O estado do jogador.
         * @return A quantidade de sprites.
         */
        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case DEATH:
                    return 6;
                case IDLE:
                    return 6;
                case JUMP:
                    return 3;
                case FALLING:
                    return 3;
                case LONG_JUMP:
                    return 18;
                case GRAB_ATTACK:
                    return 6;
                case ROLLING:
                    return 6;
                case ATTACK:
                    return 6;
                case ATTACK_JUMP_1:
                    return 7;
                case QUICK_ATTACK:
                    return 5;
                case QUICK_ATTACK_2:
                    return 6;
                case QUICK_ATTACK_3:
                    return 6;
                case ULT_DEFENSE:
                    return 10;
                case HIT:
                    return 10;
                case ENEMY_HIT:
                    return 4;

                default:
                    return 1;
            }
        }
    }

}
