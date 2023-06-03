package views;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import model.Product_Price_Views;

public class UserViews extends javax.swing.JFrame {
	private DefaultTableModel defaultTableModel;
	private List<Product_Price_Views> pViews;
	private int idUser;
	private int idOrder = 0;

	public UserViews(List<Product_Price_Views> pViews, int idUser) {
		this.pViews = pViews;
		this.idUser = idUser;
		initComponents();
		setLocationRelativeTo(null);
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtbProduct.setModel(defaultTableModel);
		defaultTableModel.addColumn("ID Sản Phẩm");
		defaultTableModel.addColumn("Tên Sản Phẩm");
		defaultTableModel.addColumn("Tên Đơn Vị");
		defaultTableModel.addColumn("Tỉ Lệ");
		defaultTableModel.addColumn("ID  Giá");
		defaultTableModel.addColumn("Giá Sản Phẩm");
		setdata(pViews);
	}

	public void setdata(List<Product_Price_Views> pViews) {
		for (Product_Price_Views pView : pViews) {
			defaultTableModel.addRow(new Object[] { pView.getIdProduct(), pView.getNameProduct(), pView.getNameUnit(),
					pView.getRatio(), pView.getIdPrice(), pView.getPrice() });
		}

	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jtbProduct = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jbtnAddProduct = new javax.swing.JButton();
		btnYourOrder = new javax.swing.JButton();
		btnRefresh = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jtbProduct.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		jScrollPane1.setViewportView(jtbProduct);

		jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Cửa Hàng Của Hạ");

		jbtnAddProduct.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jbtnAddProduct.setText("Đặt Hàng");
		jbtnAddProduct.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtnAddProductActionPerformed(evt);
			}
		});

		btnYourOrder.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		btnYourOrder.setText("Đơn Hàng Của Bạn");
		btnYourOrder.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnYourOrderActionPerformed(evt);
			}
		});

		btnRefresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		btnRefresh.setText("Refresh");
		btnRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRefreshActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 899,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 899,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(23, 23, 23)
										.addComponent(jbtnAddProduct).addGap(32, 32, 32).addComponent(btnYourOrder)
										.addGap(32, 32, 32).addComponent(btnRefresh)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(29, 29, 29).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(34, 34, 34)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jbtnAddProduct).addComponent(btnYourOrder).addComponent(btnRefresh))
						.addContainerGap(78, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void jbtnAddProductActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = jtbProduct.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			int comfirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đặt hàng ?");
			if (comfirm == JOptionPane.YES_NO_OPTION) {

//				LocalDate currentDate = LocalDate.now();
//				System.out.println("Ngày giờ hiện tại: " + currentDate);
				if (idOrder == 0) {
					
				}
			}
		}
	}

	private void btnYourOrderActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {

	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnRefresh;
	private javax.swing.JButton btnYourOrder;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jtbProduct;
	private javax.swing.JButton jbtnAddProduct;
	// End of variables declaration
}
