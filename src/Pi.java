import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pi {

    private static final Random r = new Random();

    private final static int OFFSET = 5;
    private final static int NUM_PIXELS = 600;
    private static Graphics graphics;

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static Choice choice;
    private static JLabel broj;
    private static JTextField br;
    private static JButton button;
    private static JLabel upozorenje;
    private static JLabel labela1;

    public boolean checkIsNumber(String s) {

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);

        if (s.contentEquals("1"))
            return false;
        if (matcher.matches())
            return true;
        else
            return false;

    }

    public Pi() {

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.PINK);

        label = new JLabel("Izaberite opciju:");
        label.setBounds(10, 20, 90, 25);
        panel.add(label);

        choice = new Choice();
        choice.setBounds(103, 22, 205, 25);
        choice.add("");
        choice.add("broj slučajno generisanih vrijednosti");
        choice.add("broj decimala koje treba da se poklope");
        panel.add(choice);

        broj = new JLabel("Broj je:");
        broj.setBounds(10, 50, 90, 25);
        panel.add(broj);
        broj.setVisible(false);

        br = new JTextField();
        br.setBounds(55, 53, 55, 20);
        panel.add(br);
        br.setVisible(false);

        button = new JButton("Izračunaj");
        button.setBounds(120, 53, 100, 20);
        panel.add(button);
        button.setVisible(false);

        upozorenje = new JLabel("Pogrešan unos!");
        upozorenje.setBounds(240, 53, 100, 25);
        panel.add(upozorenje);
        upozorenje.setVisible(false);

        labela1 = new JLabel();
        labela1.setBounds(10, 80, 250, 25);
        panel.add(labela1);
        labela1.setVisible(false);

        frame.setSize(350, 170);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("MONTE CARLO");
        frame.setVisible(true);

        frame.add(panel);

        choice.addItemListener(new ItemListener() {

                                   public void itemStateChanged(ItemEvent arg0) {

                                       if (choice.getSelectedItem() != "") {

                                           if (choice.getSelectedItem() == "broj slučajno generisanih vrijednosti") {
                                               broj.setVisible(true);
                                               br.setVisible(true);
                                               button.setVisible(true);

                                               button.addMouseListener(new MouseAdapter() {
                                                   @Override
                                                   public void mousePressed(MouseEvent e) {
                                                       actionOnFirstButton();
                                                   }
                                               });

                                           }

                                           if(choice.getSelectedItem() == "broj decimala koje treba da se poklope") {

                                               broj.setVisible(true);
                                               br.setVisible(true);
                                               button.setVisible(true);

                                               button.addMouseListener(new MouseAdapter() {
                                                   @Override
                                                   public void mousePressed(MouseEvent p) { actionOnSecondButton(); }
                                               });

                                           }
                                       }
                                   }
        });
    }

    public static void main(String[] args) {

        new Pi();

    }
    
    public void actionOnFirstButton() {
        labela1.setVisible(false);
        String brojN = br.getText();

        if(brojN.equals("") || !checkIsNumber(brojN)) {
            upozorenje.setVisible(true);
        }

        else if(checkIsNumber(brojN)) {
            upozorenje.setVisible(false);

            int n = Integer.parseInt(brojN);
            double pi_approx = 3.0;
            double brojac = 0.0;
            for (int i = 1; i <= n; i++) {

                Double x = r.nextDouble();
                Double y = r.nextDouble();
                if ((x * x + y * y) < 1) brojac++;
                pi_approx = 4 * brojac / i;

            }

            labela1.setText("Aproksimacija broja pi je: " + pi_approx);
            labela1.setVisible(true);
        }
    }

    public void actionOnSecondButton() {

        labela1.setVisible(false);
        String brojN = br.getText();

        if(brojN.equals("") || !checkIsNumber(brojN)) {

            upozorenje.setVisible(true);

        }

        else if(checkIsNumber(brojN)) {

            upozorenje.setVisible(false);
            labela1.setVisible(false);

            int n = Integer.parseInt(brojN);

            if(n > 15) {

                upozorenje.setVisible(true);

            }

            else {

                Double pi = Math.PI;
                double pi_approx = 3.0;
                String piMat = pi.toString();
                String odsjecen = piMat.substring(0, n + 2);
                Double praviPi = Double.parseDouble(odsjecen);
                System.out.println("Pi: " + pi);
                int brojIteracija = 0;
                double brojac = 0.0;

                do {

                        brojIteracija++;
                        Double x = r.nextDouble();
                        Double y = r.nextDouble();
                        if((x*x + y*y) < 1) brojac++;
                        pi_approx = 4 * brojac/brojIteracija;

                } while(pi_approx != praviPi);

                labela1.setText("Aproksimacija broja pi je: " + pi_approx);
                labela1.setVisible(true);

            }
        }
    }
}