/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Sensor;
import Model.SensorDAO;
import Model.Instalacion;
import Model.InstalacionDAO;
import Model.Tramadedatos;
import Model.TramadedatosDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Gestor {
    public static void enviarDato(int id) {

        Instalacion i = InstalacionDAO.buscarInstalacion(id);
        Sensor s = SensorDAO.buscarSensor(i.getId_sensor());
        int valor_tomado = (int) (Math.random() * (((s.getMax_permitido() + 2) - (s.getMin_permitido() - 2)) + 1)) + s.getMin_permitido();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha = LocalDateTime.now().format(formatter);

        Tramadedatos t = new Tramadedatos(id, valor_tomado, fecha);
        TramadedatosDAO.insertar(t);
    }

    public static ArrayList<Object[]> mostrarTramas(int id) {
        ArrayList<Tramadedatos> list = Model.TramadedatosDAO.leerMultiple(id);
        ArrayList<Object[]> rows = new ArrayList<>();
        Instalacion ins = InstalacionDAO.buscarInstalacion(id);
        Sensor s = SensorDAO.buscarSensor(ins.getId_sensor());

        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];
            row[0] = list.get(i).getId_toma();
            row[1] = list.get(i).getId_instalacion();
            row[2] = InstalacionDAO.buscarInstalacion(list.get(i).getId_instalacion()).getUbicacion_sensor();
            row[3] = list.get(i).getValor_tomado();
            row[4] = list.get(i).getFecha_toma();

            if (list.get(i).getValor_tomado() < s.getMax_permitido()
                    && list.get(i).getValor_tomado() > s.getMin_permitido()) {
                row[5] = "NORMAL";
            } else {
                row[5] = "ALERTA";
            }

            rows.add(row);
        }

        return rows;
    }
    
    public static ArrayList<Object[]> procesarDatos(int id_s){
        ArrayList<Object[]> rows = new ArrayList<>();
        ArrayList<Tramadedatos> datos = new ArrayList<>();
        Instalacion i = InstalacionDAO.buscarInstalacion(id_s);
        Sensor s = SensorDAO.buscarSensor(i.getId_sensor());
        
        if(s.isPromedio()){
            datos = Model.TramadedatosDAO.leerMultiple(id_s);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = null;
            
            for (Tramadedatos t : datos) {
                date = LocalDateTime.parse(t.getFecha_toma(), formatter);
                System.out.println(t.getFecha_toma());
                if(date.isAfter(LocalDateTime.now().minusHours(s.getNum_horas()))){
                    Object[] row = new Object[6];
            
                    row[0] = t.getId_toma();
                    row[1] = t.getId_instalacion();
                    row[2] = i.getUbicacion_sensor();
                    row[3] = t.getValor_tomado();
                    row[4] = t.getFecha_toma();
                    
                    if (t.getValor_tomado() > s.getMax_permitido()) {
                        row[5] = "SUPERIOR";
                    } else if(t.getValor_tomado() < s.getMin_permitido()){
                        row[5] = "INFERIOR";
                    }
                    else{
                        row[5] = "NORMAL";
                    }

                    rows.add(row);
                }
            }
            
            
        }
        else{
            Tramadedatos t = TramadedatosDAO.leerSingle(id_s);
            Object[] row = new Object[6];
            
            row[0] = t.getId_toma();
            row[1] = t.getId_instalacion();
            row[2] = i.getUbicacion_sensor();
            row[3] = t.getValor_tomado();
            row[4] = t.getFecha_toma();

            if (t.getValor_tomado() > s.getMax_permitido()) {
                row[5] = "SUPERIOR";
            } else if(t.getValor_tomado() < s.getMin_permitido()){
                row[5] = "INFERIOR";
            }
            else{
                row[5] = "NORMAL";
            }
            
            rows.add(row);
        }
        
        return rows;
        
    }
}
