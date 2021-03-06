package Forms;


import Clases.Conexion;
import Clases.Constantes;
import Clases.Figura;
import Clases.FiltroArchivo;
import Clases.ImgTabla;
import Clases.Linea;
import Clases.Texto;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PanelDibujo extends javax.swing.JPanel implements Serializable, Printable{
   String xRuta ="";
   JTable tabla;
   int fila, columna;
    private final static int 
            PINCEL = 5, LINEA = 6, LAPIZ = 7,
             ARRASTRAR = 10, NULO = 0;

   
    private int coordenadasInicioX, coordenadasFinX;
    private int coordenadasInicioY, coordenadasFinY;
    private int modoDibujar;
    private int lineaX1, lineaX2, lineaY1, lineaY2;
    private Color colorFondoPantallaDibujo;
    private Color colorBorde;
    private float tamanioBorde;


    private File nombreArchivo;

    private boolean conRelleno = false;

    /**
     * La imagen actual que se mostrará.
     * @since 1.6
     */
    private BufferedImage imagen;

    /**
     * Escala por defecto de la imagen.
     * Su valor prederminado es 1.0.
     * @since 1.6
     */
    private double escala = 1.0;

    /**
     * El cursor actual que se esta usando.
     * @since 1.6
     */
    private Cursor cursorActual;
    
    /**
     * Lista de figuras a dibujar.
     * @since 1.6
     */
    private LinkedList<Figura> listaFiguras = new LinkedList<Figura>();

    /**
     * Lista de Texto a dibujar.
     * @since 1.6
     */
   // private LinkedList<Texto> listaTexto = new LinkedList<Texto>();

    /**
     * Si actualmente se está arrastrando o no una Figura.
     * @since 1.6
     */
    private Figura figuraArrastrandose;
    private int xAnteriorRaton;
    private int yAnteriorRaton;

    /**
     * Dos pilas para hacer y deshacer las opciones de dibujo.
     * @since 1.6
     */
    private Stack desHacerPila;

    /**
     * La ubicacion donde se colocará la imagen, si es null, se colocará en el centro.
     * * @since 1.6
     */
    private Point2D ubicacionDeImagen;

    /**
     * Valor boleano para saber si es la ultima version del archivo generado.
     *
     * @since 1.6
     */
    boolean archivoGuardadoUltimaVersion;

    
    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye un Panel Dibujo.
     * @since 1.6
     */
    public PanelDibujo() {
        initComponents();
        setModoDibujar(NULO);
        setTamanioBorde(1.0f);    
        archivoGuardadoUltimaVersion = false;
        ubicacionDeImagen = null;
        nombreArchivo = null;
        figuraArrastrandose = null;
        desHacerPila = new Stack();
        colorFondoPantallaDibujo    = Color.WHITE;          // De color blanco
        colorBorde                  = Color.BLACK;          // De color negro
        
        setBackground(getColorFondoPantallaDibujo());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setCoordenadasInicioX(evt.getX());
        setLineaX1(evt.getX());
        setLineaX2(evt.getX());
        setCoordenadasInicioY(evt.getY());
        setLineaY1(evt.getY());
        setLineaY2(evt.getY());
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(modoDibujar == getLINEA()){
            Linea linea = new Linea(getCoordenadasInicioX(), getCoordenadasInicioY(),
                    getCoordenadasFinX(), getCoordenadasFinY(), getColorBorde(), 
                    (int)getTamanioBorde());    
            agregarFigura(linea);
            desHacerPila.push(linea);
        
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        //repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        archivoGuardadoUltimaVersion = false;
        setCoordenadasFinX(evt.getX());
        setCoordenadasFinY(evt.getY());
        
         if (modoDibujar == getLAPIZ()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            setTamanioBorde((int)1);
            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamanioBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }else if (modoDibujar == getPINCEL()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamanioBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }
        /**
         * Método al que se llama cuando se arrastra el ratón.
         * Se comprueba con el atributo arrastrando si está empezando el arrastre o
         * ya se esta en medio del mismo.
         * Si se comienza el arrastre, se guardan las coordenadas del ratón que
         * vienen en el evento MouseEvent y se cambia el valor del atributo arrastrando.
         * Si se está en medio de un arrastre, se calcula la nueva posición del
         * rectángulo y se llama al método repaint() para que se pinte.
         *
         * @param e Evento del ratón
         * @since 1.6
        */
        if(modoDibujar == getARRASTRAR()){
            // Si comienza el arrastre
            if (figuraArrastrandose == null){
                // Se guardan las posiciones del ratón
                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                // y se marca que ha comenzado el arrastre.
                figuraArrastrandose = dameFigura(evt);
            }else{
                // Si ya había empezado el arrastre, se calculan las nuevas
                // coordenadas del rectángulo
                figuraArrastrandose.setPosicion((int)figuraArrastrandose.getInicio().getX()
                        + (evt.getX() - xAnteriorRaton), (int)figuraArrastrandose.getInicio().getY()
                        + (evt.getY() - yAnteriorRaton));

                // Se guarda la posición del ratón para el siguiente cálculo
                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                // y se manda repintar el Canvas
                repaint();
            }
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * El ratón se mueve sin arrastrar. Se marca fin de arrastre.
     *
     * @param e MouseEvent
     * @since 1.6
     */
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        figuraArrastrandose = null;
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        GUI_Principal.jLabelCoordenadasPuntero.setText("");
    }//GEN-LAST:event_formMouseExited

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setCursor(getCursorActual());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
 
    }//GEN-LAST:event_formMouseClicked


    ////////////////////////////////////////////////////////////////////////////
    // Metodos SETTERS y GETTERS
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve la coordenada final de x.
     *
     * @return La coordenada final de x
     * @since 1.6
     */
    public int getCoordenadasFinX() {
        return coordenadasFinX;
    }

    /**
     * Establece la coordenada final de x.
     *
     * @param coordenadasFinX La coordenada final de x
     * @since 1.6
     */
    public void setCoordenadasFinX(int coordenadasFinX) {
        if(coordenadasFinX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasFinX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
           this.coordenadasFinX = coordenadasFinX;
        }
    }

    /**
     * Devuelve la coordenada final de y.
     *
     * @return La coordenada final de y
     * @since 1.6
     */
    public int getCoordenadasFinY() {
        return coordenadasFinY;
    }

    /**
     * Establece la coordenada final de y.
     *
     * @param coordenadasFinY La coordenada final de y
     * @since 1.6
     */
    public void setCoordenadasFinY(int coordenadasFinY) {
        if(coordenadasFinY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasFinY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasFinY = coordenadasFinY;
        }
    }

    /**
     * Devuelve la coordenada inicial de x.
     *
     * @return La coordenada inicial de x
     * @since 1.6
     */
    public int getCoordenadasInicioX() {
        return coordenadasInicioX;
    }

    /**
     * Establece la coordenada inicial de x.
     *
     * @param coordenadasInicioX La coordenada inicial de x
     * @since 1.6
     */
    public void setCoordenadasInicioX(int coordenadasInicioX) {
        if(coordenadasInicioX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasInicioX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
            this.coordenadasInicioX = coordenadasInicioX;
        }
    }

    /**
     * Devuelve la coordenada inicial de y.
     *
     * @return La coordenada inicial de y
     * @since 1.6
     */
    public int getCoordenadasInicioY() {
        return coordenadasInicioY;
    }

    /**
     * Establece la coordenada inicial de y.
     *
     * @param coordenadasInicioY La coordenada inicial de y
     * @since 1.6
     */
    public void setCoordenadasInicioY(int coordenadasInicioY) {
        if(coordenadasInicioY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasInicioY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasInicioY = coordenadasInicioY;
        }
    }

    /**
     * Devuelve el color del borde.
     *
     * @return El color del borde
     * @since 1.6
     */
    public Color getColorBorde() {
        return colorBorde;
    }

    /**
     * Establece el color del borde.
     *
     * @param colorBorde El color del borde
     * @since 1.6
     */
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    /**
     * Devuelve el color del fondo de pantalla del dibujo.
     *
     * @return El color del fondo de pantalla del dibujo
     * @since 1.6
     */
    public Color getColorFondoPantallaDibujo() {
        return colorFondoPantallaDibujo;
    }

    /**
     * Establece el color del fondo de pantalla del dibujo.
     *
     * @param colorFondoPantallaDibujo El color del fondo de pantalla del dibujo
     * @since 1.6
     */
    public void setColorFondoPantallaDibujo(Color colorFondoPantallaDibujo) {
        this.colorFondoPantallaDibujo = colorFondoPantallaDibujo;
    }

    /**
     * Devuelve el color de relleno.
     *
     * @return El color de relleno
     * @since 1.6
     */


    /**
     * Devuelve el tamanhio del borde.
     *
     * @return El tamanhio del borde
     * @since 1.6
     */
    public float getTamanioBorde() {
        return tamanioBorde;
    }

    /**
     * Establece el tamanhio del borde.
     *
     * @param tamanioBorde El tamanhio del borde
     * @since 1.6
     */
    public void setTamanioBorde(float tamanioBorde) {
        if(tamanioBorde >= Constantes.MINIMO_GROSOR_BORDE
                &&  tamanioBorde <= Constantes.MAXIMO_GROSOR_BORDE){
           this.tamanioBorde = tamanioBorde;
        }
    }

    /**
     * Devuelve la escala actual de la imagen.
     *
     * @return La escala actual de la imagen
     * @since 1.6
     */
    public double getEscala(){
        return escala;
    }

    /**
     * Establece la escala actual de la imagen.
     *
     * @param escala La escala actual de la imagen
     * @since 1.6
     */
    public void setEscala(double escala){
        if(imagen != null){
            double anteriorEscala = this.escala;

            this.escala = escala;
            this.firePropertyChange("escala", anteriorEscala, escala);
            repaint();
        }
    }

    /**
     * Devuelve el cursor actual.
     *
     * @return El cursor actual
     * @since 1.6
     */
    public Cursor getCursorActual() {
        return cursorActual;
    }

    /**
     * Establece el cursor actual.
     *
     * @param cursorActual El cursor actual
     * @since 1.6
     */
    public void setCursorActual(Cursor cursorActual) {
        this.cursorActual = cursorActual;
    }

    /**
     * Devuelve la ubicacion actual de la imagen dentro del panel.
     *
     * @return La ubicacion actual de la imagen dentro del panel
     * @since 1.6
     */
    public Point2D getUbicacionDeImagen(){
        return ubicacionDeImagen;
    }

    /**
     * Establece la ubicacion de la imagen dentro del panel.
     *
     * @param imageLocation La ubicacion actual de la imagen dentro del panel
     * @since 1.6
     */
    public void setUbicacionDeImagen(Point2D imageLocation){
        this.ubicacionDeImagen = imageLocation;
        repaint();              // Vuelve a dibujar la imagen en la ubicacion especificada
    }

    /**
     * Devuelve la coordenada inicial de x para el lapiz y pincel.
     *
     * @return La coordenada inicial de x para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaX1() {
        return lineaX1;
    }

    /**
     * Establece la coordenada inicial de x para el lapiz y pincel.
     *
     * @param lineaX1 La coordenada inicial de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX1(int lineaX1) {
        this.lineaX1 = lineaX1;
    }

    /**
     * Devuelve la coordenada final de x para el lapiz y pincel.
     *
     * @return La coordenada final de x para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaX2() {
        return lineaX2;
    }

    /**
     * Establece la coordenada final de x para el lapiz y pincel.
     *
     * @param lineaX2 La coordenada final de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX2(int lineaX2) {
        this.lineaX2 = lineaX2;
    }

    /**
     * Devuelve la coordenada inicial de y para el lapiz y pincel.
     *
     * @return La coordenada inicial de y para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaY1() {
        return lineaY1;
    }

    /**
     * Establece la coordenada inicial de y para el lapiz y pincel.
     *
     * @param lineaY1 La coordenada inicial de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY1(int lineaY1) {
        this.lineaY1 = lineaY1;
    }

    /**
     * Devuelve la coordenada final de y para el lapiz y pincel.
     *
     * @return La coordenada final de y para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaY2() {
        return lineaY2;
    }

    /**
     * Establece la coordenada final de y para el lapiz y pincel.
     *
     * @param lineaY2 La coordenada final de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY2(int lineaY2) {
        this.lineaY2 = lineaY2;
    }

    /**
     * Devuelve true si tiene relleno.
     *
     * @return True si tiene relleno
     * @since 1.6
     */
    public boolean isConRelleno() {
        return conRelleno;
    }

    /**
     * Establece true si tiene relleno, false sin relleno.
     *
     * @param conRelleno True si tiene relleno, false sin relleno
     * @since 1.6
     */
    public void setConRelleno(boolean conRelleno) {
        this.conRelleno = conRelleno;
    }

    /**
     * Devuelve el numero del modo a dibujar.
     *
     * @return El numero del modo a dibujar
     * @since 1.6
     */
    public int getModoDibujar() {
        return modoDibujar;
    }

    /**
     * Establece el numero del modo a dibujar.
     *
     * @param modoDibujar El numero del modo a dibujar
     * @since 1.6
     */
    public void setModoDibujar(int modoDibujar) {
        this.modoDibujar = modoDibujar;
    }

    /**
     * Devuelve la imagen actual.
     *
     * @return La imagen actual
     * @since 1.6
     */
    public BufferedImage getImagen(){
        return imagen;
    }

    /**
     * Establece la imagen actual.
     *
     * @param imagen La imagen actual
     * @since 1.6
     */
    public void setImagen(Image imagen){
        this.imagen = (BufferedImage) imagen;                   // Imagen actual
        setUbicacionDeImagen(null);                             // Se centra la imagen en el medio del panel
        repaint();                                              // Se dibuja la nueva imagen
    }

    /**
     * Asigna la imagen actual mediante un archivo.
     *
     * @param file El archivo de entrada para guardar la imagen.
     * @throws IOException Error del archivo de entrada para guardar la imagen.
     * @since 1.6
     */
    public void setImagen(File file) throws IOException{
        setImagen(ImageIO.read(file));
        repaint();                                              // Se dibuja la nueva imagen
    }

    /**
     * Devuelve el numero de la constante ARRASTRAR.
     *
     * @return El numero de la constante ARRASTRAR
     * @since 1.6
     */
    public static int getARRASTRAR() {
        return ARRASTRAR;
    }

    /**
     * Devuelve el numero de la constante CIRCULO.
     *
     * @return El numero de la constante CIRCULO
     * @since 1.6
     */
    

    /**
     * Devuelve el numero de la constante LAPIZ.
     *
     * @return El numero de la constante LAPIZ
     * @since 1.6
     */
    public static int getLAPIZ() {
        return LAPIZ;
    }

    /**
     * Devuelve el numero de la constante LINEA.
     *
     * @return El numero de la constante LINEA
     * @since 1.6
     */
    public static int getLINEA() {
        return LINEA;
    }

    /**
     * Devuelve el numero de la constante OVALO.
     *
     * @return El numero de la constante OVALO
     * @since 1.6
     */
    

    /**
     * Devuelve el numero de la constante PINCEL.
     *
     * @return El numero de la constante PINCEL
     * @since 1.6
     */
    public static int getPINCEL() {
        return PINCEL;
    }

    /**
     * Devuelve el numero de la constante RECTANGULO.
     *
     * @return El numero de la constante RECTANGULO
     * @since 1.6
     */
  
    /**
     * Devuelve el numero de la constante RECTANGULO CON CURVAS REDONDAS.
     *
     * @return El numero de la constante RECTANGULO CON CURVAS REDONDAS
     * @since 1.6
     */
  

    /**
     * Devuelve el numero de la longitud del borrador.
     *
     * @return El numero de la longitud del borrador
     * @since 1.6
     */
    

    /**
     * Establece el numero de la longitud del borrador.
     *
     * @param longitudBorrador El numero de la longitud del borrador
     * @since 1.6
     */
 

    /**
     * Devuelve para saber si se puede dibujar (true) o no (false) un texto.
     *
     * @return True cuando se puede dibujar un texto; caso contrario, false
     * @since 1.6
     */
 

    /**
     * Establece en true para poder dibujar texto; false para no dibujar texto.
     *
     * @param habilitarDibujarTexto En true para poder dibujar texto; false para no dibujar texto
     * @since 1.6
     */
    

    /**
     * Establece el nombre del archivo.
     *
     * @param nombreArchivo El nombre del archivo
     * @since 1.6
     */
    public void setNombreArchivo(File nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Devuelve para saber si se ha guardado la ultima version de la imagen(true),
     * caso contrario, en false.
     *
     * @return True cuando se ha guardado la última versión de la imagen; caso contrario, false
     * @since 1.6
     */
    public boolean isArchivoGuardadoUltimaVersion() {
        return archivoGuardadoUltimaVersion;
    }

    /**
     * Establece en True para guardar la ultima version de la imagen;
     * caso contrario, en false.
     *
     * @param archivoGuardadoUltimaVersion En true saber si se ha guardado la ultima version de la imagen(true), caso contrario, en false.
     * @since 1.6
     */
    public void setArchivoGuardadoUltimaVersion(boolean archivoGuardadoUltimaVersion) {
        this.archivoGuardadoUltimaVersion = archivoGuardadoUltimaVersion;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Añada una figura a la lista de figuras a dibujar.
     *
     * @param figura Una nueva figura a dibujar
     * @since 1.6
     */
    public void agregarFigura(Figura figura){
        listaFiguras.add(figura);
    }

    /**
     * Quita la figura en la lista de figuras a dibujar.
     *
     * @param figura La figura a quitar de la lista
     * @since 1.6
     */
    public void eliminarFigura(Figura figura){
        listaFiguras.remove(figura);
    }

    /**
     * Añada un objeto Texto a la lista de texto a dibujar.
     *
     * @param texto Una nuevo objeto Texto a dibujar
     * @since 1.6
     */
   

    /**
     * Quita el texto en la lista de textos a dibujar.
     *
     * @param texto Un texto a quitar de la lista
     * @since 1.6
     */
   

    /**
     * Para ver si el ratón está dentro del rectángulo.
     * Se utiliza para poder comenzar un arrastre o eliminar un objeto individual
     *
     * @param e El evento de ratón
     *
     * @return True si el ratón está dentro del rectángulo
     * @since 1.6
     */
    private Figura dameFigura(MouseEvent e){
        for (Figura figura : listaFiguras){
            if (figura.estaDentro(e.getX(), e.getY())){
                return figura;
            }
        }
        return null;
    }

    /**
     * Dibuja todos los componentes en la pantalla.
     *
     * @param g Graphics con el que dibujar
     * @since 1.6
     */

    @Override
    public void paint(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        setBackground(getColorFondoPantallaDibujo());

        if (getImagen() != null){
            Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
            if (getUbicacionDeImagen() != null){
                center = getUbicacionDeImagen();
            }
            Point2D loc = new Point2D.Double();
            double width = imagen.getWidth() * getEscala();
            double height = imagen.getHeight() * getEscala();
            loc.setLocation(center.getX() - width / 2, center.getY() - height / 2);
            setColorFondoPantallaDibujo(getColorFondoPantallaDibujo());
            g.drawImage(getImagen(), (int) loc.getX(), (int) loc.getY(),(int) width, (int) height, null); // centra la imagen
        }
        dibujarFiguras(g);
        dibujarTexto(g);
  
        this.setBackground(getColorFondoPantallaDibujo());
        g.setColor(getColorBorde());      

        if(modoDibujar != getARRASTRAR()){
            if (modoDibujar == getLINEA()) {
                Line2D line2D = new Line2D.Float(getCoordenadasInicioX(),
                    getCoordenadasInicioY(), getCoordenadasFinX(), getCoordenadasFinY());
                g2 = (Graphics2D)g;
                Stroke bordeFigura = new BasicStroke(getTamanioBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(line2D);
            }

            

           

            



        }
    }

    /**
     * Deshace una opcion que se realizo al dibujar en la mesa de trabajo.
     *
     * @since 1.6
     */
    public void deshacer(){
        modoDibujar = 0;
        if(desHacerPila.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ya no se puede deshacer",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            // Compara si el ultimo elemento de la Pila es de tipo Figura
            if(desHacerPila.lastElement() instanceof Figura){
                Figura objeto = (Figura) desHacerPila.pop();
                eliminarFigura(objeto);
            }else{
                Texto objeto = (Texto) desHacerPila.pop();
              //  eliminarTexto(objeto);
            }
        }
        repaint();
    }
   
    /**
     * Metodo que borra todos los elementos de la pantalla.
     * @since 1.6
     */
    public void borrarTodo(){
        listaFiguras.clear();
        //listaTexto.clear();
        desHacerPila.clear();
        imagen = null;
        setModoDibujar(0);
        repaint();
    }

    /**
     * Devuelve true si se pudo guardar la imagen; false, en caso contrario.
     *
     * @return True si se pudo guardar la imagen; false, en caso contrario.
     * @since 1.6
     */
    public boolean guardarImagen() throws SQLException{
        if(nombreArchivo == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileSelectionMode(0);
            fileChooser.setFileFilter(new FiltroArchivo());
            int resultado = fileChooser.showSaveDialog(null);
            
            if(resultado == JFileChooser.CANCEL_OPTION)
                return false;
            nombreArchivo = fileChooser.getSelectedFile();

            if(nombreArchivo == null || nombreArchivo.getName().equals("")){
                JOptionPane.showMessageDialog(null,"Nombre de archivo inválido",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(getImagen() == null){
                crearImagen();
            }
	}   
        actualizarImagen();
        
        try{
            JOptionPane.showMessageDialog(null, "Archivo Guardado",
                    "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO
                    + Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try {
            File file;
            // Compara si tiene una extension .png
            if(nombreArchivo.toString().endsWith(".png")){
                 file = new File(nombreArchivo.toString());
            }else{
                 file = new File(nombreArchivo.toString()+".png");
            }
          if(imagen == null){
                crearImagen();
            }
            control(ultimoControl()+1);
            xRuta = "C:\\Users\\ismar\\Desktop\\EmpresaContinental-master\\src\\Firmas\\FIRMA"+ultimoControl()+".png";
            ImageIO.write(getImagen(), "png", new File(xRuta));
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // vaciar las dos listas y el deshacer
        listaFiguras.clear();
        ////listaTexto.clear();
        desHacerPila.clear();

        archivoGuardadoUltimaVersion = true;
        setModoDibujar(NULO);
        
        //setEscala(1.0);
	repaint();
        return true;
    }

    /**
     * Abre la imagen con formato png.
     *
     * @since 1.6
     */
    public void abrirImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION)
            return;

        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo != null){
            try{

                borrarTodo();
                BufferedImage image = ImageIO.read(nombreArchivo);
                imagen = ImageIO.read(nombreArchivo);
                setImagen(nombreArchivo);

                Graphics g = image.getGraphics();
                setColorFondoPantallaDibujo(Color.WHITE);
                g.drawImage(image, 0, 0, this);
            }catch(Exception exp){
                JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        archivoGuardadoUltimaVersion = true;
        repaint();
    }

    /**
     * Metodo que crea la imagen.
     * @since 1.6
     */
    public void crearImagen() {
        imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagen.createGraphics();
        g2.setColor(getColorFondoPantallaDibujo());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        dibujarFiguras(g2);
        dibujarTexto(g2);
        setImagen(imagen);
        g2.dispose();
        setImagen(imagen);
    }

    /**
     * Metodo para acercar la imagen actual.
     * @since 1.6
     */
    public void acercar(){
        setEscala(getEscala() * 1.09);
    }

    /**
     * Metodo para alejar la imagen actual.
     * @since 1.6
     */
    public void alejar(){
        setEscala(getEscala() * 0.9174311926605505);
    }

    /**
     * Dibuja todas las figuras de la lista.
     *
     * @param g Dibuja todas las figuras de la lista
     * @since 1.6
     */
    public void dibujarFiguras(Graphics g){
        for (Figura figura : listaFiguras){
            figura.dibujar(g);
        }
    }

    /**
     * Dibuja todos los objetos texto de la lista.
     *
     * @param g Dibuja todos los objetos texto de la lista
     * @since 1.6
     */
    public void dibujarTexto(Graphics g){
       // for (Texto texto : listaTexto){
        //        texto.dibujar(g);
       // }
    }


    /**
     * Actualiza la imagen actual con la imagen nueva.
     *
     * @since 1.6
     */
    public void actualizarImagen(){
        if(getImagen() != null){
            Graphics g = getImagen().getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(getColorFondoPantallaDibujo());
            g2.drawImage(getImagen(), 0, 0, null);
            dibujarFiguras(g2);
            dibujarTexto(g2);
            setImagen(getImagen());
            g2.dispose();
        }
    }

    /**
     * Este es el metodo que se encarga de la impresion.
     *
     * @param g El contexto en el que se dibuja la página
     * @param pageFormat El tamaño y la orientación de la página
     * @param pageIndex El indice de la pagina
     * 
     * @return Si la página se representa con éxito PAGE_EXISTS o si pageIndex se
     *         especifica una página que no existe NO_SUCH_PAGE.
     * @throws PrinterException Se lanza cuando termina la impresion
     * @since 1.6
     */
    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if( pageIndex >= 1 ) {
            return( Printable.NO_SUCH_PAGE );
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.translate( pageFormat.getImageableX(), pageFormat.getImageableY() );
        paint( g2 );
        return( Printable.PAGE_EXISTS );
    }

    /**
     * Metodo que muestra la ventana Texto.
     * @since 1.6
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void control(int control){
          String sql = "INSERT INTO Control(ControlFirma) VALUES(?)";
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();

        try {
            PreparedStatement pps = cn.prepareStatement(sql);
            pps.setInt(1,control);
          
            pps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCION  DE SESION");
        }
   }
     public int ultimoControl() throws SQLException {
        Conexion con = new Conexion();
        Connection cn = con.ConectarBaseDatos();
        int x = 0;
        String sql = "SELECT MAX(id) AS ultimo FROM Control";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            x = rs.getInt("ultimo");
        }
        return x;
    }
     public void Firma(JTable tabla, int fila,int columna,String firma){
         propiedades(tabla);
         ImageIcon imagen = new ImageIcon(getClass().getResource(firma));
        Image conversion = imagen.getImage();
        Image tamano = conversion.getScaledInstance(170, 55, Image.SCALE_SMOOTH);
        ImageIcon fin = new ImageIcon(tamano);
         tabla.setValueAt(fin, fila, columna);
         
     }
      public void propiedades(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
    }

}