/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class VoziloDto implements Serializable{
     private Long voziloID;
    private String registarskiBroj;
    private String marka;
    private String model;
    private String kategorija;
    private BigDecimal cenaPoDanu;
    private TipVozilaDto tipVozila;
    
    @Override
    public String toString() {
        return marka + " " + model + " (" + registarskiBroj+")";
    }
}
