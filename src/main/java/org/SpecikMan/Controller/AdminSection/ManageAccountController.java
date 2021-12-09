package org.SpecikMan.Controller.AdminSection;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.Entity.Account;
import org.SpecikMan.Entity.Rank;
import org.SpecikMan.Tools.GenerateID;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManageAccountController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnChooseImage;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dbCreateDate;

    @FXML
    private DatePicker dbDob;

    @FXML
    private DatePicker dbLatestLoginDate;

    @FXML
    private TableView<Account> tbViewAccount;

    @FXML
    private TableColumn<Account, Integer> tbcCoin;

    @FXML
    private TableColumn<Account, Integer> tbcCountLoginDate;

    @FXML
    private TableColumn<Account, Date> tbcCreateDate;

    @FXML
    private TableColumn<Account, Date> tbcDob;

    @FXML
    private TableColumn<Account, String> tbcEmail;

    @FXML
    private TableColumn<Account, String> tbcFullname;

    @FXML
    private TableColumn<Account, Boolean> tbcGender;

    @FXML
    private TableColumn<Account, Date> tbcLatestLoginDate;

    @FXML
    private TableColumn<Account, String> tbcPassword;

    @FXML
    private TableColumn<Account, String> tbcPathImage;

    @FXML
    private TableColumn<Account, String> tbcRole;

    @FXML
    private TableColumn<Account, String> tbcUserName;

    @FXML
    private TableColumn<Account, String> tbcUud;

    @FXML
    private TableColumn<Account, String> tbcVerificationCode;

    @FXML
    private TextField txtCoin;

    @FXML
    private TextField txtCountLoginDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullname;

    @FXML
    private ComboBox<String> cbbGender;

    @FXML
    private ComboBox<String> cbbRole;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPathImage;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtUud;

    @FXML
    private TextField txtVerificationCode;

    @FXML
    void onClickAdd(MouseEvent event) {
        if (btnAdd.getText().equals("Add")) {
            hide();
            btnAdd.setText("Cancel");
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
            dbCreateDate.setValue(LocalDate.now());
            dbLatestLoginDate.setValue(LocalDate.now());
            txtCountLoginDate.setText("1");
            dbCreateDate.setDisable(true);
            dbLatestLoginDate.setDisable(true);
            txtCountLoginDate.setDisable(true);
            txtVerificationCode.setDisable(true);
            txtUud.setDisable(true);
        } else {
            btnAdd.setText("Add");
            btnSave.setDisable(true);
            dbDob.setDisable(true);
            presently();
            setNull();
        }
    }

    @FXML
    void onClickDelete(MouseEvent event) {
        AccountDao accountDao = new AccountDao();
        Account acc = tbViewAccount.getSelectionModel().getSelectedItem();
        accountDao.delete(acc);
        initialize();
        btnAdd.setDisable(false);
        setNull();
    }

    @FXML
    void onClickEdit(MouseEvent event) {
        if (btnEdit.getText().equals("Edit")) {
            btnEdit.setText("Back");
            hide();
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            txtVerificationCode.setDisable(true);
            txtUud.setDisable(true);
            dbCreateDate.setDisable(true);
            dbLatestLoginDate.setDisable(true);
            txtCountLoginDate.setDisable(true);
            btnSave.setDisable(false);
        } else {
            btnEdit.setText("Edit");
            presently();
            btnAdd.setDisable(false);
            btnEdit.setDisable(true);
            btnSave.setDisable(true);
            setNull();
        }
    }

    @FXML
    void onClickSave(MouseEvent event) {
        if (btnAdd.getText().equals("Cancel")) {
            AccountDao accountDao = new AccountDao();
            Account acc = new Account();
            acc.setIdAccount(GenerateID.genAccount());
            acc.setUsername(txtUsername.getText());
            acc.setPassword(BCrypt.withDefaults().hashToString(12, txtPassword.getText().toCharArray()));
            acc.setEmail(txtEmail.getText());
            acc.setPathImage(txtPathImage.getText());
            acc.setFullName(txtFullname.getText());
            acc.setDob(java.sql.Date.valueOf(dbDob.getValue()));
            acc.setGender(cbbGender.getValue().equals("Male"));
            acc.setCoin(Integer.parseInt(txtCoin.getText()));
            if (cbbRole.getValue().equals("Admin")) {
                acc.setIdRole("RL1");
            } else {
                if (cbbRole.getValue().equals("User")) {
                    acc.setIdRole("RL2");
                } else {
                    acc.setIdRole("RL3");
                }
            }
            acc.setRank(new Rank("RK1"));
            acc.setAccountLevel(1);
            acc.setLevelExp(0);
            acc.setLevelCap(500);
            accountDao.add(acc);
            btnAdd.setText("Add");
            btnAdd.setDisable(false);
            btnEdit.setDisable(false);
        } else {
            AccountDao accountDao = new AccountDao();
            Account acc = tbViewAccount.getSelectionModel().getSelectedItem();
            acc.setUsername(txtUsername.getText());
            if (acc.getPassword() != null) {
                if (acc.getPassword().equals(txtPassword.getText())) {
                    acc.setPassword(txtPassword.getText());
                } else {
                    acc.setPassword(BCrypt.withDefaults().hashToString(12, txtPassword.getText().toCharArray()));
                }
            } else {
                acc.setPassword(BCrypt.withDefaults().hashToString(12, txtPassword.getText().toCharArray()));
            }
            acc.setEmail(txtEmail.getText());
            acc.setPathImage(txtPathImage.getText());
            acc.setFullName(txtFullname.getText());
            acc.setDob(java.sql.Date.valueOf(dbDob.getValue()));
            acc.setGender(cbbGender.getValue().equals("Male"));
            acc.setCoin(Integer.parseInt(txtCoin.getText()));
            if (cbbRole.getValue().equals("Admin")) {
                acc.setIdRole("RL1");
            } else {
                if (cbbRole.getValue().equals("User")) {
                    acc.setIdRole("RL2");
                } else {
                    acc.setIdRole("RL3");
                }
            }
            accountDao.update(acc);
            btnEdit.setText("Edit");
            btnAdd.setDisable(false);
        }
        initialize();
        presently();
        setNull();
    }

    public void initialize() {
        presently();
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.getAll();
        BindingDataToTableViewAccount(accounts);
        ObservableList<String> gt = FXCollections.observableArrayList();
        gt.add("Male");
        gt.add("Female");
        cbbGender.setItems(gt);
        ObservableList<String> gt1 = FXCollections.observableArrayList();
        gt1.add("Admin");
        gt1.add("User");
        gt1.add("Guest");
        cbbRole.setItems(gt1);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void BindingDataToTableViewAccount(List<Account> logs) {
        ObservableList<Account> list = FXCollections.observableList(logs);
        tbcUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        tbcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbcCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tbcLatestLoginDate.setCellValueFactory(new PropertyValueFactory<>("latestLoginDate"));
        tbcCountLoginDate.setCellValueFactory(new PropertyValueFactory<>("countLoginDate"));
        tbcPathImage.setCellValueFactory(new PropertyValueFactory<>("pathImage"));
        tbcFullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tbcDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tbcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tbcCoin.setCellValueFactory(new PropertyValueFactory<>("coin"));
        tbcVerificationCode.setCellValueFactory(new PropertyValueFactory<>("verificationCode"));
        tbcUud.setCellValueFactory(new PropertyValueFactory<>("uud"));
        tbcRole.setCellValueFactory(new PropertyValueFactory<>("idRole"));
        tbViewAccount.setItems(list);
    }

    @FXML
    void selectionChanged(MouseEvent event) {
        try {
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
            btnAdd.setDisable(true);
            Account list = tbViewAccount.getSelectionModel().getSelectedItem();
            if (list != null) {
                txtUsername.setText(list.getUsername());
                if (list.getPassword() != null) {
                    txtPassword.setText(list.getPassword());
                } else {
                    txtPassword.setText("");
                }
                if (list.getEmail() != null) {
                    txtEmail.setText(list.getEmail());
                } else {
                    txtEmail.setText("");
                }
                if (list.getCreateDate() != null) {
                    dbCreateDate.setValue(LocalDate.parse(list.getCreateDate().toString()));
                } else {
                    dbCreateDate.setValue(null);
                }
                if (list.getLatestLoginDate() != null) {
                    dbLatestLoginDate.setValue(LocalDate.parse(list.getLatestLoginDate().toString()));
                } else {
                    dbLatestLoginDate.setValue(null);
                }
                if (list.getCountLoginDate() != null) {
                    txtCountLoginDate.setText(list.getCountLoginDate() + "");
                } else {
                    txtCountLoginDate.setText("");
                }
                if (list.getPathImage() != null) {
                    txtPathImage.setText(list.getPathImage());
                } else {
                    txtPathImage.setText("");
                }
                if (list.getFullName() != null) {
                    txtFullname.setText(list.getFullName());
                } else {
                    txtFullname.setText("");
                }
                if (list.getDob() != null) {
                    dbDob.setValue(LocalDate.parse(list.getDob().toString()));
                } else {
                    dbDob.setValue(null);
                }
                if (list.isGender() != null) {
                    cbbGender.setValue(list.isGender() ? "Male" : "Female");
                } else {
                    cbbGender.setValue(null);
                }
                if (list.getCoin() != null) {
                    txtCoin.setText(list.getCoin() + "");
                } else {
                    txtCoin.setText("");
                }
                if (list.getVerificationCode() != null) {
                    txtVerificationCode.setText(list.getVerificationCode());
                } else {
                    txtVerificationCode.setText("");
                }
                if (list.getUud() != null) {
                    txtUud.setText(list.getUud());
                } else {
                    txtUud.setText("");
                }
                String idrole = list.getIdRole();
                if (idrole.equals("RL1")) {
                    cbbRole.getSelectionModel().select("Admin");
                } else {
                    if (idrole.equals("RL2")) {
                        cbbRole.getSelectionModel().select("User");
                    } else {
                        cbbRole.getSelectionModel().select("Guest");
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    @FXML
    void onClickChooseImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);
        String path = fileChooser.showOpenDialog(btnChooseImage.getScene().getWindow()).getPath();
        txtPathImage.setText(path);
    }

    private void configuringFileChooser(FileChooser fileChooser) {

        // Set tiêu đề cho FileChooser
        fileChooser.setTitle("Select Text File");


        // Sét thư mục bắt đầu nhìn thấy khi mở FileChooser
        fileChooser.setInitialDirectory(new File("C:/"));


        // Thêm các bộ lọc file vào
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG ", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

    public void presently() {
        txtUsername.setEditable(false);
        txtPassword.setEditable(false);
        txtEmail.setEditable(false);
        dbCreateDate.setEditable(false);
        dbLatestLoginDate.setEditable(false);
        txtCountLoginDate.setEditable(false);
        txtPathImage.setEditable(false);
        txtFullname.setEditable(false);
        dbDob.setEditable(false);
        cbbGender.setEditable(false);
        txtCoin.setEditable(false);
        txtVerificationCode.setEditable(false);
        txtUud.setEditable(false);
        cbbRole.setEditable(false);
        btnChooseImage.setDisable(true);
    }

    public void hide() {
        txtUsername.setEditable(true);
        txtPassword.setEditable(true);
        txtEmail.setEditable(true);
        dbCreateDate.setEditable(true);
        dbLatestLoginDate.setEditable(true);
        txtCountLoginDate.setEditable(true);
        txtPathImage.setEditable(true);
        txtFullname.setEditable(true);
        dbDob.setEditable(true);
        cbbGender.setEditable(true);
        txtCoin.setEditable(true);
        txtVerificationCode.setEditable(true);
        txtUud.setEditable(true);
        cbbRole.setEditable(true);
        btnChooseImage.setDisable(false);
    }

    private void setNull() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtPathImage.setText("");
        dbLatestLoginDate.setValue(LocalDate.now());
        dbCreateDate.setValue(LocalDate.now());
        txtFullname.setText("");
        dbDob.setValue(LocalDate.now());
        ObservableList<String> gt = FXCollections.observableArrayList();
        gt.add("");
        cbbGender.setItems(gt);
        txtCoin.setText("");
        txtVerificationCode.setText("");
        txtUud.setText("");
        cbbRole.setItems(gt);
    }
}
