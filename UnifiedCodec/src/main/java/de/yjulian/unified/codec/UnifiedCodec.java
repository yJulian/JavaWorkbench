package de.yjulian.unified.codec;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class UnifiedCodec<T> implements Codec<T> {

    public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
        try {
            Class<T> aClass = getEncoderClass();
            List<Class<?>> constParams = new LinkedList<>();
            List<Object> initParams = new LinkedList<>();
            for (Field field : aClass.getFields()) {
                Class<?> typeClass = field.getType();
                constParams.add(typeClass);
            }


            return aClass.getConstructor(constParams.toArray(new Class[0])).newInstance(constParams.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void encode(BsonWriter bsonWriter, T data, EncoderContext encoderContext) {
        try {
            for (Field field : getEncoderClass().getFields()) {
                if (!Modifier.isTransient(field.getModifiers())) {
                    Class<?> aClass = field.getType();
                    if (CodecUtil.getInterfaces(aClass).contains(Collection.class)) {
                        Collection<?> collection = (Collection<?>) field.get(data);
                        CodecUtil.writeArray(bsonWriter, encoderContext, field.getName(), collection);
                    } else {
                        CodecUtil.writeValue(field.getName(), bsonWriter, encoderContext, field.get(data));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
