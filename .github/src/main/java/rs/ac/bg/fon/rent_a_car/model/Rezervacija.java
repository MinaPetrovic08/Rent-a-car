/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.rent_a_car.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author PC
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rezervacija implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long rezervacijaID;
    private BigDecimal cenaBezPDV;
    private BigDecimal PDV;
    private BigDecimal cenaSaPDV;
    @ManyToOne(fetch = FetchType.EAGER)
    private Korisnik korisnik;
    @OneToMany(cascade = CascadeType.ALL)  
    @JoinColumn(name="rezervacijeID")
    private List<StavkaRezervacije> stavkeRezervacije;
}
