package com.bb.queryhelper;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class BBQueryHelper {

	public static void main(String[] args) {
		
		System.out.println("실행");
		
		// FlatLaf Look and Feel 설정
		try {
			// 모든 컴포넌트를 각지게 만들기 (arc = 0)
			UIManager.put("Button.arc", 0);
			UIManager.put("Component.arc", 0);
			UIManager.put("CheckBox.arc", 0);
			UIManager.put("RadioButton.arc", 0);
			UIManager.put("ComboBox.arc", 0);
			UIManager.put("ProgressBar.arc", 0);
			UIManager.put("TextComponent.arc", 0);
			UIManager.put("ScrollBar.arc", 0);
			UIManager.put("TabbedPane.tabArc", 0);
			
			FlatLightLaf.setup();
		} catch (Exception e) {
			System.err.println("FlatLaf 초기화 실패: " + e.getMessage());
			e.printStackTrace();
		}
		
		// 초기화
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BBQueryHelper queryHelper = new BBQueryHelper();
				queryHelper.showForm();
			}
		});
	}

	public static JScrollPane inputPane = null;
	public static JScrollPane outputPane = null;
	public static BBTextArea inputTextArea = null;
//	public static BBTextArea inputTextArea2 = null;
	public static BBTextArea outputTextArea = null;
	public static JButton button = null;
	public static JButton button2 = null;
	
	public void showForm() {
		int top = 10;
		
		final BasicForm form = new BasicForm(740, 780, "BBQueryHelper_" + CConst.version);
		
		form.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				resizeForm(form.getWidth(), form.getHeight());
	        }
		});
		
		inputTextArea = new BBTextArea();
		inputTextArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
		
		inputPane = form.addScrollPane(inputTextArea, 10, 10, 760, 240);
		
//		top = top + 250;
//		
//		inputTextArea2 = new BBTextArea();
//		inputTextArea2.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
//		
//		form.addScrollPane(inputTextArea2, 10, 260, 760, 240);
		
		top = top + 250;
		
		outputTextArea = new BBTextArea();
		outputTextArea.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
		outputPane = form.addScrollPane(outputTextArea, 10, top, 760, 240);
		
		top = top + 250;
		
		button = form.addButton(10, top, 100, 30, "Revise");
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String result = "";
				
				String input1 = inputTextArea.getText();
//				String input2 = inputTextArea2.getText();
//				
//				if (input2 != null && input2.trim().length() > 0) {
//					// 쿼리를 첫번째 칸에, json을 두번째 칸에 넣는 방식
//					QueryReviseController2 queryReviseCtrl2 = new QueryReviseController2();
//					result = queryReviseCtrl2.reviseQuery(input1, input2);
//					if (result == null) {
//						result = "";
//					}
//					
//				} else {
					// 쿼리와 파라미터를 한 칸에 넣는 방식
					QueryReviseController queryReviseCtrl = new QueryReviseController();
					result = queryReviseCtrl.reviseQuery(input1);
					if (result == null) {
						result = "";
					}
//				}
				
				outputTextArea.setText(result);
				
			}
		});
		
		button2 = form.addButton(120, top, 100, 30, "Clear");
		button2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String result = "";
				
				inputTextArea.setText("");
//				inputTextArea2.setText("");
				outputTextArea.setText("");
				
				outputTextArea.setText(result);
				
			}
		});
		
		form.setVisible(true);
	}
	
	private static void resizeForm(int formWidth, int formHeight) {
		int textAreaHeight = formHeight - 130;
		
		int halfHeight = 0;
		
		int remain = textAreaHeight % 2;
		if (remain == 1) {
			textAreaHeight = textAreaHeight - 1;
			halfHeight = textAreaHeight / 2;
		} else {
			halfHeight = textAreaHeight / 2;
		}
		
		inputPane.setBounds(10, 10, formWidth - 40, halfHeight);
		outputPane.setBounds(10, 10 + halfHeight + 10, formWidth - 40, halfHeight);
		
		int buttonWidth = formWidth - 50;
		
		int oneButtonWidth = 0;
		int remain2 = oneButtonWidth % 2;
		if (remain2 == 1) {
			buttonWidth = buttonWidth - 1;
			oneButtonWidth = buttonWidth / 2;
		} else {
			oneButtonWidth = buttonWidth / 2;
		}
		
		button.setBounds(10, textAreaHeight + 30, oneButtonWidth, 30);
		button2.setBounds(10 + oneButtonWidth + 10, textAreaHeight + 30, oneButtonWidth, 30);
	}
}