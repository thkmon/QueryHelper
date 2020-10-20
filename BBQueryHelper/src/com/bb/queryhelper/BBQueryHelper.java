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
	public BBTextArea outputTextArea = null;
	
	public void showForm() {
		BasicForm form = new BasicForm(800, 600, "BBQueryHelper_" + CConst.version);
		
		inputTextArea = new BBTextArea();
		inputTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		
		form.addScrollPane(inputTextArea, 10, 10, 760, 240);
		
		outputTextArea = new BBTextArea();
		outputTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		form.addScrollPane(outputTextArea, 10, 260, 760, 240);
		
		JButton button = form.addButton(10, 510, 100, 30, "Revise");
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println(e);
				QueryReviseController queryReviseCtrl = new QueryReviseController();
				String result = queryReviseCtrl.reviseQuery(inputTextArea.getText());
				if (result == null) {
					result = "";
				}
				
				outputTextArea.setText(result);
				
			}
		});
		
		form.setVisible(true);
	}
}
