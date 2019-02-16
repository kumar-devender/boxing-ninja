package com.ninja.boxing.app.module.config;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AppConfiguration.class)
public class AppConfigurationTests {

    @BeforeClass
    public static void setUp() {
        PowerMockito.mockStatic(AppConfiguration.class ,Mockito.CALLS_REAL_METHODS);
    }
    
    @Test
    public void testWelcomeShouldReturnInstanceOfWelcomeMenuPresenter() throws Exception {
       // AppConfiguration.welcome();
    }
    
   @Test
    public void testWelcomeShouldCallBoutOrganizerFactory() throws Exception {
        //PowerMockito.suppress(method(AppConfiguration.class, "boutOrganizerFactory"));
        //AppConfiguration.welcome();
       // PowerMockito.verifyPrivate(AppConfiguration.class, times(1)).invoke("boutOrganizerFactory"); 
    }
    
}
