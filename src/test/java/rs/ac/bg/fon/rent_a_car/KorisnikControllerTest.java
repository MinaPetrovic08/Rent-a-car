/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import rs.ac.bg.fon.rent_a_car.controller.KorisnikController;
import rs.ac.bg.fon.rent_a_car.dto.KorisnikDto;
import rs.ac.bg.fon.rent_a_car.service.KorisnikService;

@ExtendWith(MockitoExtension.class)
public class KorisnikControllerTest {

    @Mock
    private KorisnikService korisnikService;

    @InjectMocks
    private KorisnikController korisnikController;

    @Mock
    private Model model;

    @Test
    public void testShowTable() {
        List<KorisnikDto> korisnici = new ArrayList<>();
        korisnici.add(new KorisnikDto());

        when(korisnikService.getAll()).thenReturn(korisnici);

        String viewName = korisnikController.showTable(model);

        assertEquals("korisnici", viewName);
        verify(model).addAttribute("korisnici", korisnici);
        verify(korisnikService).getAll();
    }

    @Test
    public void testOpenForm() {
        String viewName = korisnikController.openForm(model);

        assertEquals("noviKorisnik", viewName);
        ArgumentCaptor<KorisnikDto> captor = ArgumentCaptor.forClass(KorisnikDto.class);
        verify(model).addAttribute(eq("korisnik"), captor.capture());
        KorisnikDto capturedKorisnikDto = captor.getValue();
        Assertions.assertNotNull(capturedKorisnikDto);
    }

    @Test
    public void testSaveKorisnik() {
        KorisnikDto korisnikDto = new KorisnikDto();

        String viewName = korisnikController.saveKorisnik(korisnikDto);

        assertEquals("redirect:/korisnici", viewName);
        verify(korisnikService).add(korisnikDto);
    }
}
