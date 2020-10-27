package loginfrom.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import loginfrom.controllers.Controller;

public class View extends JFrame {

    Controller controller = new Controller();
    static JLabel lblmessreg;
    static JLabel lblmess;
    JPanel panel;
    JLabel lblname;
    JLabel lblpassword;
    JTextField txtname;
    JPasswordField txtpassword;
    JButton btlogin;
    JPanel panelreg;
    JLabel lblnamereg;
    JLabel lblpasswordreg;
    JTextField txtnamereg;
    JPasswordField txtpasswordreg;
    JButton btsubmit;

    public View() {

        setDefaultCloseOperation(0);
        setUndecorated(true);
        setTitle("Diaz Cervera Brian Noe");
        setSize(620, 205);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container content = getContentPane();
        JDesktopPane des = new JDesktopPane();
        JInternalFrame flog = new JInternalFrame();
        flog.setSize(300, 200);
        flog.setLocation(10, 2);
        flog.setTitle("Login");
        lblname = new JLabel("User name:");
        lblpassword = new JLabel("Password:");
        lblmess = new JLabel("");
        btlogin = new JButton("Login");
        txtname = new JTextField(20);
        txtpassword = new JPasswordField(20);
        panel = new JPanel();
        panel.add(lblname);
        panel.add(txtname);
        panel.add(lblpassword);
        panel.add(txtpassword);
        panel.add(btlogin);
        panel.add(lblmess);
        flog.add(panel);
        flog.setVisible(true);
        JInternalFrame freg = new JInternalFrame();
        freg.setSize(300, 200);
        freg.setLocation(315, 2);
        freg.setTitle("Check in");
        lblnamereg = new JLabel("User Name:");
        lblpasswordreg = new JLabel("Password:");
        lblmessreg = new JLabel("");
        btsubmit = new JButton("Create");
        txtnamereg = new JTextField(20);
        txtpasswordreg = new JPasswordField(20);
        txtpasswordreg.addKeyListener(new View.KeyList());
        panelreg = new JPanel();
        panelreg.add(lblnamereg);
        panelreg.add(txtnamereg);
        panelreg.add(lblpasswordreg);
        panelreg.add(txtpasswordreg);
        panelreg.add(btsubmit);
        panelreg.add(lblmessreg);
        freg.add(panelreg);
        freg.setVisible(true);
        des.add(flog);
        des.add(freg);
        content.add(des, BorderLayout.CENTER);
        setVisible(true);
        txtname.requestFocus();

        btlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.login2(e, txtnamereg.getText(), txtpasswordreg.getText());
            }

        });
        btsubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.Create2(e, txtnamereg.getText(), txtpasswordreg.getText());
            }

        }
        );

    }

    public class KeyList extends KeyAdapter {

        public void keyPressed(KeyEvent ke) {
            String passw = new String(txtpasswordreg.getPassword());
            String mess = checkStrength(passw);
            showMess(mess + " password", lblpasswordreg);
        }

    }

    public void keyPressed(KeyEvent ke) {
        String passw = new String(txtpasswordreg.getPassword());
        String mess = checkStrength(passw);
        showMess(mess + " password", lblpasswordreg);
    }

    public String checkStrength(String passw) {
        Pattern pat = Pattern.compile("([0-9][aA-zZ]|[aA-zZ][0-9])");
        Matcher mat = pat.matcher(passw);
        if (mat.find()) {
            if (passw.length() >= 8) {
                return "Strong";
            } else {
                return "Medium";
            }
        } else {
            return "Weak";
        }

    }

    public void showMess(String mess, JLabel lbl) {
        lbl.setText(mess);
        lbl.setForeground(Color.RED);
    }

    public static void showAlertNotFound() {

        lblmess.setText("User not found");

    }

    public static void showAlertRepeat() {

        lblmessreg.setText("Repeat user");

    }
}
