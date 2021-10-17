/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author angel
 */
public class Productos {    
    public int IDProducto;
    public String NomProducto;
    public int Cantidad;
    public String FechaObtencion;
    
    public Productos(){}
    
    public Productos(Productos dato){
    
    IDProducto = dato.IDProducto;
    NomProducto= dato.NomProducto;
    Cantidad = dato.Cantidad;
    FechaObtencion = dato.FechaObtencion;
    }
}
