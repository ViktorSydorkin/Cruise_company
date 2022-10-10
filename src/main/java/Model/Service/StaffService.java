package Model.Service;

import Model.Entity.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();

    boolean addStaff(Staff staff);

    boolean updateStaff(Staff staff);

    boolean deleteStaff(long id);
}
