package com.bridgelabz;

import com.bridgelabz.model.IplCSVDao;

import java.util.Comparator;

public interface IComparatorProvider {
     Comparator<IplCSVDao> getComparator(LeagueAnalyser.ComparatorStatus comparatorStatus);
}
