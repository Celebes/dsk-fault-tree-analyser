package www.wcy.wat.dsk.core;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class JDialogHelper {
	
	public static void showDialog(final TitleType title, final String msg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MsgDialog dialog = new MsgDialog(title, msg);
			}
		});
	}
	
	private static class MsgDialog extends JDialog {
		
		private JPanel mainPanel;
	    private JLabel msgLabel;
	    private JButton okButton;
	    
		private String msg;
		private TitleType title;
		
		public MsgDialog(TitleType title, String msg) {
			this.msg = msg;
			this.title = title;
			setModal(true);
			initComponents();
			setTitle(title.getNazwa());
			setSize(480, 350);
			
			setVisible(true);
		}
		
		private void initComponents() {

	        mainPanel = new JPanel();
	        msgLabel = new JLabel();
	        okButton = new JButton();

	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	        msgLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	        msgLabel.setText(msg);

	        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
	        mainPanel.setLayout(mainPanelLayout);
	        mainPanelLayout.setHorizontalGroup(
	            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(mainPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(msgLabel)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        mainPanelLayout.setVerticalGroup(
	            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(mainPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(msgLabel)
	                .addContainerGap(235, Short.MAX_VALUE))
	        );

	        okButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	        okButton.setText("OK");
	        okButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                okButtonActionPerformed(evt);
	            }
	        });

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(214, 214, 214)
	                .addComponent(okButton)
	                .addContainerGap(215, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
	                .addComponent(okButton)
	                .addContainerGap())
	        );

	        pack();
	    }
		
		private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
	        dispose();
	    } 
	}

	
}
