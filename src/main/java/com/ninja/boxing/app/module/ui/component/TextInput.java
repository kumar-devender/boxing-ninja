package com.ninja.boxing.app.module.ui.component;

import com.ninja.boxing.app.module.reader.InputReader;

/**
 * @author DEVENDER
 * Represent test input  component from user
 */
public class TextInput implements Component {

    /**
     * Title of input
     */
    private final String title;

    public TextInput(final String title) {
        this.title = title;
    }

    /* (non-Javadoc)
     * @see com.ninja.boxing.app.module.ui.component.Component#draw()
     * Draw title
     */
    @Override
    public void draw() {
        System.out.println(title);
    }

    /**
     * @return
     * read string from InputReader 
     */
    public String getValue() {
        return InputReader.getInstance().readString();
    }
}
