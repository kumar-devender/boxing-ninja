package com.ninja.boxing.app.module.helper;

import java.io.IOException;

import com.ninja.boxing.app.module.constant.CommonConstant;
import com.ninja.boxing.app.module.serializer.SerializationManager;

public class SeralizeHelper {

    public static void serializUser() throws IOException {
        SerializationManager.serialize(CommonConstant.USER_PLAYER, BuilderHelper.createUserPlayer());
    }
    
    public static void serializeEnemy() throws IOException {
        SerializationManager.serialize(CommonConstant.ENEMY_PLAYER, BuilderHelper.createEnemyPlayer().get(0));
    }
}
