package controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.AdminProduct;
import model.DetailView;
import model.Product_Price_Views;
import model.Stock;
import model.UserDetailView;
import views.AcceptOrderView;
import views.AdminView;
import views.CartView;
import views.LoginView;
import views.OrderdetailView;
import views.StockView;
import views.UserViews;

public class ClientHandleReceive extends Thread {

	private Socket socket;
	private DataInputStream dis;
	private int idOrder;
	private int sumEnd;
	private int idUserRecevie;
	private List<UserDetailView> listOrder = new ArrayList<>();
	boolean check = false;
	private List<AdminProduct> list;

	public ClientHandleReceive(Socket socket) {
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			synchronized (dis) {
				while (!socket.isClosed()) {
					int jsonLength = dis.readInt();
					byte[] jsonBytes = new byte[jsonLength];
					dis.readFully(jsonBytes);
					String json = new String(jsonBytes);
					JsonElement jsonElement = JsonParser.parseString(json);
					JsonObject jsonObject = jsonElement.getAsJsonObject();
					String notifyMode = jsonObject.get("notify").getAsString();
					System.out.println(jsonObject);
					if (notifyMode.equals("login-success-mode-1")) {
						idUserRecevie = Integer.parseInt(jsonObject.get("content").getAsString());
						List<Product_Price_Views> pViews = handleProductViews(jsonObject);
						socket.close();
						dis.close();
						new UserViews(pViews, idUserRecevie).setVisible(true);
					}
					if (notifyMode.equals("login-success-mode-2")) {
						List<AdminProduct> list = handleOrderAd(jsonObject);

						new AdminView(list).setVisible(true);
					}
					if (notifyMode.equals("send-list-ad-pro")) {
						List<AdminProduct> list = handleOrderAd(jsonObject);
						new AdminView(list).setVisible(true);
					}
					if (notifyMode.equals("send-list-none-accept")) {
						List<AdminProduct> list = handleNoneOrderAd(jsonObject);
						new AcceptOrderView(list).setVisible(true);
					}
					if (notifyMode.equals("send-list-ad-stock")) {
						List<Stock> list = handleStockAd(jsonObject);
						new StockView(list).setVisible(true);
					}
					if (notifyMode.equals("login-failed")) {
						String notifyContent = jsonObject.get("content").getAsString();
						JOptionPane.showMessageDialog(null, notifyContent, "Thông báo", JOptionPane.ERROR_MESSAGE);
						socket.close();
						dis.close();
						new LoginView().setVisible(true);
					}
					if (notifyMode.equals("view-detail")) {
						JsonElement data = jsonObject.get("data");
						JsonObject dataObject = data.getAsJsonObject();

						int idProduct = dataObject.get("idproduct").getAsInt();
						String nameProduct = dataObject.get("nameProduct").getAsString();
						String nameUnit = dataObject.get("nameUnit").getAsString();
						int ratio = dataObject.get("ratio").getAsInt();
						double value = dataObject.get("value").getAsDouble();
						int quantity = dataObject.get("quantity").getAsInt();
						int idUser = dataObject.get("idUser").getAsInt();
						int idPrice = dataObject.get("idPrice").getAsInt();

						DetailView detailView = new DetailView();
						detailView.setIdproduct(idProduct);
						detailView.setNameProduct(nameProduct);
						detailView.setNameUnit(nameUnit);
						detailView.setQuantity(quantity);
						detailView.setRatio(ratio);
						detailView.setValue(value);
						detailView.setIdUser(idUser);
						detailView.setIdPrice(idPrice);

						new OrderdetailView(detailView, socket).setVisible(true);
					}

					if (notifyMode.equals("list-userOrder")) {
						JsonArray data = jsonObject.get("data").getAsJsonArray();
						List<UserDetailView> list = new ArrayList<>();
						int idUser = jsonObject.get("id").getAsInt();
						for (JsonElement element : data) {
							JsonObject dataObject = element.getAsJsonObject();
							UserDetailView userDetailView = new UserDetailView();
							userDetailView.setId(dataObject.get("id").getAsInt());
							userDetailView.setNameProduct(dataObject.get("nameProduct").getAsString());
							userDetailView.setQuantity(dataObject.get("quantity").getAsInt());
							userDetailView.setRatio(dataObject.get("ratio").getAsInt());
							userDetailView.setUnit(dataObject.get("unit").getAsString());
							userDetailView.setValue(dataObject.get("value").getAsDouble());
							list.add(userDetailView);
						}
						new CartView(list, socket, idUser, this).setVisible(true);

					}

					if (notifyMode.equals("not-cancel")) {
						JOptionPane.showMessageDialog(null, "Đơn đã được cửa hàng xác nhận", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
					}
					if(notifyMode.equals("do-not-accept")) {
						JOptionPane.showMessageDialog(null, "Khách Hàng đã hủy đơn ", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}

		} catch (IOException e) {
			System.out.println("Không có gì");
		}

	}

	public List<Product_Price_Views> handleProductViews(JsonObject jsonObject) throws IOException {
		JsonArray data = jsonObject.get("data").getAsJsonArray();
		List<Product_Price_Views> pViews = new ArrayList<>();
		for (JsonElement element : data) {
			JsonObject dataObject = element.getAsJsonObject();
			Product_Price_Views pView = new Product_Price_Views();
			pView.setIdProduct(dataObject.get("idProduct").getAsInt());
			pView.setNameProduct(dataObject.get("nameProduct").getAsString());
			pView.setNameUnit(dataObject.get("nameUnit").getAsString());
			pView.setRatio(dataObject.get("ratio").getAsInt());
			pView.setIdPrice(dataObject.get("idPrice").getAsInt());
			pView.setPrice(dataObject.get("price").getAsDouble());
			pViews.add(pView);
		}
		return pViews;
	}

	public List<AdminProduct> handleOrderAd(JsonObject jsonObject) throws IOException {
		JsonArray data = jsonObject.get("data").getAsJsonArray();
		List<AdminProduct> list = new ArrayList<>();
		for (JsonElement element : data) {
			JsonObject dataObject = element.getAsJsonObject();
			AdminProduct adminProduct = new AdminProduct();
			adminProduct.setIdOrderdetails(dataObject.get("idOrderdetails").getAsInt());
			adminProduct.setIdUser(dataObject.get("idUser").getAsInt());
			adminProduct.setNameProduct(dataObject.get("nameProduct").getAsString());
			adminProduct.setQuantity(dataObject.get("quantity").getAsInt());
			adminProduct.setNameUnit(dataObject.get("nameUnit").getAsString());
			adminProduct.setValue(dataObject.get("value").getAsFloat());
			adminProduct.setUnit(dataObject.get("unit").getAsInt());
			list.add(adminProduct);
		}
		return list;
	}

	public List<AdminProduct> handleNoneOrderAd(JsonObject jsonObject) throws IOException {
		JsonArray data = jsonObject.get("data").getAsJsonArray();
		List<AdminProduct> list = new ArrayList<>();
		for (JsonElement element : data) {
			JsonObject dataObject = element.getAsJsonObject();
			AdminProduct adminProduct = new AdminProduct();
			adminProduct.setIdOrderdetails(dataObject.get("idOrderdetails").getAsInt());
			adminProduct.setIdUser(dataObject.get("idUser").getAsInt());
			adminProduct.setNameProduct(dataObject.get("nameProduct").getAsString());
			adminProduct.setQuantity(dataObject.get("quantity").getAsInt());
			adminProduct.setNameUnit(dataObject.get("nameUnit").getAsString());
			adminProduct.setValue(dataObject.get("value").getAsFloat());
			adminProduct.setUnit(dataObject.get("unit").getAsInt());
			list.add(adminProduct);
		}
		return list;
	}

	public List<Stock> handleStockAd(JsonObject jsonObject) {
		JsonArray data = jsonObject.get("data").getAsJsonArray();

		List<Stock> list = new ArrayList<>();
		for (JsonElement e : data) {
			JsonObject dataObject = e.getAsJsonObject();
			Stock stock = new Stock();
			stock.setId(dataObject.get("id").getAsInt());
			stock.setNameProduct(dataObject.get("nameProduct").getAsString());
			stock.setSum_end(dataObject.get("sum_end").getAsInt());
			stock.setRatio_unit(dataObject.get("ratio_unit").getAsInt());
			list.add(stock);
		}
		return list;
	}

}
