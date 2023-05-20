package com.aluralatam.hibernatatutorial.dao;

import com.aluralatam.hibernatatutorial.modelo.Categoria;

import jakarta.persistence.EntityManager;

public class CategoriaDao {
    
    private EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }

    public void guardar(Categoria categoria){
        this.em.persist(categoria);
    }

    public void actualizar(Categoria categoria){
        this.em.merge(categoria);
    }
    
}
