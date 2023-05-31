/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.rent_a_car.model.Korisnik;

/**
 *
 * @author Mina
 */
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
    
}
