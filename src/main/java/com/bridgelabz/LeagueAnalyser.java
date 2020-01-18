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
                                    LeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return batsManData.size();

    }

    public List sortBaseOnAverage() {

        return batsManData.stream().sorted((batsMan1,batsMan2)-> batsMan2.average.
                compareTo(batsMan1.average)).collect(Collectors.toList());

    }
}
