package snake.entity;

import java.util.Random;

public class Food {

    private Position position;

    public Food(int screenHeight, int screenWidth) {
        respawn(screenHeight, screenWidth, null);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int screenHeight, int screenWidth, Snake snake) {
        Random r = new Random();
        Position actualPosition;

        do {
            actualPosition = new Position(
                    r.nextInt(screenHeight - 2) + 1,
                    r.nextInt(screenWidth - 2) + 1
            );
        } while (snake != null && snake.contains(actualPosition));

        this.position = actualPosition;
    }
}
