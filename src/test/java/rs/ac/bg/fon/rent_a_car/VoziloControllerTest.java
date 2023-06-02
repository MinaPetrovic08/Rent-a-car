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
import rs.ac.bg.fon.rent_a_car.dto.TipVozilaDto;
import rs.ac.bg.fon.rent_a_car.dto.VoziloDto;
import rs.ac.bg.fon.rent_a_car.service.TipVozilaService;
import rs.ac.bg.fon.rent_a_car.service.VoziloService;
import rs.ac.bg.fon.rent_a_car.controller.VoziloController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class VoziloControllerTest {

    @Mock
    private VoziloService voziloService;

    @Mock
    private TipVozilaService tipVozilaService;

    @InjectMocks
    private VoziloController voziloController;

    @Mock
    private Model model;

    @Test
    public void testShowTable() {
        List<VoziloDto> vozila = new ArrayList<>();
        vozila.add(new VoziloDto());

        when(voziloService.getAll()).thenReturn(vozila);

        String viewName = voziloController.showTable(model);

        assertEquals("vozila", viewName);
        verify(model).addAttribute("vozila", vozila);
        verify(voziloService).getAll();
    }

    @Test
    public void testOpenForm() {
        List<TipVozilaDto> tipoviVozila = new ArrayList<>();
        tipoviVozila.add(new TipVozilaDto());

        when(tipVozilaService.getAll()).thenReturn(tipoviVozila);

        String viewName = voziloController.openForm(model);

        assertEquals("novoVozilo", viewName);
        verify(model).addAttribute(eq("vozilo"), any(VoziloDto.class));
        verify(model).addAttribute(eq("tipoviVozila"), eq(tipoviVozila));
    }

    @Test
    public void testSaveVozilo() {
        VoziloDto voziloDto = new VoziloDto();

        String viewName = voziloController.saveVozilo(voziloDto);

        assertEquals("redirect:/vozila", viewName);
        verify(voziloService).add(voziloDto);
    }

    @Test
    public void testShowUpdateFrom() {
        Long id = 1L;
        VoziloDto voziloDto = new VoziloDto();
        List<TipVozilaDto> tipoviVozila = new ArrayList<>();
        tipoviVozila.add(new TipVozilaDto());

        when(voziloService.getById(id)).thenReturn(voziloDto);
        when(tipVozilaService.getAll()).thenReturn(tipoviVozila);

        String viewName = voziloController.showUpdateFrom(id, model);

        assertEquals("updateVozilo", viewName);
        verify(voziloService).getById(id);
        verify(model).addAttribute("vozilo", voziloDto);
        verify(model).addAttribute("tipoviVozila", tipoviVozila);
    }

    @Test
    public void testUpdateVozila() {
        Long id = 1L;
        VoziloDto voziloDto = new VoziloDto();

        String viewName = voziloController.updateVozila(id, voziloDto);

        assertEquals("redirect:/vozila", viewName);
        verify(voziloService).update(id, voziloDto);
    }

    @Test
    public void testDeleteVozilo() {
        Long voziloID = 1L;

        ResponseEntity<String> responseEntity = voziloController.deleteVozilo(voziloID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(voziloService).delete(voziloID);
    }

    // Add more test methods for other controller methods
    // ...
}
