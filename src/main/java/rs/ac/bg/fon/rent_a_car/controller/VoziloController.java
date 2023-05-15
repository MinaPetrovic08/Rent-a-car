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
import org.springframework.web.bind.annotation.PutMapping;
import rs.ac.bg.fon.rent_a_car.dto.TipVozilaDto;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.service.TipVozilaService;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;

/**
 *
 * @author Mina
 */
@Controller
public class VoziloController {
    @Autowired
    VoziloService voziloService;
    @Autowired
    TipVozilaService tipVozilaService;

    @GetMapping("/vozila")
    public String showTable(Model model){
        List<VoziloDto> vozila = voziloService.getAll();
        model.addAttribute("vozila",vozila);
        return "vozila";
    }
    
    @GetMapping("/vozila/new")
    public String openForm(Model model){
        VoziloDto voziloDto = new VoziloDto();
        model.addAttribute("vozilo", voziloDto);
        List<TipVozilaDto> tipoviVozila = tipVozilaService.getAll();
        model.addAttribute("tipoviVozila", tipoviVozila);
        return "novoVozilo";
    }
    
    @PostMapping("/vozila")
    public String saveVozilo(@ModelAttribute("vozilo") VoziloDto voziloDto){
        voziloService.add(voziloDto);
        return "redirect:/vozila";
    }
    
    @GetMapping("/vozila/{id}")
    public String showUpdateFrom(@PathVariable (value="id") Long id, Model model){
        VoziloDto voziloDto = voziloService.getById(id);
        model.addAttribute("vozilo", voziloDto);
        List<TipVozilaDto> tipoviVozila = tipVozilaService.getAll();
        model.addAttribute("tipoviVozila", tipoviVozila);
        return "updateVozilo";
    }
    
    @PutMapping("/vozila/{id}")
    public String updateVozila(@PathVariable (value="id") Long id, @ModelAttribute("vozilo") VoziloDto voziloDto){
        voziloService.update(id ,voziloDto);
        return "redirect:/vozila";
    }
    
    @DeleteMapping("/vozila/{id}")
    public ResponseEntity<String> deleteVozilo(@PathVariable(value="id") Long voziloID){
        voziloService.delete(voziloID);
        return ResponseEntity.ok().build();
    }
}
