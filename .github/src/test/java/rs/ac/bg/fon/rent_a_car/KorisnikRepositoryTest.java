/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rs.ac.bg.fon.rent_a_car.model.Korisnik;
import rs.ac.bg.fon.rent_a_car.repository.KorisnikRepository;

/**
 *
 * @author Mina
 */
@DataJpaTest
@AutoConfigureTestDatabase
public class KorisnikRepositoryTest {
    
    @Autowired
    KorisnikRepository korisnikRepository;
    
    @Test
    void testSave(){
        Korisnik korisnik = new Korisnik();
        korisnik.setAdresa("Test adresa");
        korisnik.setEmail("test@mail.com");
        korisnik.setImePrezime("Marko Markovic");
        korisnik.setTelefon("06587888888");
        korisnik.setJMBG("1308222658895");
        Korisnik savedKorisnik = korisnikRepository.save(korisnik);
        assertEquals(korisnik.getAdresa(), savedKorisnik.getAdresa());
        assertEquals(korisnik.getEmail(), savedKorisnik.getEmail());
        assertEquals(korisnik.getImePrezime(), savedKorisnik.getImePrezime());
        assertEquals(korisnik.getTelefon(), savedKorisnik.getTelefon());
        assertEquals(korisnik.getJMBG(), savedKorisnik.getJMBG());
    }
    
    @Test
    void testFindById(){
        Korisnik korisnik = new Korisnik();
        korisnik.setAdresa("Glasinacka 6");
        korisnik.setEmail("pera@mail.com");
        korisnik.setImePrezime("Pera Peric");
        korisnik.setJMBG("5879465215478");
        korisnik.setTelefon("0365874124");
        Korisnik savedKorisnik = korisnikRepository.save(korisnik);
        
        Optional<Korisnik> foundKorisnik = korisnikRepository.findById(savedKorisnik.getKorisnikID());
        assertTrue(foundKorisnik.isPresent());
        assertEquals(foundKorisnik.get().getAdresa(), savedKorisnik.getAdresa());
        assertEquals(foundKorisnik.get().getEmail(), savedKorisnik.getEmail());
        assertEquals(foundKorisnik.get().getImePrezime(), savedKorisnik.getImePrezime());
        assertEquals(foundKorisnik.get().getTelefon(), savedKorisnik.getTelefon());
        assertEquals(korisnik.getJMBG(), savedKorisnik.getJMBG());
    }
    
    @Test
    void testDeleteById(){
        Korisnik korisnik = new Korisnik();
        korisnik.setAdresa("Kralja Milana 2");
        korisnik.setEmail("joca@mail.com");
        korisnik.setImePrezime("Joca Jocic");
        korisnik.setJMBG("1234567891023");
        korisnik.setTelefon("0123456789");
        Korisnik savedKorisnik = korisnikRepository.save(korisnik);
        
        korisnikRepository.deleteById(savedKorisnik.getKorisnikID());
        Optional<Korisnik> deletedKorisnik = korisnikRepository.findById(savedKorisnik.getKorisnikID());
        assertFalse(deletedKorisnik.isPresent());
    }
    
    @Test
    void testGetAll(){
         Korisnik korisnik1 = new Korisnik();
        korisnik1.setAdresa("Glasinacka 6");
        korisnik1.setEmail("pera1@mail.com");
        korisnik1.setImePrezime("Pera Peric01");
        korisnik1.setJMBG("5879465215478");
        korisnik1.setTelefon("0365874124");
        korisnikRepository.save(korisnik1);
        
        Korisnik korisnik2 = new Korisnik();
        korisnik2.setAdresa("Glasinacka 16");
        korisnik2.setEmail("pera2@mail.com");
        korisnik2.setImePrezime("Pera Peric 2");
        korisnik2.setJMBG("5679575215478");
        korisnik2.setTelefon("5785874124");
        korisnikRepository.save(korisnik2);
        
        List<Korisnik> korisnici = korisnikRepository.findAll();
        assertEquals(2, korisnici.size());
    }
}
