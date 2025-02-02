package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.Year;
import java.time.LocalDate;


/**
 * Classe model (paradigma MVC) che rappresenta un ristorante.
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 *
 */
public class Ristorante implements Serializable {

    public List<Cliente> listaClienti = new ArrayList<>();
    public List<String> idLista =  new ArrayList<>();
    int numRecord;
    private static Scanner input = new Scanner(System.in);
    public Ristorante()
    {
        this.listaClienti=new ArrayList<>();
        this.idLista= new ArrayList<>();
        this.numRecord=0;

    }





    public Cliente ricercaClienteModel(String iden)
    {


        int i=0;

        while(listaClienti.get(i).getId().compareTo(iden)!=0&&i<numRecord)
        {
            i=i+1;

        }

        if(i<numRecord) {


            return listaClienti.get(i);

        }

        return null;



    }

    public List<Cliente> ricercaEtaClienteModel(int etaMin, int etaMax)
    {


        List<Cliente> listaOut = new ArrayList<>();



        int i=0;
        while(i<listaClienti.size())
        {
            int etaCalc= calcolaEta(listaClienti.get(i).getAnnoNascita());
            if((etaCalc>=etaMin)&&(etaCalc<=etaMax))
            {
                listaOut.add(listaClienti.get(i));

            }
            i=i+1;
        }




        return listaOut;


    }

    private int calcolaEta(int annoNasc)
    {

        int year = Year.now().getValue();
        return year - annoNasc;

    }




    private void checkId(String id)
    {
        if(idLista.contains(id)) {throw new IllegalArgumentException();}

    }

    public boolean inserisciClienteModel(String ideInser, int annoNasInser, LocalDate giornoReg)
    {


        try
        {
            checkId(ideInser);
            Cliente c= new Cliente(ideInser,annoNasInser,giornoReg);
            idLista.add(ideInser);
            listaClienti.add(c);

            numRecord++;
            return true;
        }catch(IllegalArgumentException e)
        {
            System.out.println("Errore:inserisci nuovo cliente.");
            return false;
        }

    }

    public boolean inserisciClienteModel(int annoNasInser, LocalDate giornoReg)
    {

        try
        {



            Cliente c= new Cliente(annoNasInser,giornoReg);
            String idC=c.getId();
            while(idLista.contains(idC))
            {
                c= new Cliente(annoNasInser,giornoReg);
            }

            idLista.add(idC);
            listaClienti.add(c);
            numRecord++;
            return true;
        }catch(IllegalArgumentException e)
        {
            System.out.println("Errore:inserisci nuovo cliente.");
            return false;
        }
    }

    public boolean aggiungiUnOrdineModel(String iden, LocalDate giornoOrdine,int numPiat, int tipMenu)
    {
        Cliente c= ricercaClienteModel(iden);
        if(c!=null)
        {

            c.addOrdine(giornoOrdine,numPiat,tipMenu);
            return true;
        }
        return false;

    }

    public int[] statisticheNumeroPiattiModel()
    {

        int[] arr=new int[4];
        int i=0;
        int j=0;
        int n=0;
        int somma=0;
        int max=0;
        int min=0;
        int media=0;
        try{
            Cliente c=listaClienti.getFirst();

            int sizeId = listaClienti.size();

            while(j<sizeId&&max==0)
            {

                if(!c.listaOrdini.isEmpty()){
                    max= listaClienti.get(j).listaOrdini.getFirst().getNumeroPiatti();
                    min= listaClienti.get(j).listaOrdini.getFirst().getNumeroPiatti();
                }
                else j++;

            }


            while (j < sizeId) {
                c = listaClienti.get(j);
                int sizeC = c.listaOrdini.size();
                while (i < sizeC) {

                    int numeroPiatti = c.listaOrdini.get(i).getNumeroPiatti();


                    somma = somma + numeroPiatti;
                    n++;

                    if (numeroPiatti > max) {
                        max = numeroPiatti;
                    }

                    if (numeroPiatti < min) {
                        min = numeroPiatti;
                    }


                    i++;

                }

                j++;
                i = 0;

                }



        }catch(NoSuchElementException e){System.out.println("Lista clienti vuota");}


        if(somma!=0) {
            arr[0]=max;
            arr[1]=min;
            arr[2]=somma;
            arr[3]=n;

        }else System.out.println("Nessun ordine eseguito!");
        return arr;
    }

    public int[] statisticheTipoMenuModel()
    {
        int[] arr =new int[3];
        int i=0;
        int j=0;
        int Piccolo=0;
        int Medio=0;
        int Grande=0;

        int sizeId=listaClienti.size();
        while(j < sizeId)
        {
            Cliente c=listaClienti.get(j);
            int sizeC=c.listaOrdini.size();
            while(i < sizeC) {

                switch (c.listaOrdini.get(i).getTipoMenu()) {

                    case 1:
                        Piccolo++;
                        break;
                    case 2:
                        Medio++;
                        break;
                    case 3:
                        Grande++;
                        break;
                    default:
                        break;
                }

                i++;

            }

            j++;
            i=0;

        }

        if(!(Piccolo==0)||!(Medio==0)||!(Grande==0)) {
            arr[0]=Piccolo;
            arr[1]=Medio;
            arr[2]=Grande;

        }
        return arr;
    }

    public float calcolaMediaModel(int somma, int numero)
    {
        return (float) somma/numero;
    }

    public List<Ordine> getOrdineDaDataModel(String ident, LocalDate data)
    {
        Cliente c = ricercaClienteModel(ident);
        if(c!=null) return c.getOrdineDaData(data);
        else throw new NoSuchElementException();

    }


    public List<Ordine> getListaOrdiniDaTimeFrameModel(String ident, LocalDate dataMin, LocalDate dataMax)
    {
        Cliente c = ricercaClienteModel(ident);
        if(c!=null) return c.getListaOrdiniDaTimeFrame(dataMin,dataMax);
        else throw new NoSuchElementException();
    }

    public List<Ordine> getListaOrdiniDaTipoMenuModel(String ident, int tipoMenu)
    {
        Cliente c = ricercaClienteModel(ident);
        if(c!=null) return c.getListaOrdiniDaTipoMenu(tipoMenu);
        else throw new NoSuchElementException();
    }
    public int getNumberOfOrdersModel(String ident)
    {
        Cliente c = ricercaClienteModel(ident);
        if(c!=null) return c.getNumberOfOrders();
        else throw new NoSuchElementException();
    }

    public void cancellaDaTimeFrameModel(String ident, LocalDate minDat, LocalDate maxDat)
    {
        Cliente c = ricercaClienteModel(ident);
        if(c!=null) c.cancellaDaTimeFrame(minDat,maxDat);
        else throw new NoSuchElementException();

    }

    public void cancellaDaTipoMenuModel(String ident, int tipoMenu)
    {

        Cliente c = ricercaClienteModel(ident);
        if(c!=null) c.cancellaDaTipoMenu(tipoMenu);
        else throw new NoSuchElementException();

    }
    public void cancellaDaNumeroPiattiModel(String ident, int numPiat)
    {

        Cliente c = ricercaClienteModel(ident);
        if(c!=null) c.cancellaDaNumeroPiatti(numPiat);
        else throw new NoSuchElementException();

    }


}
