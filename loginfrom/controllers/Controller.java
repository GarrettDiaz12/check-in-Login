package loginfrom.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import loginfrom.models.Model;
import loginfrom.views.View;

public class Controller extends KeyAdapter implements ActionListener {

    Model model = new Model();

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void login2(ActionEvent e, String Uname, String password) {
        String uname = Uname;
        String passw = password;
        if (!model.checkBlank(uname, passw)) {
            model.validateUser("user.txt", uname, passw);
        } else {
            View.showAlertNotFound();
        }
    }

    public void Create2(ActionEvent e, String Uname, String password) {
        String uname = Uname;
        String passw = password;
        if (!model.checkBlank(uname, passw)) {
            if (!model.checkExist("user.txt", uname)) {
                passw = new String(model.encrypt(passw));
                String accinfo = uname + "-" + passw;
                model.saveToFile("user.txt", accinfo);
            } else {
                View.showAlertRepeat();
            }
        } else {
            View.showAlertRepeat();
        }
    }
}
