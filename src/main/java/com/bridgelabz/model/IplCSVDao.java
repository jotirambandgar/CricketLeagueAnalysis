package com.bridgelabz.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IplCSVDao {

    private int pos;

    private String playerName;

    private int matches;

    private int innings;

    private int notOut;

    private int runs;

    private String highScore;

    private String average;

    private int ballFaced;

    private String strikeRate;

    private int hundred;

    private int fifty;

    private int fours;

    private int six;

    public IplCSVDao(BatsMan batsMan) {

        this.pos = batsMan.getPos();
        this.playerName = batsMan.getPlayerName();
        this.matches = batsMan.getMatches();
        this.innings = batsMan.getInnings();
        this.notOut = batsMan.getNotOut();
        this.runs = batsMan.getRuns();
        this.highScore = batsMan.getHighScore();
        this.average = batsMan.getAverage();
        this.ballFaced = batsMan.getBallFaced();
        this.strikeRate = batsMan.getStrikeRate();
        this.hundred = batsMan.getHundred();
        this.fifty = batsMan.getFifty();
        this.fours = batsMan.getFours();
        this.six = batsMan.getSix();
    }

    @Override
    public String toString() {
        return "IplCSVDao{" +
                "pos=" + pos +
                ", playerName='" + playerName + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highScore='" + highScore + '\'' +
                ", average='" + average + '\'' +
                ", ballFaced=" + ballFaced +
                ", strikeRate='" + strikeRate + '\'' +
                ", hundred=" + hundred +
                ", fifty=" + fifty +
                ", fours=" + fours +
                ", six=" + six +
                '}';
    }
}
