package com.bridgelabz;

import com.bridgelabz.model.IplCSVDao;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComparatorProviderImpl implements IComparatorProvider {


    Map<LeagueAnalyser.ComparatorStatus, Comparator<IplCSVDao>> comparators = null;

    public ComparatorProviderImpl() {
        comparators = new HashMap<>();
        comparators.put(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE, (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getBattingverage() -
                (player1.getBattingverage())));

        comparators.put(LeagueAnalyser.ComparatorStatus.STRIKERATE, (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getStrikeRate() -
                (player1.getStrikeRate())));

        comparators.put(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR, (IplCSVDao player1, IplCSVDao player2) ->
                ((player2.getSix() * 6) + (player2.getFours() * 4)) -
                        ((player1.getSix() * 6) + (player1.getFours() * 4)));

        comparators.put(LeagueAnalyser.ComparatorStatus.STRIKESIXFOUR, getComparator(LeagueAnalyser.ComparatorStatus.SIXESANDFOUR).
                thenComparing(comparators.get(LeagueAnalyser.ComparatorStatus.STRIKERATE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.AVERAGESTRIKERATE, getComparator(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE).
                thenComparing(comparators.get(LeagueAnalyser.ComparatorStatus.STRIKERATE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.MAXRUN, (IplCSVDao player1, IplCSVDao player2) -> player2.getRuns() - player1.getRuns());


        comparators.put(LeagueAnalyser.ComparatorStatus.AVERAGERUN, getComparator(LeagueAnalyser.ComparatorStatus.MAXRUN)
                .thenComparing(comparators.get(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE)));


        comparators.put(LeagueAnalyser.ComparatorStatus.BOWLINGAVERAGE,
                        (IplCSVDao player1, IplCSVDao player2) -> (int) (player2.getBowlingAverage() -
                            player1.getBowlingAverage()));

        comparators.put(LeagueAnalyser.ComparatorStatus.BOWLINGSTRIKINGRATE,
                Comparator.comparingDouble(IplCSVDao::getBowlingAverage).reversed());

        comparators.put(LeagueAnalyser.ComparatorStatus.BLSTRIKINGWICKET,
                Comparator.comparingDouble(IplCSVDao::getStrikeRate).reversed()
                        .thenComparing((IplCSVDao player1, IplCSVDao player2)->
                        (player2.getFiveWicket()*5 + player2.getFourWickete()*4)-
                                (player1.getFiveWicket()*5 + player1.getFourWickete()*4)));

        comparators.put(LeagueAnalyser.ComparatorStatus.BLAVERAGESTRIKINGWICKET,
                comparators.get(LeagueAnalyser.ComparatorStatus.BOWLINGSTRIKINGRATE)
                        .thenComparing(comparators.get(LeagueAnalyser.ComparatorStatus.BOWLINGAVERAGE)));

        comparators.put(LeagueAnalyser.ComparatorStatus.ECONOMYRATE,
                Comparator.comparingDouble(IplCSVDao::getEconomeyRate).reversed());

        comparators.put(LeagueAnalyser.ComparatorStatus.BLAVGBTAVG,
                comparators.get(LeagueAnalyser.ComparatorStatus.BOWLINGAVERAGE)
                        .thenComparing(comparators.get(LeagueAnalyser.ComparatorStatus.BATTINGAVERAGE)));

    }

    @Override
    public  Comparator<IplCSVDao>  getComparator(LeagueAnalyser.ComparatorStatus comparatorStatus){

        return comparators.get(comparatorStatus);
    }
}
