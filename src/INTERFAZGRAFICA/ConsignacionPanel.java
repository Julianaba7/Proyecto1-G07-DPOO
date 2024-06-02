package INTERFAZGRAFICA;

import javax.swing.*;

import CONSIGNACION.Consignacion;
import PERSISTENCIA.PersistenciaSerializar;
import PIEZAS.Escultura;
import PIEZAS.Fotografia;
import PIEZAS.Impresion;
import PIEZAS.Pieza;
import PIEZAS.PiezaExhibicion;
import PIEZAS.PiezaSubasta;
import PIEZAS.PiezaVenta;
import PIEZAS.Pintura;
import PIEZAS.TipoPieza;
import PIEZAS.Video;
import USUARIOS.Propietario;
import galeria.Inventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ConsignacionPanel extends JPanel {
    private Inventario inventario;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ConsignacionPanel(Inventario inventario, CardLayout cardLayout, JPanel mainPanel) {
        this.inventario = inventario;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(16, 2, 10, 10));

        // Campos para la pieza
        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField();

        JLabel yearLabel = new JLabel("Año:");
        JTextField yearField = new JTextField();

        JLabel placeLabel = new JLabel("Lugar de creación:");
        JTextField placeField = new JTextField();

        JLabel authorLabel = new JLabel("Autor:");
        JTextField authorField = new JTextField();

        JLabel typeLabel = new JLabel("Tipo de pieza:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Pintura", "Escultura", "Fotografía", "Video", "Impresión"});

        JLabel startDateLabel = new JLabel("Fecha de inicio:"); //"yyyy-MM-dd"
        JTextField startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("Fecha de finalización:"); //"yyyy-MM-dd"
        JTextField endDateField = new JTextField();

        JLabel purposeLabel = new JLabel("Propósito:");
        JComboBox<String> purposeComboBox = new JComboBox<>(new String[]{"Venta", "Exhibición", "Subasta"});

        JLabel valueLabel = new JLabel("Valor (solo para venta):");
        JTextField valueField = new JTextField();
        
        JLabel minvalueLabel = new JLabel("Valor mínimo (solo para subasta):");
        JTextField minvalueField = new JTextField();
        
        JLabel incvalueLabel = new JLabel("Valor inicial (solo para subasta):");
        JTextField incvalueField = new JTextField();

        // Campos para el propietario
        JLabel ownerNameLabel = new JLabel("Nombre del propietario:");
        JTextField ownerNameField = new JTextField();

        JLabel ownerUserLabel = new JLabel("Usuario del propietario:");
        JTextField ownerUserField = new JTextField();

        JLabel ownerPasswordLabel = new JLabel("Contraseña del propietario:");
        JPasswordField ownerPasswordField = new JPasswordField();

        // Paneles específicos de tipo de pieza
        JPanel specificFieldsPanel = new JPanel();
        CardLayout specificFieldsLayout = new CardLayout();
        specificFieldsPanel.setLayout(specificFieldsLayout);

        JPanel paintingPanel = createPaintingPanel();
        JPanel sculpturePanel = createSculpturePanel();
        JPanel photographyPanel = createPhotographyPanel();
        JPanel videoPanel = createVideoPanel();
        JPanel printPanel = createPrintPanel();

        specificFieldsPanel.add(paintingPanel, "Pintura");
        specificFieldsPanel.add(sculpturePanel, "Escultura");
        specificFieldsPanel.add(photographyPanel, "Fotografía");
        specificFieldsPanel.add(videoPanel, "Video");
        specificFieldsPanel.add(printPanel, "Impresión");

        typeComboBox.addActionListener(e -> specificFieldsLayout.show(specificFieldsPanel, (String) typeComboBox.getSelectedItem()));

        JButton consignButton = new JButton("Consignar");
        consignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = titleField.getText();
                String año = yearField.getText();
                String lugarCreacion = placeField.getText();
                String autor = authorField.getText();
                
                // Creación del propietario
                String propietarioId = new BigInteger(50, new Random()).toString(32);
                String propietarioNombre = ownerNameField.getText();
                String propietarioUsuario = ownerUserField.getText();
                String propietarioContraseña = new String(ownerPasswordField.getPassword());
                Propietario propietario = new Propietario(propietarioId, propietarioNombre, propietarioUsuario, propietarioContraseña);
                
                String tipoPieza = (String) typeComboBox.getSelectedItem();
                TipoPieza tipoPiezaObj = null;

                switch (tipoPieza) {
                    case "Pintura":
                        JTextField altoField = (JTextField) paintingPanel.getComponent(1);
                        JTextField anchoField = (JTextField) paintingPanel.getComponent(3);
                        JTextField tipoPinturaField = (JTextField) paintingPanel.getComponent(5);
                        JTextField tecnicaField = (JTextField) paintingPanel.getComponent(7);
                        JTextField estiloField = (JTextField) paintingPanel.getComponent(9);
                        tipoPiezaObj = new Pintura(Double.parseDouble(altoField.getText()), Double.parseDouble(anchoField.getText()),
                                tipoPinturaField.getText(), tecnicaField.getText(), estiloField.getText());
                        break;
                    case "Escultura":
                        altoField = (JTextField) sculpturePanel.getComponent(1);
                        anchoField = (JTextField) sculpturePanel.getComponent(3);
                        JTextField profundidadField = (JTextField) sculpturePanel.getComponent(5);
                        JTextField materialField = (JTextField) sculpturePanel.getComponent(7);
                        JTextField tecnicaUtilizadaField = (JTextField) sculpturePanel.getComponent(9);
                        tipoPiezaObj = new Escultura(Double.parseDouble(altoField.getText()), Double.parseDouble(anchoField.getText()),
                                Double.parseDouble(profundidadField.getText()), materialField.getText(), tecnicaUtilizadaField.getText());
                        break;
                    case "Fotografía":
                        altoField = (JTextField) photographyPanel.getComponent(1);
                        anchoField = (JTextField) photographyPanel.getComponent(3);
                        JTextField tecnicaFotografiaField = (JTextField) photographyPanel.getComponent(5);
                        tipoPiezaObj = new Fotografia(Double.parseDouble(altoField.getText()), Double.parseDouble(anchoField.getText()),
                                tecnicaFotografiaField.getText());
                        break;
                    case "Video":
                        JTextField duracionField = (JTextField) videoPanel.getComponent(1);
                        JTextField formatoField = (JTextField) videoPanel.getComponent(3);
                        JTextField resolucionField = (JTextField) videoPanel.getComponent(5);
                        tipoPiezaObj = new Video(Double.parseDouble(duracionField.getText()), formatoField.getText(), resolucionField.getText());
                        break;
                    case "Impresión":
                        altoField = (JTextField) printPanel.getComponent(1);
                        anchoField = (JTextField) printPanel.getComponent(3);
                        JTextField tipoImpresionField = (JTextField) printPanel.getComponent(5);
                        JTextField tipoPapelField = (JTextField) printPanel.getComponent(7);
                        tipoPiezaObj = new Impresion(Double.parseDouble(altoField.getText()), Double.parseDouble(anchoField.getText()),
                                tipoImpresionField.getText(), tipoPapelField.getText());
                        break;
                }
                
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                String fechaI = startDateField.getText();
                Date fechaInicio = null;
				try {
					fechaInicio = formato.parse(fechaI);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                String fechaF = endDateField.getText();
                Date fechaFinal = null;
				try {
					fechaFinal = formato.parse(fechaF);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                String proposito = (String) purposeComboBox.getSelectedItem();
                
                Pieza pieza;
                if ("Venta".equals(proposito)) {
                    double valorFijo = Double.parseDouble(valueField.getText());
                     pieza = new PiezaVenta(titulo, año, lugarCreacion, autor, propietario, valorFijo, tipoPiezaObj);
                     PersistenciaSerializar persistenciaPieza = new PersistenciaSerializar("dataSerializacion/Propietarios.txt", pieza);
                } else if ("Exhibición".equals(proposito)){
                     pieza = new PiezaExhibicion(titulo, año, lugarCreacion, autor, propietario, tipoPiezaObj);
                }else {
                	double valorMinimo = Double.parseDouble(minvalueField.getText());
                	double valorInicial = Double.parseDouble(incvalueField.getText());
                	pieza = new PiezaSubasta(titulo, año, lugarCreacion, autor, valorInicial, valorMinimo, propietario, tipoPiezaObj);
                }

                Consignacion consignacion = new Consignacion(pieza, propietario, fechaInicio, fechaFinal);
                inventario.consignarPieza(consignacion);
                

                JOptionPane.showMessageDialog(ConsignacionPanel.this, "Pieza consignada con éxito");
                cardLayout.show(mainPanel, "AdminPanel");
            }
        });

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(placeLabel);
        panel.add(placeField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(typeLabel);
        panel.add(typeComboBox);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(purposeLabel);
        panel.add(purposeComboBox);
        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(minvalueLabel);
        panel.add(minvalueField);
        panel.add(incvalueLabel);
        panel.add(incvalueField);
        panel.add(ownerNameLabel);
        panel.add(ownerNameField);
        panel.add(ownerUserLabel);
        panel.add(ownerUserField);
        panel.add(ownerPasswordLabel);
        panel.add(ownerPasswordField);

        panel.add(new JLabel()); // Placeholder
        panel.add(specificFieldsPanel);
        panel.add(consignButton);


        return panel;
    }

    private JPanel createPaintingPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Alto:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Ancho:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Tipo de pintura:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Técnica utilizada:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Estilo:"));
        panel.add(new JTextField());
        return panel;
    }

    private JPanel createSculpturePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Alto:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Ancho:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Profundidad:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Material:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Técnica utilizada:"));
        panel.add(new JTextField());
        return panel;
    }

    private JPanel createPhotographyPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Alto:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Ancho:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Técnica utilizada:"));
        panel.add(new JTextField());
        return panel;
    }

    private JPanel createVideoPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Duración:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Formato:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Resolución:"));
        panel.add(new JTextField());
        return panel;
    }

    private JPanel createPrintPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Alto:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Ancho:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Tipo de impresión:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Tipo de papel:"));
        panel.add(new JTextField());
        return panel;
    }
}
