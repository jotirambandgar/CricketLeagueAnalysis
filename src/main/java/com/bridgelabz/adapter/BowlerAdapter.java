package com.bridgelabz.adapter;

import com.bridgelabz.model.Bowler;
import com.bridgelabz.model.IplCSVDao;

import java.util.List;

public class BowlerAdapter extends IPLLeagueAdapter {
    @Override
    public List<IplCSVDao> getIplData(String csvFile) {
        return super.getIplData(Bowler.class,csvFile);
    }
}
