/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.repository.KorisnikRepository;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;

/**
 *
 * @author Mina
 */

@SpringBootTest
@AutoConfigureTestDatabase
public class KorisnikServiceTest {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KorisnikRepository korisnikRepository;
   
    
    @Test
    public void testAdd(){
        KorisnikDto korisnik = new KorisnikDto();
        korisnik.setAdresa("Test adresa");
        korisnik.setEmail("test@mail.com");
        korisnik.setImePrezime("Marko Markovic");
        korisnik.setTelefon("06587888888");
        korisnik.setJMBG("1308222658895");
        KorisnikDto addedKorisnik = korisnikService.add(korisnik);
        assertEquals(korisnik.getAdresa(), addedKorisnik.getAdresa());
        assertEquals(korisnik.getEmail(), addedKorisnik.getEmail());
        assertEquals(korisnik.getImePrezime(), addedKorisnik.getImePrezime());
        assertEquals(korisnik.getTelefon(), addedKorisnik.getTelefon());
        assertEquals(korisnik.getJMBG(), addedKorisnik.getJMBG());
    }
    
    @Test
    public void testAddWithException(){
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setAdresa("Kralja Milana 3");
        korisnikDto.setEmail("pera@peric.com");
        korisnikDto.setImePrezime("Pera Peric");
        korisnikDto.setJMBG("12545");
        korisnikDto.setTelefon("0625897413");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> korisnikService.add(korisnikDto));
    }
    
    @Test
    void testGetById(){
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setAdresa("Kralja Milana 3");
        korisnikDto.setEmail("pera12@peric.com");
        korisnikDto.setImePrezime("Pera Peric");
        korisnikDto.setJMBG("1254589578412");
        korisnikDto.setTelefon("0625897413");
        KorisnikDto addedKorisnik = korisnikService.add(korisnikDto);
         
        KorisnikDto foundKorisnik = korisnikService.getById(addedKorisnik.getKorisnikID());
        assertEquals(korisnikDto.getAdresa(), addedKorisnik.getAdresa());
        assertEquals(korisnikDto.getEmail(), addedKorisnik.getEmail());
        assertEquals(korisnikDto.getImePrezime(), addedKorisnik.getImePrezime());
        assertEquals(korisnikDto.getTelefon(), addedKorisnik.getTelefon());
         
    }
    
    @Test
    void testUpdate(){
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setAdresa("Kralja Milana 3");
        korisnikDto.setEmail("pera123@peric.com");
        korisnikDto.setImePrezime("Pera Peric");
        korisnikDto.setJMBG("1285989578412");
        korisnikDto.setTelefon("0625897413");
        KorisnikDto addedKorisnik = korisnikService.add(korisnikDto);
         
        KorisnikDto foundKorisnik = korisnikService.getById(addedKorisnik.getKorisnikID());
        foundKorisnik.setAdresa("Bulevar Oslobodjenja bb");
        foundKorisnik.setEmail("pera.peric@gmail.com");
        KorisnikDto updatedKorisnik = korisnikService.update(foundKorisnik.getKorisnikID(), foundKorisnik);
        assertEquals(updatedKorisnik.getAdresa(), foundKorisnik.getAdresa());
        assertEquals(updatedKorisnik.getEmail(), foundKorisnik.getEmail());
         
    }
    
    @Test
    void testDelete(){
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setAdresa("Kralja Milana 3");
        korisnikDto.setEmail("pera123@peric.com");
        korisnikDto.setImePrezime("Pera Peric");
        korisnikDto.setJMBG("1354689578412");
        korisnikDto.setTelefon("0625897413");
        KorisnikDto addedKorisnik = korisnikService.add(korisnikDto);
        
        korisnikService.delete(addedKorisnik.getKorisnikID());
        Assertions.assertThrows(EntityNotFoundException.class, () -> korisnikService.getById(addedKorisnik.getKorisnikID()));
    }
    
    @Test
    void testGetAll(){
        KorisnikDto korisnikDto1 = new KorisnikDto();
        korisnikDto1.setAdresa("Kralja Milana 578");
        korisnikDto1.setEmail("pera78@peric.com");
        korisnikDto1.setImePrezime("Pera Peric");
        korisnikDto1.setJMBG("4589689578412");
        korisnikDto1.setTelefon("0625897413");
        korisnikService.add(korisnikDto1);
        KorisnikDto korisnikDto2 = new KorisnikDto();
        korisnikDto2.setAdresa("Kralja Aleksandra 578");
        korisnikDto2.setEmail("jova78@jovic.com");
        korisnikDto2.setImePrezime("Jova Jovic");
        korisnikDto2.setJMBG("7415895784127");
        korisnikDto2.setTelefon("0985897413");
        korisnikService.add(korisnikDto2);
        
        List<KorisnikDto> korisnici = korisnikService.getAll();
        Assertions.assertTrue(korisnici.size()>=2);
    }
}
