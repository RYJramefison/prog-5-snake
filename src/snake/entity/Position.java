package snake.entity;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position move(Direction dir) {
        return new Position(row + dir.dy, col + dir.dx);
    }
}
