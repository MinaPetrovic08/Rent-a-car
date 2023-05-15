/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.rent_a_car.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Korisnik implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long korisnikID;
    @Column(unique = true)
    private String JMBG;
    private String imePrezime;
    private String adresa;
    private String telefon;
    @Column(unique = true)
    private String email;
}
