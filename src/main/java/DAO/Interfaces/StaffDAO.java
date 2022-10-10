package DAO.Interfaces;

import Model.Entity.Staff;

public interface StaffDAO extends DAOImpl<Staff>{
    Staff getStaffById(long id);
}
