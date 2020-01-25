package com.bridgelabz.adapter;

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

public abstract class  IPLLeagueAdapter {
    public abstract List<IplCSVDao> getIplData(String csvFile);

    public <E> List<IplCSVDao> getIplData(Class IplCSVClass, String csvFile) {

        List<IplCSVDao> iplDaoList = new ArrayList<>();


        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile))) {

            IOpenCsvBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> iterator = csvBuilder.getIterator(reader, IplCSVClass);
            Iterable<E> iterable = () -> iterator;
            if (IplCSVClass.getName().equals("com.bridgelabz.model.BatsMan")) {
                StreamSupport.stream(iterable.spliterator(), false).
                        map(BatsMan.class::cast).forEach(batsMan -> iplDaoList.add(new IplCSVDao(batsMan)));

            } else {
                StreamSupport.stream(iterable.spliterator(), false).
                        map(Bowler.class::cast).
                        forEach(bowler ->
                                iplDaoList.add(new IplCSVDao(bowler)));
            }

            return iplDaoList;

        } catch (IOException e) {

            throw new LeagueAnalyserException(e.getMessage(), LeagueAnalyserException.ExceptionType.NO_CSV_FILE);

        } catch (CSVBuilderException builderException) {
            throw new LeagueAnalyserException(builderException.getMessage(),
                    LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }
    public <E> List<IplCSVDao> getIplData(String... csvFile){
        List<IplCSVDao> iplLeagueData = new ArrayList<>();
        iplLeagueData = this.getIplData(BatsMan.class,csvFile[0]);

        List<IplCSVDao> finalIplLeagueData = iplLeagueData;
        this.getIplData(Bowler.class,csvFile[1])
                .forEach(bowler -> {
                    Optional<IplCSVDao> batsmanFirst = finalIplLeagueData.stream().
                            filter(batsman -> batsman.getPlayerName()
                                    .equals(bowler.getPlayerName())).findFirst();

                        if(batsmanFirst.isPresent()) {
                            finalIplLeagueData.remove(batsmanFirst.get());
                            batsmanFirst.get().setBowlingAverage(bowler.getBowlingAverage());
                            finalIplLeagueData.add(batsmanFirst.get());
                        }
                    });
//                .forEach(iplCSVDao ->
//                {if (iplData.containsKey(iplCSVDao.getPlayerName())) {
//                    iplCSVDao.setBattingverage(iplCSVDao.getBattingverage());
//                    iplLeagueData.add(iplCSVDao);}else {iplLeagueData.add(iplCSVDao);}});
        iplLeagueData = finalIplLeagueData;
        return iplLeagueData;
    }

}
