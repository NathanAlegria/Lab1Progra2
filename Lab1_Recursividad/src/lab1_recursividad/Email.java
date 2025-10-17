/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_recursividad;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Email {
    // Atributos
    private String emisor;
    private String asunto;
    private String contenido;
    private boolean leido;
    private Calendar fechaEnvio;
    
    // Constructor
    public Email(String emisor, String asunto, String contenido) {
        this.emisor = emisor;
        this.asunto = asunto;
        this.contenido = contenido;
        this.leido = false;
        this.fechaEnvio = Calendar.getInstance();
    }
    
    public String getEmisor() { 
        return emisor; 
    }
    
    public String getAsunto() { 
        return asunto; 
    }
    
    public String getContenido() { 
        return contenido; 
    }
    
    public boolean isLeido() { 
        return leido; 
    }
    
    public Calendar getFechaEnvio() { 
        return fechaEnvio; 
    }
    
    public void marcarLeido() {
        this.leido = true;
    }
    
    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(fechaEnvio.getTime());
    }
    
    public String getFormatoCompleto() {
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "DE: " + emisor +
               "\nASUNTO: " + asunto +
               "\nCONTENIDO: " + contenido +
               "\nFECHA: " + formato.format(fechaEnvio.getTime()) +
               "\nESTADO: " + (leido ? "LEIDO" : "SIN LEER");
    }
    
    public void print() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fechaEnvio.getTime());
        String estado = leido ? "LEÍDO" : "SIN LEER";
        
        System.out.println("DE: " + emisor);
        System.out.println("ASUNTO: " + asunto);
        System.out.println("CONTENIDO: " + contenido);
        System.out.println("FECHA: " + fechaFormateada);
        System.out.println("ESTADO: " + estado);
        System.out.println("----------------------------------------");
    }
}
