package pl.minecodes.minediscordstats.discord;

import pl.minecodes.minediscordstats.model.Statistic;

import java.util.List;

public interface StatisticService {

    void updateStatistic(String id, String value);

    void updateStatistic(Statistic statistic);

    void removeStatistic(String id);

    void removeStatistic(Statistic statistic);

    Statistic getStatistic(String id);

    List<Statistic> getStatistics(Statistic.Objective objective);

    List<Statistic> getActiveStatistics();



}
