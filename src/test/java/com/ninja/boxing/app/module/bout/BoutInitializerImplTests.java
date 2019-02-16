package com.ninja.boxing.app.module.bout;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ninja.boxing.app.module.bout.factory.BoxerFactory;
import com.ninja.boxing.app.module.bout.referee.Referee;
import com.ninja.boxing.app.module.bout.strategy.BoutStrategy;
import com.ninja.boxing.app.module.bout.strategy.UserFirstBoutStrategy;
import com.ninja.boxing.app.module.config.PropertyConfig;
import com.ninja.boxing.app.module.constant.CommonConstant;
import com.ninja.boxing.app.module.constant.CommonEnum.BoutAction;
import com.ninja.boxing.app.module.helper.BuilderHelper;
import com.ninja.boxing.app.module.helper.SeralizeHelper;
import com.ninja.boxing.app.module.model.Playable;
import com.ninja.boxing.app.module.model.Player.PlayerBuilder;
import com.ninja.boxing.app.module.serializer.SerializationManager;
import com.ninja.boxing.app.module.ui.component.Menu;

import static org.powermock.api.support.membermodification.MemberMatcher.methods;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BoutInitializerImpl.class)
public class BoutInitializerImplTests {

    // This is the name of the private method which we want to mock
    private static final String METHOD = "createEnemyPlayer";
    private BoxerFactory factory = null;
    private BoutStrategy boutStrategy = null;
    private BoutInitializerImpl boutInitializer = null;
    
    private Menu<BoutAction> menu = new Menu<>("Choose your action:", BoutAction.values()); 

    @Before
    public void setUp() {
        factory = mock(BoxerFactory.class);
        boutStrategy = new UserFirstBoutStrategy(new BoutActionView(menu));
        boutInitializer = new BoutInitializerImpl(factory, boutStrategy);
    }

    @Test
    public void testCreateShouldCall_createEnemyPlayer() throws Exception {
        when(factory.getPlayer()).thenReturn(BuilderHelper.createUserPlayer());
        BoutInitializerImpl spy = PowerMockito.spy(boutInitializer);
        PowerMockito.doReturn(BuilderHelper.createEnemyPlayer()).when(spy, METHOD);
        Optional<Referee> optional = spy.create();
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke(METHOD);
        assertNotNull(optional.get());
        assertNotNull(optional.get().getEnemy());
        assertNotNull(optional.get().getUser());

    }

    @Test
    public void testCreateNewEnemyShouldReturnNullWhenEnemyListIsNull() {
        Optional<Referee> optional = boutInitializer.createNewEnemy();
        assertThat(optional.isPresent(), is(false));
    }

    @Test
    public void testCreateNewEnemyShouldReturnWhenEnemyListCreatedIsNotNull() throws Exception {
        when(factory.getPlayer()).thenReturn(BuilderHelper.createUserPlayer());
        boutInitializer.create();
        Optional<Referee> optional = boutInitializer.createNewEnemy();
        assertThat(optional.isPresent(), is(true));
    }


    @Test
    public void testLoadShouldReturnNotNullWhenSerializedObjectPresent() throws Exception {
        SeralizeHelper.serializUser();
        SeralizeHelper.serializeEnemy();
        Optional<Referee> optional = boutInitializer.load();
        assertThat(optional.isPresent(), is(true));
    }

    @Test
    public void testLoadShouldReturnNullWhenSerializeFileNotPresent() throws Exception {
        PropertyConfig config = PropertyConfig.INSTANCE;
        String filePath = config.getPropertyValue(CommonConstant.PROP_SERIALIZATION_FILE_PATH);
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
        Optional<Referee> optional = boutInitializer.load();
        assertThat(optional.isPresent(), is(true));
    }



    @Test
    public void testGetUserShouldReturnUserPlayer() throws Exception {
        when(factory.getPlayer()).thenReturn(BuilderHelper.createUserPlayer());
        BoutInitializerImpl spy = PowerMockito.spy(boutInitializer);
        PowerMockito.doReturn(BuilderHelper.createEnemyPlayer()).when(spy, METHOD);
        spy.create();
        Optional<Playable> player = spy.getUser();
        assertThat(player.get(), is(notNullValue()));
    }
    
    @Test
    public void testGetUserShouldReturnEmptyWhenNoUserCreated() throws Exception {
        Optional<Playable> player = boutInitializer.getUser();
        assertThat(player.isPresent(), is(false));
    }

}
