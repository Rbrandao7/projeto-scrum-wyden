
package com.freelas.etiquetas;

import java.io.ByteArrayInputStream;

public class Etiqueta {
    
    private String codigo;
    private String label;
    private String quantidade;
    private String imagem;
    private ByteArrayInputStream codigoBarras;
    private String codigoEAN;

    public Etiqueta(String codigo, String label, String quantidade,String imagem) {
        this.codigo = codigo;
        this.label = label;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }
    
    public Etiqueta(String codigo, String label, String quantidade,String imagem, String codigoEAN) {
        this.codigo = codigo;
        this.label = label;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.codigoEAN = codigoEAN;
    }
    
        public Etiqueta(String codigo, String label, String quantidade) {
        this.codigo = codigo;
        this.label = label;
        this.quantidade = quantidade;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public ByteArrayInputStream getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(ByteArrayInputStream codigoBaras) {
        this.codigoBarras = codigoBaras;
    }
    
    
    
//    CREATE TABLE Etiqueta (
//    codigo TEXT PRIMARY KEY,
//    label TEXT NOT NULL,
//    quantidade TEXT NOT NULL
//);

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(String codigoEAN) {
        this.codigoEAN = codigoEAN;
    }
    
    
    
    
}
