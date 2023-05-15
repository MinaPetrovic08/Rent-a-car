/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.rent_a_car.service.impl.common.Mapper;
import rs.ac.bg.fon.rent_a_car.model.Korisnik;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.repository.KorisnikRepository;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;

/**
 *
 * @author Mina
 */
@Transactional
@Service(value = "korisnikService")
public class KorisnikServiceImpl implements KorisnikService{

    @Autowired
    private final KorisnikRepository korisnikRepository;
    
    private final Mapper mapper = new Mapper();
    
    public KorisnikServiceImpl(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }
    
    @Override
    public void add(KorisnikDto korisnikDto) {
        if(korisnikDto.getJMBG().length()!=13){
            throw new IllegalArgumentException("JMBG mora imati 13 cifara!");
        }
        korisnikRepository.save(mapper.korisnikDtoToKorisnik(korisnikDto));
    }

    @Override
    public void delete(Long korisnikID) {
        Optional<Korisnik> k = korisnikRepository.findById(korisnikID);
        if(!k.isPresent()){
            throw new EntityNotFoundException("Greska! Korisnik ne postoji u bazi!");
        }
        korisnikRepository.delete(k.get());
    }

    @Override
    public void update(Long korisnikID, KorisnikDto korisnikDto) {
        Optional<Korisnik> k = korisnikRepository.findById(korisnikID);
        if(!k.isPresent()){
            throw new EntityNotFoundException("Greska! Korisnik ne postoji u bazi!");
        }
        k.get().setImePrezime(korisnikDto.getImePrezime());
        if(korisnikDto.getJMBG().length()!=13){
            throw new IllegalArgumentException("JMBG mora imati 13 cifara!");
        }
        k.get().setJMBG(korisnikDto.getJMBG());
        k.get().setAdresa(korisnikDto.getAdresa());
        k.get().setEmail(korisnikDto.getEmail());
        k.get().setTelefon(korisnikDto.getTelefon());
        korisnikRepository.save(k.get());
    }

    @Override
    public List<KorisnikDto> getAll() {
       List<Korisnik> korisnici = korisnikRepository.findAll();
       if(!korisnici.isEmpty()){
           List<KorisnikDto> korisniciDto = new ArrayList<>();
        for (Korisnik korisnik : korisnici) {
            korisniciDto.add(mapper.korisnikToKorisnikDto(korisnik));
        }
        return korisniciDto;
       }
       return null;
    }

    @Override
    public KorisnikDto getById(Long korisnikID) {
        Optional<Korisnik> korisnik = korisnikRepository.findById(korisnikID);
        if(!korisnik.isPresent()){
            throw new EntityNotFoundException("Greska! Korisnik ne postoji u bazi.");
        }
        return mapper.korisnikToKorisnikDto(korisnik.get());
    }
}
