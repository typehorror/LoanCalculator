package com.google.gwt.sample.loancalculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoanCalculator implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	private Button computeButton = new Button("Compute");

	private Label totalLabel = new Label("Total");
	private Label totalAmountLabel = new Label("$0.0");
	private Label costLabel = new Label("Cost");
	private Label costAmountLabel = new Label("$0.0");
	private Label amountLabel = new Label("Amount");
	private Label monthLabel = new Label("Months");
	private Label downPaymentLabel = new Label("Down paymt");
	private Label rateLabel = new Label("Rate");
	private Label monthlyPaymentLabel = new Label("Monthly paymt");
	private Label monthlyPaymentAmountLabel = new Label("$0.0");
		
	private TextBox amountTextBox = new TextBox();
	private TextBox monthTextBox = new TextBox();
	private TextBox downPaymentTextBox = new TextBox();
	private TextBox rateTextBox = new TextBox();
	
	private double amount;
	private double month;
	private double rate;
	private double downPayment;
	private double totalAmount;
	private double costAmount;
	
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel amountPanel = new HorizontalPanel();
	private HorizontalPanel costPanel = new HorizontalPanel();
	private HorizontalPanel monthPanel = new HorizontalPanel();
	private HorizontalPanel downPaymentPanel = new HorizontalPanel();
	private HorizontalPanel ratePanel = new HorizontalPanel();
	private HorizontalPanel totalPanel = new HorizontalPanel();
	private HorizontalPanel monthlyPaymentPanel = new HorizontalPanel();
	
	
	public void onModuleLoad() {
		// input zone for loan's amount
		amountLabel.setWidth("7em");
		amountTextBox.setWidth("6em");
		amountPanel.add(amountLabel);
		amountPanel.add(amountTextBox);
		
		// input for down payment rate
		downPaymentLabel.setWidth("7em");
		downPaymentTextBox.setWidth("6em");
		downPaymentPanel.add(downPaymentLabel);
		downPaymentPanel.add(downPaymentTextBox);
		
		// input for how many months
		monthLabel.setWidth("7em");
		monthTextBox.setWidth("6em");
		monthPanel.add(monthLabel);
		monthPanel.add(monthTextBox);
		
		// input for loan rate
		rateLabel.setWidth("7em");
		rateTextBox.setWidth("6em");
		ratePanel.add(rateLabel);
		ratePanel.add(rateTextBox);
		
		// Add cost of the credit
		costLabel.setWidth("7em");
		costPanel.add(costLabel);
		costPanel.add(costAmountLabel);
		
		// Add the total amount of the credit
		totalLabel.setWidth("7em");
		totalPanel.add(totalLabel);
		totalPanel.add(totalAmountLabel);
		
		// Add the monthly payment amount
		monthlyPaymentLabel.setWidth("7em");
		monthlyPaymentPanel.add(monthlyPaymentLabel);
		monthlyPaymentPanel.add(monthlyPaymentAmountLabel);
		
		// Panels setup and disposition
		mainPanel.add(amountPanel);
		mainPanel.add(downPaymentPanel);
		mainPanel.add(monthPanel);
		mainPanel.add(ratePanel);
		mainPanel.add(costPanel);
		mainPanel.add(totalPanel);
		mainPanel.add(monthlyPaymentPanel);
		mainPanel.add(computeButton);
		mainPanel.setStyleName("mainPanel");
		RootPanel.get("loanCalculator").add(mainPanel);
		
		// Set the focus on the first box as the amount
		amountTextBox.setFocus(true);
		
		// set some default value
		amountTextBox.setText("10000");
		monthTextBox.setText("10");
		rateTextBox.setText("4.0%");
		downPaymentTextBox.setText("1000");
		
		// Setup all the listener for click and enter event
		computeButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				computeCredit();	
			}
		});
		
		amountTextBox.addKeyPressHandler(new KeyPressHandler(){
			public void onKeyPress(KeyPressEvent event){
				if (event.getCharCode() == KeyCodes.KEY_ENTER){
					computeCredit();
				}
			}
		});	
		
		downPaymentTextBox.addKeyPressHandler(new KeyPressHandler(){
			public void onKeyPress(KeyPressEvent event){
				if (event.getCharCode() == KeyCodes.KEY_ENTER){
					computeCredit();
				}
			}
		});	

		rateTextBox.addKeyPressHandler(new KeyPressHandler(){
			public void onKeyPress(KeyPressEvent event){
				if (event.getCharCode() == KeyCodes.KEY_ENTER){
					computeCredit();
				}
			}
		});	

		monthTextBox.addKeyPressHandler(new KeyPressHandler(){
			public void onKeyPress(KeyPressEvent event){
				if (event.getCharCode() == KeyCodes.KEY_ENTER){
					computeCredit();
				}
			}
		});	
	}

	protected void computeCredit() {
		// validate entries types (float and integer only)
		final String amountString = amountTextBox.getText().trim();
		if (!amountString.matches("[0-9]*\\.?[0-9]*")){
			Window.alert("'" + amountString + "' is not a valid entry for amount.");
			amountTextBox.selectAll();
			amountTextBox.setFocus(true);
			return;
		}
		amount = Double.parseDouble(amountString);
		
		final String downPaymentString = downPaymentTextBox.getText().trim();
		if (!downPaymentString.matches("[0-9]*\\.?[0-9]*")){
			Window.alert("'" + downPaymentString + "' is not a valid entry for down payment.");
			downPaymentTextBox.selectAll();
			downPaymentTextBox.setFocus(true);
			return;
		}
		if (downPaymentString.isEmpty()){
			downPayment = 0.0;
		}
		else{
			downPayment = Double.parseDouble(downPaymentString);
		}
		
		final String monthString = monthTextBox.getText().trim();
		if (!monthString.matches("[1-9][0-9]*")){
			Window.alert("'" + monthString + "' is not a valid entry for month.");
			monthTextBox.selectAll();
			monthTextBox.setFocus(true);
			return;
		}
		month = Double.parseDouble(monthString);
		
		final String rateString = rateTextBox.getText().trim();
		if (!rateString.matches("[0-9]+\\.?[0-9]*%?|\\.[0-9]+%?")){
			Window.alert("'" + rateString + "' is not a valid entry for rate.");
			rateTextBox.selectAll();
			rateTextBox.setFocus(true);
			return;
		}
		else{
			rate = Double.parseDouble(rateString.replace("%", ""));
		}
				
		if  (!amountString.isEmpty()){
			computeWithAmount();
		}
		else {
			Window.alert("'Please enter a value for amount.");
			amountTextBox.setFocus(true);
			return;
		}
	}

	private void computeWithAmount() {
		double exact=((amount-downPayment)*(rate/1200.0))/(1.0-(Math.pow((1.0/(1.0+(rate/1200.0))),(month))));
        totalAmount = (exact * month) + downPayment;
        costAmount = totalAmount - amount;
        
        // rounding
        double rounded = Math.round(exact*100)/100.0;
        totalAmount = Math.round(totalAmount*100)/100.0;
        costAmount = Math.round(costAmount*100)/100.0;
        
        monthlyPaymentAmountLabel.setText("$" + Double.toString(rounded));
        totalAmountLabel.setText("$" + Double.toString(totalAmount));
        costAmountLabel.setText("$" + Double.toString(costAmount));
        return;
	}
}