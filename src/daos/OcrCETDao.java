package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.ConexaoMySql;
import connections.ConexaoSQLServer;
import domain.Contrato;

public class OcrCETDao {
    

    public  static void exportContratosSqlServerToMySql() throws SQLException  {

        String sqlSqlServer = "SELECT * FROM DWOCR.dbo.Dim_Contrato WHERE CGCT <> '000'";
        String sqlMySql = "insert into cet_ocrs.dim_contrato (cgct, numero_contrato, contratada, contrato, id_serie)   VALUES   (?,?,?,?,?);";


        List<Contrato> contratos = new ArrayList<Contrato>();

        PreparedStatement psSqlServer = ConexaoSQLServer.getConnection().prepareStatement(sqlSqlServer);
        PreparedStatement psMySql = ConexaoMySql.getConnection().prepareStatement(sqlMySql);

        ResultSet resultSet = psSqlServer.executeQuery();

        while(resultSet.next()) {
            Contrato nextContrato =  new Contrato(
                    resultSet.getString("CGCT"),
                    resultSet.getString("NumeroContrato"),
                    resultSet.getString("Contratada"),
                    resultSet.getString("Contrato"),
                    resultSet.getString("IDSerie")
                );

            contratos.add(nextContrato);
        }

       

            contratos.forEach(contrato -> { 
               
                try {
                    psMySql.setString(1, contrato.Cgct());
                    psMySql.setString(2, contrato.NumeroContrato());
                    psMySql.setString(3, contrato.Contratada());
                    psMySql.setString(4, contrato.Contrato());
                    psMySql.setString(5, contrato.IdSerie());
                
                    psMySql.execute();
                    
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
               
                
               // System.out.println(contrato.Cgct()); 
            });
    }
    
    public  static void exportDataSqlServerToMySql() throws SQLException  {

        String sqlSqlServer = "SELECT Data, Ano, Semestre, Trimestre, NumeroMes, MesAbrev, DSAbrev, NumeroDS, Dia, SeqDia, EhFeriado, EhDiaUtil, MesPorExtenso, DiaMesDiaSem, Quinzena FROM DWOCR.dbo.Dim_Data";
        String sqlMySql = "insert into cet_ocrs.dim_data (data, ano, semestre, trimestre, numero_mes, mes_abrev, ds_abrev, numero_ds, dia, seq_dia, eh_feriado, eh_dia_util, mes_por_extenso, dia_mes_dia_sem, quinzena)   VALUES   (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?);";


        PreparedStatement psSqlServer = ConexaoSQLServer.getConnection().prepareStatement(sqlSqlServer);
        PreparedStatement psMySql = ConexaoMySql.getConnection().prepareStatement(sqlMySql);

        ResultSet resultSet = psSqlServer.executeQuery();

        while(resultSet.next()) {
            try {
                
                psMySql.setDate(1, resultSet.getDate("Data"));
                psMySql.setInt(2, resultSet.getInt("Ano"));
                psMySql.setInt(3, resultSet.getInt("Semestre"));
                psMySql.setInt(4, resultSet.getInt("Trimestre"));
                psMySql.setInt(5, resultSet.getInt("NumeroMes"));

                psMySql.setString(6, resultSet.getString("MesAbrev"));
                psMySql.setString(7, resultSet.getString("DSAbrev"));
                psMySql.setInt(8, resultSet.getInt("NumeroDS"));
                psMySql.setInt(9, resultSet.getInt("Dia"));
                psMySql.setInt(10, resultSet.getInt("SeqDia"));

                psMySql.setBoolean(11, resultSet.getBoolean("EhFeriado"));
                psMySql.setBoolean(12, resultSet.getBoolean("EhDiaUtil"));
                psMySql.setString(13, resultSet.getString("MesPorExtenso"));
                psMySql.setString(14, resultSet.getString("DiaMesDiaSem"));
                psMySql.setString(15, resultSet.getString("Quinzena"));

                psMySql.execute();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };
    

    public  static void exportEquipamentoSqlServerToMySql() throws SQLException  {

        StringBuilder sqlSqlServer = new StringBuilder();
        sqlSqlServer.append("SELECT CodCET, NEQUIPEMP, LOCEQUIP, BAIRRO, AP, Latitude, Longitude, ");
        sqlSqlServer.append("Logradouro, CodLog, CGCT, Sentido, Suspenso, Status, ");
        sqlSqlServer.append("InicioOperacao, FimContrato");
        sqlSqlServer.append("FROM Dim_Equipamento");
        
        
        StringBuilder sqlMySql = new StringBuilder();

        sqlMySql.append("insert into cet_ocrs.dim_equipamento (");
        sqlMySql.append("codcet, n_equipemp, locequip, bairro, ");
        sqlMySql.append("ap, latitude, longitude, logradouro, codlog, ");
        sqlMySql.append("cgct, sentido, suspenso, status, inicio_operacao, fim_contrato ");
        sqlMySql.append(") VALUES ()");
        sqlMySql.append("?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?");
        sqlMySql.append(");");
        
        PreparedStatement psSqlServer = ConexaoSQLServer.getConnection().prepareStatement(sqlSqlServer.toString());
        PreparedStatement psMySql = ConexaoMySql.getConnection().prepareStatement(sqlMySql.toString());

        ResultSet resultSet = psSqlServer.executeQuery();

        while(resultSet.next()) {
            try {
                
                //psMySql.setString(1, resultSet.setString("CodCET"));
                psMySql.setInt(2, resultSet.getInt("Ano"));
                psMySql.setInt(3, resultSet.getInt("Semestre"));
                psMySql.setInt(4, resultSet.getInt("Trimestre"));
                psMySql.setInt(5, resultSet.getInt("NumeroMes"));

                psMySql.setString(6, resultSet.getString("MesAbrev"));
                psMySql.setString(7, resultSet.getString("DSAbrev"));
                psMySql.setInt(8, resultSet.getInt("NumeroDS"));
                psMySql.setInt(9, resultSet.getInt("Dia"));
                psMySql.setInt(10, resultSet.getInt("SeqDia"));

                psMySql.setBoolean(11, resultSet.getBoolean("EhFeriado"));
                psMySql.setBoolean(12, resultSet.getBoolean("EhDiaUtil"));
                psMySql.setString(13, resultSet.getString("MesPorExtenso"));
                psMySql.setString(14, resultSet.getString("DiaMesDiaSem"));
                psMySql.setString(15, resultSet.getString("Quinzena"));

                psMySql.execute();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };


      


}
