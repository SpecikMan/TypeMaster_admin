package org.SpecikMan.Controller.AdminSection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.DAL.LevelDao;
import org.SpecikMan.Entity.Account;
import org.SpecikMan.Entity.Difficulty;
import org.SpecikMan.Entity.Level;
import org.SpecikMan.Entity.Mode;
import org.SpecikMan.Tools.GenerateID;
import org.SpecikMan.Tools.ShowAlert;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManageLevelController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dbCreateDate;

    @FXML
    private DatePicker dbUpdateDate;

    @FXML
    private TableView<Level> tbViewLevel;

    @FXML
    private TableColumn<Level, Date> tbcCreateDate;

    @FXML
    private TableColumn<Level, String> tbcIdDifficulty;

    @FXML
    private TableColumn<Level, String> tbcIdPublisher;

    @FXML
    private TableColumn<Level, String> tbcIdMode;

    @FXML
    private TableColumn<Level, String> tbcLevelContent;

    @FXML
    private TableColumn<Level, String> tbcNameLevel;

    @FXML
    private TableColumn<Level, Integer> tbcNumLike;

    @FXML
    private TableColumn<Level, String> tbcTime;

    @FXML
    private TableColumn<Level, Integer> tbcTotalWords;

    @FXML
    private TableColumn<Level, Date> tbcUpdateDate;

    @FXML
    private ComboBox<String> cbbDifficulty;

    @FXML
    private ComboBox<String> cbbMode;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtLevelContent;

    @FXML
    private TextField txtNameLevel;

    @FXML
    private TextField txtNumLike;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtTotalWords;


    @FXML
    void onClickAdd(MouseEvent event) {
        if (btnAdd.getText().equals("Add")) {
            hide();
            btnAdd.setText("Cancel");
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
            dbCreateDate.setValue(LocalDate.now());
            dbUpdateDate.setValue(LocalDate.now());
        } else {
            btnAdd.setText("Add");
            presently();
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
            btnAdd.setDisable(false);
            setNull();
        }
    }

    @FXML
    void onClickDelete(MouseEvent event) {
        LevelDao levelDao = new LevelDao();
        Level level = tbViewLevel.getSelectionModel().getSelectedItem();
        levelDao.delete(level);
        initialize();
        setNull();
    }

    @FXML
    void onClickEdit(MouseEvent event) {
        if (btnEdit.getText().equals("Edit")) {
            btnEdit.setText("Back");
            hide();
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
        } else {
            btnEdit.setText("Edit");
            presently();
            btnAdd.setDisable(false);
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            btnSave.setDisable(true);
            setNull();
        }
    }


    @FXML
    void onClickSave(MouseEvent event) {
        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.getAll();
        if(txtNameLevel.getText().isEmpty() ||  txtNameLevel.getText().isEmpty() || txtLevelContent.getText().isEmpty() ||
                txtTotalWords.getText().isEmpty() || txtTime.getText().isEmpty() || txtPublisher.getText().isEmpty())
        {
            ShowAlert.show("Warning!", "Please fill out the form");
        }
        if (btnAdd.getText().equals("Cancel")) {
            LevelDao levelDao = new LevelDao();
            Level acc = new Level();
            acc.setIdLevel(GenerateID.genLevel());
            acc.setNameLevel(txtNameLevel.getText());
            acc.setNumLike(Integer.parseInt(txtNumLike.getText()));
            acc.setCreateDate(java.sql.Date.valueOf(dbCreateDate.getValue()));
            acc.setUpdatedDate(java.sql.Date.valueOf(dbUpdateDate.getValue()));
            acc.setTime(txtTime.getText());
            acc.setLevelContent(txtLevelContent.getText());
            acc.setTotalWords(Integer.parseInt(txtTotalWords.getText()));
            String difficulty = cbbDifficulty.getValue();
            Difficulty difficulty1 = new Difficulty();
            if (difficulty.equals("Easy")) {
                difficulty1.setIdDifficulty("DF1");
            } else if (difficulty.equals("Normal")) {
                difficulty1.setIdDifficulty("DF2");
            } else {
                difficulty1.setIdDifficulty("DF3");
            }
            acc.setDifficulty(difficulty1);
            String modes = cbbMode.getValue();
            Mode mode1 = new Mode();
            switch (modes) {
                case "Ranked":
                    mode1.setIdMode("MD5");
                    break;
                case "Instant Death":
                    mode1.setIdMode("MD2");
                    break;
                case "Blackout":
                    mode1.setIdMode("MD3");
                    break;
                case "Hidden":
                    mode1.setIdMode("MD4");
                    break;
                default:
                    mode1.setIdMode("MD1");
                    break;
            }
            acc.setMode(mode1);
            Account account = null;
            for (Account i : accounts) {
                if (i.getUsername().trim().equals(txtPublisher.getText().trim())) {
                    account = i;
                }
            }
            assert account != null;
            acc.setIdAccount(account.getIdAccount());
            levelDao.add(acc);
            btnAdd.setText("Add");
            btnEdit.setDisable(false);
        } else {
            LevelDao levelDao = new LevelDao();
            Level acc = tbViewLevel.getSelectionModel().getSelectedItem();
            acc.setNameLevel(txtNameLevel.getText());
            acc.setNumLike(Integer.parseInt(txtNumLike.getText()));
            acc.setCreateDate(java.sql.Date.valueOf(dbCreateDate.getValue()));
            acc.setTime(txtTime.getText());
            acc.setUpdatedDate(java.sql.Date.valueOf(dbUpdateDate.getValue()));
            acc.setLevelContent(txtLevelContent.getText());
            acc.setTotalWords(Integer.parseInt(txtTotalWords.getText()));
            String difficulty = cbbDifficulty.getValue();
            Difficulty difficulty1 = new Difficulty();
            if (difficulty.equals("Easy")) {
                difficulty1.setIdDifficulty("DF1");
            } else if (difficulty.equals("Normal")) {
                difficulty1.setIdDifficulty("DF2");
            } else {
                difficulty1.setIdDifficulty("DF3");
            }
            acc.setDifficulty(difficulty1);
            String modes = cbbMode.getValue();
            Mode mode1 = new Mode();
            switch (modes) {
                case "Ranked":
                    mode1.setIdMode("MD5");
                    break;
                case "Instant Death":
                    mode1.setIdMode("MD2");
                    break;
                case "Blackout":
                    mode1.setIdMode("MD3");
                    break;
                case "Hidden":
                    mode1.setIdMode("MD4");
                    break;
                default:
                    mode1.setIdMode("MD1");
                    break;
            }
            acc.setMode(mode1);
            Account account = null;
            for (Account i : accounts) {
                if (i.getUsername().trim().equals(txtPublisher.getText().trim())) {
                    account = i;
                }
            }
            assert account != null;
            acc.setIdAccount(account.getIdAccount());
            levelDao.update(acc);
            btnEdit.setText("Edit");
            btnAdd.setDisable(false);
        }
        btnDelete.setDisable(false);
        initialize();
        presently();
        setNull();
    }

    public void initialize() {
        LevelDao levelDao = new LevelDao();
        List<Level> levels = levelDao.getAll();
        BindingDataToTableViewAccount(levels);

        ObservableList<String> gt1 = FXCollections.observableArrayList();
        gt1.add("Easy");
        gt1.add("Normal");
        gt1.add("Hard");
        cbbDifficulty.setItems(gt1);

        ObservableList<String> gt2 = FXCollections.observableArrayList();
        gt2.add("Normal");
        gt2.add("Instant Death");
        gt2.add("Blackout");
        gt2.add("Hidden");
        gt2.add("Ranked");
        cbbMode.setItems(gt2);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }


    @FXML
    void obClicktable(MouseEvent event) {
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnEdit.setDisable(false);
        Level list = tbViewLevel.getSelectionModel().getSelectedItem();
        txtNameLevel.setText(list.getNameLevel());
        txtNumLike.setText(String.valueOf(list.getNumLike()));
        String d = list.getCreateDate().toString();
        dbCreateDate.setValue(LocalDate.parse(d));
        d = list.getUpdatedDate().toString();
        dbUpdateDate.setValue(LocalDate.parse(d));
        txtLevelContent.setText(list.getLevelContent());
        txtTotalWords.setText(String.valueOf(list.getTotalWords()));
        String idDifficulty = String.valueOf(list.getDifficulty());
        if (idDifficulty.equals("Easy")) {
            cbbDifficulty.getSelectionModel().select("Easy");
        } else if (idDifficulty.equals("Normal")) {
            cbbDifficulty.getSelectionModel().select("Normal");
        } else {
            cbbDifficulty.getSelectionModel().select("Hard");
        }
        String mode = String.valueOf(list.getMode());
        System.out.println(mode);
        if (mode.equals("Normal"))
        {
            cbbMode.getSelectionModel().select("Normal");
        }
        else {
            if(mode.equals("Instant Death"))
            {
                cbbMode.getSelectionModel().select("Instant Death");
            }
            else {
                if(mode.equals("Blackout"))
                {
                    cbbMode.getSelectionModel().select("Blackout");
                }
                else {
                    if(mode.equals("Hidden"))
                    {
                        cbbMode.getSelectionModel().select("Hidden");
                    }
                    else {
                        cbbMode.getSelectionModel().select("Ranked");
                    }
                }
            }
        }
        txtTime.setText(list.getTime());
        txtPublisher.setText(list.getUsername());
    }

    public void BindingDataToTableViewAccount(List<Level> logs) {
        ObservableList<Level> list = FXCollections.observableList(logs);
        tbcNameLevel.setCellValueFactory(new PropertyValueFactory<>("nameLevel"));
        tbcNumLike.setCellValueFactory(new PropertyValueFactory<>("numLike"));
        tbcCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tbcUpdateDate.setCellValueFactory(new PropertyValueFactory<>("updatedDate"));
        tbcLevelContent.setCellValueFactory(new PropertyValueFactory<>("levelContent"));
        tbcTotalWords.setCellValueFactory(new PropertyValueFactory<>("totalWords"));
        tbcIdDifficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        tbcIdMode.setCellValueFactory(new PropertyValueFactory<>("mode"));
        tbcIdPublisher.setCellValueFactory(new PropertyValueFactory<>("idAccount"));
        tbcTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tbViewLevel.setItems(list);
    }

    public void presently() {
        txtNameLevel.setEditable(false);
        txtNumLike.setEditable(false);
        dbUpdateDate.setEditable(false);
        txtLevelContent.setEditable(false);
        txtTotalWords.setEditable(false);
        dbCreateDate.setEditable(false);
        txtTime.setEditable(false);
        cbbDifficulty.setEditable(false);
        cbbMode.setEditable(false);
        txtPublisher.setEditable(false);
    }

    public void hide() {
        txtNameLevel.setEditable(true);
        txtNumLike.setEditable(true);
        dbUpdateDate.setEditable(true);
        txtLevelContent.setEditable(true);
        txtTotalWords.setEditable(true);
        dbCreateDate.setEditable(true);
        txtTime.setEditable(true);
        cbbDifficulty.setEditable(true);
        cbbMode.setEditable(true);
        txtPublisher.setEditable(true);
    }
    private void setNull(){
        txtNameLevel.setText(null);
        txtNameLevel.setText(null);
        dbUpdateDate.setValue(null);
        txtLevelContent.setText(null);
        txtTotalWords.setText(null);
        dbCreateDate.setValue(null);
        txtTime.setText(null);
        cbbMode.setValue(null);
        cbbDifficulty.setValue(null);
        txtPublisher.setText(null);
        txtNumLike.setText(null);
    }
}
