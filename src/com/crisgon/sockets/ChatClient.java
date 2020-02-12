package com.crisgon.sockets;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ChatClient extends JFrame {

	private static final String TAG = "ChatClient";
	private static final long serialVersionUID = -3834654030503859646L;

	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 280, 135, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket;
				DataOutputStream dos;
				
				try {
					socket = new Socket("192.168.1.39", 3465);
					
					dos = new DataOutputStream(socket.getOutputStream());
					dos.writeUTF(textField.getText());
					dos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(155, 279, 69, 23);
		contentPane.add(btnSend);

		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 11, 78, 14);
		contentPane.add(lblCliente);
	}
}
