/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.freelas.etiquetas;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author rodri
 */
public class HistoricoRelatorio {
    
    private String mes;
    private String codigoMes;
    private Integer totalGeral;
    private List<Historico> listaDados;
    private JRBeanCollectionDataSource listaRelatorio;
    
    public HistoricoRelatorio(){
    
        this.listaDados = new ArrayList<>();
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<Historico> getListaDados() {
        return listaDados;
    }

    public void setListaDados(List<Historico> listaDados) {
        this.listaDados = listaDados;
    }
    
    public JRBeanCollectionDataSource setListaRelatorio(){
    
            JRBeanCollectionDataSource dadosSubRelatorio = new JRBeanCollectionDataSource(this.listaDados);
            this.listaRelatorio = dadosSubRelatorio;
            
                int contador = 0;
        
                for(Historico his : this.listaDados){
                        contador += his.getQuantidade();

                }
                setTotalGeral(contador);
                
            
            return this.listaRelatorio;
    
    }
    
      public JRBeanCollectionDataSource getListaRelatorio(){

            
            return this.listaRelatorio;
    
    }

    public String getCodigoMes() {
        return codigoMes;
    }

    public void setCodigoMes(String codigoMes) {
        this.codigoMes = codigoMes;
    }
    
    
    public Integer getTotalGeral(){

        return this.totalGeral;
    
    }

    public void setTotalGeral(Integer totalGeral) {
        this.totalGeral = totalGeral;
    }
      
      
      
      

}
