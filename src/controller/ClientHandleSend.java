package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import model.Notify;
import model.UserInfo;

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

	public void orderProduct() {
		Notify notify = new Notify();
	}
}
