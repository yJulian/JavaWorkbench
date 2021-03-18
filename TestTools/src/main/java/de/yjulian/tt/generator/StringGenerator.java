package de.yjulian.tt.generator;

public class StringGenerator {

    private final String format;
    private Replacer replacer;
    private int iterations;

    public StringGenerator(String format) {
        this.format = format;
    }

    public StringGenerator(String format, Replacer replacer) {
        this.format = format;
        this.replacer = replacer;
    }

    public StringGenerator setReplacer(Replacer replacer) {
        this.replacer = replacer;
        return this;
    }

    public StringGenerator setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (replacer == null) throw new UnsupportedOperationException("Replacer not set.");

        for (int i = 0; i < iterations; i++) {
            if (builder.length() > 0) {
                builder.append(System.lineSeparator());
            }
            builder.append(String.format(format, replacer.replaceWith(i)));
        }

        return builder.toString();
    }

}
