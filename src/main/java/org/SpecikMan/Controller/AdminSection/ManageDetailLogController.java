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
import org.SpecikMan.DAL.DetailLogDao;
import org.SpecikMan.Entity.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManageDetailLogController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;


    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dpDatePlayed;

    @FXML
    private TableView<DetailLog> tbViewDetailLog;

    @FXML
    private TableColumn<DetailLog, String> tbcAccuracy;

    @FXML
    private TableColumn<DetailLog, String> tbcChartData;

    @FXML
    private TableColumn<DetailLog, Integer> tbcCorrect;

    @FXML
    private TableColumn<DetailLog, Date> tbcDatePlayed;

    @FXML
    private TableColumn<DetailLog, String> tbcLevelName;

    @FXML
    private TableColumn<DetailLog, Integer> tbcScore;

    @FXML
    private TableColumn<DetailLog, String> tbcTimeLeft;

    @FXML
    private TableColumn<DetailLog, Float> tbcWpm;

    @FXML
    private TableColumn<DetailLog, Float> tbcWps;

    @FXML
    private TableColumn<DetailLog, Integer> tbcWrong;

    @FXML
    private TableColumn<DetailLog, String> tbcPlayerName;

    @FXML
    private TableColumn<DetailLog, String> tbcPublisherName;

    @FXML
    private TextField txtAccuracy;

    @FXML
    private TextField txtChartData;

    @FXML
    private TextField txtCorrect;

    @FXML
    private TextField txtScore;

    @FXML
    private TextField txtTimeLeft;

    @FXML
    private TextField txtWpm;

    @FXML
    private TextField txtWps;

    @FXML
    private TextField txtWrong;

    @FXML
    void onClickDelete(MouseEvent event) {
        DetailLogDao detailLogDao = new DetailLogDao();
        DetailLog del = tbViewDetailLog.getSelectionModel().getSelectedItem();
        detailLogDao.delete(del);
        initialize();
        setNull();
    }

    @FXML
    void onClickEdit(MouseEvent event) {
        if(btnEdit.getText().equals("Edit")){
            btnEdit.setText("Cancel");
            hide();
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
        } else {
            btnEdit.setText("Edit");
            presently();
            btnEdit.setDisable(true);
            btnSave.setDisable(true);
            btnDelete.setDisable(true);
            setNull();
        }

    }

    @FXML
    void onClickSave(MouseEvent event) {
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        DetailLogDao detailLogDao = new DetailLogDao();
        DetailLog  detailLog = tbViewDetailLog.getSelectionModel().getSelectedItem();
        detailLog.setDatePlayed(java.sql.Date.valueOf(dpDatePlayed.getValue()));
        detailLog.setChartData(txtChartData.getText());
        detailLog.setWps(Float.parseFloat(txtWps.getText()));
        detailLog.setWpm(Float.parseFloat(txtWpm.getText()));
        detailLog.setTimeLeft(txtTimeLeft.getText());
        detailLog.setScore(Long.parseLong(txtScore.getText()));
        detailLog.setCorrect(Integer.parseInt(txtCorrect.getText()));
        detailLog.setWrong(Integer.parseInt(txtWrong.getText()));
        detailLog.setAccuracy(txtAccuracy.getText());
        detailLogDao.update(detailLog);
        btnDelete.setDisable(false);
        btnEdit.setText("Edit");
        initialize();
        presently();
        setNull();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        DetailLog log = tbViewDetailLog.getSelectionModel().getSelectedItem();
        txtTimeLeft.setText(log.getTimeLeft());
        txtScore.setText(String.valueOf(log.getScore()));
        txtWpm.setText(String.valueOf(log.getWpm()));
        txtWps.setText(String.valueOf(log.getWps()));
        txtCorrect.setText(String.valueOf(log.getCorrect()));
        txtWrong.setText(String.valueOf(log.getWrong()));
        txtAccuracy.setText(String.valueOf(log.getAccuracy()));
        txtChartData.setText(String.valueOf(log.getChartData()));
        String d = log.getDatePlayed().toString();
        dpDatePlayed.setValue(LocalDate.parse(d));

        presently();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(true);
    }

    public void initialize() {
        DetailLogDao detailLogDao = new DetailLogDao();
        List<DetailLog> detailLogs = detailLogDao.getAll();
        BindingDataToTableViewDetailLog(detailLogs);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }
    public void BindingDataToTableViewDetailLog(List<DetailLog> logs) {
        ObservableList<DetailLog> list = FXCollections.observableList(logs);
        tbcLevelName.setCellValueFactory(new PropertyValueFactory<>("levelName"));
        tbcPublisherName.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        tbcPlayerName.setCellValueFactory(new PropertyValueFactory<> ("playerName"));
        tbcScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tbcWpm.setCellValueFactory(new PropertyValueFactory<>("wpm"));
        tbcWps.setCellValueFactory(new PropertyValueFactory<>("wps"));
        tbcCorrect.setCellValueFactory(new PropertyValueFactory<>("correct"));
        tbcWrong.setCellValueFactory(new PropertyValueFactory<>("wrong"));
        tbcAccuracy.setCellValueFactory(new PropertyValueFactory<>("accuracy"));
        tbcTimeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
        tbcDatePlayed.setCellValueFactory(new PropertyValueFactory<>("datePlayed"));
        tbcChartData.setCellValueFactory(new PropertyValueFactory<>("chartData"));
        tbViewDetailLog.setItems(list);
    }
    public void presently() {
        dpDatePlayed.setEditable(false);
        txtTimeLeft.setEditable(false);
        txtScore.setEditable(false);
        txtWpm.setEditable(false);
        txtWps.setEditable(false);
        txtCorrect.setEditable(false);
        txtWrong.setEditable(false);
        txtAccuracy.setEditable(false);
        txtChartData.setEditable(false);
    }
    public void hide() {
        dpDatePlayed.setEditable(true);
        txtTimeLeft.setEditable(true);
        txtScore.setEditable(true);
        txtWpm.setEditable(true);
        txtWps.setEditable(true);
        txtCorrect.setEditable(true);
        txtWrong.setEditable(true);
        txtAccuracy.setEditable(true);
        txtChartData.setEditable(true);
    }
    private void setNull(){
        dpDatePlayed.setValue(LocalDate.now());
        txtTimeLeft.setText("");
        txtScore.setText("");
        txtWpm.setText("");
        txtWps.setText("");
        txtCorrect.setText("");
        txtWrong.setText("");
        txtAccuracy.setText("");
        txtChartData.setText("");
    }
}
