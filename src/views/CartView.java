package views;

import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ClientHandleSend;
import model.UserDetailView;

public class CartView extends javax.swing.JFrame {

	private List<UserDetailView> detailView;
	private DefaultTableModel defaultTableModel;
	private Socket socket;
	private ClientHandleSend clientHandleSend;

	public CartView(List<UserDetailView> detailView, Socket socket) {
		initComponents();
		this.setLocationRelativeTo(null);
		this.socket = socket;
		clientHandleSend = new ClientHandleSend(socket);
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;

			}
		};
		jtb.setModel(defaultTableModel);
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Tên Sản Phẩm");
		defaultTableModel.addColumn("Tên Đơn Vị");
		defaultTableModel.addColumn("Số Lượng");
		defaultTableModel.addColumn("Giá");
		defaultTableModel.addColumn("Tổng Tiền");
		setdata(detailView);
	}

	public void setdata(List<UserDetailView> detailView) {
		for (UserDetailView uOrder : detailView) {
			int soluong = uOrder.getQuantity() / uOrder.getRatio();
			double gia = soluong * uOrder.getValue();
			defaultTableModel.addRow(new Object[] { uOrder.getId(), uOrder.getNameProduct(), uOrder.getUnit(), soluong,
					uOrder.getValue(), gia });
		}
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jtb = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jtb.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		jScrollPane1.setViewportView(jtb);

		jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Đơn Hàng Của Bạn");

		jButton1.setText("Back");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Hủy Đơn");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}

		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton2).addGap(27, 27, 27).addComponent(jButton1).addGap(54, 54, 54)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(44, Short.MAX_VALUE).addComponent(jLabel1)
						.addGap(18, 18, 18)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2))
						.addGap(26, 26, 26)));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = jtb.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			int comfirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy đơn ?");
			if (comfirm == JOptionPane.YES_NO_OPTION) {
				int id = Integer.valueOf(String.valueOf(jtb.getValueAt(selectedRow, 0)));
				clientHandleSend.cancelOrder(id);
			}
		}
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jtb;
}
