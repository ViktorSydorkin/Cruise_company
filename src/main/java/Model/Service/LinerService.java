package  Model.Service;

import Model.Entity.Liner;


import java.util.List;

public interface LinerService {
    boolean addLiner(Liner liner);

    Liner getLinerById(long liner_id);

    List<Liner> getAllLiners(long lang_id);

    boolean updateLiner(Liner liner);

    boolean deleteLiner(long liner_id);
}
