package com.ninja.boxing.app.module.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DEVENDER
 * Represent Boxer Experience
 */
public class Experience implements Serializable{

    /**
     * Serial Version ID
     */
    private static final long serialVersionUID = 1L;

    private final List<Bout> bouts;

    public Experience(){
        this.bouts = new ArrayList<>();
    }

    public List<Bout> getBouts() {
        return bouts;
    }

    @Override
    public String toString() {
        return "Experience [bouts =" + bouts + "]";
    }
}
