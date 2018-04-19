package com.test.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class RecordKeeperHome extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordKeeperHome() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Record Keeper");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

     // create File menu and Exit menu item
        JMenu burlapMenu = new JMenu( "Burlaps" );
        burlapMenu.setMnemonic( 'B' );
        JMenuItem viewBurlap = new JMenuItem( "View Burlaps" );
        viewBurlap.setMnemonic( 'V' );
        viewBurlap.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
            	  new JTableBasicWithDynamicModelObject().setVisible(true);
              }
           }
        );
        burlapMenu.add( viewBurlap );
        
        JMenu bannerMenu = new JMenu( "Banner" );
        bannerMenu.setMnemonic( 'N' );
        JMenuItem viewBanner = new JMenuItem( "View Banner" );
        viewBanner.setMnemonic( 'V' );
        viewBanner.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
            	  //new JTableBasicWithDynamicModelObject().setVisible(true);
              }
           }
        );
        bannerMenu.add( viewBanner );
        
        JMenu cakeTopperMenu = new JMenu( "Cake Topper" );
        cakeTopperMenu.setMnemonic( 'C' );
        JMenuItem viewCakeTopper = new JMenuItem( "View CakeTopper" );
        viewCakeTopper.setMnemonic( 'V' );
        viewCakeTopper.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
            	  //new JTableBasicWithDynamicModelObject().setVisible(true);
              }
           }
        );
        cakeTopperMenu.add( viewCakeTopper );
        
        JMenu expenseMenu = new JMenu( "Expense" );
        expenseMenu.setMnemonic( 'E' );
        JMenuItem viewExpense = new JMenuItem( "View Expenses" );
        viewExpense.setMnemonic( 'V' );
        viewExpense.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
            	  //new JTableBasicWithDynamicModelObject().setVisible(true);
              }
           }
        );
        expenseMenu.add( viewExpense );
        
        
        JMenu closeMenu = new JMenu("Close");
        closeMenu.setMnemonic( 'C' );
        JMenuItem exit = new JMenuItem( "Exit" );
        exit.setMnemonic( 'E' );
        exit.addActionListener(
           new ActionListener() {
              public void actionPerformed( ActionEvent e )
              {
            	  dispose();
              }
           }
        );
        closeMenu.add( exit );

        menubar.add(burlapMenu);
        menubar.add(bannerMenu);
        menubar.add(cakeTopperMenu);
        menubar.add(expenseMenu);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(closeMenu);

        setJMenuBar(menubar);
        JLabel label = new JLabel("<html><h1>Boston Creative Company", SwingConstants.CENTER);
        add(label);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	RecordKeeperHome ex = new RecordKeeperHome();
            ex.setVisible(true);
        });
    }
}
