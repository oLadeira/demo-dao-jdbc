package application;

import java.util.Date;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args){
        
        Department d1 = new Department(12, "Marketing");
        
        Seller s1 = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00);
        
        System.out.println(d1);
        System.out.println();
        System.out.println(s1);
        
    }
    
}
