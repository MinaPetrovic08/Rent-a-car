/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.model.Vozilo;
import rs.ac.bg.fon.rent_a_car.repository.TipVozilaRepository;
import rs.ac.bg.fon.rent_a_car.repository.VoziloRepository;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;

/**
 *
 * @author Mina
 */
@SpringBootTest
@AutoConfigureTestDatabase
public class VoziloServiceTest {

    @Autowired
    VoziloService voziloService;
    @Autowired
    VoziloRepository voziloRepository;
    @Autowired
    TipVozilaRepository tipVozilaRepository;
    Mapper mapper = new Mapper();

    @Test
    void testGetById() {
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Audi");
        vozilo.setModel("A3");
        vozilo.setRegistarskiBroj("BG-025-CB");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        Vozilo savedVozilo = voziloRepository.save(vozilo);

        VoziloDto foundVozilo = voziloService.getById(savedVozilo.getVoziloID());
        Assertions.assertEquals(foundVozilo.getVoziloID(), savedVozilo.getVoziloID());
        Assertions.assertEquals(foundVozilo.getCenaPoDanu().doubleValue(), savedVozilo.getCenaPoDanu().doubleValue());
        Assertions.assertEquals(foundVozilo.getRegistarskiBroj(), savedVozilo.getRegistarskiBroj());
        Assertions.assertEquals(foundVozilo.getMarka(), savedVozilo.getMarka());
        Assertions.assertEquals(foundVozilo.getModel(), savedVozilo.getModel());
        Assertions.assertEquals(foundVozilo.getTipVozila().getTipVozilaID(), savedVozilo.getTipVozila().getTipVozilaID());
        Assertions.assertEquals(foundVozilo.getTipVozila().getNazivTipaVozila(), savedVozilo.getTipVozila().getNazivTipaVozila());
        Assertions.assertEquals(foundVozilo.getKategorija(), savedVozilo.getKategorija());
    }

    @Test
    void testSave() {
        VoziloDto vozilo = new VoziloDto();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Astra");
        vozilo.setModel("Opel");
        vozilo.setRegistarskiBroj("BG-123-CB");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo.setTipVozila(mapper.tipVozilaToTipVozilaDto(tipVozila));
        VoziloDto savedVozilo = voziloService.add(vozilo);
        Assertions.assertEquals(vozilo.getCenaPoDanu().doubleValue(), savedVozilo.getCenaPoDanu().doubleValue());
        Assertions.assertEquals(vozilo.getRegistarskiBroj(), savedVozilo.getRegistarskiBroj());
        Assertions.assertEquals(vozilo.getMarka(), savedVozilo.getMarka());
        Assertions.assertEquals(vozilo.getModel(), savedVozilo.getModel());
        Assertions.assertEquals(vozilo.getTipVozila().getTipVozilaID(), savedVozilo.getTipVozila().getTipVozilaID());
        Assertions.assertEquals(vozilo.getTipVozila().getNazivTipaVozila(), savedVozilo.getTipVozila().getNazivTipaVozila());
        Assertions.assertEquals(vozilo.getKategorija(), savedVozilo.getKategorija());
    }

    
    @Test
    void testDelete(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("2");
        vozilo.setMarka("Mercedes");
        vozilo.setModel("GLA");
        vozilo.setRegistarskiBroj("BG-987-LD");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        Vozilo savedVozilo = voziloRepository.save(vozilo);
        
        voziloService.delete(savedVozilo.getVoziloID());
        Optional<Vozilo> deletedVozilo = voziloRepository.findById(savedVozilo.getVoziloID());
        Assertions.assertFalse(deletedVozilo.isPresent());
    }
    
    @Test
    void testUpdate(){
        Vozilo vozilo = new Vozilo();
        vozilo.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo.setKategorija("1");
        vozilo.setMarka("Fiat");
        vozilo.setModel("Bravo");
        vozilo.setRegistarskiBroj("BG-1025-VT");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo.setTipVozila(tipVozila);
        VoziloDto savedVozilo = voziloService.add(mapper.voziloToVoziloDto(vozilo));
        
        savedVozilo.setCenaPoDanu(BigDecimal.valueOf(14.5));
        savedVozilo.setKategorija("2");
        savedVozilo.setRegistarskiBroj("BG-1036-BT");
        
        VoziloDto updatedVozilo = voziloService.update(savedVozilo.getVoziloID(), savedVozilo);
        Assertions.assertEquals(savedVozilo.getCenaPoDanu().doubleValue(), updatedVozilo.getCenaPoDanu().doubleValue());
        Assertions.assertEquals(savedVozilo.getRegistarskiBroj(), updatedVozilo.getRegistarskiBroj());
        Assertions.assertEquals(savedVozilo.getKategorija(), updatedVozilo.getKategorija());
    }
    
    @Test
    void testGetAll(){
        Vozilo vozilo1 = new Vozilo();
        vozilo1.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo1.setKategorija("1");
        vozilo1.setMarka("Fiat");
        vozilo1.setModel("Bravo");
        vozilo1.setRegistarskiBroj("BG-1025-VT");
        TipVozila tipVozila = tipVozilaRepository.save(new TipVozila(1L, "Auto"));
        vozilo1.setTipVozila(tipVozila);
        voziloRepository.save(vozilo1);
        
        Vozilo vozilo2 = new Vozilo();
        vozilo2.setCenaPoDanu(BigDecimal.valueOf(12.5));
        vozilo2.setKategorija("1");
        vozilo2.setMarka("Fiat");
        vozilo2.setModel("Bravo");
        vozilo2.setRegistarskiBroj("BG-1026-DS");
        vozilo2.setTipVozila(tipVozila);
        voziloRepository.save(vozilo2);
        
        List<VoziloDto> vozila = voziloService.getAll();
        Assertions.assertTrue(vozila.size()>=2);
    }
}
