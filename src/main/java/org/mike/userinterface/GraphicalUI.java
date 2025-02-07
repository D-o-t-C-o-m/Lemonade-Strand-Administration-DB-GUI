package org.mike.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GraphicalUI extends JPanel implements ActionListener {
private static final double PRICE_HEALTHY = 12.00;
private static final double PRICE_ORIGINAL = 10.00;
private final JPanel cardPanel;
private final CardLayout cardLayout;
private JLabel totalLabel;
private JLabel orderIdLabel;
private int orderQtyLemonade1;
private int orderQtyLemonade2;
private double ticketTotal;
private int orderId;

public GraphicalUI() {
	JFrame frame = new JFrame("Lemonade Administration");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(640, 960);

	// Initialize order ID and set initial values
	resetOrderId();
	this.orderQtyLemonade1 = 0;
	this.orderQtyLemonade2 = 0;
	this.ticketTotal = 0.00;

	// Set up CardLayout
	cardLayout = new CardLayout();
	cardPanel = new JPanel(cardLayout);

	createMainMenu();
	createOrderPanel();

	frame.add(cardPanel);
	frame.setVisible(true);
}

public static void main(String[] args) {
	SwingUtilities.invokeLater(GraphicalUI::new);
}

@Override
public void actionPerformed(ActionEvent event) {
	totalLabel.setText("Your Order Total is: $" + String.format("%.2f", ticketTotal));
}

private void createMainMenu() {
	JPanel mainMenuPanel = new JPanel();
	mainMenuPanel.setLayout(new BorderLayout());

	JButton button1 = new JButton("Create an Order");
	button1.addActionListener(_ -> {
		resetOrderId();
		cardLayout.show(cardPanel, "OrderPanel");
	});
	button1.setToolTipText("Create a new Order");

	mainMenuPanel.add(button1, BorderLayout.CENTER);
	cardPanel.add(mainMenuPanel, "MainMenu");
}

private void createOrderPanel() {
	JPanel orderPanel = new JPanel();
	orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

	// Order ID Panel
	JPanel orderIdPanel = new JPanel();
	orderIdPanel.setBackground(Color.white);

	orderIdLabel = new JLabel("Your Order ID is: " + this.orderId);
	orderIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
	orderIdLabel.setOpaque(true);
	orderIdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	orderIdPanel.add(orderIdLabel);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
	topPanel.setBackground(Color.white);

	topPanel.add(orderIdPanel);
	topPanel.add(Box.createVerticalGlue());

	// Lemonade Buttons with Icons and formatted prices
	JButton healthyLemonadeButton = createLemonadeButton("Healthy Lemonade", PRICE_HEALTHY, "waterIcon.png", 1);
	JButton originalLemonadeButton = createLemonadeButton("Original Lemonade", PRICE_ORIGINAL, "lemonadeIcon.png", 2);

	// Panel for the lemonade buttons
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	buttonPanel.setBackground(Color.white);
	buttonPanel.add(healthyLemonadeButton);
	buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	buttonPanel.add(originalLemonadeButton);

	topPanel.add(buttonPanel);
	topPanel.add(Box.createVerticalGlue());

	orderPanel.add(topPanel);

	// Total Panel
	JPanel totalPanel = new JPanel();
	totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
	totalPanel.setBackground(Color.white);
	totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

	totalLabel = new JLabel("Your Order Total is: $0.00");
	totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	totalPanel.add(totalLabel);

	orderPanel.add(totalPanel);

	// Button Panel for Checkout and Cancel
	JPanel buttonPanelBottom = new JPanel();
	buttonPanelBottom.setLayout(new BoxLayout(buttonPanelBottom, BoxLayout.X_AXIS));

	JButton cancelButton = new JButton("Cancel");
	cancelButton.setBackground(Color.red);
	cancelButton.setFont(new Font("Tahoma", Font.BOLD, 10));
	cancelButton.addActionListener(_ -> cancelOrder());
	cancelButton.setPreferredSize(new Dimension(120, 40));
	buttonPanelBottom.add(cancelButton);

	JButton checkoutButton = new JButton("CHECK OUT");
	checkoutButton.setBackground(Color.yellow);
	checkoutButton.setFont(new Font("Tahoma", Font.BOLD, 10));
	checkoutButton.addActionListener(_ -> openCheckoutWindow());
	checkoutButton.setPreferredSize(new Dimension(120, 40));
	buttonPanelBottom.add(Box.createHorizontalGlue());
	buttonPanelBottom.add(checkoutButton);

	orderPanel.add(buttonPanelBottom);

	cardPanel.add(orderPanel, "OrderPanel");
}

private void cancelOrder() {

	orderQtyLemonade1 = 0;
	orderQtyLemonade2 = 0;
	ticketTotal = 0.00;
	resetOrderId();
	totalLabel.setText("Your Order Total is: $0.00");

	cardLayout.show(cardPanel, "MainMenu");
}

private void openCheckoutWindow() {
	JFrame checkoutFrame = new JFrame("Checkout");
	checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	checkoutFrame.setSize(400, 300);

	JPanel checkoutPanel = new JPanel();
	checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.Y_AXIS));
	checkoutPanel.setBackground(Color.white);


	JLabel checkoutOrderIdLabel = new JLabel("Your Order ID: " + this.orderId);
	checkoutOrderIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	checkoutOrderIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


	JLabel orderDetailsLabel = getJLabel();

	checkoutPanel.add(checkoutOrderIdLabel);
	checkoutPanel.add(orderDetailsLabel);

	JButton printButton = new JButton("Print");
	printButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	printButton.setBackground(Color.blue);
	printButton.setForeground(Color.white);
	printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	printButton.addActionListener(_ -> {
		saveOrder();
		checkoutFrame.dispose();
		cardLayout.show(cardPanel, "MainMenu");
	});

	checkoutPanel.add(Box.createVerticalStrut(20));
	checkoutPanel.add(printButton);

	checkoutFrame.add(checkoutPanel);
	checkoutFrame.setVisible(true);
}

private JLabel getJLabel() {
	StringBuilder details = new StringBuilder("<html>");
	if (orderQtyLemonade1 > 0) {
		details.append(String.format("Healthy Lemonade: %d @ $%.2f<br>", orderQtyLemonade1, PRICE_HEALTHY));
	}
	if (orderQtyLemonade2 > 0) {
		details.append(String.format("Original Lemonade: %d @ $%.2f<br>", orderQtyLemonade2, PRICE_ORIGINAL));
	}
	details.append(String.format("Total: $%.2f</html>", ticketTotal));

	JLabel orderDetailsLabel = new JLabel(details.toString());
	orderDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	orderDetailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	return orderDetailsLabel;
}

private void saveOrder() {
	// Placeholder save function
	System.out.println("Order has been saved.");
	resetOrderData();
}

private void resetOrderData() {

	orderQtyLemonade1 = 0;
	orderQtyLemonade2 = 0;
	ticketTotal = 0.00;
	totalLabel.setText("Your Order Total is: $0.00");
}

private void resetOrderId() {

	this.orderId = (int) (Math.random() * 1000);
	updateOrderIdLabel();
}

private void updateOrderIdLabel() {

	if (orderIdLabel != null) {
		orderIdLabel.setText("Your Order ID is: " + this.orderId);
	}
}

private JButton createLemonadeButton(String label, double price, String iconName, int lemonadeId) {

	String priceString = String.format("%.2f", price);

	JButton button = new JButton(label + " $" + priceString);
	button.setBackground(price == PRICE_HEALTHY ? Color.cyan : Color.green);
	button.setFont(new Font("Tahoma", Font.BOLD, 12));
	button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/pictures/" + iconName))));  // Use relative path
	button.addActionListener(_ -> displayQuantityPad(new LemonadeWrapper(lemonadeId)));
	button.setPreferredSize(new Dimension(350, 80));
	return button;
}

private void displayQuantityPad(LemonadeWrapper lemonadeWrapper) {

	if (lemonadeWrapper.lemonadeID() == 1) {
		orderQtyLemonade1++;
	} else if (lemonadeWrapper.lemonadeID() == 2) {
		orderQtyLemonade2++;
	}

	ticketTotal = (orderQtyLemonade1 * PRICE_HEALTHY) + (orderQtyLemonade2 * PRICE_ORIGINAL);

	StringBuilder details = new StringBuilder("<html>");
	if (orderQtyLemonade1 > 0) {
		details.append(String.format("Healthy Lemonade: %d @ $%.2f<br>", orderQtyLemonade1, PRICE_HEALTHY));
	}
	if (orderQtyLemonade2 > 0) {
		details.append(String.format("Original Lemonade: %d @ $%.2f<br>", orderQtyLemonade2, PRICE_ORIGINAL));
	}
	details.append(String.format("Total: $%.2f</html>", ticketTotal));

	totalLabel.setText(details.toString());
}

record LemonadeWrapper(int lemonadeID) {
}
}
