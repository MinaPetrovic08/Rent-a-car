/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service;


import java.util.List;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;

/**
 *
 * @author Mina
 */
public interface KorisnikService {
    public void add(KorisnikDto korisnikDto);
    public void delete(Long korisnikID);
    public void update(Long korisnikID, KorisnikDto korisnikDto);
    
    public List<KorisnikDto> getAll();

    public KorisnikDto getById(Long korisnikID);
}
