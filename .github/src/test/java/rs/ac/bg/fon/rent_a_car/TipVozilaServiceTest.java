/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.rent_a_car.dto.TipVozilaDto;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.repository.TipVozilaRepository;
import rs.ac.bg.fon.rent_a_car.service.TipVozilaService;

/**
 *
 * @author Mina
 */
@SpringBootTest
@AutoConfigureTestDatabase
public class TipVozilaServiceTest {
    
    @Autowired
    TipVozilaService tipVozilaService;
    @Autowired
    TipVozilaRepository tipVozilaRepository;
    
    @Test
    void testGetAll(){
        TipVozila tipVozila1 = new TipVozila(1L, "Auto");
        TipVozila tipVozila2 = new TipVozila(2L, "Kombi");
        tipVozilaRepository.save(tipVozila1);
        tipVozilaRepository.save(tipVozila2);
        
        List<TipVozilaDto> tipoviVozila = tipVozilaService.getAll();
        Assertions.assertEquals(2, tipoviVozila.size());
    }
    
}
