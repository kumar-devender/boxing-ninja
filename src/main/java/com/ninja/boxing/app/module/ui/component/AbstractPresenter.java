package com.ninja.boxing.app.module.ui.component;


@SuppressWarnings("rawtypes")
public abstract class AbstractPresenter<T extends View> implements Presenter {

    protected final T view;

    public AbstractPresenter(final T view) {
        this.view = view;
    }

    @Override
    public void show() {
        view.draw();
    }
}

