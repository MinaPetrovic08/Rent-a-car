/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.rent_a_car.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mina
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vozilo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long voziloID;
    private String registarskiBroj;
    private String marka;
    private String model;
    private String kategorija;
    private BigDecimal cenaPoDanu;
    @ManyToOne(fetch = FetchType.EAGER)
    private TipVozila tipVozila;

    @Override
    public String toString() {
        return marka + " " + model + " (Kategorija: " + kategorija + ", Cena/dan: " + cenaPoDanu + "€)";
    }
}
