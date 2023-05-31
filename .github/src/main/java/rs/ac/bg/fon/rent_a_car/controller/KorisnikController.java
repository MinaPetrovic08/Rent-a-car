/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;

/**
 *
 * @author Mina
 */
@Controller
public class KorisnikController {
    @Autowired
    KorisnikService korisnikService;
    
    @GetMapping("/korisnici")
    public String showTable(Model model){
        List<KorisnikDto> korisnici = korisnikService.getAll();
        model.addAttribute("korisnici", korisnici);
        return "korisnici";
    }
    
    @GetMapping("/korisnici/new")
    public String openForm(Model model){
        KorisnikDto korisnikDto = new KorisnikDto();
        model.addAttribute("korisnik", korisnikDto);
        return "noviKorisnik";
    }
    
    @PostMapping("/korisnici")
    public String saveKorisnik(@ModelAttribute("korisnik") KorisnikDto korisnikDto){
        korisnikService.add(korisnikDto);
        return "redirect:/korisnici";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String updateKorisnik(@PathVariable(value="id") Long korisnikID, @ModelAttribute KorisnikDto korisnikDto){
        korisnikService.update(korisnikID, korisnikDto);
        return "redirect:/korisnici";
    }
    
    @GetMapping("/korisnici/{id}")
    public String showUpdateForm(@PathVariable(value="id") Long korisnikID, Model model){
        KorisnikDto korisnikDto = korisnikService.getById(korisnikID);
        model.addAttribute("korisnik", korisnikDto);
        return "updateKorisnik";
    }
    
    @DeleteMapping("/korisnici/{id}")
    public ResponseEntity<String> deleteVozilo(@PathVariable(value="id") Long korisnikID){
        korisnikService.delete(korisnikID);
        return ResponseEntity.ok().build();
    }
}
