package de.yjulian.unified.codec;

import com.mongodb.lang.Nullable;
import de.yjulian.unified.codec.exceptions.CodecException;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.StringCodec;

import java.util.Collection;
import java.util.LinkedList;

class CodecUtil {

    public static <T> Collection<T> readArray(BsonReader reader, DecoderContext context, Class<T> aClass) {
        reader.readStartArray();

        Collection<T> data = new LinkedList<>();

        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            data.add(readValue(reader, context, aClass));
        }

        reader.readEndArray();

        return data;
    }

    public static <T> void writeArray(BsonWriter writer, EncoderContext context, String name, Collection<?> data) {
        writer.writeStartArray(name);

        for (Object entry : data) {
            writeValue(null, writer, context, entry);
        }

        writer.writeEndArray();
    }

    public static <T> T readValue(BsonReader reader, DecoderContext context, Class<T> aClass) {
        Codec<T> codec = UnifiedRegistryBuilder.getInstance().getCodecRegistry().get(aClass);

        if (codec == null) {
            throw new CodecException(aClass);
        }

        return codec.decode(reader, context);
    }

    @SuppressWarnings("unchecked")
    public static <T> void writeValue(@Nullable String name, BsonWriter writer, EncoderContext context, Object value) {
        Class<?> aClass = value.getClass();
        Codec<T> codec = (Codec<T>) UnifiedRegistryBuilder.getInstance().getCodecRegistry().get(aClass);
        T data = (T) value;

        if (codec == null) {
            throw new CodecException(aClass);
        }

        if (name != null) {
            writer.writeName(name);
        }
        codec.encode(writer, data, context);
    }

    public static Collection<Class<?>> getInterfaces(Class<?> aClass) {
        Collection<Class<?>> classes = new LinkedList<>();
        for (Class<?> anInterface : aClass.getInterfaces()) {
            classes.add(anInterface);
            classes.addAll(getInterfaces(anInterface));
        }

        return classes;
    }

}
