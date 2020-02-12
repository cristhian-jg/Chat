package com.crisgon.sockets;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class ChatServer extends JFrame implements Runnable {

	private static final String TAG = "ChatServer";
	private static final long serialVersionUID = 8646578140602989091L;

	private JPanel contentPane;
	private JTextArea textArea;

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

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		Socket socket;
		DataInputStream dis;
		String msg;

		try {
			serverSocket = new ServerSocket(3465);

			while (true) {
				socket = serverSocket.accept();

				dis = new DataInputStream(socket.getInputStream());
				msg = dis.readUTF();

				textArea.append("\n" + msg);

				dis.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
