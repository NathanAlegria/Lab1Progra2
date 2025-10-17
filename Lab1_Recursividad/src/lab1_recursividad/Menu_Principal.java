/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1_recursividad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nathan
 */



public class Menu_Principal extends JFrame {
    private JButton btnVerInbox, btnMandarCorreo, btnLeerCorreo, btnLimpiarInbox;
    private JButton btnBuscarEmisor, btnBuscarAsunto;

    private JTable tablaInbox;
    private JTextArea areaContenido;
    private JTextField campoDestinatario, campoAsunto, campoBuscarEmisor, campoBuscarAsunto;
    private JTextArea campoMensaje;

    private JLabel lblUsuario, lblFechaHora;

    public Menu_Principal() {
        setTitle("Menú Principal - Cliente de Correo");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        
        JPanel fondo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(10, 40, 80),
                        0, getHeight(), new Color(2, 18, 25));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        fondo.setBorder(new EmptyBorder(10, 10, 10, 10));

        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        JLabel titulo = new JLabel("📨  MENÚ PRINCIPAL");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        
        lblUsuario = new JLabel("Usuario: Desconocido"); 
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFechaHora = new JLabel(getFechaHora());
        lblFechaHora.setForeground(Color.WHITE);
        lblFechaHora.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblUsuario);
        infoPanel.add(lblFechaHora);
        panelSuperior.add(infoPanel, BorderLayout.EAST);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        
        JSplitPane splitCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCentral.setDividerLocation(500);
        splitCentral.setDividerSize(4);
        splitCentral.setOpaque(false);

        
        JPanel panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.setOpaque(false);

        
        String[] columnas = {"De", "Asunto", "Estado"};
        tablaInbox = new JTable(new Object[][]{}, columnas);
        tablaInbox.setRowHeight(25);
        JScrollPane scrollTabla = new JScrollPane(tablaInbox);
        scrollTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(35, 90, 160), 2),
                "Bandeja de entrada",
                0, 0, new Font("SansSerif", Font.BOLD, 14), Color.WHITE
        ));
        panelIzquierda.add(scrollTabla, BorderLayout.CENTER);

        
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 0;

        JLabel lblBuscarEmisor = new JLabel("Buscar por Emisor:");
        lblBuscarEmisor.setForeground(Color.WHITE);
        panelBusqueda.add(lblBuscarEmisor, c);
        c.gridx = 1;
        campoBuscarEmisor = new JTextField(12);
        styleField(campoBuscarEmisor);
        panelBusqueda.add(campoBuscarEmisor, c);
        c.gridx = 0; c.gridy++;
        JLabel lblBuscarAsunto = new JLabel("Buscar por Asunto:");
        lblBuscarAsunto.setForeground(Color.WHITE);
        panelBusqueda.add(lblBuscarAsunto, c);
        c.gridx = 1;
        campoBuscarAsunto = new JTextField(12);
        styleField(campoBuscarAsunto);
        panelBusqueda.add(campoBuscarAsunto, c);
        c.gridx = 0; c.gridy++; c.gridwidth = 2;
        btnBuscarEmisor = createButton("Buscar Emisor");
        panelBusqueda.add(btnBuscarEmisor, c);
        c.gridy++;
        btnBuscarAsunto = createButton("Buscar Asunto");
        panelBusqueda.add(btnBuscarAsunto, c);

        panelIzquierda.add(panelBusqueda, BorderLayout.SOUTH);

        splitCentral.setLeftComponent(panelIzquierda);

       
        JPanel panelDerecha = new JPanel(new GridBagLayout());
        panelDerecha.setOpaque(false);
        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(5, 5, 5, 5);
        d.fill = GridBagConstraints.HORIZONTAL;
        d.gridx = 0;
        int y = 0;

        JLabel lblDestinatario = new JLabel("Destinatario:");
        lblDestinatario.setForeground(Color.WHITE);
        d.gridy = y++; panelDerecha.add(lblDestinatario, d); 
        d.gridy = y++;
        campoDestinatario = new JTextField(20); styleField(campoDestinatario); panelDerecha.add(campoDestinatario, d); 

        JLabel lblAsunto = new JLabel("Asunto:");
        lblAsunto.setForeground(Color.WHITE);
        d.gridy = y++; panelDerecha.add(lblAsunto, d); 
        d.gridy = y++;
        campoAsunto = new JTextField(20); styleField(campoAsunto); panelDerecha.add(campoAsunto, d); 

        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setForeground(Color.WHITE);
        d.gridy = y++; panelDerecha.add(lblContenido, d); 
        d.gridy = y++;
        campoMensaje = new JTextArea(6, 20); campoMensaje.setLineWrap(true); campoMensaje.setWrapStyleWord(true);
        campoMensaje.setBorder(BorderFactory.createLineBorder(new Color(40, 100, 180), 2));
        JScrollPane scrollMsg = new JScrollPane(campoMensaje);
        panelDerecha.add(scrollMsg, d); 

        d.gridy = y++;
        btnMandarCorreo = createButton("✉️  MANDAR CORREO"); panelDerecha.add(btnMandarCorreo, d); 
        d.gridy = y++;
        btnLeerCorreo = createButton("📖  LEER CORREO"); panelDerecha.add(btnLeerCorreo, d); 
        d.gridy = y++;
        btnVerInbox = createButton("📬  VER INBOX"); panelDerecha.add(btnVerInbox, d); 
        d.gridy = y++;
        btnLimpiarInbox = createButton("🧹  LIMPIAR INBOX"); panelDerecha.add(btnLimpiarInbox, d);

        splitCentral.setRightComponent(panelDerecha);

        fondo.add(splitCentral, BorderLayout.CENTER);

        
        areaContenido = new JTextArea(5, 80);
        areaContenido.setEditable(false);
        areaContenido.setBackground(new Color(230, 240, 250));
        areaContenido.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(35, 90, 160), 2),
                "Contenido del correo leído",
                0, 0, new Font("SansSerif", Font.BOLD, 13),
                new Color(35, 90, 160)
        ));
        JScrollPane scrollContenido = new JScrollPane(areaContenido);
        fondo.add(scrollContenido, BorderLayout.SOUTH);

        setContentPane(fondo);
    }

    
    private JButton createButton(String text){
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBackground(new Color(25, 80, 180));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(5, 15, 25), 2),
                BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(35, 100, 220));
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(25, 80, 180));
            }
        });
        return btn;
    }

    private void styleField(JTextField field){
        field.setBackground(new Color(250,250,252));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30,100,200),2),
                BorderFactory.createEmptyBorder(5,5,5,5)
        ));
    }

    
    private String getFechaHora(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss a");
        return "Fecha/Hora: " + formato.format(new Date());
    }
}


