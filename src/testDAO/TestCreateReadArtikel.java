/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDAO;

import java.util.ArrayList;
import workshop1.*;

/**
 *
 * @author Peter
 */
public class TestCreateReadArtikel {
    
    
    public String testCreateRead(Artikel artikel){
       
        
        ArtikelDAO.createNewArtikel(artikel);
        
        ArrayList<Artikel> lijst = ArtikelDAO.readArtikel();
        
        
        int index = lijst.size();
        System.out.println(index + " is de index");
        Artikel read = lijst.get(index-1);
        
        String result = read.getArtikel_naam();
        
        
        return result;
    }
    
    public String testUpdateRead(Artikel artikel){
        
        ArtikelDAO.updateArtikel(artikel);
        
        int index = artikel.getArtikel_id();
        
        Artikel read = ArtikelDAO.readArtikel(index);
        String result = read.getArtikel_naam();
        
        return result;
        
        
    }
    
    
}
