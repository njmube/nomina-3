/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.utilidades;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.udec.controlador.DiastrabajadosJpaController;
import com.udec.controlador.EmpleadoJpaController;
import com.udec.controlador.NominaJpaController;
import com.udec.modelo.Diastrabajados;
import com.udec.modelo.Empleado;
import com.udec.modelo.Nomina;
import com.udec.modelo.Periodo;
import com.udec.vista.DiasTrabajados;
import java.awt.Desktop;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfTable {

    Periodo pe;
    NominaJpaController nC = new NominaJpaController();
    EmpleadoJpaController eC = new EmpleadoJpaController();
    DiastrabajadosJpaController dtC = new DiastrabajadosJpaController();
    private List<Nomina> nom;
    private List<Empleado> empleados;
    Diastrabajados dt;
    //Ruta del archivo, esto es dentro del proyecto Netbeans
    public String archivo = System.getProperty("user.dir") + "/PdfTabla.pdf";

    public PdfTable() {
    }

    public PdfTable(Periodo p) {
        this.pe = p;
        empleados = eC.findByList("estado", "ACTIVO");
        for (Empleado empleado : empleados) {
            nom = nC.findByList2("periodoIdperiodo", p, "empleadoCodigo", empleado);
            createPdf(nom, p);

        }
    }

    public void createPdf(List<Nomina> nomi, Periodo pe) {
        /*Declaramos documento como un objeto Document
         Asignamos el tamaño de hoja y los margenes */
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);

        //writer es declarado como el método utilizado para escribir en el archivo
        PdfWriter writer = null;

        try {
            //Obtenemos la instancia del archivo a utilizar
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo.replace("PdfTabla", "PdfTabla" + nomi.get(0).getEmpleadoCodigo().getCedula())));
        } catch (Exception ex) {
            ex.getMessage();
        }

        //Agregamos un titulo al archivo
        documento.addTitle("Archivo pdf generado desde Java");

        //Agregamos el autor del archivo
        documento.addAuthor("Nomina");

        //Abrimos el documento para edición
        documento.open();

        //Declaramos un texto como Paragraph
        //Le podemos dar formado como alineación, tamaño y color a la fuente.
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        parrafo.setFont(FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.BLACK));
        parrafo.add("CAJA DE PREVISION SOCIAL U. DE C.\n");
        parrafo.add(pe.getNombre() + "\n");
        parrafo.add(" ");

        try {
            //Agregamos el texto al documento
            documento.add(parrafo);

            //Agregamos un salto de linea
            documento.add(new Paragraph(" "));

            //Agregamos la tabla al documento haciendo 
            //la llamada al método tabla()
            //documento.add(tabla());
            documento.add(tabla2(nomi));
            documento.add(new Paragraph(" "));
            documento.add(tabla3(nomi));
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        documento.close(); //Cerramos el documento
        writer.close(); //Cerramos writer

        try {
            //File file = new File(archivo);
            //Desktop.getDesktop().open(file);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    //Método para crear la tabla
    public PdfPTable tabla2(List<Nomina> no) throws DocumentException {
        //Instanciamos una tabla de 3 columnas
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1, 1, 1, 3});
        //Declaramos un objeto para manejar las celdas
        PdfPCell celda;
        celda = new PdfPCell(new Phrase("Codigo:"));
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        //celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        if (!no.isEmpty()) {
            celda = new PdfPCell(new Phrase("" + no.get(0).getEmpleadoCodigo().getCodigo()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }

        celda = new PdfPCell(new Phrase("Nombre:"));
        //celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);

        if (!no.isEmpty()) {
            celda = new PdfPCell(new Phrase(no.get(0).getEmpleadoCodigo().getNombre()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }
        celda = new PdfPCell(new Phrase("Cedula:"));
        //celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);
        if (!no.isEmpty()) {
            celda = new PdfPCell(new Phrase(no.get(0).getEmpleadoCodigo().getCedula()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }
        celda = new PdfPCell(new Phrase("Cargo:"));
        //celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);
        if (!no.isEmpty()) {
            celda = new PdfPCell(new Phrase(no.get(0).getEmpleadoCodigo().getCargoCargoid().getCargo()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }
        celda = new PdfPCell(new Phrase("Dias Lab:"));
        //celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);
        if (!no.isEmpty()) {
            dt = dtC.findBySingle2("periodoIdperiodo", no.get(0).getPeriodoIdperiodo(), "empleadoCodigo", no.get(0).getEmpleadoCodigo());

            celda = new PdfPCell(new Phrase("" + dt.getDias()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }
        celda = new PdfPCell(new Phrase("Sueldo:"));

        //celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

        tabla.addCell(celda);

        if (!no.isEmpty()) {
            celda = new PdfPCell(new Phrase("" + no.get(0).getEmpleadoCodigo().getSalario()));
            //celda.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(celda);
        }

        return tabla;
    }

    public PdfPTable tabla3(List<Nomina> no) throws DocumentException {
        //Instanciamos una tabla de 3 columnas
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1, 3, 1, 1});
        //Declaramos un objeto para manejar las celdas
        PdfPCell celda;
        celda = new PdfPCell(new Phrase("CODIGO"));
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.BOTTOM);
        celda.setVerticalAlignment(Element.ALIGN_TOP);
        celda.setBorderWidth(1);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("DESCRIPCION"));
        celda.setBorder(Rectangle.BOTTOM);
        celda.setVerticalAlignment(Element.ALIGN_TOP);
        celda.setBorderWidth(1);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("VLR.DEVEN"));
        celda.setBorder(Rectangle.BOTTOM);
        celda.setVerticalAlignment(Element.ALIGN_TOP);
        celda.setBorderWidth(1);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorderWidth(1);
        celda.setVerticalAlignment(Element.ALIGN_TOP);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("VLR.DEDUC"));
        celda.setBorder(Rectangle.BOTTOM);
        celda.setVerticalAlignment(Element.ALIGN_TOP);
        celda.setBorderWidth(1);
        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.addCell(celda);
        double totalDev = 0, totalDed = 0;
        for (Nomina nomina : no) {
            celda = new PdfPCell(new Phrase("" + nomina.getConceptoIdconcepto().getCodigo()));
            celda.setBorder(Rectangle.NO_BORDER);
            celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabla.addCell(celda);
            celda = new PdfPCell(new Phrase("" + nomina.getConceptoIdconcepto().getConcepto()));
            celda.setBorder(Rectangle.NO_BORDER);
            celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabla.addCell(celda);
            if (nomina.getConceptoIdconcepto().getTipo().equals("DEVENGADO")) {
                totalDev += nomina.getValor();
                celda = new PdfPCell(new Phrase("" + nomina.getValor()));
                celda.setBorder(Rectangle.NO_BORDER);
                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tabla.addCell(celda);
            } else {
                celda = new PdfPCell(new Phrase(""));
                celda.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(celda);
            }
            if (nomina.getConceptoIdconcepto().getTipo().equals("DEDUCIDO")) {
                totalDed += nomina.getValor();
                celda = new PdfPCell(new Phrase("" + nomina.getValor()));
                celda.setBorder(Rectangle.NO_BORDER);
                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tabla.addCell(celda);
            } else {
                celda = new PdfPCell(new Phrase(""));
                celda.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(celda);
            }

        }

        celda = new PdfPCell(new Phrase(""));
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("TOTALES"));
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("" + totalDev));
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.TOP);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase(" " + totalDed));
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.TOP);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase(""));
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase("NETO A PAGAR"));
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase(" " + (totalDev - totalDed)));
        celda.setBorder(Rectangle.NO_BORDER);
        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(celda);
        celda = new PdfPCell(new Phrase(" "));
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);

        return tabla;
    }

    public static void main(String[] args) {
        //Llamamos por el método para generar el pdf
        //new PdfTable().createPdf();
    }
}
