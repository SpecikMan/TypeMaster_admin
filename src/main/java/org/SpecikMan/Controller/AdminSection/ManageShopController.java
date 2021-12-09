package org.SpecikMan.Controller.AdminSection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.DAL.LevelDao;
import org.SpecikMan.DAL.ShopDao;
import org.SpecikMan.Entity.Account;
import org.SpecikMan.Entity.Level;
import org.SpecikMan.Entity.Shop;
import org.SpecikMan.Tools.DisposeForm;
import org.SpecikMan.Tools.GenerateID;

import java.util.List;

public class ManageShopController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Shop> tbViewShop;

    @FXML
    private TableColumn<Shop, String> tbcDescription;

    @FXML
    private TableColumn<Shop, String> tbcItemName;

    @FXML
    private TableColumn<Shop, Integer> tbcCost;

    @FXML
    private TableColumn<Shop, Integer> tbcMaxLimit;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtMaxLimit;

    @FXML
    void onClickAdd(MouseEvent event) {
        if (btnAdd.getText().equals("Add"))
        {
            hide();
            btnAdd.setText("Cancel");
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            btnSave.setDisable(false);
        }
        else {
            btnAdd.setText("Add");
            presently();
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            btnAdd.setDisable(false);
            btnSave.setDisable(true);
        }
    }

    @FXML
    void onClickDelete(MouseEvent event) {
        ShopDao shopDao = new ShopDao();
        Shop shop = tbViewShop.getSelectionModel().getSelectedItem();
        shopDao.delete(shop);
        initialize();
    }

    @FXML
    void onClickEdit(MouseEvent event) {
        if(btnEdit.getText().equals("Edit")){
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
        }
    }

    @FXML
    void onClickSave(MouseEvent event) {
        ShopDao shopDao = new ShopDao();
        if (btnAdd.getText().equals("Cancel")){
            Shop shop = new Shop();
            shop.setIdItem(GenerateID.genShop());
            shop.setItemName(txtItemName.getText());
            shop.setDescription(txtDescription.getText());
            shop.setCost(Integer.parseInt(txtCost.getText()));
            shop.setMaxLimit(Integer.parseInt(txtMaxLimit.getText()));
            shopDao.add(shop);
            btnAdd.setText("Add");
            btnEdit.setDisable(false);
        }
        else{
            Shop shop = tbViewShop.getSelectionModel().getSelectedItem();
            shop.setItemName(txtItemName.getText());
            System.out.println(txtItemName.getText());
            System.out.println(shop.getItemName());
            shop.setDescription(txtDescription.getText());
            shop.setCost(Integer.parseInt(txtCost.getText()));
            shop.setMaxLimit(Integer.parseInt(txtMaxLimit.getText()));
            shopDao.update(shop);
            btnEdit.setText("Edit");
            btnAdd.setDisable(false);
        }
        btnDelete.setDisable(false);
        initialize();
        presently();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
        btnSave.setDisable(true);
        Shop shop = tbViewShop.getSelectionModel().getSelectedItem();
        txtItemName.setText(shop.getItemName());
        txtDescription.setText(shop.getDescription());
        txtMaxLimit.setText(String.valueOf(shop.getMaxLimit()));
        txtCost.setText(String.valueOf(shop.getCost()));
        presently();
    }

    public void initialize() {
        ShopDao shopDao = new ShopDao();
        List<Shop> shops = shopDao.getAll();
        BindingDataToTableViewAccount(shops);
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        btnAdd.setDisable(false);
    }

    public void BindingDataToTableViewAccount(List<Shop> logs) {
        ObservableList<Shop> list = FXCollections.observableList(logs);
        tbcItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbcCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tbcMaxLimit.setCellValueFactory(new PropertyValueFactory<>("maxLimit"));
        tbViewShop.setItems(list);
    }

    public void presently(){
        txtItemName.setEditable(false);
        txtDescription.setEditable(false);
        txtCost.setEditable(false);
        txtMaxLimit.setEditable(false);
    }
    public void hide() {
        txtItemName.setEditable(true);
        txtDescription.setEditable(true);
        txtCost.setEditable(true);
        txtMaxLimit.setEditable(true);
    }
}
