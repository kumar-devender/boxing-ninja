package com.ninja.boxing.app.module.utils;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.ninja.boxing.app.module.constant.CommonEnum.FightStatus;

public class CommonLambdaTests {

    @Test
    public void testEnumTOString() {
        List<String> enumList = new ArrayList<>();
        Stream.of(FightStatus.values()).map(CommonLambda.ENUM_TO_STRING).forEach(e->enumList.add(e));
        assertThat(enumList.size(), is(3));
    }
    
    @Test
    public void testNonNagativeShouldReturnTrue() {
        assertThat(CommonLambda.NON_NEGATIVE.test("1"), is(true));
    }
    
    @Test
    public void testNonNagativeShouldReturnFalse() {
        assertThat(CommonLambda.NON_NEGATIVE.test("-1"), is(false));
    }
    
    @Test
    public void testIS_NumericShouldReturnFalseWhenString() {
        assertThat(CommonLambda.IS_NUMERIC.test("abc"), is(false));
    }
    
    
    @Test
    public void testIS_NumericShouldReturnFalseWhenNull() {
        assertThat(CommonLambda.IS_NUMERIC.test(null), is(false));
    }
    
    @Test
    public void testIS_NumericShouldReturnTrueWhenNumber() {
        assertThat(CommonLambda.IS_NUMERIC.test("123"), is(true));
    }
    
    @Test
    public void testNOT_BlankShouldReturnTrue() {
        assertThat(CommonLambda.NOT_BLANK.test("123"), is(true));
    }
    
    @Test
    public void testNOT_BlankShouldReturnFalse() {
        assertThat(CommonLambda.NOT_BLANK.test(""), is(false));
    }
    
    @Test
    public void testNOT_BlankShouldReturnFalseWhenNull() {
        assertThat(CommonLambda.NOT_BLANK .test(null), is(false));
    }
    
    @Test
    public void testNOT_BlankShouldReturnFalseWhenBlank() {
        assertThat(CommonLambda.NOT_BLANK.test(""), is(false));
    }
    
    @Test
    public void testNOT_BlankShouldReturnFalseWhenWhiteSpace() {
        assertThat(CommonLambda.NOT_BLANK.test("    "), is(false));
    }
    
    
    
    @Test
    public void TestValid_Input_Data_ConditionShouldReturnTrue() {
        assertThat(CommonLambda.VALID_INPUT_DATA_CONDITION.test("12"), is(true));
    }
    
    @Test
    public void TestValid_Input_Data_ConditionShouldReturnFalse() {
        assertThat(CommonLambda.VALID_INPUT_DATA_CONDITION.test("-12"), is(false));
    }
    
    
    
    

}
