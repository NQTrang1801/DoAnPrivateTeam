/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoanClass;
import model.TaiKhoanCtr;

/**
 *
 * @author DLHa
 */
public class QuanLyTK extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public QuanLyTK(String maTK, String loaiTK) {
        initComponents();
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        if (loaiTK.equals("quan ly") == false) {
            txtMaNV.setEditable(false); // không cho phép chỉnh sửa
            txtMaNV.setEnabled(false); // không cho phép nhập liệu
            txtMaTK.setEditable(false); // không cho phép chỉnh sửa
            txtMaTK.setEnabled(false); // không cho phép nhập liệu
            if (loaiTK.equals("nhan vien ban hang")){
                jRNVK.setEnabled(false);
                jRQL.setEnabled(false);
            }else if (loaiTK.equals("nhan vien kho")){
                jRNVBH.setEnabled(false);
                jRQL.setEnabled(false);
            }else if (loaiTK.equals("quan ly")){
                jRNVK.setEnabled(false);
                jRQL.setEnabled(false);
            }
            jBThem.setEnabled(false);
            jBXoa.setEnabled(false);
        }
        this.setLocationRelativeTo(this);
        KetNoiCSDL();
        Load_data_TK();
    }

    public void KetNoiCSDL() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //Load lớp Oracle JDBC driver bằng cách sử dụng phương thức static `forName()` của lớp Class
            System.out.print("Ket noi thanh cong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyTK.class.getName()).log(Level.SEVERE, null, ex);    //Xử lý ngoại lệ ClassNotFoundException và hiển thị chi tiết lỗi lên bảng điều khiển
        }   // Bắt đầu khối try-catch để bắt ngoại lệ ClassNotFoundException, trong trường hợp không tìm thấy lớp jdbc driver để kết nối với cơ sở dữ liệu
        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl21", "PrivateTeam", "12345678");
            //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTK.class.getName()).log(Level.SEVERE, null, ex);
        }   //  Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp kết nối cơ sở dữ liệu thất bại

    }

    public void Load_data_TK() {
        // đoạn code sử dụng để tải dữ liệu từ cơ sở dữ liệu và hiển thị chúng trong bảng `jTListTK
        try {
            if (loaiTK.equals("quan ly"))   //Nếu loại tài khoản là "quan ly", thì lấy tất cả các bản ghi trong bảng
            {
                ps = conn.prepareStatement("SELECT * FROM TAIKHOAN ");
            } else   //nếu loại tài khoản là "nhan vien ban hang" hoặc "nhan vien kho", thì chỉ lấy bản ghi có mã tài khoản tương ứng với mã tài khoản của người dùng đăng nhập
            {
                ps = conn.prepareStatement("SELECT * FROM TAIKHOAN where MaTK = " + maTK);
            }
            rs = ps.executeQuery(); //thực hiện câu truy vấn lưu kết quả vào rs

            ResultSetMetaData rsd = rs.getMetaData();   //lấy thông tin về các cột trong kết quả truy vấn
            int c = rsd.getColumnCount();  //lưu trữ số lượng cột vào biến `c`

            DefaultTableModel model = (DefaultTableModel) jTListTK.getModel();   // Lấy một tham chiếu đến mô hình dữ liệu của bảng `jTListTK`
            model.setRowCount(0);   // xóa tất cả các dòng trong bảng để chuẩn bị cho việc tải dữ liệu mới

            while (rs.next())
            {
                Vector v1 = new Vector();   //tạo một đối tượng `Vector` và thêm các giá trị của các cột trong bản ghi vào đó
                for (int i = 1; i <= c; i++) {
                    v1.add(rs.getString("MaTK"));
                    v1.add(rs.getString("TenTK"));
                    v1.add(rs.getString("MatKhau"));
                    v1.add(rs.getString("LoaiTK"));
                    v1.add(rs.getString("MaNV"));
                }
                model.addRow(v1);   //Thêm đối tượng `Vector` vừa tạo vào `DefaultTableModel`
                jTListTK.setModel(model);   //cập nhật lại bảng `jTListTK` để hiển thị tất cả các bản ghi được tải
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaTK.setText("");
        txtTenTK.setText("");
        txtMatKhau.setText("");
        jRNVBH.setSelected(false);
        jRNVK.setSelected(false);
        jRQL.setSelected(false);
        txtMaNV.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListTK = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBTimKiem = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JTextField();
        txtTenTK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBLamMoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jRNVBH = new javax.swing.JRadioButton();
        jRNVK = new javax.swing.JRadioButton();
        jRQL = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN TÀI KHOẢN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(197, 110, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH TÀI KHOẢN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTListTK.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Tên tài khoản ", "Mật khẩu", "Loại tài khoản ", "Mã nhân viên"
            }
        ));
        jTListTK.setGridColor(new java.awt.Color(204, 204, 204));
        jTListTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListTKMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListTK);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã tài khoản");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Tên tài khoản ");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Mật khẩu ");

        txtMaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTKActionPerformed(evt);
            }
        });

        jBThem.setBackground(new java.awt.Color(103, 242, 158));
        jBThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBThem.setText("INSERT");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        jBXoa.setBackground(new java.awt.Color(241, 72, 72));
        jBXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBXoa.setText("DELETE");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        jBSua.setBackground(new java.awt.Color(220, 220, 85));
        jBSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBSua.setText("UPDATE");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        jBTimKiem.setBackground(new java.awt.Color(196, 101, 38));
        jBTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBTimKiem.setText("Search");
        jBTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTimKiemActionPerformed(evt);
            }
        });

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Loại tài khoản");

        jBLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        jBLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBLamMoi.setText("REFRESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Mã nhân viên");

        buttonGroup1.add(jRNVBH);
        jRNVBH.setText("nhan vien ban hang");

        buttonGroup1.add(jRNVK);
        jRNVK.setText("nhan vien kho");

        buttonGroup1.add(jRQL);
        jRQL.setText("quan ly");
        jRQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRQLActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jBThem))
                                .addComponent(jLabel9))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jBLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                    .addGap(50, 50, 50)
                                    .addComponent(jBSua))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(29, 29, 29)
                                    .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRNVBH)
                                        .addComponent(jRNVK)
                                        .addComponent(jRQL)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(txtTenTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbTroLai)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel8))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRNVBH)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRNVK)
                                .addGap(10, 10, 10)
                                .addComponent(jRQL)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbTroLai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ  TÀI KHOẢN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTListTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListTKMouseClicked
        // đoạn code này dùng để hiển thị thông tin tài khoản khi người dùng click chuột vào một dòng trong bảng jTListTK

        LamMoi();   //làm mới các trường nhập liệu và các nút trên giao diện `QuanLyTK`
        int selectedIndex = jTListTK.getSelectedRow();   // Lấy chỉ số của dòng được chọn trong bảng `jTListTK`
        jTListTK.setColumnSelectionInterval(0, 4);  //Thiết lập cột được chọn trong bảng `jTListTK` từ cột 0 đến cột 4
        txtMaTK.setText(jTListTK.getValueAt(selectedIndex, 0).toString());  //hiển thị giá trị mã tài khoản 
        txtTenTK.setText(jTListTK.getValueAt(selectedIndex, 1).toString());
        txtMatKhau.setText(jTListTK.getValueAt(selectedIndex, 2).toString());
        if (jTListTK.getValueAt(selectedIndex, 3).toString().equals("nhan vien ban hang"))   //Kiểm tra giá trị của loại tài khoản và thiết lập nút lựa chọn tương ứng
        {
            jRNVBH.setSelected(true);
            jRNVK.setSelected(false);
            jRQL.setSelected(false);
        } else if (jTListTK.getValueAt(selectedIndex, 3).toString().equals("nhan vien kho"))
        { 
            jRNVK.setSelected(true);
            jRNVBH.setSelected(false);
            jRQL.setSelected(false);
        } 
        else if (jTListTK.getValueAt(selectedIndex, 3).toString().equals("quan ly")){ 
            jRQL.setSelected(true);
            jRNVBH.setSelected(false);
            jRNVK.setSelected(false);
        }
        txtMaNV.setText(jTListTK.getValueAt(selectedIndex, 4).toString());
    }//GEN-LAST:event_jTListTKMouseClicked

    private void txtMaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTKActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một tài khoản mới vào cơ sở dữ liệu      
        StringBuilder sb = new StringBuilder();    //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaTK.getText().equals(""))  //Kiểm tra nếu mã tài khoản để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("Mã tài khoản không được để trống!!!");
            txtMaTK.setBackground(Color.red);
        } else {
            txtMaTK.setBackground(Color.white);
        }
        if (sb.length() > 0)   //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó và không thực hiện thêm tài khoản mới vào cơ sở dữ liệu
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            TaiKhoanClass tk = new TaiKhoanClass();
            //thiết lập các giá trị và các nút lựa chọn 
            tk.setMaTK(txtMaTK.getText());
            tk.setTenTK(txtTenTK.getText());
            tk.setMatKhau(txtMatKhau.getText());
            if (jRNVBH.isSelected()) {
                tk.setLoaiTK(jRNVBH.getText());
            }
            if (jRNVK.isSelected()) {
                tk.setLoaiTK(jRNVK.getText());
            }
            if (jRQL.isSelected()) {
                tk.setLoaiTK(jRQL.getText());
            }
            tk.setMaNV(txtMaNV.getText());
            TaiKhoanCtr.InsertTK(tk);   //Gọi phương thức `InsertTK()` trong lớp `TaiKhoanCtr` để thêm đối tượng `TaiKhoanClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Đã thêm thành công!");  //Hiển thị thông báo xác nhận nếu thêm tài khoản mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());   //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm tài khoản mới
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một tài khoản trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaTK.getText().equals("")) {
            sb.append("Mã tài khoản không được để trống!!!");
            txtMaTK.setBackground(Color.red);
        } else {
            txtMaTK.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài khoản không?", "Xóa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)   //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa tài khoản hay không
            {
                TaiKhoanCtr.DeleteTK(txtMaTK.getText());  //gọi phương thức `DeleteTK()` trong lớp `TaiKhoanCtr` để xóa tài khoản tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã xóa tài khoản thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản chưa được xóa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật một tài khoản trong cơ sở dữ liệu

        StringBuilder sb = new StringBuilder();
        if (txtMaTK.getText().equals("")) {
            sb.append("Mã tài khoản không được để trống!!!");
            txtMaTK.setBackground(Color.red);
        } else {
            txtMaTK.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            TaiKhoanClass tk = new TaiKhoanClass();
            tk.setMaTK(txtMaTK.getText());
            tk.setTenTK(txtTenTK.getText());
            tk.setMatKhau(txtMatKhau.getText());
            if (jRNVBH.isSelected()) {
                tk.setLoaiTK(jRNVBH.getText());
            }
            if (jRNVK.isSelected()) {
                tk.setLoaiTK(jRNVK.getText());
            }
            if (jRQL.isSelected()) {
                tk.setLoaiTK(jRQL.getText());
            }
            tk.setMaNV(txtMaNV.getText());
            int result = JOptionPane.showConfirmDialog(null, "Bạn có cập nhật tài khoản không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                TaiKhoanCtr.UpdateTK(tk);  //Gọi phương thức `UpdateTK()` trong lớp `TaiKhoanCtr` để cập nhật đối tượng `TaiKhoanClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Chưa cập nhật!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
        // Đoạn code này dùng để tìm kiếm một tài khoản trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaTK.getText().equals("")) {
            sb.append("Mã tài khoản không được để trống!");
            txtMaTK.setBackground(Color.red);
        } else {
            txtMaTK.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            TaiKhoanCtr tkc = new TaiKhoanCtr();
            TaiKhoanClass TK = tkc.find(txtMaTK.getText());  //sử dụng phương thức `find()` để tìm kiếm tài khoản có mã `txtMaTK` trong cơ sở dữ liệu

            if (TK != null)   //Kiểm tra xem tài khoản được tìm thấy hay không
            {
                //hiển thị thông tin của tài khoản
                int selectedIndex = jTListTK.getSelectedRow();

                txtMaTK.setText(TK.getMaTK());
                txtTenTK.setText(TK.getTenTK());
                txtMatKhau.setText(TK.getMatKhau());
                if (TK.getLoaiTK().equals("nhan vien ban hang")) {
                    jRQL.setSelected(false);
                    jRNVK.setSelected(false);
                    jRNVBH.setSelected(true);
                } else if (TK.getLoaiTK().equals("nhan vien kho")) {
                    jRQL.setSelected(false);
                    jRNVBH.setSelected(false);
                    jRNVK.setSelected(true);
                }
                else if (TK.getLoaiTK().equals("quan ly")) {
                    jRNVK.setSelected(false);
                    jRNVBH.setSelected(false);
                    jRQL.setSelected(true);
                }
                txtMaNV.setText(TK.getMaNV());
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
       // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách tài khoản và các trường nhập liệu
        LamMoi();   //làm mới các trường nhập liệu
        Load_data_TK();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách
    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void jRQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRQLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRQLActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã tài khoản mới và hiển thị mã đó lên trường nhập liệu "txtMaTK"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaTK() as newMaTK from dual";    //Tạo câu truy vấn SQL để tìm kiếm một mã tài khoản mới
            PreparedStatement ps = con.prepareStatement(SQL);   //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();    //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())   //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaTK.setText(rs.getString("NEWMATK"));   //Lấy giá trị của cột "newMaTK" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaTK"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    public static void main(String maTK, String loaiTK) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyTK(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBTimKiem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRNVBH;
    private javax.swing.JRadioButton jRNVK;
    private javax.swing.JRadioButton jRQL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListTK;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtTenTK;
    // End of variables declaration//GEN-END:variables
}