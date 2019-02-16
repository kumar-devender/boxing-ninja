package com.ninja.boxing.app.module.utils;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.EnumSet;

import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.PunchingPower;

public class RandomSelectedEnumUtilTests {

    @Test
    public void testMenu() {
        PunchingPower punchingPower = RandomSelectedEnumUtil.menu(EnumSet.allOf(PunchingPower.class));
        assertThat(punchingPower,instanceOf(PunchingPower.class));
    }

}
