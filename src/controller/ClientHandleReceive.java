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

import model.DetailView;
import model.Product_Price_Views;
import model.UserDetailView;
import views.CartView;
import views.LoginView;
import views.OrderdetailView;
import views.UserViews;

public class ClientHandleReceive extends Thread {

	private Socket socket;
	private DataInputStream dis;
	private int idOrder;
	private int sumEnd;
	private int idUser;

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

					if (notifyMode.equals("login-success")) {
						int idUser = Integer.parseInt(jsonObject.get("content").getAsString());
						List<Product_Price_Views> pViews = handleProductViews(jsonObject);
						socket.close();
						dis.close();
						new UserViews(pViews, idUser).setVisible(true);
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
						System.out.println(data);
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

						new CartView(list,socket).setVisible(true);
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

}
