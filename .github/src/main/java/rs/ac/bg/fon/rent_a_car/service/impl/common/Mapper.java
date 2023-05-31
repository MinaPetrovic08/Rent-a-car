/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.service.impl.common;

import java.io.Serializable;
import rs.ac.bg.fon.rent_a_car.model.Korisnik;
import rs.ac.bg.fon.rent_a_car.model.Rezervacija;
import rs.ac.bg.fon.rent_a_car.model.StavkaRezervacije;
import rs.ac.bg.fon.rent_a_car.model.TipVozila;
import rs.ac.bg.fon.rent_a_car.model.Vozilo;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.dto.RezervacijaDto;
import rs.ac.bg.fon.rent_a_car.dto.StavkaRezervacijeDto;
import rs.ac.bg.fon.rent_a_car.dto.TipVozilaDto;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;

/**
 *
 * @author Mina
 */

public class Mapper implements Serializable {

    public RezervacijaDto rezervacijaToRezervacijaDto(Rezervacija r){
        return new RezervacijaDto(r.getRezervacijaID(),r.getCenaBezPDV(), r.getPDV(),  r.getCenaSaPDV(),korisnikToKorisnikDto(r.getKorisnik()),null);
    }
    
    public Rezervacija rezervacijaDtoToRezervacija(RezervacijaDto r){
        return new Rezervacija(r.getRezervacijaID(),r.getCenaBezPDV(), r.getPDV(),  r.getCenaSaPDV(), korisnikDtoToKorisnik(r.getKorisnik()), null);
    }
    
    public StavkaRezervacije stavkaRezervacijeDtoToStavkaRezervacije(StavkaRezervacijeDto stavkaRezervacije){
        return new StavkaRezervacije(stavkaRezervacije.getRbStavkeRezervacije(),
        stavkaRezervacije.getDatumOd(),stavkaRezervacije.getDatumDo(),stavkaRezervacije.getCenaStavke(),voziloDtoToVozilo(stavkaRezervacije.getVozilo()));
    }
    
    public StavkaRezervacijeDto stavkaRezervacijeToStavkaRezervacijeDto(StavkaRezervacije stavkaRezervacije){
        return new StavkaRezervacijeDto(stavkaRezervacije.getRbStavkeRezervacije(),
        stavkaRezervacije.getDatumOd(),stavkaRezervacije.getDatumDo(),stavkaRezervacije.getCenaStavke(),voziloToVoziloDto(stavkaRezervacije.getVozilo()));
    }
    
    public VoziloDto voziloToVoziloDto(Vozilo v){
        return new VoziloDto(v.getVoziloID(),v.getRegistarskiBroj() ,v.getMarka(),v.getModel(),
                v.getKategorija(), v.getCenaPoDanu(), tipVozilaToTipVozilaDto(v.getTipVozila()));
    }
    
    public Vozilo voziloDtoToVozilo(VoziloDto v){
        return new Vozilo(v.getVoziloID(),v.getRegistarskiBroj() ,v.getMarka(),v.getModel(),
                v.getKategorija(), v.getCenaPoDanu(), tipVozilaDtoToTipVozila(v.getTipVozila()));
    }

    public KorisnikDto korisnikToKorisnikDto(Korisnik k) {
        return new KorisnikDto(k.getKorisnikID(), k.getJMBG(), k.getImePrezime(), k.getAdresa(), k.getTelefon(), k.getEmail());
    }

    public Korisnik korisnikDtoToKorisnik(KorisnikDto k) {
        return new Korisnik(k.getKorisnikID(), k.getJMBG(), k.getImePrezime(), k.getAdresa(), k.getTelefon(), k.getEmail());
    }

    public TipVozilaDto tipVozilaToTipVozilaDto(TipVozila tipVozila) {
        return new TipVozilaDto(tipVozila.getTipVozilaID(), tipVozila.getNazivTipaVozila());
    }

    public TipVozila tipVozilaDtoToTipVozila(TipVozilaDto tipVozila) {
        return new TipVozila(tipVozila.getTipVozilaID(), tipVozila.getNazivTipaVozila());
    }
}
