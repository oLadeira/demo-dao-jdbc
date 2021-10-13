package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;


public class DepartmentDaoJDBC implements DepartmentDao{

    private Connection con;
    
    public DepartmentDaoJDBC(Connection con){
        this.con = con;
    }
    
    
    @Override
    public void insert(Department obj) {
        PreparedStatement prst = null;
        
        try {
            prst = con.prepareStatement("INSERT INTO department (Name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            
            prst.setString(1, obj.getName());
            
            int rowsAffected = prst.executeUpdate();
            
            if(rowsAffected > 0){
                ResultSet rs = prst.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id); //atribuindo ao objeto o id atualizado
                }
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        
    }

    @Override
    public void update(Department obj) {
        PreparedStatement prst = null;
        
        try {
            prst = con.prepareStatement("UPDATE department SET Id = ?, Name = ?");
            
            prst.setInt(1, obj.getId());
            prst.setString(2, obj.getName());
            
            prst.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(prst);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement prst = null;
        
        try {
           prst = con.prepareStatement("DELETE FROM department WHERE Id = ?");
           
           prst.setInt(1, id);
           
           int rowsAffected = prst.executeUpdate();
           
           if (rowsAffected == 0){
               throw new DbException("Error! User not found!");
           }
           
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(prst);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement prst = null;
        ResultSet rs = null;
        
        try {
            prst = con.prepareStatement("SELECT * FROM department WHERE Id = ?");
            
            prst.setInt(1, id);
 
            rs = prst.executeQuery();
            
            if (rs.next()){
                Department dep = instantiateDepartment(rs);
                
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(prst);
            DB.closeResulSet(rs);
        }
        
    }

    @Override
    public List<Department> findAll() {
    }

    
    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
    
    
}
