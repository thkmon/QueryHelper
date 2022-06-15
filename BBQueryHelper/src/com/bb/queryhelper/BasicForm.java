package com.bb.queryhelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BasicForm extends JFrame {
	
	public static Color buttonColor = new Color(255, 255, 200);
	public static Color buttonTextColor = new Color(0, 0, 0);
	
	public static Color formBackgroundColor = new Color(230, 230, 230);
	
	private Container container = null;
	private Font font = null;
	
	
	public BasicForm(int width, int height, String title) {
		container = getContentPane();
		container.setLayout(null);
		setSize(width, height);
		setBounds(200, 200, width, height);
		
		setBackground(formBackgroundColor);
		container.setBackground(formBackgroundColor);
		
		setTitle(title);		
		font = new Font("굴림", 13, 13);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("사용자 명령으로 종료합니다.");
				System.exit(0);
			}
		});
		
//		this.addMouseMotionListener(new MouseMotionListener() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				setCursor(Cursor.DEFAULT_CURSOR);
//			}
//			
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
	}
	
	
	public void open() {
		setVisible(true);
	}
	
	
	public void close() {
		setVisible(false);
	}
	
	
	public JScrollPane addScrollPane(JTextArea obj, int left, int top, int width, int height) {
		JScrollPane scrollPane = new JScrollPane(obj);
		scrollPane.setBackground(Color.white);
		scrollPane.setBounds(left, top, width, height);
		
		addComponentObj(scrollPane);
		return scrollPane;
	}
	
	
	public JTextArea addTextArea(int left, int top, int width, int height) {

		JTextArea obj = new JTextArea();
		obj.setBackground(Color.white);
		obj.setBounds(left, top, width, height);
		obj.setFont(font);
		return obj;
	}
	
	
	public JTextField addTextInput(int left, int top, int width, int height) {
		JTextField obj = new JTextField();
		obj.setBackground(Color.white);
		obj.setBounds(left, top, width, height);
		obj.setFont(font);
		
		addComponentObj(obj);
		return obj;
	}
	
	
	public JLabel addLabel(int left, int top, int width, int height, String value) {
		JLabel obj = new JLabel();
		obj.setBackground(Color.white);
		obj.setBounds(left, top, width, height);
		obj.setText(value);
		obj.setFont(font);
		
//		obj.addMouseMotionListener(new MouseMotionListener() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				setCursor(Cursor.DEFAULT_CURSOR);
//			}
//			
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
		
		addComponentObj(obj);
		return obj;
	}
	
	
	public JButton addButton(int left, int top, int width, int height, String value) {
		JButton obj = new JButton();
		obj.setBackground(buttonColor);
		obj.setBounds(left, top, width, height);
		obj.setText(value);
		obj.setFont(font);
		obj.setForeground(buttonTextColor);
		
//		obj.addMouseMotionListener(new MouseMotionListener() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				setCursor(Cursor.HAND_CURSOR);
//			}
//			
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
		
		addComponentObj(obj);
		return obj;
	}
	
	
	public JCheckBox addCheckBox(int left, int top, int width, int height, String value) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setBackground(formBackgroundColor);
		checkBox.setBounds(left, top, width, height);
		checkBox.setText(value);
		checkBox.setFont(font);
		
//		checkBox.addMouseMotionListener(new MouseMotionListener() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				setCursor(Cursor.HAND_CURSOR);
//			}
//			
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
		
		addComponentObj(checkBox);
		return checkBox;
	}
	
	
	private void addComponentObj(Component comp) {
		container.add(comp);
	}
}
