package de.yjulian.tt.generator;

public class StringGenerator {

    private final String template;
    private Replacer replacer;
    private int iterations;

    public StringGenerator(String template) {
        this.template = template;
    }

    public StringGenerator(String template, Replacer replacer) {
        this.template = template;
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
            builder.append(String.format(template, replacer.replaceWith(i)));
        }

        return builder.toString();
    }

}
