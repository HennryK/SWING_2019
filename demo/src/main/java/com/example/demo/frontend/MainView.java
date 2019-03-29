package com.example.demo.frontend;

import com.example.demo.backend.DB.entities.ShopCustomer;
import com.example.demo.backend.DB.entities.ShopCustomerOrder;
import com.example.demo.backend.DB.entities.ShopCustomerOrderGood;
import com.example.demo.backend.DB.entities.ShopProduct;
import com.example.demo.backend.DB.repositories.ShopCustomerOrderGoodRepository;
import com.example.demo.backend.DB.repositories.ShopCustomerOrderRepository;
import com.example.demo.backend.DB.repositories.ShopCustomerRepository;
import com.example.demo.backend.DB.repositories.ShopProductRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Route("")
public class MainView extends VerticalLayout {

    private Set<ShopProduct> listOfCheckedProducts;
    private int tmp = 0;

    private TextField customerNameInput = new TextField("Zadejte jméno zákazníka");
    private final ShopProductRepository productRepository;
    final Grid<ShopProduct> productGrid;

    private final ShopCustomerRepository customerRepository;
    private final ShopCustomerOrderRepository orderRepository;

    Dialog confirmOrderDialog = new Dialog();


    //TODO pole pro vytvoření doného zákazníka
    private Button createOrderButton = new Button("Vytvořit objednávku");
    private Button clearOrderButton = new Button("Smazat");
    private Button confirmOrderButton = new Button("Dokončit objednávku");


    public MainView(ShopProductRepository productRepository,
                    ShopCustomerRepository customerRepository,
                    ShopCustomerOrderRepository orderRepository,
                    ShopCustomerOrderGoodRepository goodRepository) {

        this.productRepository = productRepository;
        this.productGrid = new Grid<>();

        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;


        add(customerNameInput, productGrid, createOrderButton);

        listOfCustomer();
        listOfProducts();

        createOrderButton.addClickListener(event -> confOrder());
    }

    void confOrder(){
        listOfCheckedProducts = productGrid.getSelectedItems();

        confirmOrderDialog.add(new Label(customerNameInput.getValue()));
        for (ShopProduct listOfCheckedProduct : listOfCheckedProducts) {
            confirmOrderDialog.add(listOfCheckedProduct.getName());
            tmp += listOfCheckedProduct.getCost();
        }
        confirmOrderDialog.add(new Label("Konečná cena:" + tmp));
        confirmOrderDialog.setWidth("500px");
        confirmOrderDialog.setHeight("300px");
        confirmOrderDialog.add(confirmOrderButton);
        confirmOrderDialog.add(clearOrderButton);

        confirmOrderDialog.open();


        clearOrderButton.addClickListener(event -> clear());
        confirmOrderButton.addClickListener(event -> create());
    }

    private void create() {

    }

    void clear(){
        listOfCheckedProducts.clear();
        tmp = 0;
    }

    private void listOfCustomer() {
        //TODO Výpis již registrovaných uživatelů

    }


















    private void confirmOrder() {
        double costTmp = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime lcd = LocalDateTime.now();
        String date = lcd.format(dtf);


        String customerName = customerNameInput.getValue();


        //TODO uložení nové objednávky + do foru všechny zaškrnuté produkty
        for (ShopProduct listOfCheckedProduct : listOfCheckedProducts) {
            String name = listOfCheckedProduct.getName();
           // int cost = Integer.parseInt(listOfCheckedProduct.getCost());
            int quantity = 1;
            //costTmp += cost;

            customerRepository.save(new ShopCustomer(customerName,
                    new ShopCustomerOrder(date, costTmp,
                            new ShopCustomerOrderGood(name, quantity))));
            //orderRepository.save(costTmp, date, new ShopCustomerOrderGood(name, quantity));
        }
        customerRepository.save(new ShopCustomer(customerName));


    }

    private void listOfProducts() {
        productGrid.setItems(productRepository.findAll());

        productGrid.addColumn(ShopProduct::getName).setHeader("Název zboží");
        productGrid.addColumn(ShopProduct::getCost).setHeader("Cena zboží");

        productGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        productGrid.asMultiSelect().addSelectionListener(event -> {
            event.getValue();
        });
    }
}
