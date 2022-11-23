package com.example.dataserves.Entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Dasturchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private  String fam;
    @Column(nullable = false)
    private  String maosh;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String lavozim;
}
