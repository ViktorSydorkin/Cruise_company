package DAO.Interfaces;

import Model.Entity.Cruise;
import Model.Entity.CruiseTranslation;

import java.sql.Timestamp;
import java.util.List;

public interface CruiseDAO extends DAOImpl<Cruise> {
    /**
     * Gets cruise by its id
     *
     * @param lang_id   - id of language of cruise translation
     * @param cruise_id - id og cruise to be selected
     * @return - cruise if it exists
     * @see Cruise
     */
    Cruise getCruiseById(long lang_id, long cruise_id);

    /**
     * Gets the amount of cruises in DB
     *
     * @return - amount of cruises
     */
    int getCruiseAmount();

    /**
     * Gets the amount of cruises in DB which start day is bigger than inputted
     *
     * @param date - a start date
     * @return - amount of cruises
     */
    int getCruiseAmountDate(Timestamp date);

    /**
     * Gets translations of the particular cruise
     *
     * @param cruise_id - id of cruise to be translated
     * @return - list of cruise translations
     * @see CruiseTranslation
     */
    List<CruiseTranslation> getCruiseTranslation(long cruise_id);

    /**
     * Gets the limited amount of cruises where the start date is bigger than given
     *
     * @param lang_id    - id of cruises' translation language
     * @param start      - selection offset
     * @param amount     - amount of cruises to select
     * @param start_date - the min date
     * @return - list of cruises
     */
    List<Cruise> getCruiseByStartDate(long lang_id, int start, int amount, Timestamp start_date);
}
