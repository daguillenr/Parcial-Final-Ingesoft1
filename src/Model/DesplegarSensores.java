/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author User
 */
public class DesplegarSensores {

    public static void main(String[] args) {

        Sensor s = new Sensor("KY-017", "Módulo Optico de mercurio", 0, 1, 0, false);
        boolean x = SensorDAO.insertar(s);
        System.out.println(x);
    }
}
