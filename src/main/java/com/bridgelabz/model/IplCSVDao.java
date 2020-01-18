package com.bridgelabz.model;

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

        this.pos = batsMan.pos;
        this.playerName = batsMan.playerName;
        this.matches = batsMan.matches;
        this.innings = batsMan.innings;
        this.notOut = batsMan.notOut;
        this.runs = batsMan.runs;
        this.highScore = batsMan.highScore;
        this.average = batsMan.average;
        this.ballFaced = batsMan.ballFaced;
        this.strikeRate = batsMan.strikeRate;
        this.hundred = batsMan.hundred;
        this.fifty = batsMan.fifty;
        this.fours = batsMan.fours;
        this.six = batsMan.six;
    }
}
