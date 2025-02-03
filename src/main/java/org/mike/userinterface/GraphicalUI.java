package org.mike.userinterface;

import org.mike.service.OrderService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicalUI {
private OrderMenu orderMenu;
private JFrame frame;
private JPanel cardPanel;
private CardLayout cardLayout;
private JPanel mainMenuPanel;
private JPanel orderPanel;

public GraphicalUI(OrderService orderService) {
	frame = new JFrame("Lemonade Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300, 300);

	var webIcon = new ImageIcon("src/main/resources/pictures/lemonIcon.png");
	frame.setIconImage(webIcon.getImage());

	this.orderMenu = new OrderMenu(orderService);

	cardLayout = new CardLayout();
	cardPanel = new JPanel(cardLayout);

	createMainMenu();
	createOrderPanel();

	frame.add(cardPanel);
	frame.setVisible(true);
}

private void createMainMenu() {
	mainMenuPanel = new JPanel();
	mainMenuPanel.setLayout(new BorderLayout());

	JMenuBar menuBar = new JMenuBar();
	JMenu menu1 = new JMenu("Manager");
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
	JMenuItem report1 = new JMenuItem("Daily Sales Report");
	report1.setToolTipText("Everything sold in Units and Dollars");
	JMenuItem report2 = new JMenuItem("Out of Stock Report");
	report2.setToolTipText("A list of lemonades we currently can't make");
	menu2.add(report1);
	menu2.add(report2);
	menuBar.add(menu1);
	menuBar.add(menu2);

	JButton button1 = new JButton("Create an Order");
	button1.addActionListener(e -> cardLayout.show(cardPanel, "OrderPanel"));
	button1.setToolTipText("Create a new Order");

	mainMenuPanel.add(menuBar, BorderLayout.NORTH);
	mainMenuPanel.add(button1, BorderLayout.CENTER);

	cardPanel.add(mainMenuPanel, "MainMenu");
}


private void createOrderPanel() {
	orderPanel = new JPanel();
	orderPanel.setLayout(new BorderLayout());


	JPanel orderIdPanel = new JPanel();
	orderIdPanel.setBackground(Color.white);
	JLabel orderIdLabel = new JLabel("Your Order ID is: " + new Random().nextInt(100));
	orderIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	orderIdLabel.setOpaque(true);
	orderIdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	orderIdPanel.add(orderIdLabel);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
	topPanel.setBackground(Color.white);


	topPanel.add(orderIdPanel);

	topPanel.add(Box.createRigidArea(new Dimension(0, 20)));


	JButton button3 = new JButton("Healthy Lemonade");
	button3.setBackground(Color.cyan);
	button3.setFont(new Font("Tahoma", Font.BOLD, 18));
	button3.setIcon(new ImageIcon(new ImageIcon("src/main/resources/pictures/waterIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

	JButton button4 = new JButton("Original Lemonade");
	button4.setBackground(Color.green);
	button4.setFont(new Font("Tahoma", Font.BOLD, 18));
	button4.setIcon(new ImageIcon(new ImageIcon("src/main/resources/pictures/lemonadeIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

	Dimension buttonSize = new Dimension(250, 80);
	button3.setPreferredSize(buttonSize);
	button4.setPreferredSize(buttonSize);


	JPanel buttonPanel3 = new JPanel();
	buttonPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
	buttonPanel3.setBackground(Color.white);
	buttonPanel3.add(button3);
	topPanel.add(buttonPanel3);

	topPanel.add(Box.createRigidArea(new Dimension(0, 5)));

	JPanel buttonPanel4 = new JPanel();
	buttonPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
	buttonPanel4.setBackground(Color.white);
	buttonPanel4.add(button4);
	topPanel.add(buttonPanel4);

	orderPanel.add(topPanel, BorderLayout.NORTH);


	JPanel buttonPanelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton button2 = new JButton("Cancel");
	button2.setBackground(Color.red);
	button2.setFont(new Font("Tahoma", Font.BOLD, 10));
	button2.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

	buttonPanelBottom.add(button2);
	orderPanel.add(buttonPanelBottom, BorderLayout.SOUTH);


	cardPanel.add(orderPanel, "OrderPanel");
}

}