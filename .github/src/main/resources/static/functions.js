/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

function obrisiVozilo(id){
    $.ajax({
        type: "DELETE",
        url: "/vozila/" + id,
        success: function(result){
            window.location.href = "/vozila";
        },
        error: function(){
            alert("Greska! Vozilo nije obrisano!");
            return;
        }
    })
}
function obrisiKorisnika(id){
    $.ajax({
        type: "DELETE",
        url: "/korisnici/" + id,
        success: function(result){
            window.location.href = "/korisnici";
        },
        error: function(){
            alert("Greska! Korsnik nije obrisan!");
            return; 
        }
    })
}

function obrisiRezervaciju(id){
    $.ajax({
        type: "DELETE",
        url: "/rezervacije/" + id,
        success: function(result){
            window.location.href = "/rezervacije";
        },
        error: function(){
             alert("Greska!Rezervacija nije obrisana!");
            return;
        }
    })
}