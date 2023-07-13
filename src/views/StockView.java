package views;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.ClientHandleReceive;
import controller.ClientHandleSend;
import model.Stock;

public class StockView extends javax.swing.JFrame {

	private DefaultTableModel defaultTableModel;
	private ClientHandleSend clientHandleSend;
	private ClientHandleReceive clientHandleReceive;
	private Socket socket;
	private List<Stock> listStocks;

	public StockView(List<Stock> listStocks) {
		this.listStocks = listStocks;
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
		defaultTableModel.addColumn("Tên Sản Phẩm");
		defaultTableModel.addColumn("Còn Lại");
		defaultTableModel.addColumn("Tỉ Lệ");
		setdata(listStocks);
		setLocationRelativeTo(null);
	}

	public void setdata(List<Stock> list) {
		for (Stock item : list) {
			defaultTableModel.addRow(
					new Object[] { item.getId(), item.getNameProduct(), item.getSum_end(), item.getRatio_unit() });
		}
		int[] rightAlignedColumns = { 0, 2, 3 };
		for (int columnIndex : rightAlignedColumns) {
			jTable1.getColumnModel().getColumn(columnIndex).setCellRenderer(new RightAlignTableCellRenderer());
		}
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		Refresh = new javax.swing.JButton();
		Back = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		jScrollPane1.setViewportView(jTable1);

		Refresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		Refresh.setText("Refresh");
		Refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RefreshActionPerformed(evt);
			}
		});

		Back.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		Back.setText("Back");
		Back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BackActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Thống Kê Sản Phẩm");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(Refresh).addGap(18, 18, 18)
										.addComponent(Back).addGap(0, 0, Short.MAX_VALUE))
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE).addComponent(jLabel1)
								.addGap(18, 18, 18)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(Refresh).addComponent(Back))
								.addGap(20, 20, 20)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
	}

	private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {
		clientHandleSend.getStock();
		this.dispose();
	}

	private void BackActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private javax.swing.JButton Back;
	private javax.swing.JButton Refresh;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;

}
