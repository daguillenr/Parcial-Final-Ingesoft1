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
public class Promedio {
    public static void main(String[] args) {
        boolean x = SensorDAO.actualizarPromedio(1, true);
        System.out.println(x);
    }
}
