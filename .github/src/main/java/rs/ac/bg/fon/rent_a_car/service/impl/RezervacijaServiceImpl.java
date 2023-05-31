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
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaData;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;
import rs.ac.bg.fon.rent_a_car.model.Rezervacija;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaDto;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeData;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeDto;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.model.StavkaRezervacije;
import rs.ac.bg.fon.rent_a_car.repository.RezervacijaRepository;
import rs.ac.bg.fon.rent_a_car.repository.StavkaRezervacijeRepository;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;
import rs.ac.bg.fon.rent_a_car.service.RezervacijaService;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;

/**
 *
 * @author Mina
 */
@Transactional
@Service(value = "rezervacijaService")
public class RezervacijaServiceImpl implements RezervacijaService {

    @Autowired
    private final RezervacijaRepository rezervacijaRepository;

    @Autowired
    KorisnikService korisnikService;
    
    @Autowired
    VoziloService voziloService;
    
    @Autowired
    StavkaRezervacijeRepository stavkaRezervacijeRepository;
    
    private Mapper mapper = new Mapper();

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @Override
    public RezervacijaDto add(RezervacijaData rezervacijaData) {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setCenaBezPDV(rezervacijaData.getCenaBezPDV());
        rezervacija.setCenaSaPDV(rezervacijaData.getCenaSaPDV());
        rezervacija.setPDV(rezervacijaData.getPDV());
        rezervacija.setKorisnik(mapper.korisnikDtoToKorisnik(korisnikService.getById(rezervacijaData.getKorisnikID())));
        List<StavkaRezervacije> stavkeRezervacije = new ArrayList<>();
        for (StavkaRezervacijeData stavkaRezervacijeData : rezervacijaData.getStavkeRezervacije()) {
            StavkaRezervacije stavkaRezervacije = new StavkaRezervacije();
            stavkaRezervacije.setCenaStavke(stavkaRezervacijeData.getCenaStavke());
            stavkaRezervacije.setDatumOd(stavkaRezervacijeData.getDatumOd());
            stavkaRezervacije.setDatumDo(stavkaRezervacijeData.getDatumDo());
            stavkaRezervacije.setRbStavkeRezervacije(stavkaRezervacijeData.getRbStavkeRezervacije());
            stavkaRezervacije.setVozilo(mapper.voziloDtoToVozilo(voziloService.getById(stavkaRezervacijeData.getVoziloID())));
            stavkeRezervacije.add(stavkaRezervacije);
        }
        rezervacija.setStavkeRezervacije(stavkeRezervacije);
       return  mapper.rezervacijaToRezervacijaDto(rezervacijaRepository.save(rezervacija));
    }

    @Override
    public void delete(Long rezervacijaID) {
        Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(rezervacijaID);
        if (!rezervacija.isPresent()) {
            throw new EntityNotFoundException("Greska! Rezervacija ne postoji u bazi!");
        }
        List<StavkaRezervacije> postojeceStavke = rezervacija.get().getStavkeRezervacije();
        for (StavkaRezervacije stavkaRezervacije : postojeceStavke) {
            stavkaRezervacijeRepository.delete(stavkaRezervacije);
        }
        rezervacijaRepository.delete(rezervacija.get());
    }

    @Override
    public List<RezervacijaDto> getAll() {
        List<Rezervacija> rezervacije = rezervacijaRepository.findAll();
        if (!rezervacije.isEmpty()) {
            List<RezervacijaDto> rezervacijeDto = new ArrayList<>();
            for (Rezervacija rezervacija : rezervacije) {
                rezervacijeDto.add(mapper.rezervacijaToRezervacijaDto(rezervacija));
            }
            return rezervacijeDto;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public RezervacijaDto getById(Long rezervacijaID) {
        Optional<Rezervacija> rez = rezervacijaRepository.findById(rezervacijaID);
        if(!rez.isPresent()){
            throw  new EntityNotFoundException("Rezervacija ne postoji u bazi!");
        }
        Rezervacija rezervacija = rez.get();
        RezervacijaDto rezervacijaDto = mapper.rezervacijaToRezervacijaDto(rezervacija);
        List<StavkaRezervacijeDto> stavkeRezervacijeDto = new ArrayList<>();
        for (StavkaRezervacije stavkaRezervacije : rezervacija.getStavkeRezervacije()) {
            stavkeRezervacijeDto.add(mapper.stavkaRezervacijeToStavkaRezervacijeDto(stavkaRezervacije));
        }
        rezervacijaDto.setStavkeRezervacije(stavkeRezervacijeDto);
        return rezervacijaDto;
    }

    @Override
    public RezervacijaDto update(Long rezervacijaID, RezervacijaData rezervacijaData) {
        Optional<Rezervacija> rez = rezervacijaRepository.findById(rezervacijaID);
        if(!rez.isPresent()){
            throw new EntityNotFoundException("Rezervacija ne postoji u bazi!");
        }
        Rezervacija rezervacija = rez.get();
        List<StavkaRezervacije> postojeceStavke = rezervacija.getStavkeRezervacije();
        for (StavkaRezervacije stavkaRezervacije : postojeceStavke) {
            stavkaRezervacijeRepository.delete(stavkaRezervacije);
        }
        rezervacija.setCenaBezPDV(rezervacijaData.getCenaBezPDV());
        rezervacija.setCenaSaPDV(rezervacijaData.getCenaSaPDV());
        rezervacija.setPDV(rezervacijaData.getPDV());
        rezervacija.setKorisnik(mapper.korisnikDtoToKorisnik(korisnikService.getById(rezervacijaData.getKorisnikID())));
        List<StavkaRezervacije> stavkeRezervacije = new ArrayList<>();
        for (StavkaRezervacijeData stavkaRezervacijeData : rezervacijaData.getStavkeRezervacije()) {
            StavkaRezervacije sr = new StavkaRezervacije();
            sr.setCenaStavke(stavkaRezervacijeData.getCenaStavke());
            sr.setDatumDo(stavkaRezervacijeData.getDatumDo());
            sr.setDatumOd(stavkaRezervacijeData.getDatumOd());
            sr.setRbStavkeRezervacije(stavkaRezervacijeData.getRbStavkeRezervacije());
            VoziloDto voziloDto = voziloService.getById(stavkaRezervacijeData.getVoziloID());
            sr.setVozilo(mapper.voziloDtoToVozilo(voziloDto));
            stavkeRezervacije.add(sr);
        }
        rezervacija.setStavkeRezervacije(stavkeRezervacije);
        return mapper.rezervacijaToRezervacijaDto(rezervacijaRepository.save(rezervacija));
    }

}
