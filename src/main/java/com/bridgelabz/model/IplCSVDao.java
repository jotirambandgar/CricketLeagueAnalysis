package com.bridgelabz.model;

public class IplCSVDao {

    public int pos;

    public String playerName;

    public int matches;

    public int innings;

    public int notOut;

    public int runs;

    public String highScore;

    public String average;

    public int ballFaced;

    public String strikeRate;

    public int hundred;

    public int fifty;

    public int fours;

    public int six;

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
