package com.bridgelabz.factory;

import com.bridgelabz.LeagueAnalyser;
import com.bridgelabz.adapter.BatsManAdapter;
import com.bridgelabz.adapter.BowlerAdapter;
import com.bridgelabz.adapter.IPLLeagueAdapter;
import com.bridgelabz.model.IplCSVDao;

import java.util.List;

public class LeagueAdapterFactory {
    public static List<IplCSVDao> getLeagueAdapter(LeagueAnalyser.CsvFileType csvFileType, String... csvFilePath){

        if(csvFileType.equals(LeagueAnalyser.CsvFileType.BATSMAN)) {
            return new BatsManAdapter().getIplData(csvFilePath[0]);
        }
        if(csvFileType.equals(LeagueAnalyser.CsvFileType.BOWLER)) {
            return new BowlerAdapter().getIplData(csvFilePath[0]);
        }
        return new BatsManAdapter().getIplData(csvFilePath);
    }
}
