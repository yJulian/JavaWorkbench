package de.yjulian.unified.codec;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.LinkedList;
import java.util.List;

public class UnifiedRegistryBuilder {

    private static UnifiedRegistryBuilder instance;
    private final List<UnifiedCodec<?>> codecs = new LinkedList<>();
    private CodecRegistry codecRegistry;

    public UnifiedRegistryBuilder() {
        if (instance != null) {
            throw new UnsupportedOperationException("You can only create one builder per session.");
        }

        instance = this;
    }

    public static UnifiedRegistryBuilder getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("You have to build a UnifiedRegistryBuilder before using the codec.");
        }

        return instance;
    }

    public UnifiedRegistryBuilder addClass(UnifiedCodec<?> aClass) {
        this.codecs.add(aClass);

        return this;
    }

    public CodecRegistry getCodecRegistry() {
        return codecRegistry;
    }

    public CodecRegistry buildCodecRegistry() {
        CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        CodecRegistry extraCodecs = CodecRegistries.fromCodecs(codecs);

        this.codecRegistry = CodecRegistries.fromRegistries(defaultCodecRegistry, extraCodecs);

        return codecRegistry;
    }

}
