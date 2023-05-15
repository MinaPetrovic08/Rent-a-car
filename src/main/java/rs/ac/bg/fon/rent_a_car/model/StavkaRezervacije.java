/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.rent_a_car.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity
public class StavkaRezervacije implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int rbStavkeRezervacije;
    private Date datumOd;
    private Date datumDo;
    private BigDecimal cenaStavke;
    @OneToOne
    @JoinColumn(name="voziloID")
    private Vozilo vozilo;

}
