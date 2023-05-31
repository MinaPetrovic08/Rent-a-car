/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.rent_a_car.dto;

import java.io.Serializable;
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
public class KorisnikDto implements Serializable{
    private Long korisnikID;
    private String JMBG;
    private String imePrezime;
    private String adresa;
    private String telefon;
    private String email;
}
