package com.test.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.plaf.FontUIResource;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.test.controller.Controller;
import com.test.exceptions.ServiceException;
import com.test.model.DecryptedEntry;
import com.test.model.EncryptedEntry;

public class MainView extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    public static JFrame frame;
    public static JComboBox<String> comboBox;
    public static JTextField userId;
    public static JPasswordField password;
    public static JTextField passwordTest;
    public static final ImageIcon DRIVE_ICON;
    public static final ImageIcon TITLE_ICON;
    public static final ImageIcon VISIBLE_ICON;
    public static boolean driveSynced;
    
    static {
        DRIVE_ICON = new ImageIcon(Controller.DRIVE_ICON_URL);
        TITLE_ICON = new ImageIcon(Controller.TITLE_ICON_URL);
        VISIBLE_ICON = new ImageIcon(Controller.VISIBLE_ICON_URL);
        MainView.driveSynced = false;
        setUIFont(new FontUIResource("Tahoma", 0, 20));
        final File dataFile = new File(Controller.DRIVEFILE);
        if (dataFile.exists()) {
            Controller.FILE = Controller.DRIVEFILE;
            MainView.driveSynced = true;
        }
    }
    
    public MainView() {
        super(new BorderLayout());
        if (MainView.driveSynced) {
            JOptionPane.showMessageDialog(MainView.frame, "Data Synced with Google Drive", "Info", 1, MainView.DRIVE_ICON);
        }
        final DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout());
        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        builder.appendColumn("left:pref");
        builder.appendColumn("5dlu");
        builder.appendColumn("fill:max(pref; 150px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("left:pref");
        builder.appendColumn("fill:max(pref; 26px)");
        builder.append("Entity:", (Component)(MainView.comboBox = new JComboBox<String>()));
        MainView.comboBox.setToolTipText("Entity");
        MainView.comboBox.setEditable(true);
        final JButton btn = new JButton("Show");
        btn.setPreferredSize(new Dimension(90, 25));
        btn.addActionListener(this);
        builder.append((Component)btn);
        builder.nextLine();
        final Dimension textDimension = new Dimension(150, 26);
        (MainView.userId = new JTextField()).setPreferredSize(textDimension);
        MainView.userId.setToolTipText("User ID");
        builder.append("User ID:", (Component)MainView.userId);
        final JButton btn2 = new JButton("Add");
        btn2.setPreferredSize(new Dimension(90, 25));
        btn2.addActionListener(this);
        builder.append((Component)btn2);
        builder.nextLine();
        (MainView.password = new JPasswordField()).setPreferredSize(textDimension);
        MainView.password.setToolTipText("Password");
        (MainView.passwordTest = new JTextField()).setPreferredSize(textDimension);
        MainView.passwordTest.setToolTipText("Password");
        final JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BorderLayout());
        passwordPanel.add(MainView.password);
        builder.append("Password:", (Component)passwordPanel);
        final JButton btn3 = new JButton(MainView.VISIBLE_ICON);
        btn3.setPreferredSize(new Dimension(26, 26));
        btn3.setMargin(new Insets(0, 0, 0, 0));
        btn3.setBorder(null);
        btn3.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(final MouseEvent e) {
                passwordPanel.remove(MainView.password);
                passwordPanel.add(MainView.passwordTest);
                MainView.passwordTest.setText(new String(MainView.password.getPassword()));
                passwordPanel.revalidate();
                MainView.this.repaint();
            }
            
            @Override
            public void mouseClicked(final MouseEvent arg0) {
            }
            
            @Override
            public void mouseEntered(final MouseEvent arg0) {
            }
            
            @Override
            public void mouseExited(final MouseEvent arg0) {
            }
            
            @Override
            public void mouseReleased(final MouseEvent arg0) {
                passwordPanel.remove(MainView.passwordTest);
                passwordPanel.add(MainView.password);
                passwordPanel.revalidate();
                MainView.this.repaint();
            }
        });
        MainView.comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                clearForm();
            }
        });
        try {
            reloadList();
        }
        catch (ServiceException e) {
            showError(e.getMessage());
        }
        builder.append((Component)btn3);
        this.add(builder.getPanel());
    }
    
    public static void main(final String[] a) {
        startMainView();
    }
    
    @Override
    public void actionPerformed(final ActionEvent ae) {
        final String action = ae.getActionCommand();
        if (action.equals("Add")) {
            final int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to Add data?", null, 0);
            if (result == 0) {
                try {
                    final String entityText = (String)MainView.comboBox.getSelectedItem();
                    final String userIdText = MainView.userId.getText();
                    final String pwdText = new String(MainView.password.getPassword());
                    if (Controller.isBlankOrEmpty(entityText) || Controller.isBlankOrEmpty(userIdText) || Controller.isBlankOrEmpty(pwdText)) {
                        showError("Enter valid Data to continue ");
                    }
                    else {
                        Controller.upadateData(new EncryptedEntry(entityText, userIdText, pwdText));
                        reloadList();
                        showInfo("Entity: " + entityText + " data added Successfully");
                    }
                }
                catch (ServiceException e) {
                    showError(e.getMessage());
                }
            }
        }
        else if (action.equals("Show")) {
            try {
                fillForm(Controller.revealSelectedData((String)MainView.comboBox.getSelectedItem()));
            }
            catch (ServiceException e2) {
                showError(e2.getMessage());
            }
        }
    }
    
    private static void startMainView() {
        final JPasswordField pf = new JPasswordField();
        final JOptionPane pane = new JOptionPane(pf, -1, 2, MainView.TITLE_ICON);
        final JDialog dialog = pane.createDialog("ENTER PASSWORD");
        dialog.setIconImage(MainView.TITLE_ICON.getImage());
        dialog.addComponentListener(new ComponentListener() {
            @Override
            public void componentShown(final ComponentEvent e) {
                pf.requestFocusInWindow();
            }
            
            @Override
            public void componentHidden(final ComponentEvent e) {
            }
            
            @Override
            public void componentResized(final ComponentEvent e) {
            }
            
            @Override
            public void componentMoved(final ComponentEvent e) {
            }
        });
        dialog.setVisible(true);
        if (pane.getValue() == null || (int)pane.getValue() < 0 || (int)pane.getValue() > 0) {
            System.exit(0);
        }
        else if (getScreenPwd().equals(new String(pf.getPassword()))) {
            (MainView.frame = new JFrame("Password Protector")).setDefaultCloseOperation(3);
            MainView.frame.setIconImage(MainView.TITLE_ICON.getImage());
            MainView.frame.add(new MainView());
            MainView.frame.pack();
            MainView.frame.setLocationRelativeTo(null);
            MainView.frame.setVisible(true);
        }
        else {
            showError("Enter correct secret code to continue ");
            startMainView();
        }
    }
    
    private static void reloadList() throws ServiceException {
        final List<DecryptedEntry> entryList = Controller.revealData();
        MainView.comboBox.removeAllItems();
        for (final DecryptedEntry entry : entryList) {
            MainView.comboBox.addItem(entry.getEntity());
            MainView.comboBox.setSelectedIndex(0);
        }
    }
    
    private static void fillForm(final DecryptedEntry entry) {
        MainView.userId.setText(entry.getUserId());
        MainView.password.setText(entry.getPassword());
        MainView.passwordTest.setText(entry.getPassword());
    }
    
    private static void clearForm() {
        MainView.userId.setText("");
        MainView.password.setText("");
        MainView.passwordTest.setText("");
    }
    
    private static void showError(final String message) {
        JOptionPane.showMessageDialog(MainView.frame, message, "Error", 0);
    }
    
    private static void showInfo(final String message) {
        JOptionPane.showMessageDialog(MainView.frame, message, "Info", 1);
    }
    
    private static String getScreenPwd() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
    
    public static void setUIFont(final FontUIResource f) {
        final Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            final Object key = keys.nextElement();
            final Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}