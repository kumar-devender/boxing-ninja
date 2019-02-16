package com.ninja.boxing.app.module.utils;

import static java.util.Objects.nonNull;

import java.util.function.Function;
import java.util.function.Predicate;

import com.ninja.boxing.app.module.constant.CommonConstant;


/**
 * @author DEVENDER
 * All lambda that are used in more than one class written here. 
 * Even if some are used only in one class can be 
 * put here so that if needed in future in other class can be used.
 */
public final class CommonLambda {

    /**
     * Convert enum list to string
     */
    @SuppressWarnings("rawtypes")
    public static final Function<? super Enum, String> ENUM_TO_STRING =
    someEnum -> someEnum.ordinal() + CommonConstant.MENU_ITEM_OFFSET + ". " + someEnum;

    /**
     * test given value is not blank
     */
    public static final Predicate<String> NOT_BLANK =
            text -> nonNull(text) && !text.isEmpty() 
            && text.chars().noneMatch(Character::isWhitespace);

    /**
     * test given value is numeric 
     */
    public static final Predicate<String> IS_NUMERIC =
                    text -> nonNull(text) 
                    && text.chars().allMatch(Character::isDigit);

    /**
     * test given value is non negative
     */
    public static final Predicate<String> NON_NEGATIVE = line -> Integer.parseInt(line) > 0;

    /**
     *  test given value is not blank and is numeric and non negative
     */
    public static final Predicate<String> VALID_INPUT_DATA_CONDITION = NOT_BLANK.and(IS_NUMERIC).and(NON_NEGATIVE);
    private CommonLambda() {

    }

    
}
