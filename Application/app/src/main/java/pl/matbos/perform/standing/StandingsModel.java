package pl.matbos.perform.standing;

import java.util.List;

import pl.matbos.perform.standing.converter.RankingConverter;
import pl.matbos.perform.standing.model.RankingEntry;
import pl.matbos.perform.web.PerformService;
import rx.Observable;

public class StandingsModel {
    private final PerformService service;

    public StandingsModel(PerformService service) {
        this.service = service;
    }

    public Observable<List<RankingEntry>> getRankings() {
        return service.getStandings()
                .map(gsmrsResponse -> gsmrsResponse.competition.season.rounds.get(0).resultsTable.rankings)
                .flatMap(Observable::from)
                .map(RankingConverter::from)
                .toSortedList();
    }
}