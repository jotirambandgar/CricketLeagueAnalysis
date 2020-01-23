package com.bridgelabz.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Bowler {
//    POS,PLAYER,Mat,Inns,Ov,Runs,Wkts,BBI,Avg,Econ,SR,4w,5w
    @CsvBindByName(column = "POS", required = true)
    private int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    private String playerName;

    @CsvBindByName(column = "Mat", required = true)
    private int matches;

    @CsvBindByName(column = "Inns", required = true)
    private int innings;

    @CsvBindByName(column = "Ov", required = true)
    private double overs;

    @CsvBindByName(column = "Runs", required = true)
    private int runs;

    @CsvBindByName(column = "Wkts", required = true)
    private int wickets;

    @CsvBindByName(column = "Avg", required = true)
    private double average;

    @CsvBindByName(column = "BBI", required = true)
    private int bbi;

    @CsvBindByName(column = "SR", required = true)
    private double strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    private double economeyRate;

    @CsvBindByName(column = "4w", required = true)
    private int fourWicket;

    @CsvBindByName(column = "5w", required = true)
    private int fiveWicket;

    public Bowler() {

    }

    public Bowler(int pos, String playerName, int matches, int innings, double overs, int runs,
                  int wickets, double average, int bbi,
                  double strikeRate, double economeyRate,
                  int fourWicket, int fiveWicket) {
        this.pos = pos;
        this.playerName = playerName;
        this.matches = matches;
        this.innings = innings;
        this.overs = overs;
        this.runs = runs;
        this.wickets = wickets;
        this.average = average;
        this.bbi = bbi;
        this.strikeRate = strikeRate;
        this.economeyRate = economeyRate;
        this.fourWicket = fourWicket;
        this.fiveWicket = fiveWicket;
    }
}
