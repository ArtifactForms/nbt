package schematic;

public class BytePair {

    private byte a;
    private byte b;

    public BytePair(byte a, byte b) {
        super();
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + b;
        return result;
    }

}