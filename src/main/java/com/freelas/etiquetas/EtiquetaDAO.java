package com.freelas.etiquetas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sun.jvm.hotspot.gc.shared.GCName;

public class EtiquetaDAO {

    private List<Etiqueta> listaDeEtiquetas;
    private final Connection connection;

    public EtiquetaDAO() throws SQLException {

        SQLite banco = new SQLite();
        connection = banco.getConnection();

        listaDeEtiquetas = this.getAllEtiquetas();

    }
    
    public void atualizaImagemEtiqueta(String codigo, String imagem) throws SQLException {
    
       String sql = "UPDATE Etiqueta SET imagem = ? WHERE codigo = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, imagem);
        pstmt.setString(2, codigo);
        pstmt.executeUpdate();
    
    }
    
    
            
    public void updateEtiqueta(Etiqueta etiqueta) throws SQLException {
        String sql = "UPDATE Etiqueta SET label=?,quantidade=?,imagem=?,codigoEAN=? WHERE codigo = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, etiqueta.getLabel());
        pstmt.setString(2, "0");
        pstmt.setString(3, etiqueta.getImagem());
        pstmt.setString(4, etiqueta.getCodigoEAN());
        pstmt.setString(5, etiqueta.getCodigo());
        pstmt.executeUpdate();

    }

    public void insertEtiqueta(Etiqueta etiqueta) throws SQLException {
        String sql = "INSERT INTO Etiqueta(codigo, label, quantidade,imagem,codigoEAN) VALUES(?,?,?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, etiqueta.getCodigo());
        pstmt.setString(2, etiqueta.getLabel());
        pstmt.setString(3, "0");
        pstmt.setString(4, etiqueta.getImagem());
        pstmt.setString(5, etiqueta.getCodigoEAN());
        pstmt.executeUpdate();

    }

    public List<Etiqueta> getAllEtiquetas() {
        String sql = "SELECT codigo, label, quantidade,imagem,codigoEAN FROM Etiqueta ORDER BY codigo ASC";
        List<Etiqueta> etiquetas = new ArrayList<>();

        try (
                Statement stmt = this.connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta(
                        rs.getString("codigo"),
                        rs.getString("label"),
                        rs.getString("quantidade"),
                        rs.getString("imagem"),
                        rs.getString("codigoEAN")
                );
                etiquetas.add(etiqueta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return etiquetas;
    }

    public List<Etiqueta> getListaDeEtiquetasOrdenadaPorCodigo() {
        //String sql = "SELECT codigo, label, quantidade,imagem,codigoEAN FROM Etiqueta ORDER BY codigo ASC";
        String sql = "SELECT codigo, label, quantidade,imagem,codigoEAN, SUBSTR(codigo, INSTR(codigo, '-') + 1) AS numero FROM Etiqueta ORDER BY CAST(numero as NUMBER) ASC";
        List<Etiqueta> etiquetas = new ArrayList<>();

        try (
                Statement stmt = this.connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Etiqueta etiqueta = new Etiqueta(
                        rs.getString("codigo"),
                        rs.getString("label"),
                        rs.getString("quantidade"),
                        rs.getString("imagem"),
                        rs.getString("codigoEAN")
                        
                        
                );
                etiquetas.add(etiqueta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return etiquetas;
    }

    public List<Etiqueta> getEtiquetaPorNome(String previaEtiqueta) throws SQLException {
        String sql = "SELECT codigo, label, quantidade,imagem,codigoEAN FROM Etiqueta WHERE label LIKE ? ORDER BY codigo ASC";
        List<Etiqueta> etiquetas = new ArrayList<>();

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + previaEtiqueta + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Etiqueta etiqueta = new Etiqueta(
                            rs.getString("codigo"),
                            rs.getString("label"),
                            rs.getString("quantidade"),
                            rs.getString("imagem"),
                            rs.getString("codigoEAN")
                    );
                    etiquetas.add(etiqueta);
                }
            }
        }

        return etiquetas;
    }
    
        public Etiqueta getEtiquetaPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT codigo, label, quantidade,imagem,codigoEAN FROM Etiqueta WHERE codigo = ?";
        Etiqueta etiqueta = null;
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1,codigo);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                 etiqueta = new Etiqueta(
                            rs.getString("codigo"),
                            rs.getString("label"),
                            rs.getString("quantidade"),
                            rs.getString("imagem"),
                            rs.getString("codigoEAN")
                    );
            }else{
                return etiqueta;
            }
            
       
            
       
        }

        return etiqueta;
    }

    public void deleteEtiqueta(String codigo) throws SQLException {
        String sql = "DELETE FROM Etiqueta WHERE codigo = ?";

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setString(1, codigo);
        pstmt.executeUpdate();

    }

    public List<Etiqueta> getListaDeEtiquetas() {
        listaDeEtiquetas = this.getAllEtiquetas();
        return listaDeEtiquetas;
    }

    public void setListaDeEtiquetas(List<Etiqueta> listaDeEtiquetas) {
        this.listaDeEtiquetas = listaDeEtiquetas;
    }

    public String getCodigoPorLabel(String label) {

        String retorno = "";
        for (Etiqueta et : getAllEtiquetas()) {
            if (et.getLabel().equals(label)) {
                retorno = et.getCodigo();
            }

        }

        return retorno;

    }

}
