/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaData;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaDto;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeData;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.model.Vozilo;
import rs.ac.bg.fon.rent_a_car.repository.RezervacijaRepository;
import rs.ac.bg.fon.rent_a_car.repository.StavkaRezervacijeRepository;
import rs.ac.bg.fon.rent_a_car.repository.TipVozilaRepository;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;
import rs.ac.bg.fon.rent_a_car.service.RezervacijaService;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;


/**
 *
 * @author Mina
 */
@SpringBootTest
@AutoConfigureTestDatabase
public class RezervacijaServiceTest {
    
   @Autowired
   RezervacijaService rezervacijaService;
   @Autowired
   RezervacijaRepository rezervacijaRepository;
   @Autowired
   KorisnikService korisnikService;
   @Autowired
   VoziloService voziloService;
   @Autowired
   TipVozilaRepository tipVozilaRepository;
    Mapper mapper= new Mapper();
   
   @Test
   void testAdd(){
       RezervacijaData rezervacijaData = new RezervacijaData();
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(30));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(33));
       KorisnikDto k = new KorisnikDto();
       k.setAdresa("Glasinacka 6");
       k.setEmail("test1234@mail.com");
       k.setImePrezime("Pera Peric");
       k.setJMBG("1234156327895");
       k.setTelefon("0694586978");
       KorisnikDto savedK = korisnikService.add(k);
       rezervacijaData.setKorisnikID(savedK.getKorisnikID());
       rezervacijaData.setPDV(BigDecimal.valueOf(10));
       List<StavkaRezervacijeData> stavkeRezervacije = new ArrayList<>();
       StavkaRezervacijeData srd = new StavkaRezervacijeData();
       srd.setCenaStavke(BigDecimal.valueOf(10));
       srd.setDatumOd(new Date(2023, 5, 25));
       srd.setDatumDo(new Date(2023, 5, 28));
       Vozilo v = new Vozilo();
               v.setCenaPoDanu(BigDecimal.valueOf(10));
               v.setKategorija("1");
               v.setMarka("Audi");
               v.setModel("A3");
               v.setRegistarskiBroj("VP-036-BD");
       TipVozila tipVozila = new TipVozila(1L, "Auto");
       TipVozila savedTV = tipVozilaRepository.save(tipVozila);
               v.setTipVozila(savedTV);
       VoziloDto savedV = voziloService.add(mapper.voziloToVoziloDto(v));
       srd.setVoziloID(savedV.getVoziloID());
       stavkeRezervacije.add(srd);
       rezervacijaData.setStavkeRezervacije(stavkeRezervacije);
       RezervacijaDto savedRezervacija = rezervacijaService.add(rezervacijaData);
       
       Assertions.assertEquals(savedRezervacija.getCenaBezPDV(), rezervacijaData.getCenaBezPDV());
       Assertions.assertEquals(savedRezervacija.getCenaSaPDV(), rezervacijaData.getCenaSaPDV());
       Assertions.assertEquals(savedRezervacija.getKorisnik().getKorisnikID(), rezervacijaData.getKorisnikID());
       Assertions.assertEquals(savedRezervacija.getPDV(), rezervacijaData.getPDV());
   }
   
   @Test
   void testDelete(){
       RezervacijaData rezervacijaData = new RezervacijaData();
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(30));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(33));
       KorisnikDto k = new KorisnikDto();
       k.setAdresa("Glasinacka 6");
       k.setEmail("test1@mail.com");
       k.setImePrezime("Pera Peric");
       k.setJMBG("8564156327895");
       k.setTelefon("0694586978");
       KorisnikDto savedK = korisnikService.add(k);
       rezervacijaData.setKorisnikID(savedK.getKorisnikID());
       rezervacijaData.setPDV(BigDecimal.valueOf(10));
       List<StavkaRezervacijeData> stavkeRezervacije = new ArrayList<>();
       StavkaRezervacijeData srd = new StavkaRezervacijeData();
       srd.setCenaStavke(BigDecimal.valueOf(10));
       srd.setDatumOd(new Date(2023, 5, 25));
       srd.setDatumDo(new Date(2023, 5, 28));
       Vozilo v = new Vozilo();
               v.setCenaPoDanu(BigDecimal.valueOf(10));
               v.setKategorija("1");
               v.setMarka("Audi");
               v.setModel("A3");
               v.setRegistarskiBroj("VP-036-BD");
       TipVozila tipVozila = new TipVozila(1L, "Auto");
       TipVozila savedTV = tipVozilaRepository.save(tipVozila);
               v.setTipVozila(savedTV);
       VoziloDto savedV = voziloService.add(mapper.voziloToVoziloDto(v));
       srd.setVoziloID(savedV.getVoziloID());
       stavkeRezervacije.add(srd);
       rezervacijaData.setStavkeRezervacije(stavkeRezervacije);
       RezervacijaDto savedRezervacija = rezervacijaService.add(rezervacijaData);
       
       rezervacijaService.delete(savedRezervacija.getRezervacijaID());
       Assertions.assertFalse(rezervacijaRepository.findById(savedRezervacija.getRezervacijaID()).isPresent());
   }
   
   @Test
   void testUpdate(){
       RezervacijaData rezervacijaData = new RezervacijaData();
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(30));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(33));
       KorisnikDto k = new KorisnikDto();
       k.setAdresa("Glasinacka 6");
       k.setEmail("test2@mail.com");
       k.setImePrezime("Pera Peric");
       k.setJMBG("8423156327895");
       k.setTelefon("0694586978");
       KorisnikDto savedK = korisnikService.add(k);
       rezervacijaData.setKorisnikID(savedK.getKorisnikID());
       rezervacijaData.setPDV(BigDecimal.valueOf(10));
       List<StavkaRezervacijeData> stavkeRezervacije = new ArrayList<>();
       StavkaRezervacijeData srd = new StavkaRezervacijeData();
       srd.setCenaStavke(BigDecimal.valueOf(10));
       srd.setDatumOd(new Date(2023, 5, 25));
       srd.setDatumDo(new Date(2023, 5, 28));
       Vozilo v = new Vozilo();
               v.setCenaPoDanu(BigDecimal.valueOf(10));
               v.setKategorija("1");
               v.setMarka("Audi");
               v.setModel("A3");
               v.setRegistarskiBroj("VP-036-BD");
       TipVozila tipVozila = new TipVozila(1L, "Auto");
       TipVozila savedTV = tipVozilaRepository.save(tipVozila);
               v.setTipVozila(savedTV);
       VoziloDto savedV = voziloService.add(mapper.voziloToVoziloDto(v));
       srd.setVoziloID(savedV.getVoziloID());
       stavkeRezervacije.add(srd);
       rezervacijaData.setStavkeRezervacije(stavkeRezervacije);
       RezervacijaDto savedRezervacija = rezervacijaService.add(rezervacijaData);
       
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(20));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(22));
       
       RezervacijaDto updatedRezervacija = rezervacijaService.update(savedRezervacija.getRezervacijaID(), rezervacijaData);
       
       Assertions.assertEquals(updatedRezervacija.getRezervacijaID(), savedRezervacija.getRezervacijaID());
       Assertions.assertEquals(updatedRezervacija.getCenaBezPDV(), rezervacijaData.getCenaBezPDV());
       Assertions.assertEquals(updatedRezervacija.getCenaSaPDV(), rezervacijaData.getCenaSaPDV());
       
   }
   
   @Test
   void testGetAll(){
        RezervacijaData rezervacijaData = new RezervacijaData();
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(30));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(33));
       KorisnikDto k = new KorisnikDto();
       k.setAdresa("Glasinacka 6");
       k.setEmail("test4@mail.com");
       k.setImePrezime("Pera Peric");
       k.setJMBG("8813156327895");
       k.setTelefon("0694586978");
       KorisnikDto savedK = korisnikService.add(k);
       rezervacijaData.setKorisnikID(savedK.getKorisnikID());
       rezervacijaData.setPDV(BigDecimal.valueOf(10));
       List<StavkaRezervacijeData> stavkeRezervacije = new ArrayList<>();
       StavkaRezervacijeData srd = new StavkaRezervacijeData();
       srd.setCenaStavke(BigDecimal.valueOf(10));
       srd.setDatumOd(new Date(2023, 5, 25));
       srd.setDatumDo(new Date(2023, 5, 28));
       Vozilo v = new Vozilo();
               v.setCenaPoDanu(BigDecimal.valueOf(10));
               v.setKategorija("1");
               v.setMarka("Audi");
               v.setModel("A3");
               v.setRegistarskiBroj("VP-036-BD");
       TipVozila tipVozila = new TipVozila(1L, "Auto");
       TipVozila savedTV = tipVozilaRepository.save(tipVozila);
               v.setTipVozila(savedTV);
       VoziloDto savedV = voziloService.add(mapper.voziloToVoziloDto(v));
       srd.setVoziloID(savedV.getVoziloID());
       stavkeRezervacije.add(srd);
       rezervacijaData.setStavkeRezervacije(stavkeRezervacije);
       rezervacijaService.add(rezervacijaData);
       
       List<RezervacijaDto> rezervacije = rezervacijaService.getAll();
       Assertions.assertFalse(rezervacije.isEmpty());
               
   }
   
   @Test
   void getById(){
        RezervacijaData rezervacijaData = new RezervacijaData();
       rezervacijaData.setCenaBezPDV(BigDecimal.valueOf(30));
       rezervacijaData.setCenaSaPDV(BigDecimal.valueOf(33));
       KorisnikDto k = new KorisnikDto();
       k.setAdresa("Glasinacka 6");
       k.setEmail("test3@mail.com");
       k.setImePrezime("Pera Peric");
       k.setJMBG("8423998745895");
       k.setTelefon("0694586978");
       KorisnikDto savedK = korisnikService.add(k);
       rezervacijaData.setKorisnikID(savedK.getKorisnikID());
       rezervacijaData.setPDV(BigDecimal.valueOf(10));
       List<StavkaRezervacijeData> stavkeRezervacije = new ArrayList<>();
       StavkaRezervacijeData srd = new StavkaRezervacijeData();
       srd.setCenaStavke(BigDecimal.valueOf(10));
       srd.setDatumOd(new Date(2023, 5, 25));
       srd.setDatumDo(new Date(2023, 5, 28));
       Vozilo v = new Vozilo();
               v.setCenaPoDanu(BigDecimal.valueOf(10));
               v.setKategorija("1");
               v.setMarka("Audi");
               v.setModel("A3");
               v.setRegistarskiBroj("VP-036-BD");
       TipVozila tipVozila = new TipVozila(1L, "Auto");
       TipVozila savedTV = tipVozilaRepository.save(tipVozila);
               v.setTipVozila(savedTV);
       VoziloDto savedV = voziloService.add(mapper.voziloToVoziloDto(v));
       srd.setVoziloID(savedV.getVoziloID());
       stavkeRezervacije.add(srd);
       rezervacijaData.setStavkeRezervacije(stavkeRezervacije);
       RezervacijaDto savedRezervacija = rezervacijaService.add(rezervacijaData);
       RezervacijaDto foundRezervacija = rezervacijaService.getById(savedRezervacija.getRezervacijaID());
       Assertions.assertTrue(foundRezervacija != null);
       Assertions.assertEquals(foundRezervacija.getRezervacijaID(), savedRezervacija.getRezervacijaID());
   }
}
