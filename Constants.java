package utilz;

// Classe de constantes usadas no jogo
public class Constants {

    // Classe interna para direções
    public static class Directions {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    // Classe interna para constantes relacionadas ao jogador
    public static class PlayerConstants {

        // Estados do jogador
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

        // Retorna a quantidade de sprites para uma ação específica do jogador
        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING:
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
                case DEATH:
                    return 9;

            }
            return player_action;
        }
    }
}
