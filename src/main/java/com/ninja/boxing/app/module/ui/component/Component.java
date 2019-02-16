package com.ninja.boxing.app.module.ui.component;

import static java.util.stream.IntStream.rangeClosed;

public interface Component {
    void draw();

    default void redraw() {
        erase();
        draw();
    }

    default void erase() {
        rangeClosed(1, 5).forEach(value -> System.out.println());
    }
}
