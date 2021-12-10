package org.SpecikMan.Controller.AdminSection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.SpecikMan.DAL.FeedbackDao;
import org.SpecikMan.Entity.Feedback;
import org.SpecikMan.Tools.ShowAlert;

import java.sql.Date;
import java.util.List;

public class FeedbackController {

    @FXML
    private Button btnDelete;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbUsername;

    @FXML
    private TextArea taDetail;

    @FXML
    private TableColumn<Feedback, Date> tcDate;

    @FXML
    private TableColumn<Feedback, String> tcDetail;

    @FXML
    private TableColumn<Feedback, String> tcUsername;

    @FXML
    private TableView<Feedback> tvFeedback;

    @FXML
    void btnDeleteClicked(MouseEvent event) {
        FeedbackDao fbDao = new FeedbackDao();
        Feedback fb = tvFeedback.getSelectionModel().getSelectedItem();
        fbDao.delete(fb);
        ShowAlert.show("Notice", "Delete Success");
        initialize();
    }

    @FXML
    void selectionChanged(MouseEvent event) {
        Feedback fb = tvFeedback.getSelectionModel().getSelectedItem();
        if(fb!=null){
            lbDate.setText("Date:"+fb.getSubmitDate());
            lbUsername.setText("Username:"+fb.getUsername());
            taDetail.setText(fb.getSubmitDetail());
        }
    }

    public void initialize(){
        FeedbackDao fbDao = new FeedbackDao();
        BindingDataToTable(fbDao.getAll());
    }

    public void BindingDataToTable(List<Feedback> fbs) {
        ObservableList<Feedback> list = FXCollections.observableList(fbs);
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        tcDetail.setCellValueFactory(new PropertyValueFactory<>("submitDetail"));
        tvFeedback.setItems(list);
    }
}
