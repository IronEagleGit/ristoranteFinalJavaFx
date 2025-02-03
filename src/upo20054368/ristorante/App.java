package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Classe di esecuzione della simulazione del ristorante.
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 *
 */

public class App extends Application {


    @FXML
    public Label saveNameLabel;
    @FXML
    public Button aggiungiCliente;
    @FXML
    public Button cercaCLienteDaId;
    @FXML
    public Button cercaClientiDaTimeFrame;
    @FXML
    TextField saveFileInput;
    @FXML
    Button loadSaveButton;

    @Override
    public void start(Stage popupStage) {
        AtomicReference<Ristorante> rModel = new AtomicReference<>(new Ristorante());
        AnchorPane popupSaveInput;
        popupStage = new Stage();

        //loading save file
        try {

            FXMLLoader loaderSave = new FXMLLoader(getClass().getResource("LoadSave.fxml"));
            popupSaveInput= loaderSave.load();
            App app = loaderSave.getController();
            loadSaveButton= app.loadSaveButton;
            saveFileInput=app.saveFileInput;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }


        Scene popupScene = new Scene(popupSaveInput, 400, 267);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Inserisci il nome del file, o lascia vuoto per creare un nuovo ristorante");
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
        loadSaveButton.setOnAction(e->{

           rModel.set(gestioneAperturaSalvataggio(saveFileInput.getText()));
           Stage s= (Stage) loadSaveButton.getScene().getWindow();
           menu(rModel.get());
           s.close();


        });

        //ottenimento ristorante completato

    }

    public void menu(Ristorante rModel)
    {




        Stage primary_stage = new Stage();
        RistoranteView rView = new RistoranteView();
        RistoranteController rController = new RistoranteController(rModel, rView);
        Scene scene;
        AnchorPane mainMenu = new AnchorPane();
        try {


            FXMLLoader loader0 = new FXMLLoader(getClass().getResource("MainApp.fxml"));
            mainMenu = loader0.load();
            scene = new Scene(mainMenu, 400, 300);

            AnchorPane.setTopAnchor(mainMenu, 0.0);
            AnchorPane.setBottomAnchor(mainMenu, 0.0);
            AnchorPane.setLeftAnchor(mainMenu, 0.0);
            AnchorPane.setRightAnchor(mainMenu, 0.0);


            primary_stage.setScene(scene);
            primary_stage.setTitle("Ristorante Simulator");
            primary_stage.show();




            App app = loader0.getController();

            aggiungiCliente=app.aggiungiCliente;
            cercaCLienteDaId=app.cercaCLienteDaId;
            cercaClientiDaTimeFrame =app.cercaClientiDaTimeFrame;




        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        aggiungiCliente.setOnAction(e->{
            rController.inserisciClienteController(primary_stage, scene);

        });
        cercaCLienteDaId.setOnAction(e->{

            rController.ricercaClienteController(primary_stage,scene);

        });
        cercaClientiDaTimeFrame.setOnAction(e->{

           rController.ricercaEtaClienteController(primary_stage,scene);

        });


    }





    public static void menuApp()
    {
        System.out.println("Cosa vuoi fare?\nDigita il numero relativo all'opzione scelta");
        System.out.println("1 - Inserisci un cliente");
        System.out.println("2 - Cerca un cliente in base all'identificativo");
        System.out.println("3 - Cerca clienti in base all'età");
        System.out.println("4 - Aggiungi un ordine");
        System.out.println("5 - Controlla le statistiche dei piatti");
        System.out.println("6 - Controlla le statistiche dei menù");
        System.out.println("7 - Opzioni ordine");
        System.out.println("8 - Salva ristorante attuale");
        System.out.println("9 - Salva ristorante come nuovo salvataggio");
        System.out.println("10 - Elimina un cliente");
        System.out.println("100 - Esci dall'applicazione");


    }
    public static void menuOpzioni(){

        System.out.println("Che operazione sugli ordini vuoi fare?");


    }

    public static void opzioni(RistoranteController rController)
    {
        int scelta=0;
        Scanner input= new Scanner(System.in);
        while(scelta !=100)
        {
            menuOpzioni();
            try{
                scelta=input.nextInt();
                System.out.println();
            }catch(InputMismatchException e){System.out.println("Errore nell'input di scelta");input.nextLine();}
            eseguiOpzioniMenu(scelta, rController);

        }

    }
    public static void esegui(int scelta, Ristorante rModel, RistoranteController rController, String fileName)
    {
        switch (scelta){

            case 1:
                //rController.inserisciClienteController();

                break;
            case 2:
                //rController.ricercaClienteController();

                break;
            case 3:
                //rController.ricercaEtaClienteController();

                break;
            case 4:
                rController.aggiungiUnOrdineController();

                break;
            case 5:
                rController.statisticheNumeroPiattiController();

                break;
            case 6:
                rController.statisticheTipoMenuController();

                break;
            case 7:
                opzioni(rController);

                break;

            case 8:
                boolean check=false;
                if(!fileName.isBlank())
                {
                    check =gestioneSalvataggio(fileName, rModel);
                }
                if(!check) System.out.println("Errore nel salvataggio del ristorante");
                break;
            case 9:

                String inputFile = inputNuovoSalvataggio();
                boolean check2=true;
                if(!inputFile.isBlank())
                {
                    check2 = gestioneSalvataggio(inputFile,rModel);
                }else check2=false;
                if(!check2) System.out.println("Errore nel salvataggio del ristorante");
                break;
            case 100:
                System.out.println("Uscita dal programma in corso...");
                break;
            default:
                System.out.println("Opzione non accettata, digitare nuovamente");
        }


    }

    public static void eseguiOpzioniMenu(int scelta2, RistoranteController rController)
    {
        switch(scelta2){

            case 100:

                break;

            case 1:
                rController.getOrdineDaDataController();
                break;

            case 2:
                rController.getListaOrdiniDaTipoMenuController();
                break;

            case 3:
                rController.getListaOrdiniDaNumeroPiattiController();
                break;

            case 4:
                rController.getListaOrdiniDaTimeFrameController();
                break;

            case 5:
                rController.getNumberOfOrdersController();
                break;

            case 6:
                rController.cancellaDaTimeFrameController();
                break;

            case 7:
                rController.cancellaDaTipoMenuController();
                break;

            case 8:
                rController.cancellaDaNumeroPiattiController();
                break;


        }

    }

    public static Ristorante gestioneAperturaSalvataggio(String nomeFile)
    {
        FileInputStream f;
        Ristorante r= new Ristorante();
        if(!nomeFile.isBlank()) {
            try {
                String fileName = "saves/"+nomeFile+".DAT";

                f = new FileInputStream(fileName);
                ObjectInputStream fileObject = new ObjectInputStream(f);

                r = (Ristorante) fileObject.readObject();
            } catch (IOException e) {
                System.out.println("File assente/non accessibile");
            } catch (Exception e) {
                System.out.println("Errore");
            }
        }
        return r;

    }

    public static boolean gestioneSalvataggio(String fileName, Ristorante r)
    {

        System.out.println(fileName);
        boolean check=true;
        try {
            FileOutputStream f = new FileOutputStream("saves/"+fileName+".DAT");

            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(r);

        } catch (IOException e) {check=false;}
            catch(Exception e){check=false;}


        return check;
    }

    public static String inputNuovoSalvataggio()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il nome del nuovo salvataggio in input.");

        return input.nextLine();


    }
}


