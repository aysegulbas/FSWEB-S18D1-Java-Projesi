package com.workintech.burger.dao;

import com.workintech.burger.entity.BreadType;
import com.workintech.burger.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BurgerDaolmpl implements BurgerDao {
    private EntityManager entityManager;
@Autowired
    public BurgerDaolmpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }//bize constructor oluştursun
@Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);//id ekleyip bize döncek
        return burger;
    }

    @Override
    public Burger findById(int id) {

    return entityManager.find(Burger.class,id);
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query=entityManager.createQuery("SELECT b FROM Burger b",Burger.class);//maksat b, quey oluşturup döndürmek
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query=entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>=:price ORDER BY b.price desc",Burger.class);//bize burger dönceği için Burger.Class yazdık
        query.setParameter("price",price);//parametreyi set etmemiz lazım
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query=entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType>=:type ORDER BY name desc",Burger.class);
        query.setParameter("type",breadType.name());
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String contents) {
        TypedQuery<Burger> query=entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents ilike '%:contents%'",Burger.class);
        query.setParameter("contents",contents);
        return query.getResultList();
    }
@Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }
@Transactional
    @Override
    public void delete(Burger burger) {
        entityManager.remove(burger);
    }
}
