package org.SpecikMan.Controller.AdminSection;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.Entity.Account;
import org.SpecikMan.Entity.FilePath;
import org.SpecikMan.Tools.DisposeForm;
import org.SpecikMan.Tools.FileRW;
import org.SpecikMan.Tools.LoadForm;
import org.SpecikMan.Tools.ShowAlert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LoginAdminController {
    @FXML
    private TextField txtusername;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnLogin;

    @FXML
    void btnLoginClicked(MouseEvent e) {
        if (txtusername.getText() == null || txtusername.getText().isEmpty()) {
            ShowAlert.show("Warning!", "Nhập username ");
        } else if (txtpassword.getText() == null || txtpassword.getText().isEmpty()) {
            ShowAlert.show("Warning!", "Nhập password ");
        } else {
            String username = txtusername.getText();
            String password = txtpassword.getText();
            if (Login(username, password)) {
                ShowAlert.show("Warning!", "Đăng nhập thành công ");
                LoadForm.load("/fxml/Home.fxml","TypeMaster",false);
                DisposeForm.Dispose(txtusername);
            } else {
                ShowAlert.show("Warning!", "Đăng nhập thất bại ");
            }
        }
    }


    public boolean Login(String username, String password) {
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.getAll();
        for (Account account : accounts) {
            if(account.getNameRole().equals("Admin")) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
                if ((account.getUsername().equals(username) || account.getEmail().equals(username)) && result.verified) {
                    FileRW.Write(FilePath.getLoginAcc(), account.getIdAccount());
                    if(!account.getLatestLoginDate().equals(Date.valueOf(LocalDate.now()))){
                        account.setLatestLoginDate(Date.valueOf(LocalDate.now()));
                        account.setCountLoginDate(account.getCountLoginDate()+1);
                        accountDao.update(account);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

