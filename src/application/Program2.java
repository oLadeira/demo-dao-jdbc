package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class Program2 {
    
    public static void main(String[] args) {
        
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        
        /*
        System.out.println("==== TEST 1 : Insert Department");
        
        
        Department department = new Department(null, "Finances");
        
        departmentDao.insert(department);
        System.out.println("Insert successful! New department id: " + department.getId());
        */
        
        System.out.println("==== TEST 2 : Update Department");
        Department department = departmentDao.findById(6);
        department.setName("Finances");
        departmentDao.update(department);
    }
    
    
}
