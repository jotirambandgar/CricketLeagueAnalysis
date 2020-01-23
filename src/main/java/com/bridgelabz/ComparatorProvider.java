package com.bridgelabz;

import com.bridgelabz.model.IplCSVDao;

import java.util.Comparator;

public class ComparatorProvider {

    public static Comparator<IplCSVDao> getComparator(LeagueAnalyser.ComparatorStatus comparatorStatus){

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.AVERAGE)) {

            return (IplCSVDao player1,IplCSVDao player2) -> (int) (player2.getAverage()-
                    ( player1.getAverage()));
        }

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.STRIKERATE)) {

            return (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getStrikeRate() -
                    (player1.getStrikeRate()));
        }

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR))
            return (IplCSVDao player1,IplCSVDao player2) ->
                    ((player2.getSix() * 6) + (player2.getFours() * 4)) -
                            ((player1.getSix() * 6) + (player1.getFours() * 4));

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.STRIKESIXFOUR)) {
            return getComparator(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR).
                    thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.STRIKERATE));
        }

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.AVERAGESTRIKERATE)) {
            return getComparator(LeagueAnalyser.ComparatorStatus.AVERAGE).
                    thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.STRIKERATE));
        }

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.MAXRUN)) {
            return ((IplCSVDao player1,IplCSVDao player2)-> player2.getRuns()-player1.getRuns());
        }

        if(comparatorStatus.equals(LeagueAnalyser.ComparatorStatus.AVERAGERUN)) {
            return getComparator(LeagueAnalyser.ComparatorStatus.MAXRUN).thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.AVERAGE));
        }

        return null;
    }
}
