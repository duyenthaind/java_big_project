/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haui.thangnd.XemKetQuaHocTap;

import com.haui.common.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TAVPLTNDTSUN
 */
public class Connect{
    Connection con = null;
    Statement sta = null;
    ResultSet rs = null;
    public void getConnect()
    {
        
        Config.loadConfig();
        
        String url = Config.DB_URL;
        String user = Config.DB_USER_NAME;
        String password = Config.DB_PASSWORD;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = (Connection)DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Statement getStatement() throws Exception
    {
        if (this.sta == null || this.sta.isClosed()) {
            //Khới Tạo Statement Mới
            this.sta = this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        return this.sta;
    }
    public  ResultSet executeQuery(String qr) throws Exception
    {
        try {
            rs = getStatement().executeQuery(qr);
        } catch (SQLException ex) {
            throw  new Exception("Command Not Executed !");
        }
        return rs;
    }
    public int executeUpdate(String qr) throws Exception
    {
        int result = 0;
        try {
            result = getStatement().executeUpdate(qr);
        } catch (Exception ex) {
            throw  new Exception("ExecuteUpdate Not Executed !");
        }
        finally
        {
            this.con.close();
        }
        return result;
    }
    public ResultSet getData(String nameTable) throws SQLException
    {
        String query = "Select * From "+nameTable;
        ResultSet res = null;
        try {
            res = getStatement().executeQuery(query);
            return res;
        } catch (Exception ex) {
            throw new SQLException("Error Connection !");
        }
//        return  res;
    }
    public void closeConnect()
    {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
                this.con = null;
            }
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null,"Error Of Connect Closed !");
        }
    }
}
