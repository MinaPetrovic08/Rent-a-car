/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service;

import java.util.List;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaData;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaDto;

/**
 *
 * @author Mina
 */
public interface RezervacijaService {
    public RezervacijaDto add(RezervacijaData rezervacijaData);
    public void delete(Long rezervacijaID);
    public RezervacijaDto update(Long rezervacijaID, RezervacijaData rezervacijaData);
    
    public List<RezervacijaDto> getAll();
    public RezervacijaDto getById(Long rezervacijaID);
}
