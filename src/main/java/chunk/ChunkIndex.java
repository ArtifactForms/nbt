package chunk;

public class ChunkIndex {

    private int x;
    private int z;

    public ChunkIndex(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChunkIndex other = (ChunkIndex) obj;
        if (x != other.x)
            return false;
        if (z != other.z)
            return false;
        return true;
    }

}
