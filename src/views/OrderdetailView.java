package views;

import java.net.Socket;

import javax.swing.JOptionPane;

import controller.ClientHandleSend;
import model.DetailView;

public class OrderdetailView extends javax.swing.JFrame {

	private DetailView detailView;
	private ClientHandleSend clientHandleSend;
	private int num;

	public OrderdetailView(DetailView detailView, Socket socket) {
		this.detailView = detailView;
		clientHandleSend = new ClientHandleSend(socket);
		initComponents();
		num = detailView.getQuantity() / detailView.getRatio();
		setLocationRelativeTo(null);
		jlbName.setText(detailView.getNameProduct());
		jlbUnit.setText(detailView.getNameUnit());
		jlbNum.setText(num + "");
		jlbTong.setText("0");
		jLabel8.setText(detailView.getValue() + "");
	}

	private void initComponents() {

		jToggleButton1 = new javax.swing.JToggleButton();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jlbName = new javax.swing.JLabel();
		jlbUnit = new javax.swing.JLabel();
		jlbNum = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		btnOrder = new javax.swing.JButton();
		btnExit = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jlbTong = new javax.swing.JLabel();

		jToggleButton1.setText("jToggleButton1");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setBackground(new java.awt.Color(255, 255, 255));
		jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Sản Phẩm");

		jLabel2.setText("Tên sản phẩm : ");

		jLabel3.setText("Đơn vị : ");

		jLabel4.setText("Số lượng hiện có : ");

		jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				jTextField1KeyReleased(evt);
			}
		});

		jLabel5.setText("Số lượng :");

		btnOrder.setText("Đặt Hàng");
		btnOrder.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOrderActionPerformed(evt);
			}
		});

		btnExit.setText("Hủy");
		btnExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
		});

		jLabel6.setText("Tổng tiền : ");

		jLabel7.setText("Giá : ");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(16, 16, 16)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
												.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(
														jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(jLabel6))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jlbNum, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jlbName, javax.swing.GroupLayout.DEFAULT_SIZE,
																130, Short.MAX_VALUE)
														.addComponent(jlbUnit, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 225,
														Short.MAX_VALUE)
												.addGroup(jPanel1Layout.createSequentialGroup().addComponent(btnOrder)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(btnExit))
												.addComponent(jlbTong, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(0, 203, Short.MAX_VALUE)))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(40, 40, 40)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jlbName))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(jlbUnit, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4).addComponent(jlbNum))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel7).addComponent(jLabel8))
						.addGap(9, 9, 9)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(13, 13, 13)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel6).addComponent(jlbTong))
						.addGap(30, 30, 30)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnOrder).addComponent(btnExit))
						.addContainerGap(97, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {
		if (Integer.valueOf(jTextField1.getText()) <= num && Integer.valueOf(jTextField1.getText()) > 0) {
			int soluong = Integer.valueOf(jTextField1.getText());

			clientHandleSend.createOrderDetail(detailView.getIdproduct(), detailView.getIdPrice(),
					soluong * detailView.getRatio());
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Số lượng không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {
		String input = jTextField1.getText().trim();

		if (input.isEmpty() || !input.matches("\\d*\\.?\\d+")) {
			jlbTong.setText("0.0");
		} else {
			double sl = Double.parseDouble(input);
			if (sl <= 0 || sl > num) {
				jlbTong.setText("0.0");
			} else {
				double gia = sl * detailView.getValue();
				jlbTong.setText(String.valueOf(gia));
			}
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnExit;
	private javax.swing.JButton btnOrder;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JToggleButton jToggleButton1;
	private javax.swing.JLabel jlbName;
	private javax.swing.JLabel jlbNum;
	private javax.swing.JLabel jlbTong;
	private javax.swing.JLabel jlbUnit;
	// End of variables declaration

}
