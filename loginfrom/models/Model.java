package loginfrom.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Model {

    public boolean checkExist(String filename, String name) {
        FileReader fr;
        BufferedReader br;
        String accinfo;
        boolean exist = false;
        try {

            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while ((accinfo = br.readLine()) != null) {

                if (check(accinfo, name)) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The account already exists");
                    break;
                }

            }

            br.close();
            fr.close();
        } catch (Exception ie) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return exist;
    }

    public boolean check(String accinfo, String name) {
        String[] info = accinfo.split("-");
        String uname = info[0];
        if (uname.equals(name)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkBlank(String name, String passw) {
        boolean hasBlank = false;
        if (name.length() < 1) {
            hasBlank = true;
        }
        if (passw.length() < 1) {
            hasBlank = true;
        }
        return hasBlank;

    }

    public boolean check(String accinfo, String name, String passw) {
        String[] info = accinfo.split("-");
        String uname = info[0];
        String pass = new String(decrypt(info[1]));
        if (uname.equals(name) && pass.equals(passw)) {
            return true;
        } else {
            return false;
        }

    }

    public byte[] encrypt(String passw) {
        byte[] sb = passw.getBytes();
        int i;
        for (i = 0; i < sb.length; i++) {
            sb[i] = (byte) (sb[i] + (3 / 2) * 2);
        }

        return (sb);
    }

    public byte[] decrypt(String passw) {

        byte[] sb = passw.getBytes();
        int i;
        for (i = 0; i < sb.length; i++) {
            sb[i] = (byte) (sb[i] - (3 / 2) * 2);
        }

        return (sb);
    }

    public void saveToFile(String filename, String text) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException ie) {
            JOptionPane.showMessageDialog(null, "Error in the file");
        }
    }

    private void showMess(String invalid_login) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void validateUser(String filename, String name, String password) {
        FileReader fr;
        BufferedReader br;
        boolean valid = false;
        String accinfo;
        try {

            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while ((accinfo = br.readLine()) != null) {

                if (check(accinfo, name, password)) {
                    valid = true;
                    JOptionPane.showMessageDialog(null, "You agreed see you later.");
                    System.exit(0);
                    break;

                }

            }

            if (!valid) {
                showMess("Invalid login");
            }

            br.close();
            fr.close();
        } catch (Exception ie) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

}
