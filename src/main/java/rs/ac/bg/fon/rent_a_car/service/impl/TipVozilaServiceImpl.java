/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.dto.TipVozilaDto;
import rs.ac.bg.fon.rent_a_car.repository.TipVozilaRepository;
import rs.ac.bg.fon.rent_a_car.service.TipVozilaService;

/**
 *
 * @author Mina
 */
@Transactional
@Service(value = "tipVozilaService")
public class TipVozilaServiceImpl implements TipVozilaService{
    
    @Autowired
    private final TipVozilaRepository tipVozilaRepository;

    private Mapper mapper = new Mapper();
    
    public TipVozilaServiceImpl(TipVozilaRepository tipVozilaRepository) {
        this.tipVozilaRepository = tipVozilaRepository;
    }

    @Override
    public List<TipVozilaDto> getAll() {
        List<TipVozila> tipoviVozila = tipVozilaRepository.findAll();
        if(!tipoviVozila.isEmpty()){
            List<TipVozilaDto> tipoviVozilaDto = new ArrayList<>();
        for (TipVozila tipVozila : tipoviVozila) {
            tipoviVozilaDto.add(mapper.tipVozilaToTipVozilaDto(tipVozila));
        }
        return tipoviVozilaDto;
        }
        return null;
    }
}
