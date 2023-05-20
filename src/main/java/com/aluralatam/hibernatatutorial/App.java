package com.aluralatam.hibernatatutorial;

import java.math.BigDecimal;
import java.util.List;

import com.aluralatam.hibernatatutorial.Utils.UtilsJPA;
import com.aluralatam.hibernatatutorial.dao.CategoriaDao;
import com.aluralatam.hibernatatutorial.dao.ProductoDao;
import com.aluralatam.hibernatatutorial.modelo.Categoria;
import com.aluralatam.hibernatatutorial.modelo.Producto;

public class App 
{
    public static void main( String[] args )
    {
        Categoria categoria = new Categoria("Celulares");
        Producto celular1 = new Producto("Samsung A02", "Nueva generación", new BigDecimal("1200"), categoria);
        Producto celular2 = new Producto("Xiaomi Note 2", "Nueva generación gama alta", new BigDecimal("3000"), categoria);

        var em = UtilsJPA.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(categoria);
        productoDao.guardar(celular1);
        productoDao.guardar(celular2);

        em.getTransaction().commit();
        
        /*
         * Una entidad tiene varios estados antes de ser guardada.
         * 1. Transiente
         * 2. Managed
         * 3. Detached
         * 
         * cuando se usan metodos del em como .close \\ .clear entra en estado Detached.
         * 
         * categoria = em.merge(categoria) -> traemos nuestro objeto de Detached a Managed nuevamente
         * categoria.setNombre("Software");
         * 
         * em.flush();
         * em.clear();
         * 
         * categoria = em.merge(categoria)
         * em.remove(categoria);
         * 
         * em.flush();
         */

         // consultas hibernate
         Producto producto1 = productoDao.consultarPorId(1L);
         System.out.println(producto1.getNombre());

         System.out.println("*********************");

         List<Producto> productos = productoDao.consultarTodos();
         productos.forEach(item -> System.out.println(item.getNombre()));

         System.out.println("*********************");

         List<Producto> productos2 = productoDao.consultaPorNombre("Xiaomi Note 2");
         productos2.forEach(item -> System.out.println(item.getNombre()));

         System.out.println("*********************");
            
         List<Producto> productos3 = productoDao.consultaNombrePorCategoria("Celulares");
         productos3.forEach(item -> System.out.println(item.getNombre()));

         System.out.println("*********************");

         BigDecimal precio = productoDao.consultarNombrePorPrecioProducto("Samsung A02");
         System.out.println(precio);

        em.close();

    }
}
