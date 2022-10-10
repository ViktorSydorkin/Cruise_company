package  Model.Service;

import Model.Entity.Cruise;
import Model.Entity.CruiseTranslation;

import java.sql.Timestamp;
import java.util.List;

public interface CruiseService {
    List<Cruise> getAllCruise(long lang_id, int start, int amount);

    int getCruiseAmountDuration(long lang_id, int duration);

    List<Cruise> listFilterByDuration(int duration, List<Cruise> cruiseList);

    int getCruiseAmountDate(Timestamp date);

    List<Cruise> getCruiseByDuration(long lang_id, int start, int amount, int duration);

    List<Cruise> getCruiseByStartDate(long lang_id, int start, int amount, Timestamp startDate);

    boolean changeAvailability(long lang_id, long cruise_id);

    boolean addCruise(Cruise cruise);

    Cruise getCruiseById(long lang_id, long cruise_id);

    boolean updateCruise(long lang_id, Cruise cruise);

    int getCruiseAmount();

    List<CruiseTranslation> getAllCruiseTranslations(long cruise_id);

    boolean deleteCruise(long cruise_id);
}
