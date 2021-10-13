package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class Program2 {
    
    public static void main(String[] args) {
        
        System.out.println("==== TEST 1 : Insert Department");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        
        Department department = new Department(null, "Marketing");
        
        departmentDao.insert(department);
        System.out.println("Insert successful! New department id: " + department.getId());
        
        
        System.out.println("==== TEST 2 : Update Department");
        
    }
    
    
}
