/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.model.Vozilo;
import rs.ac.bg.fon.rent_a_car.repository.VoziloRepository;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;

/**
 *
 * @author Mina
 */
@Transactional
@Service(value = "voziloService")
public class VoziloServiceImpl implements VoziloService{
    @Autowired
    private final VoziloRepository voziloRepository;

    private Mapper mapper = new Mapper();
    
    public VoziloServiceImpl(VoziloRepository voziloRepository) {
        this.voziloRepository = voziloRepository;
    }

    @Override
    public void add(VoziloDto voziloDto) {
        voziloRepository.save(mapper.voziloDtoToVozilo(voziloDto));
    }

    @Override
    public void delete(Long voziloID) {
        Optional<Vozilo> vozilo = voziloRepository.findById(voziloID);
        if(!vozilo.isPresent()){
            throw new EntityNotFoundException("Greska! Vozilo ne postoji u bazi!");
        }
        voziloRepository.delete(vozilo.get());
    }

    @Override
    public void update(Long voziloID, VoziloDto voziloDto) {
        Optional<Vozilo> vozilo = voziloRepository.findById(voziloID);
        if(!vozilo.isPresent()){
            throw new EntityNotFoundException("Greska! Vozilo ne postoji u bazi!");
        }
        vozilo.get().setCenaPoDanu(voziloDto.getCenaPoDanu());
        vozilo.get().setKategorija(voziloDto.getKategorija());
        vozilo.get().setMarka(voziloDto.getMarka());
        vozilo.get().setModel(voziloDto.getModel());
        vozilo.get().setRegistarskiBroj(voziloDto.getRegistarskiBroj());
        vozilo.get().setTipVozila(mapper.tipVozilaDtoToTipVozila(voziloDto.getTipVozila()));
        voziloRepository.save(vozilo.get());
    }

    @Override
    public List<VoziloDto> getAll() {
        List<Vozilo> vozila = voziloRepository.findAll();
        if(!vozila.isEmpty()){
            List<VoziloDto> vozilaDto = new ArrayList<>();
            for (Vozilo vozilo : vozila) {
                vozilaDto.add(mapper.voziloToVoziloDto(vozilo));
            }
            return vozilaDto;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public VoziloDto getById(Long id) {
        Optional<Vozilo> vozilo = voziloRepository.findById(id);
        if(!vozilo.isPresent()){
            throw new EntityNotFoundException("Greska! Vozilo ne postoji u bazi!");
        }
        return mapper.voziloToVoziloDto(vozilo.get());
    }

    
}
