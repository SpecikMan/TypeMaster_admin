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
import javafx.scene.control.ComboBox;
import org.SpecikMan.DAL.AccountDao;
import org.SpecikMan.DAL.InventoryDao;
import org.SpecikMan.DAL.ShopDao;
import org.SpecikMan.Entity.Account;
import javafx.scene.control.TextField;
import org.SpecikMan.Entity.Inventory;
import org.SpecikMan.Entity.Shop;
import org.SpecikMan.Tools.GenerateID;

import java.util.List;



public class ManageInvetoryController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Inventory> tbViewInventory;

    @FXML
    private TableColumn<Inventory, Integer> tbcCurrentlyHave;

    @FXML
    private TableColumn<Inventory, String> tbcIdAccount;

    @FXML
    private TableColumn<Inventory, String> tbcIdItem;

    @FXML
    private TextField txtIdAccount;

    @FXML
    private ComboBox<String> cbbIdItem;

    @FXML
    private TextField txtCurrentlyHave;

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
        InventoryDao inventoryDao = new InventoryDao();
        Inventory inventory = tbViewInventory.getSelectionModel().getSelectedItem();
        inventoryDao.delete(inventory);
        initialize();
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
            btnDelete.setDisable(true);
            btnSave.setDisable(true);
            btnEdit.setDisable(true);
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        Inventory list = tbViewInventory.getSelectionModel().getSelectedItem();
        ShopDao shopDao = new ShopDao();
        List<Shop> shopList = shopDao.getAll();
        String item = String.valueOf(list.getItem());
        Shop sp = null;
        for (Shop i : shopList) {
            if (i.getItemName().trim().equals(item.trim())) {
                sp = i;
            }
        }
        assert sp != null;
        cbbIdItem.getSelectionModel().select(sp.getItemName());
        txtIdAccount.setText(String.valueOf(list.getIdAccount()));
        txtCurrentlyHave.setText(String.valueOf(list.getCurrentlyHave()));
        presently();
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
        btnSave.setDisable(true);
    }

    @FXML
    void onClickSave(MouseEvent event) {
        if (btnAdd.getText().equals("Cancel")){
            InventoryDao inventoryDao= new InventoryDao();
            ShopDao shopDao = new ShopDao();
            List<Shop> shopList = shopDao.getAll();
            Inventory inventory = new Inventory();
            inventory.setIdInventory(GenerateID.genInventory());
            inventory.setIdAccount(txtIdAccount.getText());
            String item = cbbIdItem.getValue();
            Shop sp = null;
            for (Shop i : shopList) {
                if (i.getItemName().trim().equals(item.trim())) {
                    sp = i;
                }
            }
            assert sp != null;
            inventory.setItem(sp);
            inventory.setCurrentlyHave(Integer.parseInt(txtCurrentlyHave.getText()));
            inventoryDao.add(inventory);
            btnAdd.setText("Add");
            btnAdd.setDisable(false);
            btnEdit.setDisable(false);
        }
        else{
            InventoryDao inventoryDao= new InventoryDao();
            ShopDao shopDao = new ShopDao();
            List<Shop> shopList = shopDao.getAll();
            Inventory inventory = tbViewInventory.getSelectionModel().getSelectedItem();
            inventory.setIdAccount(txtIdAccount.getText());
            String item = cbbIdItem.getValue();
            Shop sp = null;
            for (Shop i : shopList) {
                if (i.getItemName().trim().equals(item.trim())) {
                    sp = i;
                }
            }
            assert sp != null;
            inventory.setItem(sp);
            inventory.setCurrentlyHave(Integer.parseInt(txtCurrentlyHave.getText()));
            inventoryDao.update(inventory);
            btnEdit.setText("Edit");
            btnAdd.setDisable(false);
        }
        initialize();
        presently();
    }
    public void initialize() {
        InventoryDao inventoryDao = new InventoryDao();
        List<Inventory> inventorys = inventoryDao.getAll();
        BindingDataToTableViewInvetory(inventorys);

        ObservableList<String> gt = FXCollections.observableArrayList();
        gt.add("Shield Lv1");
        gt.add("Shield Lv2");
        gt.add("Shield Lv3");
        gt.add("Immune Lv1");
        gt.add("Immune Lv2");
        gt.add("Immune Lv3");
        gt.add("Stopwatch Lv1");
        gt.add("Stopwatch Lv2");
        gt.add("Time Lv1");
        gt.add("Time Lv2");
        gt.add("Time Lv3");
        gt.add("Time Flow");
        gt.add("X2 Correct");
        gt.add("X3 Correct");
        gt.add("Combo Master");
        gt.add("Combo Inquisitor");
        gt.add("Combo Champion");
        gt.add("Perfect Run");
        gt.add("Great Run");
        gt.add("Just a normal day");
        gt.add("Speed Master");
        gt.add("Speed Inquisitor");
        gt.add("Speed God");
        cbbIdItem.setItems(gt);
        btnAdd.setDisable(false);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
    }

    public void BindingDataToTableViewInvetory(List<Inventory> logs) {
        ObservableList<Inventory> list = FXCollections.observableList(logs);
        tbcIdAccount.setCellValueFactory(new PropertyValueFactory<>("idAccount"));
        tbcIdItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tbcCurrentlyHave.setCellValueFactory(new PropertyValueFactory<>("currentlyHave"));
        tbViewInventory.setItems(list);
    }

    public void presently(){
        txtIdAccount.setEditable(false);
        cbbIdItem.setEditable(false);
        txtCurrentlyHave.setEditable(false);
    }
    public void hide() {
        txtIdAccount.setEditable(true);
        cbbIdItem.setEditable(true);
        txtCurrentlyHave.setEditable(true);
    }
}
