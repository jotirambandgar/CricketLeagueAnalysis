package com.bridgelabz;

import com.blsolution.exception.CSVBuilderException;
import com.blsolution.factory.CSVBuilderFactory;
import com.blsolution.repository.IOpenCsvBuilder;
import com.bridgelabz.exception.LeagueAnalyserException;
import com.bridgelabz.model.BatsMan;
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




    private enum ComparatorStatus {
        AVERAGE,
        STRIKERATE,
        SIXESANDFOUR,
         STRIKESIXFOUR ,
         AVERAGESTRIKERATE ;
    }

    public int loadMostRunData(String csvFilePath) {


        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            IOpenCsvBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsMan> batsManIterator = csvBuilder.getIterator(reader, BatsMan.class);
            Iterable<BatsMan> batsManIterable = () -> batsManIterator;
            batsManData = StreamSupport.stream(batsManIterable.spliterator(),false)
                    .map(IplCSVDao::new).collect(Collectors.toList());

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

    public void getSortedData(ComparatorStatus comparatorType) {

         batsManData.sort(this.getComparator(comparatorType));

    }


    public Comparator<IplCSVDao> getComparator(ComparatorStatus comparatorType){

        if(comparatorType.equals(ComparatorStatus.AVERAGE)) {

            return (IplCSVDao player1,IplCSVDao player2) -> (int) (player2.getAverage()-( player1.getAverage()));
        }

        if(comparatorType.equals(ComparatorStatus.STRIKERATE)) {

            return (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getStrikeRate() - (player1.getStrikeRate()));
        }

        if(comparatorType.equals(ComparatorStatus.SIXESANDFOUR))
            return (IplCSVDao player1,IplCSVDao player2) ->
                ((player2.getSix() * 6) + (player2.getFours() * 4)) -
                ((player1.getSix() * 6) + (player1.getFours() * 4));

        if(comparatorType.equals(ComparatorStatus.STRIKESIXFOUR)) {
            return getComparator(ComparatorStatus.SIXESANDFOUR).
                    thenComparing(getComparator(ComparatorStatus.STRIKERATE));
        }
        if(comparatorType.equals(ComparatorStatus.AVERAGESTRIKERATE)) {
            return getComparator(ComparatorStatus.AVERAGE).
                    thenComparing(getComparator(ComparatorStatus.STRIKERATE));
        }

        return null;
    }


}
