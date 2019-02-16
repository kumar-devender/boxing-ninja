package com.ninja.boxing.app.module.serializer;

import java.io.IOException;
import org.junit.Test;

import com.ninja.boxing.app.module.model.Player.PlayerBuilder;

public class SerializationManagerTests {

    @Test
    public void testSerializeWhenFileExist() throws IOException {
        PlayerBuilder builder = new PlayerBuilder("Devender",10);
        SerializationManager.serialize("player.txt", builder.build());
    }
    
    @Test
    public void testSerializeWhenFileDoesNotExist() throws IOException {
        PlayerBuilder builder = new PlayerBuilder("Devender",10);
        SerializationManager.serialize("player.txt", builder.build());
    }
    
    @Test
    public void testDeserializeWhenFileDoesNotExist() throws IOException, ClassNotFoundException {
        SerializationManager.deSerialize("player.txt");
    }

}
