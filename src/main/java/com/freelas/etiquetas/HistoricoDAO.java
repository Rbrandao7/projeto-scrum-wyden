package com.freelas.etiquetas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoDAO {
    

    private final Connection connection;

    public HistoricoDAO() throws SQLException {

        SQLite banco = new SQLite();
        connection = banco.getConnection();


    }
    
    public void insertHistorico(Etiqueta etiqueta) throws SQLException {
        String sql = "INSERT INTO Historico(codigoEtiqueta, label, dataGeracao,quantidade) VALUES(?,?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, etiqueta.getCodigo());
        pstmt.setString(2, etiqueta.getLabel());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        pstmt.setString(3, currentDate);
        pstmt.setString(4, etiqueta.getQuantidade());

        pstmt.executeUpdate();
    }
    
    public List<Historico> getHistorico() throws SQLException, ParseException {
    //String sql = "SELECT * FROM Historico WHERE dataGeracao >= date('now', '-12 months')";
    String sql = "SELECT \n" +
"                     id,\n" +
"                     codigoEtiqueta,\n" +
"                     label,\n" +
"                     strftime('%d-%m-%Y', dataGeracao) AS mesAno,  \n" +
"                     SUM(quantidade) AS totalQuantidade\n" +
"                 FROM \n" +
"                     Historico\n" +
"                 GROUP BY \n" +
"                     codigoEtiqueta, \n" +
"                     label, \n" +
"                     mesAno  \n" +
"                 ORDER BY \n" +
"                     mesAno DESC,  \n" +
"                     codigoEtiqueta;";
                 
               
    
    
    PreparedStatement pstmt = connection.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"
            + "");

    List<Historico> historicos = new ArrayList<>();
    
    while (rs.next()) {
        Historico historico = new Historico();
        historico.setId(rs.getInt("id"));
        historico.setQuantidade(rs.getInt("totalQuantidade"));
        historico.setCodigoEtiqueta(rs.getString("codigoEtiqueta"));
        historico.setLabel(rs.getString("label"));
        historico.setDataGeracao(dateFormat.parse(rs.getString("mesAno"))); 
        
        historicos.add(historico);
    }

    return historicos;
}
    
 public List<Historico> getHistoricoPorListaEtiqueta(List<Etiqueta> listaEtiquetas) throws SQLException, ParseException {
   
     StringBuilder consulta = new StringBuilder();
     
     consulta.append("SELECT  id, codigoEtiqueta,label,strftime('%d-%m-%Y', dataGeracao) AS mesAno, SUM(quantidade) AS totalQuantidade");
     consulta.append(" FROM Historico WHERE 2=2");
     if(!listaEtiquetas.isEmpty()){
        consulta.append(" AND codigoEtiqueta IN ( ").append(formataEtiquetasIN(listaEtiquetas)).append(")"); 
     }
     consulta.append(" GROUP BY codigoEtiqueta,label,mesAno ORDER BY mesAno DESC,codigoEtiqueta");

               
    
    
    PreparedStatement pstmt = connection.prepareStatement(consulta.toString());
    ResultSet rs = pstmt.executeQuery();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"
            + "");

    List<Historico> historicos = new ArrayList<>();
    
    while (rs.next()) {
        Historico historico = new Historico();
        historico.setId(rs.getInt("id"));
        historico.setQuantidade(rs.getInt("totalQuantidade"));
        historico.setCodigoEtiqueta(rs.getString("codigoEtiqueta"));
        historico.setLabel(rs.getString("label"));
        historico.setDataGeracao(dateFormat.parse(rs.getString("mesAno"))); 
        
        historicos.add(historico);
    }

    return historicos;
}
    
 
 private String formataEtiquetasIN(List<Etiqueta> lista){
 
    String resultado = lista.stream()
    .map(l -> "'"+l.getCodigo()+"'")
    .collect(Collectors.joining(","));
    
    return resultado;
 
 }
 
 //"AND codigoEtiqueta IN ("PDT-171","PDT-377")"+ 
    
    
    
}
