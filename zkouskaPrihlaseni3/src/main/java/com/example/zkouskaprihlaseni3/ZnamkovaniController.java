package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ZnamkovaniController extends HelloApplication implements Initializable {

    //Buttons
    @FXML
    private Button button_odhlasitSe;
    @FXML
    private Button button_pridatZnamku;
    @FXML
    private Button button_odebratZnamku;
    @FXML
    private Button button_zmenitZnamku;
    @FXML
    private Button button_aktualizovat;


    //tabulka pro zaznam znamek
    @FXML
    private TableView<StudentZnamkaZaznam> table_users;

    //tabulka prumerna znamka
    @FXML
    private TableView<PrumernaZnamka> table_prumernaZnamka;


    //ComboBox pro vybrani tridy a predmetu
    @FXML
    private ComboBox<String> cmbox_trida;
    @FXML
    private ComboBox<String> cmbox_predmet;


    //pro ostatni tridyControllery
    static String tridaComboBox; //trida
    static String predmetComboBox;//predmet
    static String usernameUcitel; //ucitel

    //data pro tridu zmenitZnamkuController
    static int znamkaIDzmenitZnamku;
    static String testzmenitZnamku;
    static String krestnizmenitZnamku;
    static String prijmenizmenitZnamku;
    static int znamkazmenitZnamku;
    static int hodnotazmenitZnamku;

    String  SQL_statement;

    public Stage stagePraceSeZnamkou; //tato promenna ma vyuziti pri zavreni oken-pridat znamku, zmenit znamku, odebrat znamku

    //ArrayList trid, ktere uci vybrany ucitel, tridy jsou nactene z MySQL databaze
    private ArrayList<String> tridy = new ArrayList<>();

    //diky teto metode muzeme prenest table zaznamu znamek do jine tridy napr OdebratZnamkuController
    public TableView getTable(){
        return table_users;
    }
    public Stage getStage(){
        return stagePraceSeZnamkou;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //prepise usernameUcitel
        usernameUcitel = PrihlaseniController.ucitelUsernamePrihlaseni;

        //nacte existujici tridy pro tento username a nacte je do arraylistu tridy
        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("select trida_jmeno from trida_predmet_ucitel tpu" +
                        " JOIN tridy t  ON (tpu.trida_id=t.trida_id) " +
                        "JOIN ucitele u ON(tpu.ucitel_id=u.ucitel_id)" +
                        " WHERE username = \"" + usernameUcitel + "\"");
            while(resultSet.next()) {
                String tridaJmeno = resultSet.getString("trida_jmeno");
                tridy.add(tridaJmeno);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //array tridy nacte do comboboxu tridy, ze ktereho se vybere ke ktere tride to chceme nacist
        cmbox_trida.getItems().addAll(tridy);

        //diky tomu dostaneme string tridy z comboboxu pro tridy
        cmbox_trida.setOnAction(actionEvent->{
             tridaComboBox = cmbox_trida.getValue();
             cmbox_predmet.getItems().clear();

             //nacteme predmety pro daneho ucitele pro danou tridu, deje se podobne to co u nacitani tridy
           try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT predmet_nazev FROM trida_predmet_ucitel tpu" +
                    " JOIN predmety p ON (tpu.predmet_id=p.predmet_id) " +
                    "JOIN tridy t ON (tpu.trida_id=t.trida_id) " +
                    "JOIN ucitele u ON (tpu.ucitel_id=u.ucitel_id)" +
                    " WHERE t.trida_jmeno =\"" + tridaComboBox + "\" AND u.username =\"" + usernameUcitel+"\"");

            ArrayList<String> predmety = new ArrayList<>();
            while(resultSet.next()){
                String predmetJmeno = resultSet.getString("predmet_nazev");
                predmety.add(predmetJmeno);
            }
               cmbox_predmet.getItems().addAll(predmety);

            }catch (Exception e){
                e.printStackTrace();
            }
        });
        //dostaneme dany predmet
        cmbox_predmet.setOnAction(actionEvent->{
            predmetComboBox =cmbox_predmet.getValue();
        });
            button_aktualizovat.setOnAction(actionEvent -> {
        //nacteni zaznamu znamek do tabulky pro zaznamy znamek
                //musime refreshnout celou tabulku - vymazeme jeji obsah
                table_users.getColumns().clear();
                table_users.getItems().clear();

            //vytvorime si kolonky, ktere chceme v tabulce mit a u kazde nastavime jaky parametr z objektu StudentZnamkaZaznam dostanou
                //pomoci teto kolonky program pozna unikatni id zaznamu a muze s nim pracovat jednodussi cestou
                TableColumn tableColumnId = new TableColumn("id");
                tableColumnId.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, Integer>("id"));
                tableColumnId.setVisible(false);

                TableColumn tableColumnPrijmeni = new TableColumn("Příjmení");
                tableColumnPrijmeni.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, String>("prijmeni"));

                TableColumn tableColumnKrestni = new TableColumn("Křestní");
                tableColumnKrestni.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, String>("krestni"));

                TableColumn tableColumnTest = new TableColumn("Test");
                tableColumnTest.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, String>("test"));

                TableColumn tableColumnZnamka = new TableColumn("Znamka");
                tableColumnZnamka.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, Integer>("znamka"));

                TableColumn tableColumnHodnota = new TableColumn("Hodnota");
                tableColumnHodnota.setCellValueFactory(new PropertyValueFactory<StudentZnamkaZaznam, Integer>("hodnota"));

                //pridame kolonky do tabulky pro zaznamy
                table_users.getColumns().addAll(tableColumnId, tableColumnPrijmeni,tableColumnKrestni,tableColumnTest, tableColumnZnamka, tableColumnHodnota);


                ArrayList<StudentZnamkaZaznam> studentiTabulka = new ArrayList<>();
                try {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection connection = connectionClass.getConnection();
                    Statement statement = connection.createStatement();

                    // nactu tabulku vsech znamek
                    SQL_statement="SELECT zaznamZnamky_id, krestniJmeno, prijmeni, test, znamka, hodnota FROM zaznamyznamek zz\n" +
                            "JOIN studenti s ON(zz.student_id=s.student_id)\n" +
                            "JOIN trida_predmet_ucitel tpu ON (zz.id_trida_predmet_ucitel=tpu.id_trida_predmet_ucitel)\n" +
                            "JOIN tridy t ON (tpu.trida_id = t.trida_id)\n" +
                            "JOIN predmety p ON (tpu.predmet_id = p.predmet_id)\n" +
                            "JOIN ucitele u ON (tpu.ucitel_id = u.ucitel_id)\n" +
                            "WHERE trida_jmeno = \""+ tridaComboBox +"\" AND predmet_nazev = \""+ predmetComboBox +"\" AND username = \""+usernameUcitel+"\"" +
                            " ORDER BY s.prijmeni, s.krestniJmeno, zz.hodnota desc, zz.test, zz.znamka";

                    ResultSet resultSet = statement.executeQuery(SQL_statement);

                    while (resultSet.next()) {
                        int id = resultSet.getInt("zaznamZnamky_id");
                        String surName = resultSet.getString("prijmeni");
                        String firstName = resultSet.getString("krestniJmeno");
                        String test = resultSet.getString("test");
                        int znamka = resultSet.getInt("znamka");
                        int hodnota = resultSet.getInt("hodnota");

                        studentiTabulka.add(new StudentZnamkaZaznam(id,surName,firstName,test, znamka,hodnota));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //vlozi data do tabulky
                for (int i = 0; i < studentiTabulka.size(); i++) {
                    ObservableList<StudentZnamkaZaznam> students1 = table_users.getItems();
                    students1.add(studentiTabulka.get(i));
                    table_users.setItems(students1);
                }


        //vytvoreni tabulky pro prumernou znamku
                // musime refreshnout celou tabulku - vymazeme jeji obsah
                table_prumernaZnamka.getColumns().clear();
                table_prumernaZnamka.getItems().clear();



                //vytvorime si kolonky, ktere chceme v tabulce mit a u kazde nastavime jaky parametr z objektu PrumernaZnamka dostanou
                TableColumn tableColumnPrijmeniPZ = new TableColumn("Příjmení");
                tableColumnPrijmeniPZ.setCellValueFactory(new PropertyValueFactory<PrumernaZnamka, String>("prijmeni"));

                TableColumn tableColumnKrestniPZ = new TableColumn("Křestní");
                tableColumnKrestniPZ.setCellValueFactory(new PropertyValueFactory<PrumernaZnamka, String>("krestni"));

                TableColumn tableColumnPrumernaZnamka = new TableColumn("Průměr");
                tableColumnPrumernaZnamka.setCellValueFactory(new PropertyValueFactory<PrumernaZnamka, Double>("znamkaPrumer"));

                table_prumernaZnamka.getColumns().addAll(tableColumnPrijmeniPZ, tableColumnKrestniPZ,tableColumnPrumernaZnamka);

                ArrayList<PrumernaZnamka> studentiPrumer = new ArrayList<>();
                //z databaze nacte ke kazdemu studentocvi z vybrane tridy a predmetu jejich cele jmeno a prumernou znamku
                try {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection connection = connectionClass.getConnection();
                    Statement statement = connection.createStatement();
                    SQL_statement = "SELECT s.prijmeni, s.krestniJmeno, ROUND(sum(hodnota*znamka)/sum(hodnota),2) AS znamka " +
                            "FROM zaznamyznamek zn " +
                            "JOIN studenti s ON (s.student_id=zn.student_id)" +
                            "JOIN trida_predmet_ucitel tpu ON (zn.id_trida_predmet_ucitel=tpu.id_trida_predmet_ucitel)" +
                            "JOIN tridy t ON (tpu.trida_id = t.trida_id)" +
                            "JOIN predmety p ON (tpu.predmet_id = p.predmet_id)" +
                            "JOIN ucitele u ON (tpu.ucitel_id = u.ucitel_id)" +
                            "WHERE t.trida_jmeno = \""+tridaComboBox+"\" AND p.predmet_nazev = \""+predmetComboBox+"\" AND u.username = \""+usernameUcitel+"\"" +
                            " group by s.prijmeni, s.krestniJmeno " +
                            "ORDER BY s.prijmeni, s.krestniJmeno";

                    ResultSet resultSet = statement.executeQuery(SQL_statement);

                    while (resultSet.next()) {
                        String prijmeni = resultSet.getString("prijmeni");
                        String krestni = resultSet.getString("krestniJmeno");
                        double znamka = resultSet.getDouble("znamka");

                        studentiPrumer.add(new PrumernaZnamka(prijmeni,krestni,znamka));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //tyto data pote vlozi do tabulky pro prumerne znamky
                for (int i = 0; i < studentiPrumer.size(); i++) {
                    ObservableList<PrumernaZnamka> students2 = table_prumernaZnamka.getItems();
                    students2.add(studentiPrumer.get(i));
                    table_prumernaZnamka.setItems(students2);
                }
            });

        //nastaveni tlacitka pridat znamku
            button_pridatZnamku.setOnAction(actionEvent->{
                //nacte jakou scenu - FXML soubour budeme chtit zobrazit
                //scena zmakovani by mela zustat, objevi se male okno pro praci se znamkovanim
                FXMLLoader loader = new FXMLLoader(getClass().getResource("pridatZnamku.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PridatZnamkuController pridatZnamkuController = loader.getController();
                //tride PridatZnamkuController nastavime parent jakozto tuto tridu
                pridatZnamkuController.setParentController(this);
                Stage primaryStage = new Stage();
                stagePraceSeZnamkou = primaryStage;
                pridatZnamkuController.getStage(primaryStage);
                primaryStage.setTitle("Pridat známku");
                primaryStage.setScene(new Scene(root,270,290));


                primaryStage.initModality(Modality.WINDOW_MODAL);
                primaryStage.initOwner(button_pridatZnamku.getScene().getWindow());
                primaryStage.show();
            });
    //nastaveni tlacitka zmenit znamku
        button_zmenitZnamku.setOnAction(actionEvent->{
            //diky radce 298-304 program vi, o jaky zaznam znamky se jedna a zjisti potrebne informace o zaznamu
            //informace pak ulozi do pomocnych promennych
            StudentZnamkaZaznam selectedItems = (StudentZnamkaZaznam) table_users.getSelectionModel().getSelectedItems().get(0);
             znamkaIDzmenitZnamku = selectedItems.id;
             testzmenitZnamku = selectedItems.test;
             krestnizmenitZnamku = selectedItems.krestni;
             prijmenizmenitZnamku = selectedItems.prijmeni;
             znamkazmenitZnamku = selectedItems.znamka;
             hodnotazmenitZnamku = selectedItems.hodnota;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("zmenitZnamku.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ZmenitZnamkuController zmenitZnamkuController = loader.getController();
            zmenitZnamkuController.setParentController(this);
            Stage primaryStage = new Stage();
            stagePraceSeZnamkou = primaryStage;
            zmenitZnamkuController.getStage(primaryStage);
            primaryStage.setTitle("Změnit známku");
            primaryStage.setScene(new Scene(root,300,280));


            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initOwner(button_zmenitZnamku.getScene().getWindow());
            primaryStage.show();
        });

        //nastaveni tlacitka odebrat znamku
            button_odebratZnamku.setOnAction(actionEvent->{

               FXMLLoader loader = new FXMLLoader(getClass().getResource("odebratZnamku.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OdebratZnamkuController odebratZnamkuController = loader.getController();
                odebratZnamkuController.setParentController(this);
                Stage primaryStage = new Stage();
                stagePraceSeZnamkou = primaryStage;

                odebratZnamkuController.getStage(primaryStage);
                primaryStage.setTitle("Odebrat známku");
                primaryStage.setScene(new Scene(root,250,200));



                primaryStage.initModality(Modality.WINDOW_MODAL);
                primaryStage.initOwner(button_odebratZnamku.getScene().getWindow());
                primaryStage.show();
            });
        // vraceni na scenu kde se prihlasuju
        button_odhlasitSe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrihlaseniController.ucitelUsernamePrihlaseni = null;
              DBUtils.changeScene(event, "prihlaseni.fxml", "Přihlášení");
            }
        });

    }




}
