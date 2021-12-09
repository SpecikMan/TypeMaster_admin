package org.SpecikMan.Controller.AdminSection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.SpecikMan.DAL.RankingLevelDao;
import org.SpecikMan.Entity.RankingLevel;
import org.SpecikMan.Tools.GenerateID;
import org.SpecikMan.Tools.ShowAlert;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class RankingLevelController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;

    @FXML
    private TextArea taRound1;

    @FXML
    private TextArea taRound2;

    @FXML
    private TextArea taRound3;

    @FXML
    private TableColumn<RankingLevel, String> tcContent1;

    @FXML
    private TableColumn<RankingLevel, String> tcContent2;

    @FXML
    private TableColumn<RankingLevel, String> tcContent3;

    @FXML
    private TableColumn<RankingLevel, Date> tcCreateDate;

    @FXML
    private TableColumn<RankingLevel, Date> tcFrom;

    @FXML
    private TableColumn<RankingLevel, String> tcTime1;

    @FXML
    private TableColumn<RankingLevel, String> tcTime2;

    @FXML
    private TableColumn<RankingLevel, String> tcTime3;

    @FXML
    private TableColumn<RankingLevel, Date> tcTo;

    @FXML
    private TableView<RankingLevel> tvRL;

    @FXML
    private TextField txtTime1;

    @FXML
    private TextField txtTime2;

    @FXML
    private TextField txtTime3;

    @FXML
    void onClickAdd() {
        if (btnAdd.getText().equals("Cancel")) {
            btnAdd.setText("Add");
            setDisable(true);
            defaultButton();
            tvRL.setDisable(false);
        } else {
            btnAdd.setText("Cancel");
            setDisable(false);
            defaultButton();
            btnSave.setDisable(false);
            tvRL.setDisable(true);
        }
    }

    @FXML
    void onClickDelete() {
        RankingLevelDao rkDao = new RankingLevelDao();
        rkDao.delete(tvRL.getSelectionModel().getSelectedItem());
        RankingLevelDao rkDao2 = new RankingLevelDao();
        BindingData(rkDao2.getAll());
    }

    @FXML
    void onClickEdit() {
        if (btnEdit.getText().equals("Cancel")) {
            btnEdit.setText("Edit");
            setDisable(true);
            defaultButton();
            btnEdit.setDisable(false);
            tvRL.setDisable(false);
        } else {
            btnEdit.setText("Cancel");
            setDisable(false);
            defaultButton();
            btnEdit.setDisable(false);
            btnSave.setDisable(false);
            tvRL.setDisable(true);
        }
    }

    @FXML
    void onClickSave() {
        try {
            if (btnAdd.getText().equals("Cancel")) {
                if (isMonday(Date.valueOf(dpFrom.getValue()))) {
                    RankingLevelDao rkDao = new RankingLevelDao();
                    RankingLevel rk = new RankingLevel();
                    rk.setIdRankingLevel(GenerateID.genRankingLevel());
                    rk.setCreateDate(Date.valueOf(LocalDate.now()));
                    rk.setFromRankPeriod(Date.valueOf(dpFrom.getValue()));
                    rk.setToRankPeriod(Date.valueOf(dpTo.getValue()));
                    rk.setLevelContent1(taRound1.getText().trim());
                    rk.setLevelContent2(taRound2.getText().trim());
                    rk.setLevelContent3(taRound3.getText().trim());
                    rk.setTime1(txtTime1.getText().trim());
                    rk.setTime2(txtTime2.getText().trim());
                    rk.setTime3(txtTime3.getText().trim());
                    rkDao.add(rk);
                    btnAdd.setText("Add");
                    setDisable(true);
                    btnSave.setDisable(true);
                    setNull();
                    RankingLevelDao rkDao2 = new RankingLevelDao();
                    BindingData(rkDao2.getAll());
                } else {
                    ShowAlert.show("Warning!", "From field only accepts Mondays");
                }
            } else {
                try {
                    RankingLevelDao rkDao = new RankingLevelDao();
                    RankingLevel rk = tvRL.getSelectionModel().getSelectedItem();
                    rk.setFromRankPeriod(Date.valueOf(dpFrom.getValue()));
                    rk.setToRankPeriod(Date.valueOf(dpTo.getValue()));
                    rk.setLevelContent1(taRound1.getText().trim());
                    rk.setLevelContent2(taRound2.getText().trim());
                    rk.setLevelContent3(taRound3.getText().trim());
                    rk.setTime1(txtTime1.getText().trim());
                    rk.setTime2(txtTime2.getText().trim());
                    rk.setTime3(txtTime3.getText().trim());
                    rkDao.update(rk);
                    RankingLevelDao rkDao2 = new RankingLevelDao();
                    BindingData(rkDao2.getAll());
                    setNull();
                    btnEdit.setText("Edit");
                    btnSave.setDisable(true);
                    tvRL.setDisable(false);
                    setDisable(true);
                }catch (NullPointerException e) {
                System.out.println();
            }
            }
        }catch (NullPointerException e) {
            System.out.println();
        }
    }

    @FXML
    void selectionChanged() {
        if (tvRL.getSelectionModel().getSelectedItem() != null) {
            RankingLevel item = tvRL.getSelectionModel().getSelectedItem();
            txtTime1.setText(item.getTime1());
            txtTime2.setText(item.getTime2());
            txtTime3.setText(item.getTime3());
            taRound1.setText(item.getLevelContent1());
            taRound2.setText(item.getLevelContent2());
            taRound3.setText(item.getLevelContent3());
            dpFrom.setValue(item.getFromRankPeriod().toLocalDate());
            dpTo.setValue(item.getToRankPeriod().toLocalDate());
            btnAdd.setDisable(true);
            btnSave.setDisable(true);
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    void setDisable(boolean isD) {
        taRound1.setDisable(isD);
        taRound2.setDisable(isD);
        taRound3.setDisable(isD);
        txtTime1.setDisable(isD);
        txtTime2.setDisable(isD);
        txtTime3.setDisable(isD);
        dpFrom.setDisable(isD);
    }

    void defaultButton() {
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void initialize() {
        try {
            RankingLevelDao rkDao = new RankingLevelDao();
            setDisable(true);
            defaultButton();
            dpFrom.valueProperty().addListener((ov, oldValue, newValue) -> {
                LocalDate startThisWeek = newValue.with(DayOfWeek.MONDAY);
                LocalDate endThisWeek = startThisWeek.plusDays(6);
                dpTo.setValue(endThisWeek);
            });
            BindingData(rkDao.getAll());
        }catch (NullPointerException e) {
            System.out.println();
        }
    }

    public void setNull() {
        taRound1.setText(null);
        taRound2.setText(null);
        taRound3.setText(null);
        txtTime1.setText(null);
        txtTime2.setText(null);
        txtTime3.setText(null);
        dpFrom.setValue(null);
        dpTo.setValue(null);
    }

    boolean isMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public void BindingData(List<RankingLevel> rks) {
        ObservableList<RankingLevel> list = FXCollections.observableList(rks);
        tcCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tcFrom.setCellValueFactory(new PropertyValueFactory<>("fromRankPeriod"));
        tcTo.setCellValueFactory(new PropertyValueFactory<>("toRankPeriod"));
        tcContent1.setCellValueFactory(new PropertyValueFactory<>("levelContent1"));
        tcContent2.setCellValueFactory(new PropertyValueFactory<>("levelContent2"));
        tcContent3.setCellValueFactory(new PropertyValueFactory<>("levelContent3"));
        tcTime1.setCellValueFactory(new PropertyValueFactory<>("time1"));
        tcTime2.setCellValueFactory(new PropertyValueFactory<>("time2"));
        tcTime3.setCellValueFactory(new PropertyValueFactory<>("time3"));
        tvRL.setItems(list);
    }

}
