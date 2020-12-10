/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author user
 */
public class IniciarInstalación {
     public static void main(String[] args) {
        Instalacion i = new Instalacion(1, "garaje", SensorDAO.buscarSensor(1).getTipo());
        boolean x = InstalacionDAO.insertar(i);
        System.out.println(x);
    }
}
