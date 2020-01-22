package com.bridgelabz.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BatsMan {

    @CsvBindByName(column = "POS", required = true)
    private int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    private String playerName;

    @CsvBindByName(column = "Mat", required = true)
    private int matches;

    @CsvBindByName(column = "Inns", required = true)
    private int innings;

    @CsvBindByName(column = "NO", required = true)
    private int notOut;

    @CsvBindByName(column = "Runs", required = true)
    private int runs;

    @CsvBindByName(column = "HS", required = true)
    private double highScore;

    @CsvBindByName(column = "Avg", required = true)
    private double average;

    @CsvBindByName(column = "BF", required = true)
    private int ballFaced;

    @CsvBindByName(column = "SR", required = true)
    private double strikeRate;

    @CsvBindByName(column = "100", required = true)
    private int hundred;

    @CsvBindByName(column = "50", required = true)
    private int fifty;

    @CsvBindByName(column = "4s", required = true)
    private int fours;

    @CsvBindByName(column = "6s", required = true)
    private int six;

    public BatsMan() {
    }

    public BatsMan(int pos, String playerName,
                   int matches, int innings, int notOut,
                   int runs, double highScore, double average,
                   int ballFaced, double strikeRate, int hundred,
                   int fifty, int fours, int six)
    {
        this.pos = pos;
        this.playerName = playerName;
        this.matches = matches;
        this.innings = innings;
        this.average = average;
        this.notOut = notOut;
        this.runs = runs;
        this.highScore = highScore;
        this.ballFaced = ballFaced;
        this.strikeRate = strikeRate;
        this.hundred = hundred;
        this.fifty =fifty;
        this.six =six;
        this.fours = fours;

    }
}
