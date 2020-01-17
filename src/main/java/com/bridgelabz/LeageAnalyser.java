package com.bridgelabz;

import com.blsolution.exception.CSVBuilderException;
import com.blsolution.factory.CSVBuilderFactory;
import com.blsolution.repository.IOpenCsvBuilder;
import com.bridgelabz.exception.LeagueAnalyserException;
import com.bridgelabz.model.BatsMan;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeageAnalyser {



    public int loadMostRunData(String csvFilePath) {

        List<BatsMan> batsManData = new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            IOpenCsvBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            batsManData = csvBuilder.getFileList(reader, BatsMan.class);


        } catch (IOException e) {
           throw new LeagueAnalyserException(e.getMessage(),LeagueAnalyserException.ExceptionType.NO_CSV_FILE);
        } catch (CSVBuilderException builderException){
            throw new LeagueAnalyserException(builderException.getMessage() ,
                                    LeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return batsManData.size();

    }
}
