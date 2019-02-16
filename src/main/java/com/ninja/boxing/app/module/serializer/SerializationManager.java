package com.ninja.boxing.app.module.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ninja.boxing.app.module.config.PropertyConfig;
/**
 * @author DEVENDER
 * For serialization and de-serialization of objects.
 */
public final class SerializationManager{
    private static final String PATH =  PropertyConfig.INSTANCE.getPropertyValue("serialization.path"); ;
    private SerializationManager() {

    }

    public static void serialize(final String fileName,final Object object) throws IOException {
       final File file = new File(PATH+fileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        try(FileOutputStream fileOS = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOS);){
            out.writeObject(object);

        }
    }

    public static Object deSerialize(final String fileName) throws IOException, ClassNotFoundException {
        try(FileInputStream file = new FileInputStream(PATH+fileName);ObjectInputStream in = new ObjectInputStream
                (file);) {
           return in.readObject();
        }
    }
}
