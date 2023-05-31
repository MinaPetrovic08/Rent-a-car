/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service;

import java.util.List;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;

/**
 *
 * @author Mina
 */
public interface VoziloService {
     public VoziloDto add(VoziloDto voziloDto);
    public void delete(Long voziloID);
    public VoziloDto update(Long voziloId, VoziloDto voziloDto);
    
    public List<VoziloDto> getAll();

    public VoziloDto getById(Long id);
}
