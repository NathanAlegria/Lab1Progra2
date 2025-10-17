/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_recursividad;

/**
 *
 * @author Nathan
 */
public class Usuarios {
    

    private String usuario;
    private String contra;
    private static final int maxIngresos = 20;

    
    public Usuarios(String usuario, String contra) {
        this.usuario = usuario;
        this.contra = contra;
    }
 
    
    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }


    public void setUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
    }

    public void setContra(String nuevaContra) {
        this.contra = nuevaContra;
    }
    
    public boolean confirmarcontra(String inputcontra) {
        return this.contra.equals(inputcontra);
    }

    
}
