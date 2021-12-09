package org.SpecikMan.Controller.AdminSection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.SpecikMan.Tools.LoadForm;
import org.SpecikMan.Tools.ShowAlert;

import java.io.IOException;
import java.util.Objects;


public class HomeAdminController {
    @FXML
    private Button btnAccount;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnAccountLevelDetail;

    @FXML
    private Button btnDetailLog;

    @FXML
    private Button btnInvetory;

    @FXML
    private Button btnLevel;

    @FXML
    private Button btnShop;

    @FXML
    private Button btnSignout;

    @FXML
    private VBox pnl_scroll;

    @FXML
    private AnchorPane scenepane;

    @FXML
    private ImageView IMlogo;

    @FXML
    private ScrollPane scrollPane;


    @FXML
    void Clicklogo() throws IOException {
        initialize();
    }

    @FXML
    void onClickAccount() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageAccount.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickAccountLevelDetail() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageAccountLevelDetails.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickDetailLog() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageDetailLog.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickInvetory() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageInvetory.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickLevel() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageLevel.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickShop() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageShop.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    @FXML
    void onClickRankingLevel() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/RankingLevel.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
    }

    Stage stage;
    @FXML
    void onClickSignOut() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Logout ");
        alert.setHeaderText("Bạn có muốn đăng xuất ?");
        alert.setContentText("Đăng Xuất");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenepane.getScene().getWindow();
            ShowAlert.show("Warning!", "Đăng xuất thành công");
            stage.close();
            LoadForm.load("/fxml/AdminFXMLs/LoginAdmin.fxml","Login!!!",false);
        }
    }

    public void initialize() throws IOException {
        pnl_scroll.getChildren().clear();
        Node[] nodes = new Node[1];
        nodes[0] = (Node) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Report.fxml")));
        pnl_scroll.getChildren().add(nodes[0]);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}
