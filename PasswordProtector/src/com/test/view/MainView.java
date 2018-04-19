package com.test.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.test.controller.Controller;
import com.test.exceptions.ServiceException;
import com.test.model.DecryptedEntry;
import com.test.model.EncryptedEntry;

public class MainView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static JComboBox comboBox;
	public static JTextField userId;
	public static JPasswordField password;
	public static JTextField passwordTest;
	public static final String DRIVE_ICON_URL="/googledrive.png";
	public static final String TITLE_ICON_URL="/images.png";
	public static final String VISIBLE_ICON_URL="/visible.png";
	public static final ImageIcon DRIVE_ICON = new ImageIcon(MainView.class.getClass().getResource(DRIVE_ICON_URL));
	public static final ImageIcon TITLE_ICON = new ImageIcon(MainView.class.getClass().getResource(TITLE_ICON_URL));
	public static final ImageIcon VISIBLE_ICON = new ImageIcon(MainView.class.getClass().getResource(VISIBLE_ICON_URL));
	public static boolean driveSynced=false;
	
	static{
		setUIFont(new javax.swing.plaf.FontUIResource("Tahoma",Font.PLAIN,20));
		File dataFile=new File(Controller.DRIVEFILE);
		if(dataFile.exists()){
			Controller.FILE=Controller.DRIVEFILE;
			driveSynced=true;
		}
	}
	public MainView() {
		super(new BorderLayout());
		if(driveSynced){
			JOptionPane.showMessageDialog(frame,
					"Data Synced with Google Drive",
					"Info",
					JOptionPane.INFORMATION_MESSAGE,DRIVE_ICON);
		}
		final DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout());
		builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		builder.appendColumn("left:pref");
		builder.appendColumn("5dlu");
		builder.appendColumn("fill:max(pref; 150px)");
		builder.appendColumn("5dlu");
		builder.appendColumn("left:pref");
		builder.appendColumn("fill:max(pref; 26px)");

		comboBox=new JComboBox();
		builder.append("Entity:", comboBox);
		comboBox.setToolTipText("Entity");
		comboBox.setEditable(true);

		JButton btn =new JButton("Show");
		btn.setPreferredSize(new Dimension(90, 25));
		btn.addActionListener(this);
		builder.append(btn);
		builder.nextLine();


		Dimension textDimension=new Dimension(150, 26);
		userId=new JTextField();
		userId.setPreferredSize(textDimension);
		userId.setToolTipText("User ID");
		builder.append("User ID:", userId);
		JButton btn1 =new JButton("Add");
		btn1.setPreferredSize(new Dimension(90, 25));
		btn1.addActionListener(this);
		builder.append(btn1);
		builder.nextLine();

		password=new JPasswordField();
		password.setPreferredSize(textDimension);
		password.setToolTipText("Password");

		passwordTest=new JTextField();
		passwordTest.setPreferredSize(textDimension);
		passwordTest.setToolTipText("Password");
		final JPanel passwordPanel=new JPanel();
		passwordPanel.setLayout(new BorderLayout());
		passwordPanel.add(password);

		builder.append("Password:", passwordPanel);

		JButton btn3 = new JButton(VISIBLE_ICON);
		btn3.setPreferredSize(new Dimension(26, 26));
		btn3.setMargin(new Insets(0, 0, 0, 0));
		btn3.setBorder(null);
		btn3.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				passwordPanel.remove(password);
				passwordPanel.add(passwordTest);
				passwordTest.setText(new String(password.getPassword()));
				passwordPanel.revalidate();
				repaint();
			}
			public void mouseClicked(MouseEvent arg0) {

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			@Override
			public void mouseExited(MouseEvent arg0) {

			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				passwordPanel.remove(passwordTest);
				passwordPanel.add(password);
				passwordPanel.revalidate();
				repaint();
			}
		});

		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	clearForm();
		    }
		});
		try {
			reloadList();
		} catch (ServiceException e) {
			showError(e.getMessage());
		}
		
		builder.append(btn3);
		add(builder.getPanel());
	}

	public static void main(String[] a){
		startMainView();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("Add")) {
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you sure you wish to Add data?",null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						try {
							String entityText=(String)comboBox.getSelectedItem();
							String userIdText=userId.getText();
							String pwdText=new String(password.getPassword());
							if(Controller.isBlankOrEmpty(entityText)||Controller.isBlankOrEmpty(userIdText)||Controller.isBlankOrEmpty(pwdText)){
								showError("Enter valid Data to continue ");
							}else{
								Controller.upadateData(new EncryptedEntry(entityText,userIdText,pwdText));
								reloadList();
								showInfo("Entity: "+entityText+ " data added Successfully");
							}
						} catch (ServiceException e) {
							showError(e.getMessage());
						}
					}
		}
		else if (action.equals("Show")) {
			try {
				fillForm(Controller.revealSelectedData((String)comboBox.getSelectedItem()));
			} catch (ServiceException e) {
				showError(e.getMessage());
			}
		}

	}

	private static void startMainView(){
		final JPasswordField pf = new JPasswordField();
	    //Create OptionPane & Dialog
	    JOptionPane pane = new JOptionPane(pf, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION,TITLE_ICON);
	    JDialog dialog = pane.createDialog("ENTER PASSWORD");
	    dialog.setIconImage(TITLE_ICON.getImage());
	    //Add a listener to the dialog to request focus of Password Field
	    dialog.addComponentListener(new ComponentListener(){
	        @Override
	        public void componentShown(ComponentEvent e) {
	            pf.requestFocusInWindow();
	        }
	        @Override public void componentHidden(ComponentEvent e) {
	        }
	        @Override public void componentResized(ComponentEvent e) {
	        }
	        @Override public void componentMoved(ComponentEvent e) {
	        }
	        });
	    
	    dialog.setVisible(true);
	    int action = (Integer) pane.getValue();
	    if(action < 0 || action > 0){
			System.exit(0);
		}else{
			if(getScreenPwd().equals(new String(pf.getPassword()))){
				frame = new JFrame("Password Protector");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setIconImage(TITLE_ICON.getImage());
				frame.add(new MainView());
				frame.pack();
				frame.setLocationRelativeTo(null);// *** this will center your app ***
				frame.setVisible(true);
			}else{
				showError("Enter correct secret code to continue ");
				startMainView();
			}
		}
		
	}
	private static void reloadList() throws ServiceException{
		List<DecryptedEntry> entryList=Controller.revealData();
		comboBox.removeAllItems();
		for(DecryptedEntry entry:entryList){
			comboBox.addItem(entry.getEntity());
			comboBox.setSelectedIndex(0);
		}
	}
	
	private static void fillForm(DecryptedEntry entry){
		userId.setText(entry.getUserId());
		password.setText(entry.getPassword());
		passwordTest.setText(entry.getPassword());
	}
	
	private static void clearForm(){
		userId.setText("");
		password.setText("");
		passwordTest.setText("");
	}
	
	private static void showError(String message){
		JOptionPane.showMessageDialog(frame,
				message,
				"Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	private static void showInfo(String message){
		JOptionPane.showMessageDialog(frame,
				message,
				"Info",
				JOptionPane.INFORMATION_MESSAGE);
	}
	private static String getScreenPwd(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	@SuppressWarnings("rawtypes")
	public static void setUIFont(javax.swing.plaf.FontUIResource f)
	{   
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while(keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
	    }
	}
}
