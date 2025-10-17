package lab1_recursividad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Nathan
 */
public class Menu extends JFrame {

    private CardLayout cards;
    private JPanel cardPanel;
    private JTextField loginUserField; 

    public Menu() {
        super("Bienvenido — Email");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 360);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel background = new JPanel(new BorderLayout()) {
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 Graphics2D g2 = (Graphics2D) g;
                 int w = getWidth();
                 int h = getHeight();
                 Color color1 = new Color(8, 56, 99);
                 Color color2 = new Color(2, 18, 25);
                 GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                 g2.setPaint(gp);
                 g2.fillRect(0, 0, w, h);
             }
         };
         background.setBorder(new EmptyBorder(18, 18, 18, 18));

        cards = new CardLayout();
        cardPanel = new JPanel(cards);
        cardPanel.setOpaque(false);

        cardPanel.add(buildLoginPanel(), "login");
        cardPanel.add(buildRegisterPanel(), "register");
        
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JLabel title = new JLabel("Iniciar sesión / Crear cuenta");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        top.add(title, BorderLayout.WEST);

        background.add(top, BorderLayout.NORTH);
        background.add(cardPanel, BorderLayout.CENTER);
        
        setContentPane(background);
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(12, 12, 12, 12));

        GridBagConstraints cc = new GridBagConstraints();
        cc.insets = new Insets(6, 6, 6, 6);
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.gridx = 0;
        cc.gridy = 0;

        JLabel userLabel = new JLabel("Usuario o correo");
        userLabel.setForeground(Color.WHITE);
        card.add(userLabel, cc);

        cc.gridy++;
        loginUserField = new JTextField(20); 
        styleTextField(loginUserField);
        card.add(loginUserField, cc);

        cc.gridy++;
        JLabel passLabel = new JLabel("Contraseña");
        passLabel.setForeground(Color.WHITE);
        card.add(passLabel, cc);

        cc.gridy++;
        JPasswordField passField = new JPasswordField(20);
        styleTextField(passField);
        card.add(passField, cc);

        cc.gridy++;
        cc.gridwidth = 1;
        JButton loginBtn = createButton("Iniciar sesión");
        card.add(loginBtn, cc);

        cc.gridx = 1;
        JButton toRegister = createButton("Crear cuenta");
        toRegister.setBackground(new Color(35, 140, 255));
        toRegister.setForeground(Color.WHITE);
        card.add(toRegister, cc);

        toRegister.addActionListener(e -> cards.show(cardPanel, "register"));

        panel.add(card, c);
        return panel;
    }

    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(12, 12, 12, 12));

        GridBagConstraints cc = new GridBagConstraints();
        cc.insets = new Insets(6, 6, 6, 6);
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.gridx = 0;
        cc.gridy = 0;

        JLabel nameLabel = new JLabel("Nombre completo");
        nameLabel.setForeground(Color.WHITE);
        card.add(nameLabel, cc);

        cc.gridy++;
        JTextField nameField = new JTextField(20);
        styleTextField(nameField);
        card.add(nameField, cc);

        cc.gridy++;
        JLabel emailLabel = new JLabel("Correo electrónico");
        emailLabel.setForeground(Color.WHITE);
        card.add(emailLabel, cc);

        cc.gridy++;
        JTextField emailField = new JTextField(20);
        styleTextField(emailField);
        card.add(emailField, cc);

        cc.gridy++;
        JLabel passLabel = new JLabel("Crear contraseña (5 caracteres)"); // Aclaro la restricción
        passLabel.setForeground(Color.WHITE);
        card.add(passLabel, cc);

        cc.gridy++;
        JPasswordField passField = new JPasswordField(20);
        styleTextField(passField);
        card.add(passField, cc);
        
        cc.gridy++;
        JLabel confPassLabel = new JLabel("Confirmar contraseña");
        confPassLabel.setForeground(Color.WHITE);
        card.add(confPassLabel, cc);
        
        cc.gridy++;
        JPasswordField confPassField = new JPasswordField(20);
        styleTextField(confPassField);
        card.add(confPassField, cc);

        cc.gridy++;
        JButton createBtn = createButton("Registrar");
        card.add(createBtn, cc);
        
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                String usuario = emailField.getText();
                String contra = new String(passField.getPassword());
                String confContra = new String(confPassField.getPassword());

                if (usuario.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(card,
                            "El campo Correo no puede estar vacío.",
                            "Campo Requerido",
                            JOptionPane.WARNING_MESSAGE);
                    emailField.requestFocus();
                    return;
                }

                if (contra.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(card,
                            "El campo Contraseña no puede estar vacío.",
                            "Campo Requerido",
                            JOptionPane.WARNING_MESSAGE);
                    passField.requestFocus();
                    return;
                }

                if (confContra.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(card,
                            "El campo Confirmar Contraseña no puede estar vacío.",
                            "Campo Requerido",
                            JOptionPane.WARNING_MESSAGE);
                    confPassField.requestFocus();
                    return;
                }

                usuario = usuario.trim();
                contra = contra.trim();
                confContra = confContra.trim();

                if (!contra.equals(confContra)) {
                    JOptionPane.showMessageDialog(card,
                            "Las contraseñas no coinciden.\nPor favor, verifica e inténtalo de nuevo.",
                            "Contraseñas Diferentes",
                            JOptionPane.WARNING_MESSAGE);
                    passField.setText("");
                    confPassField.setText("");
                    passField.requestFocus();
                    return;
                }

                if (contra.length() != 5) {
                    JOptionPane.showMessageDialog(card,
                            "La contraseña debe tener exactamente 5 caracteres.\n"
                            + "Longitud actual: " + contra.length() + " caracteres.",
                            "Longitud Inválida",
                            JOptionPane.WARNING_MESSAGE);
                    passField.setText("");
                    confPassField.setText("");
                    passField.requestFocus();
                    return;
                }
                
                
                
                 JOptionPane.showMessageDialog(card,
                             "Validación exitosa para: " + usuario,
                             "Simulación de Registro",
                             JOptionPane.INFORMATION_MESSAGE);
                 cards.show(cardPanel, "login");
            }
        });

        cc.gridx = 1;
        JButton backBtn = createButton("Volver");
        card.add(backBtn, cc);

        backBtn.addActionListener(e -> cards.show(cardPanel, "login"));

        panel.add(card, c);
        return panel;
    }
    
    private void styleTextField(JTextComponent comp) {
        comp.setBackground(new Color(250, 250, 252, 220));
        comp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 100, 200), 2),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        comp.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                Color start = new Color(12, 85, 170);
                Color end = new Color(2, 18, 25);
                GradientPaint gp = new GradientPaint(0, 0, start, 0, h, end);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, w, h, 18, 18);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
                setForeground(Color.WHITE);
                setFont(new Font("SansSerif", Font.BOLD, 12));
            }
        };

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setOpaque(false);
                btn.setBorder(BorderFactory.createLineBorder(new Color(180, 220, 255, 160), 1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            }
        });

        return btn;
    }
}