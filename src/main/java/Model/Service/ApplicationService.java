package Model.Service;

import Model.Entity.Application;

import java.util.List;

public interface ApplicationService {
    boolean addApplication(Application application);

    List<Application> getAllApplications(long lang_id);

    List<Application> getApplicationsForUser(long lang_id, long user_id);

    Application getApplicationById(long lang_id, long app_id);

    boolean approveApplication(long lang_id, long app_id);

    boolean openApplication(long lang_id, long app_id);

    boolean endApplication(long lang_id, long app_id);

    boolean payApplication(long lang_id, long app_id);

    boolean deleteApplication(long app_id);
}
