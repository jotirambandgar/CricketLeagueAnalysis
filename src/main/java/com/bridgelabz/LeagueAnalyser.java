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

    public enum CsvFileType{
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
        return getDtoList();

    }

    public List<BatsMan> getTopStrikingRates() {

        getSortedData(ComparatorStatus.STRIKERATE);
        return getDtoList();

    }

    public List<BatsMan> sortBaseOnSixesAndFours() {
        getSortedData(ComparatorStatus.SIXESANDFOUR);
        return getDtoList();
    }

    public List sortByStrickingSixAndFour() {
        getSortedData(ComparatorStatus.STRIKESIXFOUR);
        return getDtoList();
    }

    public List getDtoList() {
        return batsManData.stream().map(iplCSVDao -> iplCSVDao.getLeagueDto()).collect(Collectors.toList());
    }

    public List<BatsMan> sortByAverageAndStrikingRate() {
        getSortedData(ComparatorStatus.AVERAGESTRIKERATE);
        return getDtoList();
    }

    public List<BatsMan> sortByRunsAndAverage() {
        getSortedData(ComparatorStatus.AVERAGERUN);
        return getDtoList();
    }

    public void getSortedData(ComparatorStatus comparatorType) {

         batsManData.sort(ComparatorProvider.getComparator(comparatorType));

    }





}
