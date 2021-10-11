package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args){
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("==== TEST 1: Seller findById ====");
        Seller seller = sellerDao.findById(3);
        
        System.out.println(seller);
        
        System.out.println("\n==== TEST 2: Seller findByDepartment ====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }
        
        System.out.println("\n==== TEST 3: Seller findAll ====");
        List<Seller> listAll = sellerDao.findAll();
        for(Seller obj : listAll){
            System.out.println(obj);
        }
        
        /*
        System.out.println("\n==== TEST 4: Insert ====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        */

        System.out.println("\n==== TEST 5: Seller Update ====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update Completed!");
        
        System.out.println("\n==== TEST 6: Seller delete ====");
        System.out.println("Enter id for delete test: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        
        sellerDao.deleteById(id);
        System.out.println("User id: " + id + " deleted successful!");
        
        sc.close();
    }
    
}
