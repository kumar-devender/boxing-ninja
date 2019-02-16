package com.ninja.boxing.app.module.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.ninja.boxing.app.module.constant.CommonEnum.BoxingTitle;

public class Bout implements Serializable{

    /**
     * Serial Version ID
     */
    private static final long serialVersionUID = -8863816524198133407L;

    private final LocalDate boutDate;

    private String winner;

    private String runnerUp;

    private BoxingTitle title;
    
    public Bout(final LocalDate boutDate, final BoxingTitle title) {
        this.boutDate = boutDate;
        this.title = title;
    }

    public LocalDate getBoutDate() {
        return boutDate;
    }

    public String getWinner() {
        return winner;
    }

    public String getRunnerUp() {
        return runnerUp;
    }

    public BoxingTitle getTitle() {
        return title;
    }

    public void setResult(final Playable player1,final Playable player2) {
        if(player1.getEnergyLevel()>player2.getEnergyLevel()) {
            winner = player1.getName();
            runnerUp = player2.getName();
        }else if(player1.getEnergyLevel()<player2.getEnergyLevel()){
            winner = player2.getName();
            runnerUp = player1.getName();
        }
    }

    @Override
    public String toString() {
        return "Bout [boutDate=" + boutDate + ", winner=" + winner + ", runnerUp=" + runnerUp + ", title=" + title
                + "]";
    }
}
