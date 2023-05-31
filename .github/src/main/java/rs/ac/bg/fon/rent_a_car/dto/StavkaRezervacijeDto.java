/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
public class StavkaRezervacijeDto implements Serializable{
    private int rbStavkeRezervacije;
    private Date datumOd;
    private Date datumDo;
    private BigDecimal cenaStavke;
    private VoziloDto vozilo;
}
