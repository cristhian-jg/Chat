package com.crisgon.sockets;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.crisgon.sockets.client.DataPacket;

import javax.swing.JTextArea;

/**
 * Created by @crishtian-jg on 13/02/2020
 * 
 * Clase que funciona como servidor que recibe y envía mensajes a los clientes a
 * traves de Sockets.
 */

public class ChatServer extends JFrame implements Runnable {

	private static final String TAG = "ChatServer";
	private static final long serialVersionUID = 8646578140602989091L;

	private JPanel contentPane;
	private JTextArea textArea;

	/** Nuevo hilo de ejecución que permanecerá a la escucha de nuevos mensajes */
	private Thread thread;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer frame = new ChatServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(0, 0, 234, 311);
		contentPane.add(textArea);

		/** Arranco el hilo */
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		Socket socket;
		Socket socketReceiver;

		ObjectInputStream ois;
		ObjectOutputStream oos;

		/**
		 * Necesitaré una instancia de la clase DataPacket para recoger la información
		 * recibida
		 */
		DataPacket data;

		try {
			/** TODO REPASAR VÍDEO */
			serverSocket = new ServerSocket(3465);

			while (true) {
				socket = serverSocket.accept();
				/**
				 * Obtengo un nuevo flujo de entrada para leer el objeto recibido, podremos leer
				 * la información con los GETTERS de la clase DataPacket.
				 */
				ois = new ObjectInputStream(socket.getInputStream());
				data = (DataPacket) ois.readObject();

				textArea.append("\n" + data.getNickname() + ": " + data.getMessage());

				/** TODO REPASAR VÍDEO */
				socketReceiver = new Socket(data.getIp(), 3535);

				/** Obtengo un nuevo flujo de salida por donde escribir el objeto recibido */
				oos = new ObjectOutputStream(socketReceiver.getOutputStream());
				oos.writeObject(data);

				socketReceiver.close();
				socket.close();
				ois.close();
				oos.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
