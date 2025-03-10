public class OffByN implements CharacterComparator {

    private int offset = 0;

    public OffByN(int offset) {
        this.offset = offset;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.offset;
    }

}
