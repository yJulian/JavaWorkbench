package de.yjulian.tt;

import java.util.Objects;

public class Triple<T, G, H> extends Pair<T, G>{

    private final H third;

    public Triple(T first, G second, H third) {
        super(first, second);
        this.third = third;
    }

    public H getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return super.equals(o) && Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), third);
    }
}
