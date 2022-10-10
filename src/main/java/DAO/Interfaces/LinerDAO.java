package DAO.Interfaces;

import Model.Entity.Liner;

public interface LinerDAO extends DAOImpl<Liner> {
    /**
     * Gets liner from DB by its id
     *
     * @param liner_id - id of liner to be selected
     * @return - liner if it exists
     * @see Liner
     */
    Liner getLinerById(long liner_id);
}
