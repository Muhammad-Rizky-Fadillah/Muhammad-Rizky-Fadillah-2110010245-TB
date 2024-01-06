/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;
import static views.menuUtama.maximixed;
/**
 *
 * @author ACER
 */
public class menuPengembalianBarang extends javax.swing.JDialog {

       public final Connection conn = new koneksi().connect();

    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    
    // frame maks dan geser panel
    static boolean maximixed = true;
    int xMouse;
    int yMouse;
    
    private void aktif(){
        txtKodePart.setEnabled(true);
        txtNamaBarang.setEnabled(true);
        txtKategoriPart.setEnabled(true);
        txtJumlah.setEnabled(true);
    }
    
    protected void kosong(){
        txtKodePart.setText(null);
        txtNamaBarang.setText(null);
        txtKategoriPart.setText(null);
        txtJumlah.setText(null);
    }
    
    public void noTable(){
        int Baris = tabmode.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor +".",a,0);
        }
    }
    
    public void tanggal(){
        Date tgl = new Date();
        btnPilihTanggal.setDate(tgl);
    }
    
    
    public void dataTable(){
        Object[] Baris = {"No","Kode Part","Nama Part"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode);
        String sql = "select * from tb_barang order by kode_part asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String[] data = {"",kode_part,nama_part};
                tabmode.addRow(data);
                noTable();
            }
        } catch (Exception e){
        }
    }
    
    public void dataTable2(){
        Object[] Baris2 = {"Kode Kategori","Nama Kategori"};
        tabmode2 = new DefaultTableModel(null, Baris2);
        tabelKategori.setModel(tabmode2);
        String sql = "select * from tb_kategori order by kode_kategori asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String kode_kategori = hasil.getString("kode_kategori");
                String nama_kategori = hasil.getString("nama_kategori");
                String[] data = {kode_kategori,nama_kategori};
                tabmode2.addRow(data);
            }
        } catch (Exception e){
        }
    }
    
    
    public void lebarKolom(){
        TableColumn column;
        tabelBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = tabelBarang.getColumnModel().getColumn(0);
        column.setPreferredWidth(100);
        column = tabelBarang.getColumnModel().getColumn(1);
        column.setPreferredWidth(250);
        column = tabelBarang.getColumnModel().getColumn(2);
        column.setPreferredWidth(400);
    }
    
    public void lebarKolom2(){
        TableColumn column2;
        tabelKategori.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column2 = tabelKategori.getColumnModel().getColumn(0);
        column2.setPreferredWidth(100);
        column2 = tabelKategori.getColumnModel().getColumn(1);
        column2.setPreferredWidth(200);
    }
    
    public void pencarian(String sql){
        Object[] Baris = {"No","Tanggal","Kode Part","Nama Part","Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode);
        int brs = tabelBarang.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode.removeRow(1);
        }
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("tanggal");
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String keterangan = hasil.getString("keterangan");
                String[] data = {"",tanggal,kode_part,nama_part,keterangan};
                tabmode.addRow(data);
                noTable();
            }
        } catch(Exception e){
        }
    }
    
    public void pencarian2(String sql){
        Object[] Baris2 = {"Kode Part","Nama Part"};
        tabmode2 = new DefaultTableModel(null, Baris2);
        tabelKategori.setModel(tabmode2);
        int brs = tabelKategori.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode2.removeRow(1);
        }
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String[] data = {kode_part,nama_part};
                tabmode2.addRow(data);
            }
        } catch(Exception e){
            
        }
    }
    /**
     * Creates new form menuPengembalianBarang
     */
    public menuPengembalianBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        aktif();
        dataTable();
        tanggal();
        lebarKolom();
        dataTable2();
        lebarKolom2();
        txtKodePart.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        labelNama = new javax.swing.JLabel();
        lbTanggal = new javax.swing.JLabel();
        labelKodePart = new javax.swing.JLabel();
        labelNamaPart = new javax.swing.JLabel();
        labelKategori = new javax.swing.JLabel();
        txtKodePart = new javax.swing.JTextField();
        btnPilihTanggal = new com.toedter.calendar.JDateChooser();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        btnUbah = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtKategoriPart = new javax.swing.JTextField();
        labelQty = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        txtCariKategori = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panelHeader.setBackground(new java.awt.Color(204, 102, 255));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });
        panelHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelHeaderMousePressed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(204, 102, 255));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_4.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.setOpaque(true);
        btnClose.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_30px_5.png"))); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        labelNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNama.setForeground(new java.awt.Color(255, 255, 255));
        labelNama.setText("Data Pengembalian Barang");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNama, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbTanggal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTanggal.setText("Tanggal");
        lbTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKodePart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKodePart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKodePart.setText("Kode Part");
        labelKodePart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelNamaPart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNamaPart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNamaPart.setText("Nama Barang");
        labelNamaPart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelKategori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori.setText("Kategori");
        labelKategori.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtKodePart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKodePart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodePartKeyPressed(evt);
            }
        });

        btnPilihTanggal.setDateFormatString("dd-MM-yyyy");
        btnPilihTanggal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPilihTanggal.setMaximumSize(new java.awt.Dimension(2147400000, 2147400000));
        btnPilihTanggal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPilihTanggalMouseClicked(evt);
            }
        });
        btnPilihTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPilihTanggalKeyPressed(evt);
            }
        });

        txtNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaBarangKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(":");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(":");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(":");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tabelBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelBarang.setRowHeight(30);
        tabelBarang.setRowMargin(2);
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        btnUbah.setBackground(new java.awt.Color(204, 204, 204));
        btnUbah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUbah.setText("Simpan");
        btnUbah.setContentAreaFilled(false);
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.setOpaque(true);
        btnUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUbahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUbahMouseExited(evt);
            }
        });
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnBersih.setBackground(new java.awt.Color(204, 204, 204));
        btnBersih.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBersih.setText("Bersih");
        btnBersih.setContentAreaFilled(false);
        btnBersih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBersih.setOpaque(true);
        btnBersih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBersihMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBersihMouseExited(evt);
            }
        });
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Info_30px_6.png"))); // NOI18N
        jLabel1.setText("   Information");
        jLabel1.setOpaque(true);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("\n   * Form ini digunakan untuk menyimpan data\n      barang baru\n   * Tekan TAMBAH untuk menyimpan data\n   * Tekan UBAH untuk mengedit data\n   * Tekan BERSIH untuk membersihkan field\n   * Tekan HAPUS untuk menghapus data");
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        txtKategoriPart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKategoriPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKategoriPartActionPerformed(evt);
            }
        });
        txtKategoriPart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKategoriPartKeyPressed(evt);
            }
        });

        labelQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelQty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelQty.setText("Qty");
        labelQty.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(":");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtJumlah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahKeyTyped(evt);
            }
        });

        txtCariKategori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCariKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKategoriKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Kategori");
        jLabel3.setOpaque(true);

        tabelKategori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tabelKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelKategori.setRowHeight(30);
        tabelKategori.setRowMargin(2);
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelKategori);

        txtCari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        btnCari.setBackground(new java.awt.Color(204, 204, 204));
        btnCari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCari.setText("Pencarian");
        btnCari.setContentAreaFilled(false);
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.setOpaque(true);
        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCariMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCariMouseExited(evt);
            }
        });
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtKategoriPart, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnBersih, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnPilihTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                                .addComponent(txtJumlah))
                                            .addComponent(txtKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(labelKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)))
                                .addGap(10, 10, 10)
                                .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBersih, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPilihTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKategoriPart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        btnClose.setBackground(Color.red);
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        btnClose.setBackground(new Color(204,102,255));
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void panelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseDragged
        if(maximixed){
            int x = evt.getXOnScreen();
            int y = evt.getYOnScreen();
            this.setLocation(x - xMouse, y - yMouse);
        }
    }//GEN-LAST:event_panelHeaderMouseDragged

    private void panelHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelHeaderMousePressed

    private void txtKodePartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodePartKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            txtNamaBarang.requestFocus();
        }
    }//GEN-LAST:event_txtKodePartKeyPressed

    private void btnPilihTanggalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihTanggalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPilihTanggalMouseClicked

    private void btnPilihTanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPilihTanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPilihTanggalKeyPressed

    private void txtNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaBarangKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            txtKategoriPart.requestFocus();
        }
    }//GEN-LAST:event_txtNamaBarangKeyPressed

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int bar = tabelBarang.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();


        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        try{
            dateValue = date.parse((String)tabelBarang.getValueAt(bar, 1));
        } catch (ParseException ex){
            Logger.getLogger(dataBarang.class.getName()).log(Level.SEVERE, null, ex);
        }

 
        txtKodePart.setText(b);
        txtNamaBarang.setText(c);
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void btnUbahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbahMouseEntered
        btnUbah.setBackground(new Color(0,0,204));
        btnUbah.setForeground(Color.white);
    }//GEN-LAST:event_btnUbahMouseEntered

    private void btnUbahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbahMouseExited
        btnUbah.setBackground(new Color(204,204,204));
        btnUbah.setForeground(Color.black);
    }//GEN-LAST:event_btnUbahMouseExited

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String sql = "update tb_barang set tanggal=?,kode_part=?,nama_part=?,kategori=?,jumlah=jumlah+? where  kode_part='"+txtKodePart.getText()+"'";
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(btnPilihTanggal.getDate()));
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tanggal.toString());
            stat.setString(2, txtKodePart.getText());
            stat.setString(3, txtNamaBarang.getText());
            stat.setString(4, txtKategoriPart.getText());
            stat.setString(5, txtJumlah.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            //            String refresh = "select * from tb_barang";
            kosong();
            dataTable();
            lebarKolom();
            txtKodePart.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnBersihMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBersihMouseEntered
        btnBersih.setBackground(new Color(0,0,204));
        btnBersih.setForeground(Color.white);
    }//GEN-LAST:event_btnBersihMouseEntered

    private void btnBersihMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBersihMouseExited
        btnBersih.setBackground(new Color(204,204,204));
        btnBersih.setForeground(Color.black);
    }//GEN-LAST:event_btnBersihMouseExited

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        tanggal();
        txtKodePart.requestFocus();
        txtKodePart.setText(null);
        txtNamaBarang.setText(null);
        txtKategoriPart.setText(null);
        txtJumlah.setText(null);
    }//GEN-LAST:event_btnBersihActionPerformed

    private void btnCariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseEntered
        btnCari.setBackground(new Color(0,0,204));
        btnCari.setForeground(Color.white);
    }//GEN-LAST:event_btnCariMouseEntered

    private void btnCariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseExited
        btnCari.setBackground(new Color(204,204,204));
        btnCari.setForeground(Color.black);
    }//GEN-LAST:event_btnCariMouseExited

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed

    }//GEN-LAST:event_btnCariActionPerformed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        String sqlPencarian = "select * from tb_barang where kode_part like '%"+txtCari.getText()+"%' or nama_part like '%"+txtCari.getText()+"%'";
        pencarian(sqlPencarian);
        lebarKolom();
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtKategoriPartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKategoriPartKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            txtJumlah.requestFocus();
        }
    }//GEN-LAST:event_txtKategoriPartKeyPressed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed
    }//GEN-LAST:event_txtJumlahKeyPressed

    private void txtJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyTyped
        char enter=evt.getKeyChar();
        if(!(Character.isDigit(enter)))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka 0-9", "Input Qty", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtJumlahKeyTyped

    private void txtCariKategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKategoriKeyTyped
        String sqlPencarian2 = "select * from tb_kategori where kode_kategori like '%"+txtCariKategori.getText()+"%' or nama_kategori like '%"+txtCariKategori.getText()+"%'";
        pencarian2(sqlPencarian2);
        lebarKolom2();
    }//GEN-LAST:event_txtCariKategoriKeyTyped

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        int bar = tabelKategori.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 0).toString();
        String b = tabmode2.getValueAt(bar, 1).toString();
        
        txtKategoriPart.setText(b);
        txtKategoriPart.requestFocus();
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void txtKategoriPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKategoriPartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKategoriPartActionPerformed

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
            java.util.logging.Logger.getLogger(menuPengembalianBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuPengembalianBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuPengembalianBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuPengembalianBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menuPengembalianBarang dialog = new menuPengembalianBarang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClose;
    private com.toedter.calendar.JDateChooser btnPilihTanggal;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelKategori;
    private javax.swing.JLabel labelKodePart;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelNamaPart;
    private javax.swing.JLabel labelQty;
    private javax.swing.JLabel lbTanggal;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariKategori;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKategoriPart;
    private javax.swing.JTextField txtKodePart;
    private javax.swing.JTextField txtNamaBarang;
    // End of variables declaration//GEN-END:variables
}
