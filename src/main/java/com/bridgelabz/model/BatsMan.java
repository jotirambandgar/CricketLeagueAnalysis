package com.bridgelabz.model;

import com.opencsv.bean.CsvBindByName;

public class BatsMan {

    @CsvBindByName(column = "POS", required = true)
    int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    String playerName;

    @CsvBindByName(column = "Mat", required = true)
    int matches;

    @CsvBindByName(column = "Inns", required = true)
    int innings;

    @CsvBindByName(column = "NO", required = true)
    int notOut;

    @CsvBindByName(column = "Runs", required = true)
    int runs;

    @CsvBindByName(column = "HS", required = true)
    String highScore;

    @CsvBindByName(column = "Avg", required = true)
    String average;

    @CsvBindByName(column = "BF", required = true)
    int ballFaced;

    @CsvBindByName(column = "SR", required = true)
    String strikeRate;

    @CsvBindByName(column = "100", required = true)
    int hundred;

    @CsvBindByName(column = "50", required = true)
    int fifty;

    @CsvBindByName(column = "4s", required = true)
    int fours;

    @CsvBindByName(column = "6s", required = true)
    int six;

}
