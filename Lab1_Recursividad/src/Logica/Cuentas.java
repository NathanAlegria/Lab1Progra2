/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javax.swing.JOptionPane;
import Logica.Usuarios;

/**
 *
 * @author Nathan
 */
public class Cuentas {

    private Usuarios[] usuarios;
    private int numUsuarios;
    private static final int MAX_USUARIOS = 100;

    public Cuentas() {
        usuarios = new Usuarios[MAX_USUARIOS];
        numUsuarios = 0;
    }

    public boolean registrarUsuario(String username, String password) {
        JOptionPane.showMessageDialog(null, "Inicio de sesión:" + username + "con contraseña de:" + (password != null ? password.length() : 0) + " caracteres", "Información", JOptionPane.INFORMATION_MESSAGE);

        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El nombre de usuario esta vacio", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error:La contraseña esta vacia", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        username = username.trim();
        password = password.trim();

        if (numUsuarios >= MAX_USUARIOS) {
            System.out.println("ERROR: Capacidad máxima alcanzada (" + MAX_USUARIOS + " usuarios)");
            JOptionPane.showMessageDialog(null, "Capacidad Maxima alcanzada", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (password.length() != 5) {
            JOptionPane.showMessageDialog(null, "Error: La contraseña debe tener exactamente 5 caracteres. Actual:" + password.length(), "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getUsuario().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(null, "Error el usuario:" + username + " Ya existe", "Información", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }

        if (numUsuarios < usuarios.length) {
            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                usuarios[numUsuarios] = new Usuarios(username, password);
                numUsuarios++;

                JOptionPane.showMessageDialog(null,"ÉXITO: Usuario '" + username + "' registrado.\nTotal usuarios: " + numUsuarios, "Registro exitoso",JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,"El nombre de usuario o la contraseña no pueden estar vacíos.","Error de registro",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog( null,"No se pueden registrar más usuarios (límite alcanzado).","Error de capacidad",JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean verificarCredenciales(String username, String password) {
        JOptionPane.showMessageDialog(null, "Verificando usuario: "+username, "Información", JOptionPane.INFORMATION_MESSAGE);

        if (username == null || password == null) {
            JOptionPane.showMessageDialog(null, "Error: Credenciales nulas", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null&& usuarios[i].getUsuario().equals(username)&& usuarios[i].getContra().equals(password)) {
                JOptionPane.showMessageDialog(null, "Exito: Credenciales de: "+username , "Información", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }

        JOptionPane.showMessageDialog(null, "Error Credenciales invalidas: Credenciales nulas", "Información", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    public boolean cambiarcontra(String username, String currentPassword, String newPassword) {
        System.out.println("Cambiando contraseña para usuario: '" + username + "'");
        JOptionPane.showMessageDialog(null, "Cambiando contraseña del usuario: "+username, "Información", JOptionPane.INFORMATION_MESSAGE);

        if (username == null || currentPassword == null || newPassword == null) {
            JOptionPane.showMessageDialog(null, "Error: Parametros nulos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (newPassword.trim().length() != 5) {
           JOptionPane.showMessageDialog(null, "Error: Nueva contraseña debe tener exactamente 5 caracteres", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getUsuario().equals(username)) {
                if (usuarios[i].getContra().equals(currentPassword)) {
                    usuarios[i].setContra(newPassword.trim());
                    JOptionPane.showMessageDialog(null, "Exito contraseña cambiada", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: contraseña actual incorrecta", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    public boolean eliminarUsuario(String username, String password) {
        JOptionPane.showMessageDialog(null, "Eleminando usaurio: "+username, "Información", JOptionPane.INFORMATION_MESSAGE);

        if (username == null || password == null) {
            JOptionPane.showMessageDialog(null, "Error: Parametros nulos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getUsuario().equals(username)&& usuarios[i].getContra().equals(password)) {

                for (int j = i; j < numUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[numUsuarios - 1] = null;
                numUsuarios--;
                JOptionPane.showMessageDialog(null, "Exito usurio "+username+ " Eliminado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }

        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado o contraseña incorrecta", "Información", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    public Usuarios buscarUsuario(String username) {
        if (username == null) {
            return null;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getUsuario().equals(username)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

}
