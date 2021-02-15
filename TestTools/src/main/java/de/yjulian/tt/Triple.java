package de.yjulian.tt;

public class Triple<T, G, H> extends Pair<T, G>{

    private final H third;

    public Triple(T first, G second, H third) {
        super(first, second);
        this.third = third;
    }

    public H getThird() {
        return third;
    }
}
