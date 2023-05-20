package com.aluralatam.hibernatatutorial.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtilsJPA {
    
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("tienda");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
