/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mina
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezervacijaData {
    private Long rezervacijaID;
    private BigDecimal cenaBezPDV;
    private BigDecimal PDV;
    private BigDecimal cenaSaPDV;
    private Long korisnikID;
    List<StavkaRezervacijeData> stavkeRezervacije;
}
