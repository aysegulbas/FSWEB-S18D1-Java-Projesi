package com.workintech.burger.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "burger", schema = "spring")//hangi tablo ve şemaya bağlı olduğunu yazarız
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//@Positive bunu sildik çünkü id göndermediğimiz durumlar var
    private int id;
    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;
    @Column(name = "price")
    @DecimalMin("20")
    private int price;
    @Column(name = "is_Vegan")
    private boolean isVegan;
    @Enumerated(EnumType.STRING)
    private BreadType breadType;
    @Column(name = "contents")
    private List<String> contents;
}
