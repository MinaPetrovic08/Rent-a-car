/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaData;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaDto;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeData;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeDto;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;
import rs.ac.bg.fon.rent_a_car.service.RezervacijaService;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;

/**
 *
 * @author Mina
 */
@Controller
public class RezervacijaController {
    @Autowired
    RezervacijaService rezervacijaService;
    @Autowired
    VoziloService voziloService;
    @Autowired
    KorisnikService korisnikService;
    
    @GetMapping("/rezervacije")
    public String showTable(Model model){
        List<RezervacijaDto> rezervacije = rezervacijaService.getAll();
        model.addAttribute("rezervacije", rezervacije);
        return "rezervacije";
    }
    
    @GetMapping("/rezervacije/new")
    public String openForm(Model model){
        model.addAttribute("rezervacija", new RezervacijaDto());
        List<KorisnikDto> korisniciDto = korisnikService.getAll();
        model.addAttribute("korisnici", korisniciDto);
        model.addAttribute("stavkaRezervacije", new StavkaRezervacijeDto());
        List<VoziloDto> vozila = voziloService.getAll();
        model.addAttribute("vozila", vozila);
        List<StavkaRezervacijeDto> stavkeRezervacije = new ArrayList<>();
        model.addAttribute("stavkeRezervacije", stavkeRezervacije);
        model.addAttribute("rezervacijaData", new RezervacijaData());
        model.addAttribute("stavkaRezervacijeData", new StavkaRezervacijeData());
        return "novaRezervacija";
    }
    
    @GetMapping("/rezervacije/vozilo/{id}")
    public ResponseEntity<VoziloDto> vratiVozilo(@PathVariable(value="id") Long voziloID){
        VoziloDto vozilo = voziloService.getById(voziloID);
        return ResponseEntity.ok(vozilo);
    }
    
    @PostMapping("/rezervacije")
    public ResponseEntity<RezervacijaData> sacuvajRezervaciju(@RequestBody RezervacijaData rezervacijaData){
        rezervacijaService.add(rezervacijaData);
        return ResponseEntity.ok(rezervacijaData);
    }
    
    @GetMapping("/rezervacije/{id}")
    public String openUpdateForm(@PathVariable(value="id") Long rezervacijaID, Model model){
        RezervacijaDto rezervacijaDto = rezervacijaService.getById(rezervacijaID);
        model.addAttribute("rezervacija", rezervacijaDto);
        model.addAttribute("stavkaRezervacije", new StavkaRezervacijeDto());
        List<VoziloDto> vozila = voziloService.getAll();
        model.addAttribute("vozila", vozila);
        List<KorisnikDto> korisniciDto = korisnikService.getAll();
        model.addAttribute("korisnici", korisniciDto);
         model.addAttribute("rezervacijaData", new RezervacijaData());
        model.addAttribute("stavkaRezervacijeData", new StavkaRezervacijeData());
        return "updateRezervacija";
    }
    
    @PutMapping("/rezervacije/{id}")
    public ResponseEntity<RezervacijaData> updateRezervacija(@PathVariable(value="id") Long rezervacijaID, @RequestBody RezervacijaData rezervacijaData){
        rezervacijaService.update(rezervacijaID, rezervacijaData);
        return ResponseEntity.ok(rezervacijaData);
    }
    
    @DeleteMapping("/rezervacije/{id}")
    public ResponseEntity<RezervacijaData> deleteRezervacija(@PathVariable(value="id") Long rezervacijaID){
        rezervacijaService.delete(rezervacijaID);
        return ResponseEntity.ok().build();
    }
}
