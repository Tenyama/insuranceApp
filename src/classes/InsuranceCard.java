package classes;

/**
 * @author Le Thien Son - s3977955
 */

import java.util.Date;

public class InsuranceCard {
  private String cardNumber;
  private Customer cardHolder;
  private String policyHolder;
  private Date expirationDate;

  public InsuranceCard(String cardNumber, Customer cardHolder, String policyHolder, Date expirationDate) {
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.policyHolder = policyHolder;
    this.expirationDate = expirationDate;
  }

  public InsuranceCard(String cardNumber, Customer cardHolder, Date expirationDate) {
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expirationDate = expirationDate;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Customer getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(Customer cardHolder) {
    this.cardHolder = cardHolder;
  }

  public String getPolicyHolder() {
    return policyHolder;
  }

  public void setPolicyHolder(String policyHolder) {
    this.policyHolder = policyHolder;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }
}
