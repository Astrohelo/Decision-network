package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class szulokEsely{
    private ArrayList<Integer> szuloErtekek = new ArrayList<>();
    private ArrayList<Float> valoszinusegek = new ArrayList<>();

    public szulokEsely(ArrayList<Integer> szuloErtekek, ArrayList<Float> valoszinusegek){
        this.szuloErtekek=szuloErtekek;
        this.valoszinusegek=valoszinusegek;
    }
    public szulokEsely( ArrayList<Float> valoszinusegek){
        this.valoszinusegek=valoszinusegek;
    }


    public ArrayList<Float> getValoszinusegek() {
        return valoszinusegek;
    }

    public void setValoszinusegek(ArrayList<Float> valoszinusegek) {
        this.valoszinusegek = valoszinusegek;
    }

    public ArrayList<Integer> getSzuloErtekek() {
        return szuloErtekek;
    }
}
class CsomoPont {
    private int hanyErteku;
    private int szuloSzam;
    private ArrayList<Integer> szulok;
    private ArrayList<szulokEsely> szulokEselyek = new ArrayList<>();
    private boolean fontos;
    private boolean evidencia;
    private int evidenciaErteke;
    public CsomoPont(int h, int sz,ArrayList<Integer> szulok,ArrayList<szulokEsely> szulokEselyek){
        this.hanyErteku=h;
        this.szuloSzam=sz;
        this.szulok=szulok;
        this.szulokEselyek=szulokEselyek;
        this.fontos=false;
        this.evidencia=false;
    }
    public CsomoPont(int h, int sz,ArrayList<szulokEsely> szulokEselyek){
        this.hanyErteku=h;
        this.szuloSzam=sz;
        this.szulokEselyek=szulokEselyek;
    }
    public void setSzulokEselyek(ArrayList<szulokEsely> szulokEselyek) {
        this.szulokEselyek=szulokEselyek;
    }
    public ArrayList<szulokEsely> getSzulokEselyek() {
        return szulokEselyek;
    }

    public int getHanyErteku() {
        return hanyErteku;
    }
    public int getSzuloSzam() {
        return szuloSzam;
    }

    public ArrayList<Integer> getSzulok() {
        return szulok;
    }

    public boolean isFontos() {
        return fontos;
    }

    public void setFontos(boolean fontos) {
        this.fontos = fontos;
    }

    public boolean isEvidencia() {
        return evidencia;
    }

    public void setEvidencia(boolean evidencia) {
        this.evidencia = evidencia;
    }

    public int getEvidenciaErteke() {
        return evidenciaErteke;
    }

    public void setEvidenciaErteke(int evidenciaErteke) {
        this.evidenciaErteke = evidenciaErteke;
    }
}

class EvidenciaValtozo {
    private int index;
    private int ertek;
    public EvidenciaValtozo(int index, int ertek) {
        this.index = index;
        this.ertek = ertek;
    }

    public int getErtek() {
        return ertek;
    }

    public int getIndex() {
        return index;
    }
}

class Hasznos {
    private int valtozo;
    private int dontesIndex;
    private float hasznossag;
    public Hasznos(int v, int dIndex, float szam) {
        this.valtozo = v;
        this.dontesIndex = dIndex;
        this.hasznossag = szam;
    }

    public float getHasznossag() {
        return hasznossag;
    }

    public int getDontesIndex() {
        return dontesIndex;
    }

    public int getValtozo() {
        return valtozo;
    }
}

class Tabla{
    private int valtozo;
    private int hanyadikCsomoPont;
    public Tabla(int v, int h){
        this.valtozo=v;
        this.hanyadikCsomoPont=h;
    }

    public int getValtozo() {
        return valtozo;
    }

    public int getHanyadikCsomoPont() {
        return hanyadikCsomoPont;
    }
}


public class Main {

    public static void main(String[] args) {
        int csucspontokDarab=0;
        ArrayList<CsomoPont> csomoPontok = new ArrayList<>();
        int evidenciaValtozokSzama=0;
        ArrayList<EvidenciaValtozo> evidenciaValtozok = new ArrayList<>();
        int celvaltozo=0;
        int dontesekSzama=0;
        ArrayList<Hasznos> hasznosak = new ArrayList<>(); //ebből dontesekSzama*(csomopontok[celvaltozo].getHanyerteku db lesz



        Scanner sc = new Scanner(System.in);

        csucspontokDarab=sc.nextInt();
        //so it finishes this line
        sc.nextLine();
        for(int i = 0; i<csucspontokDarab;i++){
            String line = sc.nextLine();
            line=line.replace(":","\t");
            line=line.replace(",","\t");
            String split[] = line.split("\\t");

            int index = 0;
            int lehetsegesErtekek = Integer.parseInt(split[index++]);
            int szulokSzama = Integer.parseInt(split[index++]);
            ArrayList<Integer> szulok = new ArrayList<>();
            if(szulokSzama!=0){
                int szuloVariaciok=1;
                for(int j=0; j<szulokSzama;j++){
                    int szuloIndex = Integer.parseInt(split[index++]);
                    szulok.add(szuloIndex);
                    szuloVariaciok*=csomoPontok.get(szuloIndex).getHanyErteku();
                }
                ArrayList<szulokEsely> szulokEselyek = new ArrayList<>();
                /// 0,0,1:0.23,0.77 ......
                for(int z=0; z<szuloVariaciok; z++){
                    ArrayList<Integer> szuloErtekek = new ArrayList<>();
                    ArrayList<Float> valoszinusegek = new ArrayList<>();
                    for(int enSzulo=0; enSzulo<szulokSzama; enSzulo++){
                        szuloErtekek.add(Integer.parseInt(split[index++]));
                    }
                    for(int k=0; k<lehetsegesErtekek; k++){
                        valoszinusegek.add(Float.parseFloat(split[index++]));
                    }
                    szulokEselyek.add(new szulokEsely(szuloErtekek,valoszinusegek));
                }
                //////
                csomoPontok.add( new CsomoPont(lehetsegesErtekek,szulokSzama,szulok,szulokEselyek));
            }
            //vegigmegy a szulokon hogy hany lehetséges pozicioja van
            else{
                ArrayList<Float> valoszinusegek = new ArrayList<>();
                ArrayList<szulokEsely> szulokEselyek = new ArrayList<>();
                for(int k=0; k<lehetsegesErtekek; k++){
                    valoszinusegek.add(Float.parseFloat(split[index++]));
                }
                szulokEselyek.add(new szulokEsely(valoszinusegek));
                csomoPontok.add( new CsomoPont(lehetsegesErtekek,0,szulokEselyek));
            }
        }
        /// egy szam
        evidenciaValtozokSzama=sc.nextInt();
        //sc.nextLine();
        for(int i = 0; i<evidenciaValtozokSzama;i++){
            evidenciaValtozok.add(new EvidenciaValtozo(sc.nextInt(),sc.nextInt()));
        }
        celvaltozo=sc.nextInt();
        dontesekSzama=sc.nextInt();
        sc.nextLine();
        for(int i=0; i<dontesekSzama*csomoPontok.get(celvaltozo).getHanyErteku();i++){
            String line = sc.nextLine();
            String split[] = line.split("\\t");
            hasznosak.add(new Hasznos(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Float.parseFloat(split[2])));
        }
        //megkeresem a fontos csucsokat amik számitanak
        //csomoPontok.get(celvaltozo).setEvidencia(true);
        importantRecursive(csomoPontok,csomoPontok.get(celvaltozo));
        for(int i=0; i<evidenciaValtozokSzama;i++){
            importantRecursive(csomoPontok,csomoPontok.get(evidenciaValtozok.get(i).getIndex()));
            csomoPontok.get(evidenciaValtozok.get(i).getIndex()).setEvidencia(true);
            csomoPontok.get(evidenciaValtozok.get(i).getIndex()).setEvidenciaErteke(evidenciaValtozok.get(i).getErtek());
        }

        ArrayList<Integer> fontosIndex = new ArrayList<Integer>();
        int fontosbanCelvaltozoIndexe=0;
        //int ennyiVan=1;
        for(int i=0; i<csucspontokDarab;i++){
            if(csomoPontok.get(i).isFontos()){
                //ennyiVan*=csomoPontok.get(i).getHanyErteku();
                fontosIndex.add(i);
                //System.out.println("fontos"+i);
                if(i==celvaltozo){
                    fontosbanCelvaltozoIndexe=fontosIndex.size()-1;
                }
            }
        }
        //System.out.println("am ennyi van"+ennyiVan);
        ArrayList<ArrayList<Tabla>> osszesLehetoseg = new ArrayList<ArrayList<Tabla>>();

        makeItAll(csomoPontok,fontosIndex,-1,osszesLehetoseg,new ArrayList<Tabla>());

        ArrayList<Float> celValoszinusegek=new ArrayList<Float>();
        float osszesEset=0;
        for(int i=0;i<csomoPontok.get(celvaltozo).getHanyErteku() ;i++){
            celValoszinusegek.add(0f);
        }
        for(int i=0;i<osszesLehetoseg.size() ;i++){
            //System.out.println("valsz "+i);

            //System.out.println("a sor hossza"+ osszesLehetoseg.get(i).size()+ " "+i);
            osszesEset+=getValoszinuseg(celValoszinusegek,fontosbanCelvaltozoIndexe,csomoPontok,osszesLehetoseg.get(i));
        }
        for(int i=0;i<celValoszinusegek.size() ;i++){
            celValoszinusegek.set(i,celValoszinusegek.get(i)/osszesEset);
            System.out.println(celValoszinusegek.get(i));
        }
        ArrayList<Float> hasznosVegeredmeny=new ArrayList<Float>();
        for(int i=0;i<dontesekSzama;i++){
            hasznosVegeredmeny.add(0f);
        }
        for(int i=0;i<dontesekSzama*csomoPontok.get(celvaltozo).getHanyErteku() ;i++){
            hasznosVegeredmeny.set(i%dontesekSzama,hasznosVegeredmeny.get(i%dontesekSzama)+hasznosak.get(i).getHasznossag()*celValoszinusegek.get(hasznosak.get(i).getValtozo()));
        }
        Float maxVal = Collections.max(hasznosVegeredmeny);
        Integer vegeredmeny = hasznosVegeredmeny.indexOf(maxVal);

            System.out.println(vegeredmeny);


        //System.out.println("hosszu"+osszesLehetoseg.size());


    }

    static float getValoszinuseg(ArrayList<Float> celValoszinusegek,int fontosbanCelvaltozoIndexe,ArrayList<CsomoPont> csomoPontok,ArrayList<Tabla> egySor){
        float val=1;
        for(int i =0;i<egySor.size();i++){
            int szulo=0;
            int szuloIndex=0;
            ArrayList<Integer> szuloErtekek = new ArrayList<>();
            for(int j=0; j<csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzuloSzam();j++){

                for(int z=szulo; z<egySor.size();z++){
                    if(egySor.get(z).getHanyadikCsomoPont() == csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulok().get(szuloIndex)){
                        szulo=z;
                        szuloIndex++;
                        szuloErtekek.add(egySor.get(z).getValtozo());
                        break;
                    }

                }
            }
            if(csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzuloSzam()!=0){
                for(int zs =0;zs<csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().size();zs++){

                    if(szuloErtekek.equals(csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().get(zs).getSzuloErtekek())){
                        val*=csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().get(zs).getValoszinusegek().get(egySor.get(i).getValtozo());

                        break;
                        //System.out.println(csomoPontok.get(egySor.get(i).getHanyadikCsomoPont())+"ennyiedik "+csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().get(zs).getValoszinusegek().get(egySor.get(i).getValtozo()-1));
                    }
                }
                continue;
            }
            val*=csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().get(0).getValoszinusegek().get(egySor.get(i).getValtozo());

            //System.out.println(csomoPontok.get(egySor.get(i).getHanyadikCsomoPont())+"ennyiedik "+csomoPontok.get(egySor.get(i).getHanyadikCsomoPont()).getSzulokEselyek().get(0).getValoszinusegek().get(egySor.get(i).getValtozo()-1));

        }
        celValoszinusegek.set(egySor.get(fontosbanCelvaltozoIndexe).getValtozo(),celValoszinusegek.get(egySor.get(fontosbanCelvaltozoIndexe).getValtozo())+val);
        return val;
    }


    static void importantRecursive(ArrayList<CsomoPont> csomoPontok, CsomoPont jelen){
        if(jelen.isFontos()){
            return;
        }
        else{
            jelen.setFontos(true);
        }
        if(jelen.getSzuloSzam()==0 ){
            return;
        }

        for(int i=0; i<jelen.getSzuloSzam();i++){
            importantRecursive(csomoPontok,csomoPontok.get(jelen.getSzulok().get(i)));
        }
    }


    static void makeItAll(ArrayList<CsomoPont> csomoPontok,ArrayList<Integer> fontosNums,int hanyadikMost,ArrayList<ArrayList<Tabla>>nagylista,ArrayList<Tabla>kislista){
        int x=hanyadikMost+1;
        if(csomoPontok.get(fontosNums.get(x)).isEvidencia()){
            Tabla t = new Tabla(csomoPontok.get(fontosNums.get(x)).getEvidenciaErteke(), fontosNums.get(x));
            kislista.add(t);
            if (x + 1 != fontosNums.size()) {
                //System.out.println("ennyiedik csomopont vagyok"+ fontosNums.get(x));
                makeItAll(csomoPontok, fontosNums, x, nagylista, kislista);

            } else {
                ArrayList<Tabla> vege = new ArrayList<Tabla>(kislista);
                nagylista.add(vege);
            }
            kislista.remove(t);
        }
        else {
            for (int i = 0; i < csomoPontok.get(fontosNums.get(x)).getHanyErteku(); i++) {
                Tabla t = new Tabla(i, fontosNums.get(x));
                kislista.add(t);
                if (x + 1 != fontosNums.size()) {
                    //System.out.println("ennyiedik csomopont vagyok"+ fontosNums.get(x));
                    makeItAll(csomoPontok, fontosNums, x, nagylista, kislista);
                } else {
                    ArrayList<Tabla> vege = new ArrayList<Tabla>(kislista);
                    nagylista.add(vege);
                }
                kislista.remove(t);

            }

        }
    }

}



