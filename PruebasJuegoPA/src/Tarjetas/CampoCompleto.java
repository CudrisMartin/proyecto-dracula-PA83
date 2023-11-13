package Tarjetas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Tarjetas.Tarjeta;

/**
 *
 * @author Marti
 */
public class CampoCompleto extends Tarjeta {
    public CampoCompleto(int i){
        this.tipoAtaque = 3;
        
        switch (i){
            case 2 ->{
                this.id = 2;
                this.nombre = "Ruidolf";
                this.valorAtaque = 10;
                this.valorDefensa = 25;
                this.valorMagia = 25;
                this.valorSalud = 40;
                break;
            }
            case 17 ->{
                this.id = 17;
                this.nombre = "DragonThor";
                this.valorAtaque = 5;
                this.valorDefensa = 25;
                this.valorMagia = 30;
                this.valorSalud = 40;
                break;
            }
        }
    }
}
