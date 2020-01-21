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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LeagueAnalyser {

    private List<IplCSVDao> batsManData = new ArrayList<>();


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

    public List sortBaseOnAverage() {
        batsManData.sort(Comparator.comparing(IplCSVDao::getAverage).reversed());
        return batsManData;

    }

    public List<IplCSVDao> getTopStrikingRates() {
         batsManData.sort(Comparator.comparing(IplCSVDao::getStrikeRate).reversed());
         return batsManData;
    }

    public List sortBaseOnSixesAndFours() {
       return batsManData.stream().sorted((player1, player2) ->
            ((player2.getSix() * 6) + (player2.getFours() * 4)) -
                    ((player1.getSix() * 6) + (player1.getFours() * 4))).collect(Collectors.toList());
    }

}
