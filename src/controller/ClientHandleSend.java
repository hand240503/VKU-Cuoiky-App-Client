package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import model.Notify;
import model.OrderDetail;

import model.Orders;
import model.UserInfo;
import views.OrderdetailView;

public class ClientHandleSend {

	private DataOutputStream dos;
	private Socket socket;
	private Gson gson;

	public ClientHandleSend(Socket socket) {
		this.socket = socket;
		gson = new Gson();
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(String name, String pass) {

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(name);
		userInfo.setPassWord(pass);
		Notify notify = new Notify();
		notify.setNotify("Sv_login");
		notify.setData(userInfo);
		String json = gson.toJson(notify);
		byte[] jsonBytes = json.getBytes();
		try {
			synchronized (dos) {
				dos.writeInt(json.length());
				dos.write(jsonBytes);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createOrder(String date, int idUser, int type) {

		Orders orders = new Orders();
		orders.setDate(date);
		orders.setId_user(idUser);
		orders.setType(type);

		Notify notify = new Notify();
		notify.setNotify("Create-order");
		notify.setData(orders);
		String json = gson.toJson(notify);
		byte[] jsonBytes = json.getBytes();
		try {
			synchronized (dos) {
				dos.writeInt(json.length());
				dos.write(jsonBytes);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void insertOrderdetail(int idOrder, int idProduct, int idPrice, String date) {
		OrderDetail detail = new OrderDetail();
		detail.setId_order(idOrder);
		detail.setId_product(idProduct);
		detail.setId_price(idPrice);
		detail.setDate(date);
		Notify notify = new Notify();
		notify.setNotify("Create-order-detail");
		notify.setData(detail);

		String json = gson.toJson(notify);
		byte[] jsonBytes = json.getBytes();
		try {
			synchronized (dos) {
				dos.writeInt(json.length());
				dos.write(jsonBytes);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getSumEnd(int id) {
		Notify notify = new Notify();
		notify.setNotify("get-sum-end");
		notify.setData(id);
		String json = gson.toJson(notify);
		byte[] jsonBytes = json.getBytes();
		try {
			synchronized (dos) {
				dos.writeInt(json.length());
				dos.write(jsonBytes);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getViewsOrderdetail(int id, String nameProduct, String nameUnit) {


		Notify notify = new Notify();
		notify.setNotify("load-view-order");
		notify.setData("");
		
		String json = gson.toJson(notify);
		byte[] jsonBytes = json.getBytes();
		try {
			synchronized (dos) {
				dos.writeInt(json.length());
				dos.write(jsonBytes);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
