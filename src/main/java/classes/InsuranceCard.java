package classes;

/**
 * @author Le Thien Son - s3977955
 */

import classes.Customer.Customer;
import classes.fileManip.MaxIdFinder;
import classes.fileManip.RandomNumberGenerator;

import java.time.LocalDate;

public class InsuranceCard {
  private String cardNumber;
  private Customer cardHolder;
  private String policyOwner;
  private LocalDate expirationDate;
  private InsuranceCard(Builder builder) {
    this.cardNumber = formatNumber(builder.cardNumber);
    this.cardHolder = builder.cardHolder;
    this.policyOwner = builder.policyOwner;
    this.expirationDate = builder.expirationDate;
  }
  public InsuranceCard() {
    this.cardNumber = formatNumber(RandomNumberGenerator.randTen());
  }
  public InsuranceCard(Customer cardHolder, String policyOwner, LocalDate expirationDate) {
    this.cardNumber = formatNumber(RandomNumberGenerator.randTen());
    this.cardHolder = cardHolder;
    this.policyOwner = policyOwner;
    this.expirationDate = expirationDate;
  }

  public static String formatNumber(long number) {
    // Padding with leading zeros if necessary
    return String.format("%010d", number);
  }

  @Override
  public String toString() {
    return "cardNumber=" + cardNumber +
            ", cardHolder=" + cardHolder +
            ", policyOwner=" + policyOwner +
            ", expirationDate=" + expirationDate;
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

  public String getPolicyOwner() {
    return policyOwner;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }
  public static class Builder {
    private long cardNumber = RandomNumberGenerator.randTen();
    private Customer cardHolder;
    private String policyOwner;
    private LocalDate expirationDate;

    public Builder () {
    }

    ////////////////////////
    //("Gotta add READER")//
    ////////////////////////

    public Builder cardHolder(String cardHolder) {
      this.policyOwner = policyOwner;
      return this;
    }

    public Builder policyOwner(String policyOwner) {
      this.policyOwner = policyOwner;
      return this;
    }

    public Builder expirationDate(LocalDate expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public InsuranceCard build() {
      return new InsuranceCard(this);
    }
  }
}
