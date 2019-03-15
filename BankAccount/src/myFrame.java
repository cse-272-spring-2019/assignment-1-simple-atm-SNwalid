import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class myFrame extends Application{
	ATM customer;
	int counter=-1;
	int temp=counter;
	String[]operations=new String[5];
	public static void main(String[] args)
	 {
		launch(args);
	 }
	@Override
 public void start(Stage primaryStage) throws Exception {
		customer=new ATM();
	//login form draw*****************************************************************
	Label cardNumberLabelField=new Label("Enter Card Number :");
	Label PasswordLabelField=new Label("Enter Your Password :");
	Label warningLabelField=new Label();
	TextField cardNumberTextField=new TextField();
	PasswordField passwordTextField=new PasswordField();
	Button submitButton= new Button("Submit");
	GridPane logInFormGrid=new GridPane();
	logInFormGrid.add(cardNumberLabelField, 0, 0);
	logInFormGrid.add(PasswordLabelField, 0, 1);
	logInFormGrid.add(passwordTextField,1, 1);
	logInFormGrid.add(cardNumberTextField, 1, 0);
	logInFormGrid.add(submitButton, 1,3);
	logInFormGrid.add(warningLabelField,2,3);
	Scene logInScene =new Scene(logInFormGrid, 600, 400);
	primaryStage.setScene(logInScene);
	//End of login form draw***********************************************************
	
	//Home screen draw*****************************************************************
	primaryStage.setTitle("ATM");
	Button depositeButton=new Button("Deposite");
	Button withdrawButton=new Button("Withdraw");
	Button balanceInquiryButton=new Button("Balance Inquiry");
	Button historyNavigationButton=new Button("History");
	Button backHomeScreenButton=new Button("<-back");
    GridPane homeScreenGrid=new GridPane();
	
	homeScreenGrid.add(depositeButton, 1, 5);
	homeScreenGrid.add(withdrawButton, 3, 5);
	homeScreenGrid.add(balanceInquiryButton, 5, 5);
	homeScreenGrid.add(historyNavigationButton, 3, 8);
	homeScreenGrid.add(backHomeScreenButton, 4, 8);
    Scene homeScreenScene=new Scene(homeScreenGrid,600,500);
  //End of Home Screen draw***********************************************************
    
    //operations Screen draw**********************************************************
    Label amountLabelField=new Label("Enter Your amount from 50 to 8000:");
    TextField amountTextField =new TextField();
    Label messageLabelField= new Label();
    Button okButtonField=new Button("Ok");
    Button backButton=new Button("<-back");
    GridPane operationScreenGrid =new GridPane();
    
    operationScreenGrid.add(amountLabelField, 0, 0);
    operationScreenGrid.add(amountTextField, 1, 0);
    operationScreenGrid.add(okButtonField, 1, 1);
    operationScreenGrid.add(messageLabelField, 1, 2);
    operationScreenGrid.add(backButton, 2, 2);
    Scene operationScreenScene=new Scene(operationScreenGrid,800,400);
    //End of operation Screen draw**************************************************
    
    //balance Inquiry draw**********************************************************
    Label balanceInquiryLabel=new Label();
    Button backButtonInquiry=new Button("<-back");
    GridPane balanceInquiryGrid =new GridPane();
    balanceInquiryGrid.add(balanceInquiryLabel, 0, 0);
    balanceInquiryGrid.add(backButtonInquiry, 1, 1);
    Scene balanceInquiryScene=new Scene(balanceInquiryGrid,600,400);
    //End balance inquiry draw******************************************************
    
    //history transaction Draw******************************************************
    Button nextButton =new Button("Next");
    Button previousButton=new Button("Previous");
    Button backHistoryButton=new Button("<-back");
    Label historyTransactionLabel=new Label("No History To show!");
    GridPane historyGrid=new GridPane();
    historyGrid.add(historyTransactionLabel, 0, 0);
    historyGrid.add(nextButton, 1, 1);
    historyGrid.add(previousButton, 2, 1);
    historyGrid.add(backHistoryButton, 1, 2);
    Scene historyScene=new Scene(historyGrid,600,400);
    //End history transaction Draw************************************************
    
    
   submitButton.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			String cardNumber=cardNumberTextField.getText();
			String password=passwordTextField.getText();
			boolean valid =customer.cardNumberValidation(cardNumber, password);
			if(valid) {
				primaryStage.setScene(homeScreenScene);
				backHomeScreenButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(logInScene);

					}
				});
				balanceInquiryButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
					  primaryStage.setScene(balanceInquiryScene);
					  float balance =customer.balanceInquiry();
					  balanceInquiryLabel.setText("Your Balance is "+balance);
					  if(counter==4)
					  {
						  for(int i=0;i<4;i++)
						  {
							  operations[i]=operations[i+1];
						  }
						  operations[4]="You Asked For Your Balance"+balance;
						  counter=4;
						  temp=counter;
					  }
					  else {
						  operations[++counter]="You Asked For Your Balance"+balance;
						  temp=counter;
					  }
						  
					  backButtonInquiry.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							primaryStage.setScene(homeScreenScene);

						}
					});
					}
				 
				});
				depositeButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						amountLabelField.setText("Enter Your amount (At least 50): ");
						primaryStage.setScene(operationScreenScene);
						okButtonField.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) { //I put this button event handler here As i can make use of it again in withDraw Button
								String amount =amountTextField.getText();
								boolean validation =customer.isNumeric(amount);
								if(validation) {
								 int amountInt =Integer.parseInt(amount);
								 
								 float balance=customer.deposite(amountInt); 
								 if(balance>0) {
								 messageLabelField.setText("You have put a deposite of "+amountInt +" "+"Your balance is :"+balance);
								  if(counter==4)
								  {
									 for(int i=0 ;i<4;i++)
									 {
										 operations[i]=operations[i+1]; 
									 }
									 counter=4;
									 temp=counter;
									 operations[4]="You have put a deposite of : "+amountInt;
								  }
								  else {
									  operations[++counter]="You have put a deposite of : "+amountInt;
									  temp=counter;
								  }
								 }
								 else
									 messageLabelField.setText("Inalid amount");
								}
								else
									messageLabelField.setText("Invalid amount");
							} 
						});
						backButton.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								primaryStage.setScene(homeScreenScene);
								messageLabelField.setText("");
							}
						});
					}
				});
				withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(operationScreenScene);
						amountLabelField.setText("Enter Your amount from 50 to 8000:");

						okButtonField.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								String amount=amountTextField.getText();
								boolean valid= customer.isNumeric(amount);
							if(valid) {
								int amountInt =Integer.parseInt(amount);
								float balance =customer.withDraw(amountInt);
								if(balance == -1)
								{
									messageLabelField.setText("Invalid amount!!");
								}
								else {
									messageLabelField.setText("You withdrawn an amount of :"+amountInt+" "+"Your total balance is :"+balance);
									 if(counter==4)
									 {
										 for(int i=0 ;i<4;i++)
										 {
											 operations[i]=operations[i+1]; 
										 }
										 counter=4;
										 operations[4]="You withdrawn an amount of :"+amountInt;
										 temp=counter;
									 }
								     else {
								    	 operations[++counter]="You withdrawn an amount of :"+amountInt;
								    	 temp=counter;
								     }
								}
								
							}
							else
								messageLabelField.setText("Invalid amount!!");
							}});
						backButton.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								primaryStage.setScene(homeScreenScene);
								messageLabelField.setText("");
							}
						});
					}
				});
				historyNavigationButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {						 
						primaryStage.setScene(historyScene);
				            if(counter==-1)
				            {
				            	historyTransactionLabel.setText("No history to show!");
				            	
				            }
				            else {
				            	historyTransactionLabel.setText(operations[temp]);}
					
				            backHistoryButton.setOnAction(new EventHandler<ActionEvent>() {
				            	@Override
		                           public void handle(ActionEvent event) {
						 
				            		primaryStage.setScene(homeScreenScene);	
				            		counter=temp;
				            	}
					
				            });
					
				            nextButton.setOnAction(new EventHandler<ActionEvent>() {
						
						
				            	
				            	@Override
						
				            	public void handle(ActionEvent event) {
                                     if(counter==-1)
                                     {
                                    	 historyTransactionLabel.setText("No history to show!");
                                     }

                                     else if(counter==temp)
                                     {
                                    	 historyTransactionLabel.setText(operations[temp]);
                                     }
                                     else {
                                           historyTransactionLabel.setText(operations[++counter]);}
							
						
				            	}
					
				            });
					
				            previousButton.setOnAction(new EventHandler<ActionEvent>() {
				            	@Override
						
				            	public void handle(ActionEvent event) {
				            		if(counter==0)
				            		{
				            			historyTransactionLabel.setText(operations[0]);
				            			counter=0;
				            		}
				            		else if(counter==-1)
				            		{
				            			historyTransactionLabel.setText("No history to show!");
				            		}
				            		else
								
				            			historyTransactionLabel.setText(operations[--counter]);								
				            	}
					
				            });
					
					}
					
				
				});
				
			}
			else 	
              warningLabelField.setText("Invalid Card Number OR password!!");
		}
	});

	
	
	
	primaryStage.show();
 }
}
