package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import java.time.Year;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.List;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * Classe che rappresenta un cliente per un ristorante.
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 */
public class Cliente implements Serializable{


    /**
     * Identificativo del cliente.
     */
    String id;
    /**
     * Anno di nascita
     */
    int annoNascita;
    /**
     * Data di registrazione
     */
    LocalDate giornoRegistr;
    /**
     * Lista di ordini (istanze della classe Ordine.java), utilizza java.util.List
     */
    List<Ordine> listaOrdini= new ArrayList<>();


    /**
     * Override del metodo toString della classe Object.
     *
     *
     * @return Restituisce le informazioni secondo questo formato: Id annoNascita giornoRegistr
     */
    @Override
    public String toString()
    {

        return id + "--" + annoNascita + "--" +giornoRegistr;

    }

    /**
     * Override del metodo equals della classe Object
     * @param o
     * Oggetto dato in ingresso
     * @return True se gli oggetti sono identici, false altrimenti
     */
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return (this.annoNascita==cliente.annoNascita)&&(this.giornoRegistr.isEqual(((Cliente) o).giornoRegistr));


    }

    /**
     * Costruttore di classe. Utilizza una stringa per l'identificativo.
     * @param idC
     * Identificativo del cliente dato in input al costruttore
     * @param annoNascitaC
     * Anno di nascita del cliente
     * @param giornoRegistC
     * Giorno di registrazione del cliente (usa java.util.LocalDate)
     */
    public Cliente(String idC, int annoNascitaC, LocalDate giornoRegistC)
    {

        if(idC.isEmpty() || idC.isBlank())
        {
            throw new IllegalArgumentException("Id non valido, creazione cliente fallita.");
        }


        if(annoNascitaC<0||annoNascitaC> Year.now().getValue())
        {
            throw new IllegalArgumentException("Anno negativo non accettato, anno dopo l'anno corrente non accettato, creazione cliente fallita.");
        }
        if(giornoRegistC==null||giornoRegistC.getYear()<annoNascitaC)
        {
            System.out.println("Data non idonea.");
            throw new IllegalArgumentException("Giorno di registrazione precedente all'anno di nascita, creazione cliente fallita.");
        }
        this.id = idC;

        this.annoNascita = annoNascitaC;
        this.giornoRegistr = giornoRegistC;

        

    }


    /**
     * Costruttore di classe. Genera casualmente un identificativo.
     * @param annoNascitaC
     * Anno di nascita del cliente
     * @param giornoRegistC
     * Giorno di registrazione del cliente (usa java.util.LocalDate)
     */
    public Cliente(int annoNascitaC, LocalDate giornoRegistC)
    {
        if(annoNascitaC<0||annoNascitaC> Year.now().getValue())
        {
            throw new IllegalArgumentException("Anno negativo non accettato, anno dopo l'anno corrente non accettato, creazione cliente fallita.");
        }
        if(giornoRegistC==null||giornoRegistC.getYear()<annoNascitaC)
        {
            System.out.println("Data non idonea.");
            throw new IllegalArgumentException("Giorno di registrazione precedente all'anno di nascita, creazione cliente fallita.");
        }
        SecureRandom rand = new SecureRandom();
        String idC =String.valueOf(rand.nextInt());

        this.id = idC;

        this.annoNascita = annoNascitaC;
        this.giornoRegistr = giornoRegistC;

    }

    /**
     * Imposta il giorno di nascita di un cliente già esistente.
     * @param annoNascitaInput
     * Nuovo anno di nascita da inserire.
     */
    public void setAnnoNascita(int annoNascitaInput)
    {
        this.annoNascita = annoNascitaInput;
    }

    /**
     * Imposta il giorno di registrazione di un cliente già esistente.
     * @param giornoRegistInput
     * Nuovo giorno di registrazione (utilizza java.util.LocalDate)
     */
    public void setGiornoRegistr(LocalDate giornoRegistInput)
    {
        this.giornoRegistr = giornoRegistInput;
    }

    /**
     * Restituisce l'identificativo del cliente.
     * @return id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Restiuisce l'anno di nascita del cliente.
     * @return annoNascita
     */
    public int getAnnoNascita()
    {
        return this.annoNascita;
    }

    /**
     * Restituisce il giorno di registrazione del cliente.
     * @return giornoRegistr
     */
    public LocalDate getGiornoRegistr()
    {
        return this.giornoRegistr;
    }

    /**
     * Aggiunge un ordine alla lista di ordini del cliente.
     * @param dataOrd
     * Data dell'ordine da inserire (utilizza java.util.LocalDate).
     * @param numPiat
     * Numero piatti ordinati.
     * @param tipoMen
     * Tipo di menu.
     * @return Boolean che rappresenta il successo dell'operazione di creazione dell'ordine.
     */
    public boolean addOrdine(LocalDate dataOrd, int numPiat, int tipoMen)
    {
        if(dataOrd.isBefore(giornoRegistr))
            throw new IllegalArgumentException("La data inserita è precedente a quella della registrazione.");
        if(numPiat<=0)
            throw new IllegalArgumentException("Il numero di piatti non può essere minore di 1.");
        if(tipoMen<1 || tipoMen>3)
            throw new IllegalArgumentException("Il numero di piatti può essere solo 1, 2, 3.");
        return listaOrdini.add(new Ordine(dataOrd, numPiat, tipoMen));
    }

    /**
     * Questo metodo aggiunge un ordine prendendone uno già esistente in input.
     * @param o Questo è un istanza della classe Ordine.java
     * @return Restituisce true se ha successo, false altrimenti.
     */
    public boolean addOrdine(Ordine o)
    {

        return this.listaOrdini.add(o);
    }

    /**
     * Restituisce la lista degli ordini del cliente.
     * @return Lista degli ordini del cliente.
     */
    public List<Ordine> getOrdine()
    {
        return listaOrdini;
    }
    /*
    public void setListaOrdini(List<Ordine> listaOrdini)
    {
        this.listaOrdini = listaOrdini;
    }
    **/

    /**
     * Cancella gli ordini in base al numero di piatti.
     * @param numeroPiat
     * Numero di piatti da dare in input.
     */
    public void cancellaDaNumeroPiatti(int numeroPiat)
    {
        int i=0;
        while(i<listaOrdini.size())
        {
            if(listaOrdini.get(i).getNumeroPiatti() == numeroPiat)
            {
                listaOrdini.remove(i);

            }else i++;
        }
    }

    /**
     * Cancella gli ordini in base al tipo di menù.
     * @param tipoMen
     * Tipo di menù da dare in input.
     */
    public void cancellaDaTipoMenu(int tipoMen)
    {
        int i=0;
        while(i<listaOrdini.size())
        {
            if(listaOrdini.get(i).getTipoMenu() == tipoMen)
            {
                listaOrdini.remove(i);

            }else i++;
        }


    }

    /**
     * Cancella gli ordini contenuti in un lasso di tempo.
     * @param minData
     * Estremo inferiore del periodo di tempo. (Utilizza java.util.LocalDate)
     * @param maxData
     * Estremo superiore del periodo di tempo. (Utilizza java.util.LocalDate)
     */
    public void cancellaDaTimeFrame(LocalDate minData, LocalDate maxData)
    {
        int i=0;
        while(i<listaOrdini.size())
        {
            if((listaOrdini.get(i).getDataOrdine().isAfter(minData))&&(listaOrdini.get(i).getDataOrdine().isBefore(maxData)))
            {
                 listaOrdini.remove(i);

            }else i++;
        }


    }

    /**
     * Restituisce il numero di ordini.
     * @return Numero di ordini.
     */
    public int getNumberOfOrders()
    {
        return listaOrdini.size();
    }

    /**
     * Restituisce la lista degli ordini compresi in un lasso di tempo
     * @param minData
     * Estremo inferiore del periodo di tempo. (Utilizza java.util.LocalDate)
     * @param maxData
     * Estremo superiore del periodo di tempo. (Utilizza java.util.LocalDate)
     * @return La lista degli ordini compresi in un lasso di tempo.
     */
    public List<Ordine> getListaOrdiniDaTimeFrame(LocalDate minData, LocalDate maxData)
    {

        List<Ordine> listaOutput =new ArrayList<>();
        int i=0;
        while(i<listaOrdini.size())
        {
            if((listaOrdini.get(i).getDataOrdine().isAfter(minData))&&(listaOrdini.get(i).getDataOrdine().isBefore(maxData)))
            {
                listaOutput.add(listaOrdini.get(i));
            }
            i++;
        }

        return listaOutput;
    }

    /**
     * Restituisce la lista degli ordini in base al numero di piatti.
     * @param numeroPiat
     * Il numero di piatti da dare in input.
     * @return La lista degli ordini.
     */
    public List<Ordine> getListaOrdiniDaNumeroPiatti(int numeroPiat)
    {
        List<Ordine> listaOutput =new ArrayList<>();
        int i=0;
        while(i<listaOrdini.size())
        {
            if(listaOrdini.get(i).getNumeroPiatti() == numeroPiat)
            {
                listaOutput.add(listaOrdini.get(i));
            }
            i++;
        }

        return listaOutput;
    }

    /**
     * Restituisce la lista degli ordini in base al tipo di menù.
     * @param tipoMen
     * Il tipo di menù da dare in input.
     * @return La lista degli ordini.
     */
    public List<Ordine> getListaOrdiniDaTipoMenu(int tipoMen)
    {
        List<Ordine> listaOutput =new ArrayList<>();
        int i=0;
        while(i<listaOrdini.size())
        {
            if(listaOrdini.get(i).getTipoMenu() == tipoMen)
            {
                listaOutput.add(listaOrdini.get(i));
            }
            i++;
        }
        return listaOutput;

    }

    /**
     * Restituisce la lista degli ordini in base alla data.
     * @param data La data da dare in input.
     * @return La lista degli ordini.
     */
    public List<Ordine> getOrdineDaData(LocalDate data)
    {
        List<Ordine> listaOutput =new ArrayList<>();
        int i=0;
        while(i<listaOrdini.size())
        {
            if(listaOrdini.get(i).getDataOrdine().isEqual(data))
            {
                listaOutput.add(listaOrdini.get(i));
            }
            i++;
        }
        return listaOutput;

    }
}
