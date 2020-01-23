package com.bridgelabz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IplCSVDao {



    private int pos;

    private String playerName;

    private int matches;

    private int innings;

    private int notOut;

    private int runs;

    private double highScore;

    private double average;

    private int ballFaced;

    private double strikeRate;

    private int hundred;

    private int fifty;

    private int fours;

    private int six;

    private  double over;

    private double wickets;

    private int bbi;

    private double economeyRate;

    private int fourWickete;

    private int fiveWicket;

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

    public IplCSVDao(Bowler bowler) {

        this.pos = bowler.getPos();
        this.playerName = bowler.getPlayerName();
        this.matches = bowler.getMatches();
        this.innings = bowler.getInnings();
        this.runs = bowler.getRuns();
        this.average = bowler.getAverage();
        this.strikeRate = bowler.getStrikeRate();
        this.over = bowler.getOvers();
        this.wickets = bowler.getWickets();
        this.bbi = bowler.getBbi();
        this.economeyRate = bowler.getEconomeyRate();
        this.fourWickete = bowler.getFourWicket();
        this.fiveWicket = bowler.getFiveWicket();

    }

    public BatsMan getLeagueDto() {
        return new BatsMan(pos, playerName, matches, innings, notOut,
                runs, highScore,average, ballFaced,strikeRate, hundred, fifty,fours, six);
    }


}
