package com.example.dataserves.Repozitary;

import com.example.dataserves.Entite.Dasturchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DasturchiRepozitary extends JpaRepository<Dasturchi,Integer> {
    boolean existsByEmail(String email);
}
