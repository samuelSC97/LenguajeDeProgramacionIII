package Actividad;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class VentanaPasajes extends JFrame {
    private JTextField txtNombre, txtDNI, txtFecha;
    private JCheckBox chkComida, chkWifi, chkPeliculas;
    private JRadioButton rdbAsientoVentana, rdbAsientoPasillo;
    private JComboBox<String> cmbSalida, cmbDestino, cmbClase;
    private JButton btnLimpiar, btnConfirmar;

    public VentanaPasajes() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Usuario\\Downloads\\icon.png"));
        getContentPane().setBackground(new Color(200, 255, 200));
        setTitle("Reserva de Pasajes");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(10, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        getContentPane().add(txtNombre);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblDNI);
        txtDNI = new JTextField();
        txtDNI.setFont(new Font("Arial", Font.PLAIN, 12));
        getContentPane().add(txtDNI);

        JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy):");
        lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblFecha);
        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 12));
        getContentPane().add(txtFecha);

        ButtonGroup grupoAsiento = new ButtonGroup();
        rdbAsientoVentana = new JRadioButton("Ventana");
        rdbAsientoPasillo = new JRadioButton("Pasillo");
        grupoAsiento.add(rdbAsientoVentana);
        grupoAsiento.add(rdbAsientoPasillo);

        JLabel lblAsiento = new JLabel("Ubicación de Asiento:");
        lblAsiento.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblAsiento);
        JPanel panelAsiento = new JPanel();
        panelAsiento.add(rdbAsientoVentana);
        panelAsiento.add(rdbAsientoPasillo);
        getContentPane().add(panelAsiento);

        JLabel lblServicios = new JLabel("Servicios Adicionales:");
        lblServicios.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblServicios);
        JPanel panelServicios = new JPanel();
        chkComida = new JCheckBox("Comida");
        chkWifi = new JCheckBox("Wi-Fi");
        chkPeliculas = new JCheckBox("Películas");
        panelServicios.add(chkComida);
        panelServicios.add(chkWifi);
        panelServicios.add(chkPeliculas);
        getContentPane().add(panelServicios);

        JLabel lblSalida = new JLabel("Ciudad de Salida:");
        lblSalida.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblSalida);
        cmbSalida = new JComboBox<>(new String[]{"Lima", "Chiclayo", "Arequipa"});
        getContentPane().add(cmbSalida);

        JLabel lblDestino = new JLabel("Ciudad de Destino:");
        lblDestino.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblDestino);
        cmbDestino = new JComboBox<>(new String[]{"Cusco", "Trujillo", "Tacna"});
        getContentPane().add(cmbDestino);

        JLabel lblClase = new JLabel("Clase de Pasaje:");
        lblClase.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(lblClase);
        cmbClase = new JComboBox<>(new String[]{"Económica", "Ejecutiva", "Primera Clase"});
        getContentPane().add(cmbClase);

        btnLimpiar = new JButton("Limpiar Datos");
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConfirmar = new JButton("Confirmar Reserva");
        btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
        getContentPane().add(btnLimpiar);
        getContentPane().add(btnConfirmar);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText("");
                txtDNI.setText("");
                txtFecha.setText("");
                grupoAsiento.clearSelection();
                chkComida.setSelected(false);
                chkWifi.setSelected(false);
                chkPeliculas.setSelected(false);
                cmbSalida.setSelectedIndex(0);
                cmbDestino.setSelectedIndex(0);
                cmbClase.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Formulario reiniciado", "Reiniciar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String dni = txtDNI.getText();
                String fecha = txtFecha.getText();
                String asiento = rdbAsientoVentana.isSelected() ? "Ventana" : (rdbAsientoPasillo.isSelected() ? "Pasillo" : "Sin seleccionar");

                String servicios = "";
                if (chkComida.isSelected()) servicios += "Comida, ";
                if (chkWifi.isSelected()) servicios += "Wi-Fi, ";
                if (chkPeliculas.isSelected()) servicios += "Películas, ";
                if (servicios.isEmpty()) servicios = "Ninguno";
                else servicios = servicios.substring(0, servicios.length() - 2);

                String salida = (String) cmbSalida.getSelectedItem();
                String destino = (String) cmbDestino.getSelectedItem();
                String clase = (String) cmbClase.getSelectedItem();

                String mensaje = "Resumen de Reserva:\n"
                        + "Nombre: " + nombre + "\n"
                        + "DNI: " + dni + "\n"
                        + "Fecha: " + fecha + "\n"
                        + "Asiento: " + asiento + "\n"
                        + "Servicios: " + servicios + "\n"
                        + "Salida: " + salida + "\n"
                        + "Destino: " + destino + "\n"
                        + "Clase: " + clase;

                JOptionPane.showMessageDialog(null, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPasajes();
    }
}
