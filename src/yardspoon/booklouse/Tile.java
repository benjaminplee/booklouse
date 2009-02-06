package yardspoon.booklouse;

class Tile {
    private final String letter;
    private final Tile.Type type;

    Tile(String letter) {
        this(letter, Tile.Type.STANDARD);
    }

    Tile(String letter, Type type) {
        letter = letter.toUpperCase();

        if(letter.equals("Q")) {
            this.letter = "QU";
        }
        else {
            this.letter = letter;
        }

        this.type = type;
    }

    String letter() {
        return letter;
    }

    Tile.Type type() {
        return type;
    }

    static enum Type {
        STANDARD,
        GREEN,
        GOLD,
        DIAMOND,
        FIRE;
    }

    @Override
    public String toString() {
        return "[" + letter() + ">" + type().toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if ((this.letter == null) ? (other.letter != null) : !this.letter.equals(other.letter)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.letter != null ? this.letter.hashCode() : 0);
        hash = 83 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
