/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilidades;

import com.udec.connection.jpaConnection;
import com.udec.modelo.Periodo;
import com.udec.vista.Inicio;
import com.udec.vista.NominaMain;
import com.udec.vista.Periodos;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


/**
 *
 * @author Diana
 */
public class Splash extends JWindow {

    private int duracion = 0;

    public Splash(int dura) {
        this.duracion = dura;
        JPanel panel = (JPanel) getContentPane();
        ImageIcon img = new ImageIcon("img/contable.jpg");
        panel.add(new JLabel(img), BorderLayout.CENTER);
        setSize(img.getIconWidth(), img.getIconHeight());
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            new Inicio().setVisible(true);
            jpaConnection.createEntityManagerFactory();
            Thread.sleep(duracion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(false);
        
        
    }

    public static void main(String[] arg) {
        new Splash(1000);
    }

}
