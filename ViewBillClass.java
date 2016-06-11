package BillPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ViewBillClass {

    private Connection con=null;
    private JFrame jfrm3=new JFrame("View Employee Details");
    
    private JPanel jpan=new JPanel();
    
	public ViewBillClass(){}
    
    public ViewBillClass(Connection con){
        this.con=con;
        jfrm3.setSize(500,300);
        jfrm3.setVisible(true);
        jfrm3.setLocationRelativeTo(null);
        viewBillDetail();
    }
	
	
    public void viewBillDetail(){
       
        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {

            String sql = "Select * from bill";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement( md.getColumnName(i) );
            }
        while (rs.next()) {
                Vector row = new Vector(columns);
        for (int i = 1; i <= columns; i++){
                row.addElement( rs.getObject(i) );
        }
        data.addElement( row );
        }
        rs.close();
        stmt.close();
        }
        catch(Exception e){
                System.out.println(e);
        }
        JTable table = new JTable(data, columnNames)
        {
            public boolean isCellEditable(int row,int cloumns){
            return false;
            }
            
        };
        TableColumn col;
        for (int i = 0; i < table.getColumnCount(); i++) {
                col = table.getColumnModel().getColumn(i);
        col.setMaxWidth(250);
        }
        
        JPanel jp=new JPanel();
        jp.add(table);
        
        
        jfrm3.add(jp);
        JScrollPane scrollPane = new JScrollPane(table);
        jp.add(scrollPane);
        scrollPane.setBounds(200,100,900,300);
        table.getTableHeader().setReorderingAllowed(false);
 }


}
    
    


