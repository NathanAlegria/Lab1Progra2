/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_recursividad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class EmailMain extends JFrame {
    private JList<Email> listaCorreos;
    private JTextArea areaDetalles;
    private JButton btnMarcarLeido;
    private DefaultListModel<Email> modelo;

    public EmailMain() {
        setTitle("BANDEJA DE ENTRADA");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultListModel<>();
        listaCorreos = new JList<>(modelo);
        listaCorreos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        areaDetalles = new JTextArea();
        areaDetalles.setEditable(false);
        areaDetalles.setFont(new Font("Consolas", Font.PLAIN, 14));

        btnMarcarLeido = new JButton("Marcar como leído");

        JScrollPane scrollLista = new JScrollPane(listaCorreos);
        scrollLista.setPreferredSize(new Dimension(180, 0));
        add(scrollLista, BorderLayout.WEST);

        JScrollPane scrollArea = new JScrollPane(areaDetalles);
        add(scrollArea, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnMarcarLeido);
        add(panelBoton, BorderLayout.SOUTH);

        listaCorreos.addListSelectionListener(e -> mostrarDetalles());
        btnMarcarLeido.addActionListener(e -> marcarComoLeido());

        cargarCorreosEjemplo();
    }

    private void cargarCorreosEjemplo() {
        modelo.addElement(new Email("IngErick@unitec.edu", "Laboratorio1", "Cargar los archivos de su trabajo."));
        modelo.addElement(new Email("IngErick@unitec.edu", "Siguiente Prueba", "No olviden practicar constantemente."));
        modelo.addElement(new Email("IngErick@unitec.edu", "Cancelacion de Clase", "Nos vemos la siguiente clase."));
    }
    
    
    
    private void mostrarDetalles() {
        Email seleccionado = listaCorreos.getSelectedValue();
        if (seleccionado != null) {
            areaDetalles.setText(seleccionado.getFormatoCompleto());
        }
    }

    private void marcarComoLeido() {
        Email seleccionado = listaCorreos.getSelectedValue();
        if (seleccionado != null) {
            seleccionado.marcarLeido();
            listaCorreos.repaint(); 
            areaDetalles.setText(seleccionado.getFormatoCompleto());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un correo primero.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmailMain().setVisible(true);
        });
    }
}
