/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.vista;

import com.udec.modelo.Banco;
import com.udec.modelo.Empleado;
import com.udec.modelo.Concepto;
import com.udec.modelo.Periodo;
import java.awt.Component;
import java.awt.EventQueue;
import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;

/**
 *
 * @author Ususario
 */
public class NovedadConcepto extends JInternalFrame {
    private Periodo p;
    public NovedadConcepto(Periodo pe) {
        this.p=pe;
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("com.udec_nomina_jar_1.0-SNAPSHOTPU").createEntityManager();
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT n FROM Novedadxconcepto n WHERE (n.fechaInicio BETWEEN :startDate AND :endDate) OR (n.fechaInicio < :startDate AND n.tipoSaldo ='Indefinido') OR (n.fechaInicio < :startDate and n.tipoSaldo = 'Hasta saldo' AND n.saldo > 0)").setParameter("startDate", p.getDesde()).setParameter("endDate", p.getHasta());
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        empleadoQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT e FROM Empleado e");
        empleadoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : empleadoQuery.getResultList();
        conceptoQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT c FROM Concepto c");
        conceptoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : conceptoQuery.getResultList();
        dateConverter1 = new com.udec.vista.DateConverter();
        bancoQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Banco b");
        bancoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : bancoQuery.getResultList();
        doubleConverter1 = new com.udec.vista.DoubleConverter();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        empleadoCodigoLabel = new javax.swing.JLabel();
        conceptoIdconceptoLabel = new javax.swing.JLabel();
        valorLabel = new javax.swing.JLabel();
        fechaInicioLabel = new javax.swing.JLabel();
        valorField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        tipoSaldoLabel = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        aplicarQuincenalLabel = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        saveButton1 = new javax.swing.JButton();
        refreshButton1 = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();
        bancoIdbancoLabel = new javax.swing.JLabel();
        saldoLabel = new javax.swing.JLabel();
        numeroCuotasLabel = new javax.swing.JLabel();
        totalLibranzaLabel = new javax.swing.JLabel();
        totalLibranzaField = new javax.swing.JTextField();
        numeroCuotasField = new javax.swing.JTextField();
        saldoField = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();

        FormListener formListener = new FormListener();

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${empleadoCodigo.nombre}"));
        columnBinding.setColumnName("Empleado");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${conceptoIdconcepto.concepto}"));
        columnBinding.setColumnName("Concepto");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${valor}"));
        columnBinding.setColumnName("Valor");
        columnBinding.setColumnClass(Double.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaInicio}"));
        columnBinding.setColumnName("Fecha Inicio");
        columnBinding.setColumnClass(java.util.Date.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);

        empleadoCodigoLabel.setText("Empleado:");

        conceptoIdconceptoLabel.setText("Concepto:");

        valorLabel.setText("Valor:");

        fechaInicioLabel.setText("Fecha Inicio:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.valor}"), valorField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("");
        binding.setConverter(doubleConverter1);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), valorField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jComboBox1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Empleado) {
                    Empleado mec = (Empleado)value;
                    setText(mec.getNombre());
                }
                return this;
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, empleadoList, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.empleadoCodigo}"), jComboBox1, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBox2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Concepto) {
                    Concepto mec = (Concepto)value;
                    setText(mec.getConcepto());
                }
                return this;
            }
        });

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, conceptoList, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.conceptoIdconcepto}"), jComboBox2, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBox2.addActionListener(formListener);

        tipoSaldoLabel.setText("Tipo Saldo:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solo este periodo", "Indefinido", "Hasta saldo" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tipoSaldo}"), jComboBox4, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        aplicarQuincenalLabel.setText("Aplicar Quincenal:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Primeras Quincenas", "Segundas Quincenasa", "Todas" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.aplicarQuincenal}"), jComboBox3, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jDateChooser1.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Listado de novedades por concepto");

        saveButton1.setIcon(new javax.swing.ImageIcon("img/guardar2.png"));
        saveButton1.setText("Guardar Cambios");
        saveButton1.setBorder(null);
        saveButton1.setBorderPainted(false);
        saveButton1.setContentAreaFilled(false);
        saveButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton1.setIconTextGap(-3);
        saveButton1.setPressedIcon(new javax.swing.ImageIcon("img/guardar33.png"));
        saveButton1.setRolloverIcon(new javax.swing.ImageIcon("img/guardar1.png"));
        saveButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        saveButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton1.addActionListener(formListener);

        refreshButton1.setIcon(new javax.swing.ImageIcon("img/refres2.png"));
        refreshButton1.setText("Actualizar");
        refreshButton1.setBorder(null);
        refreshButton1.setBorderPainted(false);
        refreshButton1.setContentAreaFilled(false);
        refreshButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton1.setIconTextGap(-3);
        refreshButton1.setPressedIcon(new javax.swing.ImageIcon("img/refresh3.png"));
        refreshButton1.setRolloverIcon(new javax.swing.ImageIcon("img/refresh1.png"));
        refreshButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        refreshButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshButton1.addActionListener(formListener);

        newButton1.setIcon(new javax.swing.ImageIcon("img/nuevo2.png"));
        newButton1.setText("Nuevo");
        newButton1.setBorder(null);
        newButton1.setBorderPainted(false);
        newButton1.setContentAreaFilled(false);
        newButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton1.setIconTextGap(-3);
        newButton1.setMaximumSize(new java.awt.Dimension(115, 100));
        newButton1.setMinimumSize(new java.awt.Dimension(115, 100));
        newButton1.setPreferredSize(new java.awt.Dimension(115, 100));
        newButton1.setPressedIcon(new javax.swing.ImageIcon("img/nuevo33.png"));
        newButton1.setRolloverIcon(new javax.swing.ImageIcon("img/nuevo1.png"));
        newButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        newButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newButton1.addActionListener(formListener);

        bancoIdbancoLabel.setText("Banco:");

        saldoLabel.setText("Saldo:");

        numeroCuotasLabel.setText("Numero Cuotas:");

        totalLibranzaLabel.setText("Total Libranza:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.totalLibranza}"), totalLibranzaField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("");
        binding.setConverter(doubleConverter1);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), totalLibranzaField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.numeroCuotas}"), numeroCuotasField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), numeroCuotasField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.saldo}"), saldoField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setConverter(doubleConverter1);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), saldoField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jComboBox5.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Banco) {
                    Banco mec = (Banco)value;
                    setText(mec.getBanco());
                }
                return this;
            }
        });

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bancoList, jComboBox5);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bancoIdbanco}"), jComboBox5, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(empleadoCodigoLabel)
                    .addComponent(conceptoIdconceptoLabel)
                    .addComponent(valorLabel)
                    .addComponent(fechaInicioLabel)
                    .addComponent(tipoSaldoLabel)
                    .addComponent(aplicarQuincenalLabel)
                    .addComponent(totalLibranzaLabel)
                    .addComponent(numeroCuotasLabel)
                    .addComponent(saldoLabel)
                    .addComponent(bancoIdbancoLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valorField)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalLibranzaField)
                    .addComponent(numeroCuotasField)
                    .addComponent(saldoField)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empleadoCodigoLabel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conceptoIdconceptoLabel)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valorLabel)
                    .addComponent(valorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaInicioLabel)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoSaldoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aplicarQuincenalLabel)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLibranzaLabel)
                    .addComponent(totalLibranzaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroCuotasLabel)
                    .addComponent(numeroCuotasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saldoLabel)
                    .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bancoIdbancoLabel)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fechaInicio}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("date"));
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jComboBox2) {
                NovedadConcepto.this.jComboBox2ActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton1) {
                NovedadConcepto.this.saveButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton1) {
                NovedadConcepto.this.refreshButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton1) {
                NovedadConcepto.this.newButton1ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents


    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        com.udec.modelo.Concepto aux = (com.udec.modelo.Concepto) jComboBox2.getSelectedItem();
        if (aux != null && aux.getFormato().equals("LIBRANZA")) {
            totalLibranzaField.setVisible(true);
            totalLibranzaLabel.setVisible(true);
            numeroCuotasField.setVisible(true);
            numeroCuotasLabel.setVisible(true);
            saldoField.setVisible(true);
            saldoLabel.setVisible(true);
            bancoIdbancoLabel.setVisible(true);
            jComboBox5.setVisible(true);
        } else {
            totalLibranzaField.setVisible(false);
            totalLibranzaLabel.setVisible(false);
            numeroCuotasField.setVisible(false);
            numeroCuotasLabel.setVisible(false);
            saldoField.setVisible(false);
            saldoLabel.setVisible(false);
            bancoIdbancoLabel.setVisible(false);
            jComboBox5.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
        com.udec.modelo.Novedadxconcepto n = new com.udec.modelo.Novedadxconcepto();
        entityManager.persist(n);
        list.add(n);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newButton1ActionPerformed

    private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton1ActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);

    }//GEN-LAST:event_refreshButton1ActionPerformed

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<com.udec.modelo.Novedadxconcepto> merged = new ArrayList<com.udec.modelo.Novedadxconcepto>(list.size());
            for (com.udec.modelo.Novedadxconcepto n : list) {
                merged.add(entityManager.merge(n));
            }
            list.clear();
            list.addAll(merged);
        }
    }//GEN-LAST:event_saveButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aplicarQuincenalLabel;
    private javax.swing.JLabel bancoIdbancoLabel;
    private java.util.List<com.udec.modelo.Banco> bancoList;
    private javax.persistence.Query bancoQuery;
    private javax.swing.JLabel conceptoIdconceptoLabel;
    private java.util.List<com.udec.modelo.Concepto> conceptoList;
    private javax.persistence.Query conceptoQuery;
    private com.udec.vista.DateConverter dateConverter1;
    private com.udec.vista.DoubleConverter doubleConverter1;
    private javax.swing.JLabel empleadoCodigoLabel;
    private java.util.List<com.udec.modelo.Empleado> empleadoList;
    private javax.persistence.Query empleadoQuery;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel fechaInicioLabel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private java.util.List<com.udec.modelo.Novedadxconcepto> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton1;
    private javax.swing.JTextField numeroCuotasField;
    private javax.swing.JLabel numeroCuotasLabel;
    private javax.persistence.Query query;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JTextField saldoField;
    private javax.swing.JLabel saldoLabel;
    private javax.swing.JButton saveButton1;
    private javax.swing.JLabel tipoSaldoLabel;
    private javax.swing.JTextField totalLibranzaField;
    private javax.swing.JLabel totalLibranzaLabel;
    private javax.swing.JTextField valorField;
    private javax.swing.JLabel valorLabel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
