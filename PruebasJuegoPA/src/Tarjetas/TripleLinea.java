/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarjetas;

/**
 *
 * @author Marti
 */
public class TripleLinea extends Tarjeta {
    public TripleLinea(int i){
        this.tipoAtaque = 2;
        
        switch (i){
            case 7 ->{
                this.id = 7;
                this.nombre = "Hydracall";
                this.valorAtaque = 20;
                this.valorDefensa = 20;
                this.valorMagia = 30;
                this.valorSalud = 30;
                break;
            }
            case 8 ->{
                this.id = 3;
                this.nombre = "Tubedusa";
                this.valorAtaque = 20;
                this.valorDefensa = 25;
                this.valorMagia = 30;
                this.valorSalud = 25;
                break;
            }
        }
    }
}
