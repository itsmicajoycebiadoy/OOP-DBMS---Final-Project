package MainFiles;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ExpDate_NonRaw extends javax.swing.JFrame {

   
    public ExpDate_NonRaw() {
        initComponents();
        Connect();
        LoadItemNo();
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void LoadItemNo(){
        try {
            pst = con.prepareStatement("SELECT P_Num FROM tblnonraw");
            rs = pst.executeQuery();
            cbnum.removeAllItems();
            while (rs.next()){
                cbnum.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rtis", "root", "mickay20050304");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, "SQL Error: ", ex);
        }
    
    }
    
   private void Fetch() {
    try {
        int q;
        pst = con.prepareStatement("SELECT * FROM tblnonraw");
        rs = pst.executeQuery();
        ResultSetMetaData rss = rs.getMetaData();
        q = rss.getColumnCount();

        DefaultTableModel df = (DefaultTableModel) tblnonraw.getModel();
        df.setRowCount(0); 

    
        tblnonraw.clearSelection();

        while (rs.next()) {
            Vector<String> v2 = new Vector<>();

           
            String productNumber = rs.getString("P_Num");
            String productName = rs.getString("P_Name");
            String quantity = rs.getString("P_Quantity");
            String unit = rs.getString("P_Unit");
            String costPerQuantity = rs.getString("P_CostPerQuantity");
            String totalCost = rs.getString("P_TotalCost");
            String purchaseDateStr = rs.getString("P_DateofPurchase");
            String expirationDateStr = rs.getString("P_ExpirationDate");

 
            LocalDate productPurchaseDate = LocalDate.parse(purchaseDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate expirationDate = LocalDate.parse(expirationDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

          
            v2.add(productNumber);
            v2.add(productName);
            v2.add(quantity);
            v2.add(unit);
            v2.add(costPerQuantity);
            v2.add(totalCost);
            v2.add(purchaseDateStr);
            v2.add(expirationDateStr);

       
            df.addRow(v2);

        
            int rowIndex = df.getRowCount() - 1;

            
            LocalDate currentDate = LocalDate.now();
            long remainingShelfLife = ChronoUnit.DAYS.between(productPurchaseDate, expirationDate);

            // Set expiration status
            String expirationStatus = "Good";  // Default status
             if (remainingShelfLife < 0) {
                expirationStatus = "Expired";
            } else if (remainingShelfLife <= 4) {
                expirationStatus = "Expiring Soon";
            }

            
            df.setValueAt(expirationStatus, rowIndex, 7); 

          
            if ("Expired".equals(expirationStatus) || "Expiring Soon".equals(expirationStatus)) {
                tblnonraw.setRowSelectionInterval(rowIndex, rowIndex); 
            }
        }

      
        tblnonraw.setDefaultRenderer(Object.class, new CustomRenderer());

      
        tblnonraw.revalidate();
        tblnonraw.repaint();

    } catch (SQLException ex) {
        Logger.getLogger(ExpDate_Raw.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbnum = new javax.swing.JComboBox<>();
        search = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        display = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        delete = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtexp = new javax.swing.JTextField();
        txtu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcpq = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtdop = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtquan = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnonraw = new javax.swing.JTable();
        update1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ExpDate_NonRaw");

        jPanel1.setBackground(new java.awt.Color(239, 239, 175));
        jPanel1.setEnabled(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(182, 150, 86));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jPanel3.setBackground(new java.awt.Color(239, 239, 175));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel3.setEnabled(false);
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("NON-RAW");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(14, 14, 14))
        );

        jPanel5.setBackground(new java.awt.Color(239, 239, 175));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.setEnabled(false);
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("RAW");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(182, 150, 86));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backbutton-removebg-preview (3).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 700));

        jPanel4.setBackground(new java.awt.Color(182, 150, 86));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 12)); // NOI18N
        jLabel1.setText("NON-RAW PRODUCTS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 190, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("P_Num:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

        cbnum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 530, 90, -1));

        search.setBackground(new java.awt.Color(182, 150, 86));
        search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("SEARCH");

        javax.swing.GroupLayout searchLayout = new javax.swing.GroupLayout(search);
        search.setLayout(searchLayout);
        searchLayout.setHorizontalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(17, 17, 17))
        );
        searchLayout.setVerticalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 90, 40));

        display.setBackground(new java.awt.Color(182, 150, 86));
        display.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        display.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("DISPLAY");

        javax.swing.GroupLayout displayLayout = new javax.swing.GroupLayout(display);
        display.setLayout(displayLayout);
        displayLayout.setHorizontalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        displayLayout.setVerticalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(display, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 90, 40));

        delete.setBackground(new java.awt.Color(182, 150, 86));
        delete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("DELETE");

        javax.swing.GroupLayout deleteLayout = new javax.swing.GroupLayout(delete);
        delete.setLayout(deleteLayout);
        deleteLayout.setHorizontalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        deleteLayout.setVerticalGroup(
            deleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 90, 40));
        jPanel1.add(txtexp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 220, -1));
        jPanel1.add(txtu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 220, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Product Cost per Quantity:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));
        jPanel1.add(txtcpq, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 220, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Product Total Cost:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));
        jPanel1.add(txttc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 220, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Product Date of Purchase:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));
        jPanel1.add(txtdop, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 220, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Product Expiration Date:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));
        jPanel1.add(txtquan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 220, -1));
        jPanel1.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 220, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Product Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Product Unit:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Product Quantity:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jPanel6.setBackground(new java.awt.Color(182, 150, 86));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tblnonraw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Number", "Name", "Quantity", "Unit", "Cost per Quantity", "Total Cost", "Date of Purchase", "Expiration Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnonraw.setEnabled(false);
        jScrollPane1.setViewportView(tblnonraw);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 680, 640));

        update1.setBackground(new java.awt.Color(182, 150, 86));
        update1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        update1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update1MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("UPDATE");

        javax.swing.GroupLayout update1Layout = new javax.swing.GroupLayout(update1);
        update1.setLayout(update1Layout);
        update1Layout.setHorizontalGroup(
            update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(update1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        update1Layout.setVerticalGroup(
            update1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, update1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(update1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, 90, 40));

        jPanel7.setBackground(new java.awt.Color(182, 150, 86));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 240, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Main main = new Main();

        main.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
      ExpDate_Raw expraw = new ExpDate_Raw();
      
      expraw.setVisible(true);
      
      this.dispose();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
       
    }//GEN-LAST:event_jPanel3MouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        try {
            String ItemN = cbnum.getSelectedItem().toString();
            
            pst = con.prepareStatement("SELECT * FROM tblnonraw WHERE P_Num = ?");
            pst.setString(1,ItemN);
            rs = pst.executeQuery();
            
            if(rs.next() ==  true){
                txtname.setText(rs.getString(2));
                txtquan.setText(rs.getString(3));
                txtu.setText(rs.getString(4));
                txtcpq.setText(rs.getString(5));
                txttc.setText(rs.getString(6));
                txtdop.setText(rs.getString(7));
                txtexp.setText(rs.getString(8));     
            }else{
            JOptionPane.showMessageDialog(this, "No Record Found!");
                txtname.setText("");
                txtquan.setText("");
                txtu.setText("");
                txtcpq.setText("");        
                txttc.setText("");        
                txtdop.setText("");        
                txtexp.setText("");
                txtname.requestFocus();
                Fetch();
                LoadItemNo();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchMouseClicked

    private void displayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayMouseClicked
      Fetch();
    }//GEN-LAST:event_displayMouseClicked

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        try {
            String ItemN = cbnum.getSelectedItem().toString();
            pst = con.prepareStatement("DELETE FROM tblnonraw WHERE P_Num = ?");
            pst.setString(1, ItemN);

            int k = pst.executeUpdate();
            if (k == 1){
                JOptionPane.showMessageDialog(this, "Record has been succesfully deleted!");
                txtname.setText("");
                txtquan.setText("");
                txtu.setText("");
                txtcpq.setText("");
                txttc.setText("");
                txtdop.setText("");
                txtexp.setText("");
                txtname.requestFocus();
                Fetch();
                LoadItemNo();

                Statement stmt = con.createStatement();
                String countSQL = "SELECT COUNT(*) FROM tblnonraw";
                ResultSet rs = stmt.executeQuery(countSQL);
                rs.next();
                int rowCount = rs.getInt(1);

                if (rowCount == 0) {
                    String resetAutoIncrementSQL = "ALTER TABLE tblnonraw AUTO_INCREMENT = 1";
                    stmt.executeUpdate(resetAutoIncrementSQL);
                    JOptionPane.showMessageDialog(this, "Table is empty. AUTO_INCREMENT reset to 1.");
                }

            }else{
                JOptionPane.showMessageDialog(this, "Record failed to delete!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void update1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update1MouseClicked
       try {
           String itemname = txtname.getText();
            String itemquant = txtquan.getText();
            String itemunit = txtu.getText();
            String itemcpq = txtcpq.getText();
            String itemtc = txttc.getText();
            String itemdop = txtdop.getText();
            String itemexpd = txtexp.getText();
            String ItemN = cbnum.getSelectedItem().toString();
            
            pst = con.prepareStatement("UPDATE tblnonraw SET P_Name = ?, P_Quantity = ?, P_Unit = ?, P_CostPerQuantity = ?, P_TotalCost = ?, P_DateofPurchase = ?, P_ExpirationDate = ? WHERE P_Num = ?");
            
            pst.setString(1, itemname);
            pst.setString(2, itemquant);
            pst.setString(3, itemunit);
            pst.setString(4, itemcpq);
            pst.setString(5, itemtc);
            pst.setString(6, itemdop);
            pst.setString(7, itemexpd);
            pst.setString(8, ItemN);
            
            int k = pst.executeUpdate();
            
            if (k == 1){
            JOptionPane.showMessageDialog(this, "Record has been successfully updated!");
                txtname.setText("");
                txtquan.setText("");
                txtu.setText("");
                txtcpq.setText("");        
                txttc.setText("");        
                txtdop.setText("");        
                txtexp.setText("");
                txtname.requestFocus();
                Fetch();
                LoadItemNo();
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExpDate_NonRaw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_update1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExpDate_NonRaw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExpDate_NonRaw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExpDate_NonRaw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExpDate_NonRaw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExpDate_NonRaw().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbnum;
    private javax.swing.JPanel delete;
    private javax.swing.JPanel display;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel search;
    private javax.swing.JTable tblnonraw;
    private javax.swing.JTextField txtcpq;
    private javax.swing.JTextField txtdop;
    private javax.swing.JTextField txtexp;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtquan;
    private javax.swing.JTextField txttc;
    private javax.swing.JTextField txtu;
    private javax.swing.JPanel update1;
    // End of variables declaration//GEN-END:variables
}
