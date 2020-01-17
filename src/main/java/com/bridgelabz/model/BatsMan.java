package com.bridgelabz.model;

import com.opencsv.bean.CsvBindByName;

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
    private String highScore;

    @CsvBindByName(column = "Avg", required = true)
    private String average;

    @CsvBindByName(column = "BF", required = true)
    private int ballFaced;

    @CsvBindByName(column = "SR", required = true)
    private String strikeRate;

    @CsvBindByName(column = "100", required = true)
    private int hundred;

    @CsvBindByName(column = "50", required = true)
    private int fifty;

    @CsvBindByName(column = "4s", required = true)
    private int fours;

    @CsvBindByName(column = "6s", required = true)
    private int six;

    @Override
    public String toString() {
        return "BatsMan{" +
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
