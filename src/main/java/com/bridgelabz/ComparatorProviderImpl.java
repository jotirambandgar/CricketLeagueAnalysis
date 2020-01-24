package com.bridgelabz;

import com.bridgelabz.model.IplCSVDao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComparatorProviderImpl implements IComparatorProvider {


        Map<LeagueAnalyser.ComparatorStatus, Comparator<IplCSVDao>> comparators = new HashMap<>();

    public ComparatorProviderImpl() {

        comparators.put(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE, (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getBattingverage() -
                (player1.getBattingverage())));

        comparators.put(LeagueAnalyser.ComparatorStatus.STRIKERATE, (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getStrikeRate() -
                (player1.getStrikeRate())));

        comparators.put(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR, (IplCSVDao player1, IplCSVDao player2) ->
                ((player2.getSix() * 6) + (player2.getFours() * 4)) -
                        ((player1.getSix() * 6) + (player1.getFours() * 4)));

        comparators.put(LeagueAnalyser.ComparatorStatus.STRIKESIXFOUR, getComparator(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR).
                thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.STRIKERATE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.AVERAGESTRIKERATE, getComparator(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE).
                thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.STRIKERATE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.MAXRUN, (IplCSVDao player1, IplCSVDao player2) -> player2.getRuns() - player1.getRuns());


        comparators.put(LeagueAnalyser.ComparatorStatus.AVERAGERUN, getComparator(LeagueAnalyser.ComparatorStatus.MAXRUN)
                .thenComparing(getComparator(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.BOWLINGAVERAGE,
                        (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getBowlingAverage() -
                            player1.getBowlingAverage()));

        comparators.put(LeagueAnalyser.ComparatorStatus.BOWLINGSTRIKINGRATE,
                Comparator.comparingDouble(IplCSVDao::getBowlingAverage).reversed());
    }

    @Override
    public  Comparator<IplCSVDao>  getComparator(LeagueAnalyser.ComparatorStatus comparatorStatus){
        return comparators.get(comparatorStatus);
    }
}
