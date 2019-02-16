package com.ninja.boxing.app.module.ui.component;


public abstract class AbstractConsoleView<T> implements View<T>, Component {

    protected T delegate;

    @Override
    public void setDelegate(final T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void erase() {
        Component.super.erase();
    }

}
