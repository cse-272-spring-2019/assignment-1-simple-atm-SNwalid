
public class ATM {
    private String cardNumber="5338984728291";
    private String password="shfr312$";
	private float accountBalance;
	ATM()   //first constructor
	{
		
		this.accountBalance=0;
		
	}
	ATM(float accountBalance)  //second constructor
	{
		this.accountBalance=accountBalance;
	}
	/**
	 * this function is validate the card Number and return true or false
	 * @param cardNumber type of String which is the number entered by the user
	 * @param password type of String which is the number entered by the user
	 * @return type of boolean which is the result of validation
	 */

	
	public boolean cardNumberValidation(String cardNumber,String password)
	{
       return (this.cardNumber.equals(cardNumber) && this.password.equals(password));
	}
	/**
	 * This function return the account Balance after withdraw
	 * @param amount type of integer which is the amount to be withdrawn
	 * @return type of float which is the total balance of the account
	 */
  public float withDraw(int amount) {
	 
		 if(amount<=this.accountBalance && amount%10 == 0 && amount>0 && amount>=50 && amount<=8000)
		 {
			 this.accountBalance-=amount;
			 return this.accountBalance;
		 }
		 else return -1;
  }
  /**
   * This function is used to check weather the the Number have character or not.
   * @param strNum type of String which is the Number u need to validate.
   * @return type of boolean which is the result of validation.
   */
  public  boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}

  /**
   * this function used to take Deposit and add it to your balance
   * @param deposite type of integer which is the deposit
   * @return type of float which is the updated balance
   */
  public float deposite(int deposite)
  { 
	  if(deposite%10==0 && deposite>0 && deposite >=50)
	  { 
		  this.accountBalance=this.accountBalance+deposite;
   	  return accountBalance;
   	  }
     else
		  return -1;
  }
  /**
   * This function return the total balance
   * @return type of integer which is total balance
   */
  public float balanceInquiry() {
	   return accountBalance;
  }
}