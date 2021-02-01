package de.yjulian.unified.codec.exceptions;

public class CodecException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public CodecException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CodecException(String message, Class<?> aClass) {
        super(String.format(message, aClass.getSimpleName()));
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param aClass the class used to build the message.
     */
    public CodecException(Class<?> aClass) {
        super(String.format("No codec for class %s found.", aClass.getSimpleName()));
    }
}
