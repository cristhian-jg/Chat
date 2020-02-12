package com.crisgon.sockets.client;

import java.io.Serializable;

/**
 * Created by @crishtian-jg on 13/02/2020
 * 
 * Clase serializada que almacena la información que viaja a traves de los
 * Sockets
 */

public class DataPacket implements Serializable {

	private static final String TAG = "DataPacket";
	private static final long serialVersionUID = 26798444487259699L;

	private String ip;
	private String nickname;
	private String message;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SendPacket [ip=" + ip + ", nickname=" + nickname + ", message=" + message + "]";
	}

}
