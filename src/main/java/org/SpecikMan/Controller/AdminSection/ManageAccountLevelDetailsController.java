package org.SpecikMan.Controller.AdminSection;


import com.sun.javafx.logging.PlatformLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.DAL.DetailsDao;
import org.SpecikMan.DAL.LevelDao;
import org.SpecikMan.Entity.Account;
import org.SpecikMan.Entity.AccountLevelDetails;
import org.SpecikMan.Entity.FilePath;
import org.SpecikMan.Entity.Level;
import org.SpecikMan.Tools.DisposeForm;
import org.SpecikMan.Tools.FileRW;
import org.SpecikMan.Tools.GenerateID;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;



public class ManageAccountLevelDetailsController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dpDatePlayed;

    @FXML
    private TableView<AccountLevelDetails> tbViewAccountLevelDetail;

    @FXML
    private TableColumn<AccountLevelDetails, String> tbcAccuracy;

    @FXML
    private TableColumn<AccountLevelDetails, String> tbcChartData;

    @FXML
    private TableColumn<AccountLevelDetails, Integer> tbcCorrect;

    @FXML
    private TableColumn<AccountLevelDetails, Date> tbcDatePlayed;

    @FXML
    private TableColumn<AccountLevelDetails, String> tbcIdAccount;

    @FXML
    private TableColumn<AccountLevelDetails, String> tbcIdLevel;

    @FXML
    private TableColumn<AccountLevelDetails, Integer> tbcScore;

    @FXML
    private TableColumn<AccountLevelDetails, String> tbcTimeLeft;

    @FXML
    private TableColumn<AccountLevelDetails, Float> tbcWpm;

    @FXML
    private TableColumn<AccountLevelDetails, Float> tbcWps;

    @FXML
    private TableColumn<AccountLevelDetails, Integer> tbcWrong;

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
        DetailsDao detailsDao= new DetailsDao();
        AccountLevelDetails acc = tbViewAccountLevelDetail.getSelectionModel().getSelectedItem();
        detailsDao.delete(acc);
        initialize();
        setNull();
    }

    @FXML
    void onClickEdit(MouseEvent event) {
        if(btnEdit.getText().equals("Edit")){
            btnEdit.setText("Back");
            hide();
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
            dpDatePlayed.setDisable(false);
        } else {
            btnEdit.setText("Edit");
            presently();
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
            btnEdit.setDisable(true);
            setNull();
        }
    }

    @FXML
    void onClickSave(MouseEvent event) {
        DetailsDao detailsDao= new DetailsDao();
        AccountLevelDetails acc = tbViewAccountLevelDetail.getSelectionModel().getSelectedItem();
        acc.setScore(Long.parseLong(txtScore.getText()));
        acc.setTimeLeft(txtTimeLeft.getText());
        acc.setDatePlayed(Date.valueOf(dpDatePlayed.getValue()));
        acc.setLike(acc.getLike());
        acc.setWpm(Float.parseFloat(txtWpm.getText()));
        acc.setWps(Float.parseFloat(txtWps.getText()));
        acc.setCorrect(Integer.parseInt(txtCorrect.getText()));
        acc.setWrong(Integer.parseInt(txtWrong.getText()));
        acc.setAccuracy(txtAccuracy.getText());
        detailsDao.update(acc);
        btnEdit.setText("Edit");
        btnDelete.setDisable(false);
        initialize();
        presently();
        setNull();
    }

    @FXML
    void onCickTable(MouseEvent event){
        try{
            btnEdit.setDisable(false);
            btnDelete.setDisable(false);
            presently();
            dpDatePlayed.setDisable(true);

            AccountLevelDetails list = tbViewAccountLevelDetail.getSelectionModel().getSelectedItem();
            if(list.getScore()!=null){
                txtScore.setText(String.valueOf(list.getScore()));
            } else {
                txtScore.setText("");
            }
            if (list.getTimeLeft() != null) {
                txtTimeLeft.setText(list.getTimeLeft());
            } else {
                txtTimeLeft.setText("");
            }
            if(list.getDatePlayed()==null){
                String d = list.getDatePlayed().toString();
                dpDatePlayed.setValue(LocalDate.parse(d));
            } else {
                dpDatePlayed.setValue(null);
            }
            if(list.getWpm()==null){
                txtWpm.setText(String.valueOf(list.getWpm()));
            } else {
                txtWpm.setText("");
            }
            if(list.getWps()==null){
                txtWps.setText(String.valueOf(list.getWps()));
            } else {
                txtWps.setText("");
            }
            if(list.getCorrect()==null){
                txtCorrect.setText(String.valueOf(list.getCorrect()));
            } else {
                txtCorrect.setText("");
            }
            if(list.getWrong()==null){
                txtWrong.setText(String.valueOf(list.getWrong()));
            } else {
                txtWrong.setText("");
            }
            if(list.getAccuracy()==null){
                txtAccuracy.setText(String.valueOf(list.getAccuracy()));
            } else {
                txtAccuracy.setText("");
            }
            if(list.getChartData()==null){
                txtChartData.setText(String.valueOf(list.getChartData()));
            } else {
                txtChartData.setText("");
            }
        } catch(NullPointerException e){
            System.out.println();
        }

    }

    public void initialize() {
        DetailsDao details = new DetailsDao();
        List<AccountLevelDetails> detail = details.getAll();
        BindingDataToTableViewAccountLevelDetails(detail);

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void BindingDataToTableViewAccountLevelDetails(List<AccountLevelDetails> logs) {
        ObservableList<AccountLevelDetails> list = FXCollections.observableList(logs);
        tbcIdAccount.setCellValueFactory(new PropertyValueFactory<>("username"));
        tbcIdLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        tbcScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tbcTimeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
        tbcDatePlayed.setCellValueFactory(new PropertyValueFactory<>("datePlayed"));
        tbcWpm.setCellValueFactory(new PropertyValueFactory<>("wpm"));
        tbcWps.setCellValueFactory(new PropertyValueFactory<>("wps"));
        tbcCorrect.setCellValueFactory(new PropertyValueFactory<>("correct"));
        tbcWrong.setCellValueFactory(new PropertyValueFactory<>("wrong"));
        tbcAccuracy.setCellValueFactory(new PropertyValueFactory<>("accuracy"));
        tbcChartData.setCellValueFactory(new PropertyValueFactory<>("chartData"));
        tbViewAccountLevelDetail.setItems(list);
    }
    public void presently(){
        txtScore.setEditable(false);
        txtTimeLeft.setEditable(false);
        dpDatePlayed.setEditable(false);
        txtWpm.setEditable(false);
        txtWps.setEditable(false);
        txtCorrect.setEditable(false);
        txtWrong.setEditable(false);
        txtAccuracy.setEditable(false);
        txtChartData.setEditable(false);
    }
    public void hide() {
        txtScore.setEditable(true);
        txtTimeLeft.setEditable(true);
        dpDatePlayed.setEditable(true);
        txtWpm.setEditable(true);
        txtWps.setEditable(true);
        txtCorrect.setEditable(true);
        txtWrong.setEditable(true);
        txtAccuracy.setEditable(true);
        txtChartData.setEditable(true);
    }
    private void setNull(){
        txtScore.setText("");
        txtTimeLeft.setText("");
        dpDatePlayed.setValue(LocalDate.now());
        txtWpm.setText("");
        txtWps.setText("");
        txtCorrect.setText("");
        txtWrong.setText("");
        txtAccuracy.setText("");
        txtChartData.setText("");
    }
}
