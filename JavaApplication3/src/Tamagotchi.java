import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
//primera pantalla
public class Tamagotchi extends JFrame {
    private String nombreTamagotchi;
    private int felicidad = 100;
    private int hambre = 0;
    private int edad = 0;
    private int inteligencia = 0; 
    private String clase = "bobo"; 
//dfinimos botones
    private JLabel felicidadLabel;
    private JLabel hambreLabel;
    private JLabel edadLabel;
    private JLabel inteligenciaLabel; 
    private JLabel claseLabel;
    private JLabel tamagotchiLabel;
    private JButton alimentarButton;
    private JButton jugarButton;
    private JButton bailarButton;
    private JButton estudiarButton;
// Sprites del Tamagotchi
    private ImageIcon tamagotchiNormal = new ImageIcon("/src/imagen1.png");
    private ImageIcon tamagotchiBailando1 = new ImageIcon("/src/imgagen2.png");
    private ImageIcon tamagotchiBailando2 = new ImageIcon("/src/imagen3.png");
    
// Temporizador para el baile
    private Timer baileTimer; 
    
// Configuración básica de la ventana
    public Tamagotchi() {
        setTitle("Tamagotchi");
        setSize(800, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Inicializar el sprite del Tamagotchi con la imagen normal
        tamagotchiLabel = new JLabel(tamagotchiNormal);
        tamagotchiLabel.setBounds(50, 10, 100, 100);

        // Solicitar el nombre del Tamagotchi
        nombreTamagotchi = JOptionPane.showInputDialog("Por favor, introduce el nombre de tu Tamagotchi:");

        // Crear un panel para colocar componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Crear etiquetas para mostrar los estados del Tamagotchi
        felicidadLabel = new JLabel("Felicidad: " + felicidad);
        felicidadLabel.setBounds(10, 10, 300, 20);

        hambreLabel = new JLabel("Hambre: " + hambre);
        hambreLabel.setBounds(10, 40, 300, 20);

        edadLabel = new JLabel("Edad: " + edad);
        edadLabel.setBounds(10, 70, 300, 20);

        inteligenciaLabel = new JLabel("Inteligencia: " + inteligencia); 
        inteligenciaLabel.setBounds(10, 100, 300, 20);

        claseLabel = new JLabel("Clase: " + clase);
        claseLabel.setBounds(10, 130, 300, 20);

        // Crear botones para interactuar con el Tamagotchi
        alimentarButton = new JButton("Alimentar");
        alimentarButton.setBounds(500, 10, 100, 30);
        alimentarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alimentar();
            }
        });

        jugarButton = new JButton("Jugar");
        jugarButton.setBounds(500, 60, 100, 30);
        jugarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });

        bailarButton = new JButton("Bailar");
        bailarButton.setBounds(500, 160, 100, 30);
        bailarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bailar();
            }
        });

        estudiarButton = new JButton("Estudiar");
        estudiarButton.setBounds(500, 110, 100, 30);
        estudiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estudiar();
            }
        });

        // Agregar componentes al panel
        panel.add(tamagotchiLabel);
        panel.add(felicidadLabel);
        panel.add(hambreLabel);
        panel.add(edadLabel);
        panel.add(inteligenciaLabel); 
        panel.add(claseLabel);
        panel.add(alimentarButton);
        panel.add(jugarButton);
        panel.add(bailarButton);
        panel.add(estudiarButton); 

        // Agregar el panel a la ventana
        add(panel);

        // Configurar un temporizador para actualizar el estado del Tamagotchi
        Timer timer = new Timer(2000, new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        timer.start();
        
            // Configurar el temporizador del baile
        baileTimer = new Timer(1000, new ActionListener() {
            int contador = 0; // Contador para controlar las imágenes del baile

            @Override
            public void actionPerformed(ActionEvent e) {
                if (contador == 0) {
                    tamagotchiLabel.setIcon(tamagotchiBailando1);
                } else if (contador == 1) {
                    tamagotchiLabel.setIcon(tamagotchiBailando2);
                } 
                contador++;
                if (contador == 3) {
                    contador = 0;
                }
            }
        });
        baileTimer.setRepeats(true);
        baileTimer.setCoalesce(true);
    }
    
//configurar las acciones
    private void alimentar() {
        hambre -= 10;
        if (hambre < 0) {
            hambre = 0; 
        }
        felicidad += 5;
        actualizarEtiquetas();
    }

    private void jugar() {
        hambre += 5;
        if (hambre > 100) {
            hambre = 100; 
        }
        felicidad += 10;
        if (felicidad > 100) {
            felicidad = 100; 
        }
        actualizarEtiquetas();
    }

    private void bailar() {
        felicidad += 20;
        if (felicidad > 100) {
            felicidad = 100; 
        }
         baileTimer.start();
        Timer stopBaileTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baileTimer.stop();
                // Cambia la imagen del Tamagotchi a la normal
                tamagotchiLabel.setIcon(tamagotchiNormal);
           }
        });
        stopBaileTimer.setRepeats(false);
        stopBaileTimer.start();
        actualizarEtiquetas();
    }

    private void estudiar() {
        felicidad -= 10;
        if (felicidad < 0) {
            felicidad = 0; 
        }
        inteligencia += 10; 
        actualizarClase(); 
        actualizarEtiquetas();
    }

    private void actualizar() {
        edad++;
        hambre++;
        felicidad--;
        actualizarClase(); 
        actualizarEtiquetas();
    }
//clases para estudiar
    private void actualizarClase() {
        if (inteligencia <= 30) {
            clase = "bobo";
        } else if (inteligencia <= 300) {
            clase = "normal";
        } else {
            clase = "inteligente";
        } 
        claseLabel.setText("Clase: " + clase);
    }
//actualizacion de etiquetas 
    private void actualizarEtiquetas() {
        felicidadLabel.setText("Felicidad de " + nombreTamagotchi + ": " + felicidad);
        hambreLabel.setText("Hambre de " + nombreTamagotchi + ": " + hambre);
        edadLabel.setText("Edad de " + nombreTamagotchi + ": " + edad);
        inteligenciaLabel.setText("Inteligencia de " + nombreTamagotchi + ": " + inteligencia);
    }
//iniciar y motstrar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tamagotchi tamagotchi = new Tamagotchi();
                tamagotchi.setVisible(true);
            }
        });
    }
}