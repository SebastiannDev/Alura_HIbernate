package com.aluralatam.hibernatatutorial.dao;

import java.math.BigDecimal;
import java.util.List;

import com.aluralatam.hibernatatutorial.modelo.Producto;

import jakarta.persistence.EntityManager;

public class ProductoDao {
    
    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto){
        this.em.persist(producto);
    }

    public void actualizar(Producto producto){
        this.em.merge(producto);
    }

    public void remove(Producto producto){
        this.em.remove(producto);
    }

    public Producto consultarPorId(Long id){
        return this.em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos(){
        // JPQL
        String jpql = "SELECT P FROM Producto AS P";
        return this.em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.nombre=:nombre";
        return this.em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaNombrePorCategoria(String categoria){
        String jpql = "SELECT P FROM Producto AS P WHERE P.categoria.nombre LIKE :categoria";
        return em.createQuery(jpql, Producto.class).setParameter("categoria", categoria).getResultList();
    }

    public BigDecimal consultarNombrePorPrecioProducto(String nombre){
        String jpql = "SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return this.em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
