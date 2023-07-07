import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Formulario {
    private JPanel rootPanel;
    private JButton OKButton;
    private JTextField nombreField;
    private JTextField cedulaField;
    private JTextField edadField;
    private JButton cargarButton;

    public Formulario() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String cedula = cedulaField.getText();
                int edad = Integer.parseInt(edadField.getText());

                guardarDatos(nombre, cedula, edad);
            }
        });
            cargarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarDatos();
        }
    });
}

    private void guardarDatos(String nombre, String cedula, int edad) {
        String filePath = "datos.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String datos = nombre + "," + cedula + "," + edad + "\n";
            writer.write(datos);
            System.out.println("Datos guardados correctamente en el archivo.");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en el archivo.", e);
        }
    }
    private void cargarDatos() {
        String filePath = "datos.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String nombre = datos[0];
                    String cedula = datos[1];
                    int edad = Integer.parseInt(datos[2]);

                    nombreField.setText(nombre);
                    cedulaField.setText(cedula);
                    edadField.setText(String.valueOf(edad));

                    System.out.println("Datos cargados correctamente desde el archivo.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los datos desde el archivo.", e);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");

        frame.setContentPane(new Formulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}