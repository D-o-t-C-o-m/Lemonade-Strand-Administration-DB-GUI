package org.mike.userinterface;

import org.mike.service.OrderService;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GraphicalUI extends JPanel implements ActionListener {
private OrderMenu orderMenu;
private JFrame frame;
private JPanel cardPanel;
private CardLayout cardLayout;
private JPanel mainMenuPanel;
private JPanel orderPanel;
private Document display = null;
private int orderQtyLemonade1;
private int orderQtyLemonade2;

public GraphicalUI(OrderService orderService) {

	frame = new JFrame("Lemonade Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(640, 960);

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

@Override
public void actionPerformed(ActionEvent event) {
		String key = event.getActionCommand();
	try {
		display.insertString(display.getLength(), key, null);
	} catch (BadLocationException e) {
		e.printStackTrace();
	}
}

static class LemonadeWrapper {
	int lemonadeID;
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

	final LemonadeWrapper wrapper = new LemonadeWrapper();
	wrapper.lemonadeID = 0;

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
	topPanel.add(Box.createVerticalGlue());


	JButton button3 = new JButton("     Healthy Lemonade $12.00");
	button3.setBackground(Color.cyan);
	button3.setFont(new Font("Tahoma", Font.BOLD, 14));
	button3.setIcon(new ImageIcon(new ImageIcon("src/main/resources/pictures/waterIcon.png").getImage().getScaledInstance(20, 50, Image.SCALE_SMOOTH)));
	button3.addActionListener(e -> {
		wrapper.lemonadeID = 1;
		orderPanel.revalidate();
		orderPanel.repaint();
		qtyPad(1);
	});

	JButton button4 = new JButton("     Original Lemonade  $10.00");
	button4.setBackground(Color.green);
	button4.setFont(new Font("Tahoma", Font.BOLD, 14));
	button4.setIcon(new ImageIcon(new ImageIcon("src/main/resources/pictures/lemonadeIcon.png").getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH)));
	button4.addActionListener(e -> {
		wrapper.lemonadeID = 2;
		orderPanel.revalidate();
		orderPanel.repaint();
		qtyPad(2);
	});

	Dimension buttonSize = new Dimension(400, 100);
	button3.setPreferredSize(buttonSize);
	button4.setPreferredSize(buttonSize);


	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	buttonPanel.setBackground(Color.white);
	buttonPanel.add(button3);
	buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	buttonPanel.add(button4);

	topPanel.add(buttonPanel);
	topPanel.add(Box.createVerticalGlue());


	orderPanel.add(topPanel, BorderLayout.CENTER);


	JPanel buttonPanelBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));

	JButton button5 = new JButton("CHECK OUT");
	button5.setBackground(Color.yellow);
	button5.setFont(new Font("Tahoma", Font.BOLD, 10));
	button5.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));
	buttonPanelBottom.add(button5);


	JButton button2 = new JButton("Cancel");
	button2.setBackground(Color.red);
	button2.setFont(new Font("Tahoma", Font.BOLD, 10));
	button2.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));
	buttonPanelBottom.add(button2);
	buttonPanelBottom.add(Box.createRigidArea(new Dimension(0, 50)));
	orderPanel.add(buttonPanelBottom, BorderLayout.SOUTH);


	cardPanel.add(orderPanel, "OrderPanel");
}


public void qtyPad(int lemonadetype) {

		JFrame frame = new JFrame("How many would you like?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		if (lemonadetype == 1) {
			JPanel buttons = new JPanel(new GridLayout(3, 3));  // 3x3 grid
			for (int i = 1; i < 10; i++) {
				JButton button = new JButton(Integer.toString(i));
				button.addActionListener(e -> {
					this.orderQtyLemonade1 += Integer.parseInt(button.getText());
				});
				buttons.add(button);
			}

		} else if (lemonadetype == 2) {
			JPanel buttons = new JPanel(new GridLayout(3, 3));  // 3x3 grid
			for (int i = 1; i < 10; i++) {
				JButton button = new JButton(Integer.toString(i));
				button.addActionListener(e -> {
					this.orderQtyLemonade2 += Integer.parseInt(button.getText());
				});
				buttons.add(button);
		}

		frame.add(buttons, BorderLayout.CENTER);
		frame.pack();
	}
}




}