/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.model.Vozilo;
import rs.ac.bg.fon.rent_a_car.repository.TipVozilaRepository;
import rs.ac.bg.fon.rent_a_car.repository.VoziloRepository;


/**
 *
 * @author Mina
 */
@SpringBootTest
@AutoConfigureTestDatabase
public class VoziloRespositoryTest {
    @Autowired
    VoziloRepository voziloRepository;
    @Autowired
    TipVozilaRepository tipVozilaRepository;
    
    @Test
    void testSave(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Audi");
        vozilo.setModel("A3");
        vozilo.setRegistarskiBroj("BG-025-CB");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        
        Vozilo savedVozilo = voziloRepository.save(vozilo);
        Assertions.assertEquals(vozilo.getCenaPoDanu(), savedVozilo.getCenaPoDanu());
        Assertions.assertEquals(vozilo.getKategorija(), savedVozilo.getKategorija());
        Assertions.assertEquals(vozilo.getMarka(), savedVozilo.getMarka());
        Assertions.assertEquals(vozilo.getModel(), savedVozilo.getModel());
        Assertions.assertEquals(vozilo.getRegistarskiBroj(), savedVozilo.getRegistarskiBroj());
        Assertions.assertEquals(vozilo.getTipVozila(), savedVozilo.getTipVozila()); 
    }
    
    @Test
    void testFindById(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("2");
        vozilo.setMarka("Audi");
        vozilo.setModel("A6");
        vozilo.setRegistarskiBroj("BG-123-AB");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(2L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        Vozilo savedVozilo = voziloRepository.save(vozilo);
        
        Optional<Vozilo> foundVozilo = voziloRepository.findById(savedVozilo.getVoziloID());
        Assertions.assertTrue(foundVozilo.isPresent());
        Assertions.assertEquals(foundVozilo.get().getVoziloID(), savedVozilo.getVoziloID());
        Assertions.assertEquals(foundVozilo.get().getCenaPoDanu().floatValue(), savedVozilo.getCenaPoDanu().floatValue());
        Assertions.assertEquals(foundVozilo.get().getKategorija(), savedVozilo.getKategorija());
        Assertions.assertEquals(foundVozilo.get().getMarka(), savedVozilo.getMarka());
        Assertions.assertEquals(foundVozilo.get().getModel(), savedVozilo.getModel());
        Assertions.assertEquals(foundVozilo.get().getRegistarskiBroj(), savedVozilo.getRegistarskiBroj());
        Assertions.assertEquals(foundVozilo.get().getTipVozila(), savedVozilo.getTipVozila());
        
    }
    
    @Test
    void testDelete(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Mercedes");
        vozilo.setModel("GLA");
        vozilo.setRegistarskiBroj("BG-254-CD");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(2L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        Vozilo savedVozilo = voziloRepository.save(vozilo);
        voziloRepository.delete(savedVozilo);
        
        Assertions.assertFalse(voziloRepository.findById(savedVozilo.getVoziloID()).isPresent());
    }
    
    @Test
    void testFindAll(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Reno");
        vozilo.setModel("Clio");
        vozilo.setRegistarskiBroj("BG-874-SA");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(2L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        voziloRepository.save(vozilo);
        Vozilo vozilo2 = new Vozilo();
        vozilo2.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo2.setKategorija("2");
        vozilo2.setMarka("Opel");
        vozilo2.setModel("Astra");
        vozilo2.setRegistarskiBroj("BG-856-GL");
        vozilo2.setTipVozila(tipVozila);
        voziloRepository.save(vozilo2);
        
        List<Vozilo> vozila = voziloRepository.findAll();
        Assertions.assertFalse(vozila.isEmpty());
    }
}
