package com.bridgelabz.adapter;

import com.bridgelabz.model.BatsMan;
import com.bridgelabz.model.IplCSVDao;

import java.util.List;

public class BatsManAdapter extends IPLLeagueAdapter {

    @Override
    public List<IplCSVDao> getIplData(String csvFile) {

       return super.getIplData(BatsMan.class,csvFile);

    }
}
