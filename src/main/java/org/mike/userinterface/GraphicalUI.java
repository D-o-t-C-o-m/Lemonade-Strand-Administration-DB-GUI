package org.mike.userinterface;

import org.mike.service.OrderService;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class GraphicalUI{
private OrderMenu orderMenu;
private Scanner scanner = new Scanner(System.in);

public GraphicalUI(OrderService orderService){
	JFrame frame = new JFrame("Lemonade Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300,300);

	var webIcon = new ImageIcon("src/main/resources/pictures/lemonIcon.png");
	frame.setIconImage(webIcon.getImage());

	this.orderMenu = new OrderMenu(orderService);

	JMenuBar menu = new JMenuBar();


	JMenu menu1 = new JMenu("Manager");
	menu.add(menu1);
	JMenuItem manager1 = new JMenuItem("Manager Supplier");
	manager1.setToolTipText("Vendors");
	JMenuItem manager2 = new JMenuItem("Manage Products");
	manager2.setToolTipText("Item Inventory");
	JMenuItem manager3 = new JMenuItem("Manage Recipes");
	manager3.setToolTipText("Recipe Information");
	menu1.add(manager1);
	menu1.add(manager2);
	menu1.add(manager3);

	JMenu menu2 = new JMenu("Reports");
	menu.add(menu2);
	JMenuItem report1 = new JMenuItem("Daily Sales Report");
	report1.setToolTipText("Everything sold in Units and Dollars");
	JMenuItem report2 = new JMenuItem("Out of Stock Report");
	report2.setToolTipText("A list of lemonades we currently can't make");
	menu2.add(report1);
	menu2.add(report2);
	JPanel panel = new JPanel();



	JButton button1 = new JButton("Create an Order");
	button1.addActionListener((event) -> orderMenu.runOrderOption(this.scanner));
	button1.setToolTipText("Create a new Order");
	frame.getContentPane().add(BorderLayout.NORTH, menu);
	frame.getContentPane().add(button1,BorderLayout.CENTER);

	frame.setVisible(true);
}
}
