package snake.entity;

import java.io.IOException;

import static snake.entity.Direction.*;

public class Game {

    private static final int TICK_DELAY = 120;

    private static final char SNAKE_CHAR = '#';
    private static final char FOOD_CHAR = '*';
    private static final char WALL_CHAR = 'X';
    private static final char EMPTY_CHAR = ' ';

    private final Screen screen = new Screen(20, 40);

    private final Snake snake = new Snake();
    private final Food food = new Food(screen.getHeight(), screen.getWidth());

    private Direction currentDir = RIGHT;
    private boolean isRunning = true;
    private int score = 0;

    public void start() throws Exception {
        clearScreen();

        while (isRunning) {

            handleInput();
            update();
            render();

            Thread.sleep(TICK_DELAY);
        }
    }

    private void handleInput() {
        try {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                switch (c) {
                    case 'a' -> currentDir = LEFT;
                    case 'd' -> currentDir = RIGHT;
                    case 'w' -> currentDir = UP;
                    case 's' -> currentDir = DOWN;
                }
            }
        } catch (IOException ignored) {}
    }

    private void update() {
        Position newHead = snake.computeNextHead(currentDir);

        if (hitWall(newHead) || snake.contains(newHead)) {
            isRunning = false;
            clearScreen();
            System.out.println("GAME OVER — Score : " + score);
            return;
        }

        if (newHead.equals(food.getPosition())) {
            score++;
            snake.grow(newHead);
            food.respawn(screen.getHeight(), screen.getWidth(), snake);
        } else {
            snake.move(newHead);
        }
    }

    private boolean hitWall(Position p) {
        return p.row <= 0 || p.row >= screen.getHeight() - 1 ||
                p.col <= 0 || p.col >= screen.getWidth() - 1;
    }

    private void render() {
        clearScreen();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < screen.getHeight(); i++) {
            for (int j = 0; j < screen.getWidth(); j++) {

                Position p = new Position(i, j);

                if (p.equals(food.getPosition())) sb.append(FOOD_CHAR);
                else if (snake.contains(p)) sb.append(SNAKE_CHAR);
                else if (i == 0 || j == 0 || i == screen.getHeight() - 1 || j == screen.getWidth() - 1)
                    sb.append(WALL_CHAR);
                else sb.append(EMPTY_CHAR);
            }
            sb.append("\n");
        }

        System.out.print(sb);
        System.out.println("Score : " + score);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
