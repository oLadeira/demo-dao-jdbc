package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

    private Connection con;

    public SellerDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            prst = con.prepareStatement("SELECT seller.*, department.name as DepName FROM seller "
                    + "INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.id = ?;");

            prst.setInt(1, id);

            rs = prst.executeQuery();

            if (rs.next()) { //se tiver algum dado no resultSet.
                Department dep = instantiateDepartment(rs); //fiz um metodo para a instanciação, deixando o código mais enxuto

                Seller obj = instantiateSeller(rs, dep);
                return obj;
            }
            return null; //caso não tiver retorna nullo.

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(prst);
            DB.closeResulSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            prst = con.prepareStatement("SELECT seller.*, department.name as DepName FROM seller "
                    + "INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name;");

            
            rs = prst.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while (rs.next()) { //se tiver algum dado no resultSet.
                
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            
            return list; //caso não tiver retorna nullo.

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(prst);
            DB.closeResulSet(rs);
        }
    }
    
    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            prst = con.prepareStatement("SELECT seller.*, department.name as DepName FROM seller "
                    + "INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name;");

            prst.setInt(1, department.getId());

            rs = prst.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while (rs.next()) { //se tiver algum dado no resultSet.
                
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            
            return list; //caso não tiver retorna nullo.

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(prst);
            DB.closeResulSet(rs);
        }
        
    }
    
    
    

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    

}
