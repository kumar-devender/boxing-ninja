package com.ninja.boxing.app.module.ui.component;

public interface View<T> {
    /**
     * @param delegate
     * Set delegate to which control return after user choose from options menu
     */
    void setDelegate(T delegate);

    /**
     * draw
     */
    void draw();

    /**
     * erase
     */
    void erase();

}
