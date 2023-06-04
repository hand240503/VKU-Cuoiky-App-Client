package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Product_Price_Views;
import views.LoginView;
import views.UserViews;

public class ClientHandleReceive extends Thread {

	private Socket socket;
	private DataInputStream dis;
	private int idOrder;
	private int sumEnd;

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
				while (true) {
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
						new UserViews(pViews, idUser, socket).setVisible(true);
					}
					if (notifyMode.equals("login-failed")) {
						String notifyContent = jsonObject.get("content").getAsString();
						JOptionPane.showMessageDialog(null, notifyContent, "Thông báo", JOptionPane.ERROR_MESSAGE);
						socket.close();
						dis.close();
						new LoginView().setVisible(true);
					}
					if (notifyMode.equals("getID-order")) {
						idOrder = jsonObject.get("data").getAsInt();
					}
					if (notifyMode.equals("sum-end-product")) {
						sumEnd = jsonObject.get("data").getAsInt();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
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

	public int getIdorder() {
		return idOrder;
	}

}
