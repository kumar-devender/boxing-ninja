package com.ninja.boxing.app.module.reader;

import java.util.Scanner;
import java.util.function.Predicate;

import com.ninja.boxing.app.module.utils.CommonLambda;

import static java.util.Objects.nonNull;

/**
 * @author DEVENDER
 * This Singleton class will be used for all input related operation. If needed more method can be added to this class. 
 */
public class InputReader {

    private final Scanner sc;
    private static volatile InputReader instance;

    private InputReader() {
        sc = new Scanner(System.in,"UTF-8");
    }

    public static InputReader getInstance() {
        if(instance == null) {
            synchronized (InputReader.class) {
                if(instance == null) {
                    instance = new InputReader();
                }
            }
        }
        return instance;
    }


    public int readIntegerUntil(Predicate<String> userCondition, Runnable onFail) {
        final Predicate<String> retryCondition = CommonLambda.VALID_INPUT_DATA_CONDITION.and(userCondition).negate();
        String line = null;
        do {
            if (nonNull(line)) {
                onFail.run();
            }
            line = readString();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }

    public String readString() {
        return sc.nextLine();
    }
}
