package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

//A classe DaoFactory serve para auxiliar na instanciação dos métodos 

public class DaoFactory {

    public static SellerDao createSellerDao(){ //método que retorna o tipo da INTERFACE 
        return new SellerDaoJDBC(DB.getConnection()); //mas internamente vai instanciar uma implementação, macete para não precisar expor a implementação.
                                    //exemplo de como vai ficar a implantação na classe main Program.
    }
    
}
