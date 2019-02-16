package com.ninja.boxing.app.module.serializer;

import java.io.IOException;

public interface Serializer {

    void serialize(String fileName,Object object)throws IOException;

    Object deSerialize(String fileName) throws IOException, ClassNotFoundException;
}
