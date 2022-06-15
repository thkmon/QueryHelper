package com.bb.queryhelper;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BBQueryHelper {

	public static void main(String[] args) {
		BBQueryHelper queryHelper = new BBQueryHelper();
		queryHelper.showForm();
	}

	public BBTextArea inputTextArea = null;
	public BBTextArea inputTextArea2 = null;
	public BBTextArea outputTextArea = null;
	
	public void showForm() {
		BasicForm form = new BasicForm(800, 860, "BBQueryHelper_" + CConst.version);
		
		inputTextArea = new BBTextArea();
		inputTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		form.addScrollPane(inputTextArea, 10, 10, 760, 240);
		
		inputTextArea2 = new BBTextArea();
		inputTextArea2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		form.addScrollPane(inputTextArea2, 10, 260, 760, 240);
		
		outputTextArea = new BBTextArea();
		outputTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		form.addScrollPane(outputTextArea, 10, 520, 760, 240);
		
		JButton button = form.addButton(10, 770, 100, 30, "Revise");
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
				String input2 = inputTextArea2.getText();
				
				if (input2 != null && input2.trim().length() > 0) {
					// 쿼리를 첫번째 칸에, json을 두번째 칸에 넣는 방식
					QueryReviseController2 queryReviseCtrl2 = new QueryReviseController2();
					result = queryReviseCtrl2.reviseQuery(input1, input2);
					if (result == null) {
						result = "";
					}
					
				} else {
					// 쿼리와 파라미터를 한 칸에 넣는 방식
					QueryReviseController queryReviseCtrl = new QueryReviseController();
					result = queryReviseCtrl.reviseQuery(input1);
					if (result == null) {
						result = "";
					}
				}
				
				outputTextArea.setText(result);
				
			}
		});
		
		form.setVisible(true);
	}
}