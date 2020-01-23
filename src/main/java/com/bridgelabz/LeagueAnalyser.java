package com.bridgelabz;

import com.blsolution.exception.CSVBuilderException;
import com.blsolution.factory.CSVBuilderFactory;
import com.blsolution.repository.IOpenCsvBuilder;
import com.bridgelabz.exception.LeagueAnalyserException;
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

    public LeagueAnalyser() {
    batsManData=new ArrayList<>();
    }


    public enum ComparatorStatus {
        AVERAGE,
        STRIKERATE,
        SIXESANDFOUR,
        STRIKESIXFOUR,
        AVERAGESTRIKERATE ,
        AVERAGERUN ,
         MAXRUN ;
    }

    public static enum CsvFileType{
        BATSMAN,
        BOWLER
    }

    public int loadCSVData(CsvFileType csvFileType, String csvFilePath) {


        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            IOpenCsvBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            if(csvFileType.equals(CsvFileType.BATSMAN)) {
                Iterator<BatsMan> batsManIterator = csvBuilder.getIterator(reader, BatsMan.class);
                Iterable<BatsMan> batsManIterable = () -> batsManIterator;
                batsManData = StreamSupport.stream(batsManIterable.spliterator(),false)
                        .map(IplCSVDao::new).collect(Collectors.toList());
            }

            if(csvFileType.equals(CsvFileType.BOWLER)) {
                Iterator<Bowler> batsManIterator = csvBuilder.getIterator(reader, Bowler.class);
                Iterable<Bowler> batsManIterable = () -> batsManIterator;
                batsManData = StreamSupport.stream(batsManIterable.spliterator(),false)
                        .map(IplCSVDao::new).collect(Collectors.toList());
            }


        } catch (IOException e) {

           throw new LeagueAnalyserException(e.getMessage(),LeagueAnalyserException.ExceptionType.NO_CSV_FILE);

        } catch (CSVBuilderException builderException){
            throw new LeagueAnalyserException(builderException.getMessage() ,
                                    LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
        return batsManData.size();

    }

    public List<BatsMan> sortBaseOnAverage() {

        getSortedData(ComparatorStatus.AVERAGE);
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

        getSortedData(ComparatorStatus.AVERAGE);
        return getDtoList(CsvFileType.BOWLER);
    }

    public List getDtoList(CsvFileType csvFileType) {

        return batsManData.stream().map(iplCSVDao -> iplCSVDao.getLeagueDto(csvFileType))
                .collect(Collectors.toList());

    }


    public void getSortedData(ComparatorStatus comparatorType) {
         batsManData.sort(ComparatorProvider.getComparator(comparatorType));
    }




}
