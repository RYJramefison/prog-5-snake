package snake.entity;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private final LinkedList<Position> body = new LinkedList<>();

    public Snake() {
        body.add(new Position(10, 10));
        body.add(new Position(10, 9));
        body.add(new Position(10, 8));
    }

    public List<Position> getBody() {
        return body;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Position computeNextHead(Direction d) {
        return getHead().move(d);
    }

    public void move(Position newHead) {
        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow(Position newHead) {
        body.addFirst(newHead);
    }

    public boolean contains(Position p) {
        return body.contains(p);
    }
}
