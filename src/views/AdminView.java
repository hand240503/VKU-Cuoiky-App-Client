package views;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.ClientHandleReceive;
import controller.ClientHandleSend;
import model.AdminProduct;
import model.Product_Price_Views;
import model.Stock;

public class AdminView extends javax.swing.JFrame {

	private List<AdminProduct> AdminProduct;
	private DefaultTableModel defaultTableModel;
	private ClientHandleSend clientHandleSend;
	private ClientHandleReceive clientHandleReceive;
	private Socket socket;

	public AdminView(List<AdminProduct> AdminProduct) {
		this.AdminProduct = AdminProduct;

		initComponents();

		try {
			socket = new Socket("localhost", 40123);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientHandleSend = new ClientHandleSend(socket);

		clientHandleReceive = new ClientHandleReceive(socket);
		clientHandleReceive.start();

		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable1.setModel(defaultTableModel);
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("ID Khách Hàng");
		defaultTableModel.addColumn("Tên Sản Phẩm");
		defaultTableModel.addColumn("Số Lượng");
		defaultTableModel.addColumn("Đơn Vị");
		defaultTableModel.addColumn("Giá");
		defaultTableModel.addColumn("Tổng Tiền");
		setdata(AdminProduct);
		setLocationRelativeTo(null);
	}

	public void setdata(List<AdminProduct> product) {
		float sum = 0;
		for (AdminProduct p : product) {
			int soluong = p.getQuantity() / p.getUnit();
			float gia = p.getValue() * (float) soluong;

			sum += gia;
			defaultTableModel.addRow(new Object[] { p.getIdOrderdetails(), p.getIdUser(), p.getNameProduct(), soluong,
					p.getNameUnit(), p.getValue(), gia });
		}

		int[] rightAlignedColumns = { 0, 1, 3, 5, 6 };
		for (int columnIndex : rightAlignedColumns) {
			jTable1.getColumnModel().getColumn(columnIndex).setCellRenderer(new RightAlignTableCellRenderer());
		}
		jlbTongTien.setText(String.valueOf(sum));
	}

	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		accept = new javax.swing.JButton();
		Stock = new javax.swing.JButton();
		Refresh = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jlbTongTien = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		accept.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		accept.setText("Xác Nhận");
		accept.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				acceptActionPerformed(evt);
			}
		});
		Stock.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		Stock.setText("Tồn Kho");
		Stock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StockActionPerformed(evt);
			}
		});
		Refresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		Refresh.setText("Refresh");
		Refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RefreshActionPerformed(evt);
			}
		});

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		jScrollPane1.setViewportView(jTable1);

		jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("QUẢN LÝ");

		jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jLabel2.setText("Tổng Tiền : ");

		jlbTongTien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 954,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(accept, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addComponent(Stock)
												.addGap(18, 18, 18).addComponent(Refresh))
										.addComponent(jlbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(26, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(18, Short.MAX_VALUE).addComponent(jLabel1).addGap(18, 18, 18)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jlbTongTien))
						.addGap(34, 34, 34)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(accept).addComponent(Stock).addComponent(Refresh))
						.addGap(30, 30, 30)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		pack();
	}

	private void StockActionPerformed(java.awt.event.ActionEvent evt) {
		clientHandleSend.getStock();
	}

	private void acceptActionPerformed(java.awt.event.ActionEvent evt) {
		clientHandleSend.getAccept();
	}

	private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {

		clientHandleSend.getListADProduct();
		this.dispose();
	}

	private javax.swing.JButton Refresh;
	private javax.swing.JButton Stock;
	private javax.swing.JButton accept;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JLabel jlbTongTien;

}
