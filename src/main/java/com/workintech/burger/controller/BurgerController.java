package com.workintech.burger.controller;

import com.workintech.burger.dao.BurgerDao;
import com.workintech.burger.entity.BreadType;
import com.workintech.burger.entity.Burger;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {
private BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }
    @PostMapping("/")
    public Burger save(@Validated @RequestBody Burger burger){//@validated diyerek Burger de yazdığımız validasyon kurallarına bak diyoruz
        return burgerDao.save(burger);
    }
    @GetMapping("/")
    public List<Burger>get(){
        return burgerDao.findAll();
    }
    @GetMapping("/{id}")
    public Burger find(@Positive @RequestBody int id){
        return burgerDao.findById(id);
    }
    @GetMapping("/findByPrice/{price}")
    public List<Burger> findByPrice(@PathVariable int price){
        return burgerDao.findByPrice(price);
    }
@PutMapping("/")
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
}
@DeleteMapping("/{id}")
    public Burger delete(@PathVariable int id){
        Burger burger=find(id);
        burgerDao.delete(burger);
        return burger;
}
    @GetMapping("/findByBreadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable String breadType){
        return burgerDao.findByBreadType(BreadType.BURGER);
    }
}
