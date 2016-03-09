package optim.prime.domain;


public final class PrimeRange {

    public final long from;
    public final long to;

    public static Builder from(final long from) {
        return new Builder(from);
    }


    private PrimeRange(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimeRange that = (PrimeRange) o;

        if (from != that.from) return false;
        return to == that.to;

    }

    @Override
    public int hashCode() {
        int result = (int) (from ^ (from >>> 32));
        result = 31 * result + (int) (to ^ (to >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PrimeRange{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public static class Builder {
        private long from;
        private long to;

        Builder(long from) {
            this.from = from;
        }

        public Builder to(final long toVal) {
            this.to = toVal;
            return this;
        }

        public PrimeRange build() {
            return new PrimeRange(from, to);
        }
    }
}
