package com.bridgelabz;

import com.blsolution.exception.CSVBuilderException;
import com.blsolution.factory.CSVBuilderFactory;
import com.blsolution.repository.IOpenCsvBuilder;
import com.bridgelabz.exception.LeagueAnalyserException;
import com.bridgelabz.factory.ComparatorProviderFactory;
import com.bridgelabz.factory.LeagueAdapterFactory;
import com.bridgelabz.model.BatsMan;
import com.bridgelabz.model.Bowler;
import com.bridgelabz.model.IplCSVDao;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LeagueAnalyser {

    private List<IplCSVDao> batsManData;
    IComparatorProvider comparatorProvider;

    public LeagueAnalyser() {
        batsManData=new ArrayList<>();
        comparatorProvider = ComparatorProviderFactory.getComaparatorProvider();
    }




    public enum ComparatorStatus {
        BATTINGAVERAGE,
        STRIKERATE,
        SIXESANDFOUR,
        STRIKESIXFOUR,
        AVERAGESTRIKERATE ,
        AVERAGERUN ,
        MAXRUN,
        BOWLINGAVERAGE,
        BOWLINGSTRIKINGRATE,
        ECONOMYRATE,
        BLSTRIKINGWICKET,
        BLAVERAGESTRIKINGWICKET,
        BLAVGBTAVG;
    }

    public enum CsvFileType{
        BATSMAN,
        BOWLER,
        LEAGUE
    }

    public int loadCSVData(CsvFileType csvFileType, String... csvFilePath) {

       batsManData = LeagueAdapterFactory.getLeagueAdapter(csvFileType,csvFilePath);

        return batsManData.size();

    }

    public List sortBaseOnAverage() {

        getSortedData(ComparatorStatus.BATTINGAVERAGE);
        return getDtoList(CsvFileType.BATSMAN);

    }

    public List<BatsMan> getTopStrikingRates() {

        getSortedData(ComparatorStatus.STRIKERATE);
        return getDtoList(CsvFileType.BATSMAN);

    }

    public List<BatsMan> sortBaseOnSixesAndFours() {

        getSortedData(ComparatorStatus.SIXESANDFOUR);
        return getDtoList(CsvFileType.BATSMAN);

    }

    public List sortByStrickingSixAndFour() {

        getSortedData(ComparatorStatus.STRIKESIXFOUR);
        return getDtoList(CsvFileType.BATSMAN);

    }


    public List<BatsMan> sortByRunsAndAverage() {
        getSortedData(ComparatorStatus.AVERAGERUN);
        return getDtoList(CsvFileType.BATSMAN);
    }


    public List<BatsMan> sortByAverageAndStrikingRate() {

        getSortedData(ComparatorStatus.AVERAGESTRIKERATE);
        return getDtoList(CsvFileType.BATSMAN);

    }


    public List<Bowler> SortByBowlingAvg() {

        getSortedData(ComparatorStatus.BOWLINGAVERAGE);
        return getDtoList(CsvFileType.BOWLER);

    }

    public List sortBaseOnBowlingStrikeRate() {
        getSortedData(ComparatorStatus.BOWLINGSTRIKINGRATE);
        return getDtoList(CsvFileType.BOWLER);
    }


    public List<Bowler> sortBaseOnEconomyRate() {
        getSortedData(ComparatorStatus.ECONOMYRATE);
        return getDtoList(CsvFileType.BOWLER);
    }

    public List<Bowler> sortBaseOnStrikingRateAndWicket() {
        getSortedData(ComparatorStatus.BLSTRIKINGWICKET);
        return getDtoList(CsvFileType.BOWLER);
    }

    public List<Bowler> sortBaseOnStrikingRateAndBowlingAvg() {
        getSortedData(ComparatorStatus.BLAVERAGESTRIKINGWICKET);
        return getDtoList(CsvFileType.BOWLER);
    }

    public List<IplCSVDao> sortBaseOnBatingAvgBowlingAvg() {
        getSortedData(ComparatorStatus.BLAVGBTAVG);
        return batsManData;
    }


    public List getDtoList(CsvFileType csvFileType) {

        return batsManData.stream().map(iplCSVDao -> iplCSVDao.getLeagueDto(csvFileType))
                .collect(Collectors.toList());

    }


    public void getSortedData(ComparatorStatus comparatorType) {

         batsManData.sort(comparatorProvider.getComparator(comparatorType));

    }








}
