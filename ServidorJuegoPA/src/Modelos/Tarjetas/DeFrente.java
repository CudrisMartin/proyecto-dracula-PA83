/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos.Tarjetas;

/**
 *
 * @author Marti
 * 
 * Las cartas DeFrente atacan unicamente a la unidad o casilla que tienen frente suyo en el campo de batalla.
 * 
 */
public class DeFrente extends Tarjeta{
    
    /* Establece las caracteristicas de las cartas dependiendo del indice de esta*/
    public DeFrente(int i){
        this.tipoAtaque = 1;
        
        switch (i){
            case 1 ->{
                this.id = 1;
                this.nombre = "Mecafrank 3000";
                this.valorAtaque = 5;
                this.valorDefensa = 35;
                this.valorMagia = 10;
                this.valorSalud = 50;
                break;
            }
            case 3 ->{
                this.id = 3;
                this.nombre = "Velozilda";
                this.valorAtaque = 20;
                this.valorDefensa = 15;
                this.valorMagia = 30;
                this.valorSalud = 35;
                break;
            }
            case 4 ->{
                this.id = 4;
                this.nombre = "Momiabot";
                this.valorAtaque = 30;
                this.valorDefensa = 20;
                this.valorMagia = 20;
                this.valorSalud = 30;
                break;
            }
            case 5 ->{
                this.id = 5;
                this.nombre = "Digiduende";
                this.valorAtaque = 20;
                this.valorDefensa = 15;
                this.valorMagia = 40;
                this.valorSalud = 25;
                break;
            }
            case 6 ->{
                this.id = 6;
                this.nombre = "Zombie";
                this.valorAtaque = 30;
                this.valorDefensa = 10;
                this.valorMagia = 30;
                this.valorSalud = 20;
                break;
            }
            case 9 ->{
                this.id = 9;
                this.nombre = "Espantachef";
                this.valorAtaque = 25;
                this.valorDefensa = 20;
                this.valorMagia = 25;
                this.valorSalud = 30;
                break;
            }
            case 10 ->{
                this.id = 10;
                this.nombre = "Nano Gremlin";
                this.valorAtaque = 35;
                this.valorDefensa = 10;
                this.valorMagia = 30;
                this.valorSalud = 25;
                break;
            }
            case 11 ->{
                this.id = 11;
                this.nombre = "Piratadroid";
                this.valorAtaque = 30;
                this.valorDefensa = 20;
                this.valorMagia = 20;
                this.valorSalud = 30;
                break;
            }
            case 12 ->{
                this.id = 12;
                this.nombre = "NFT-Gargol";
                this.valorAtaque = 30;
                this.valorDefensa = 20;
                this.valorMagia = 25;
                this.valorSalud = 25;
                break;
            }
            case 14 ->{
                this.id = 14;
                this.nombre = "Cíclope";
                this.valorAtaque = 30;
                this.valorDefensa = 20;
                this.valorMagia = 10;
                this.valorSalud = 40;
                break;
            }
            case 15 ->{
                this.id = 15;
                this.nombre = "Esqueletron";
                this.valorAtaque = 40;
                this.valorDefensa = 20;
                this.valorMagia = 20;
                this.valorSalud = 20;
                break;
            }
            case 16 ->{
                this.id = 16;
                this.nombre = "La Vampiresa";
                this.valorAtaque = 20;
                this.valorDefensa = 10;
                this.valorMagia = 40;
                this.valorSalud = 30;
                break;
            }
            case 19 ->{
                this.id = 19;
                this.nombre = "Ogro";
                this.valorAtaque = 50;
                this.valorDefensa = 10;
                this.valorMagia = 15;
                this.valorSalud = 25;
                break;
            }
            case 20 ->{
                this.id = 20;
                this.nombre = "Drácula del futuro";
                this.valorAtaque = 35;
                this.valorDefensa = 15;
                this.valorMagia = 20;
                this.valorSalud = 30;
                break;
            }
        }
    }
}
