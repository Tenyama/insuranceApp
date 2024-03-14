package classes;

/**
 * @author Le Thien Son - s3977955
 */

import classes.Claim;

import java.util.List;

public abstract class Customer {
  private String id;
  private String fullName;
  private InsuranceCard card;
  private List<Claim> listOfClaims;

  public Customer() {
  }

  public Customer(String id, String fullName, InsuranceCard card, List<Claim> listOfClaims) {
    this.id = id;
    this.fullName = fullName;
    this.card = card;
    this.listOfClaims = listOfClaims;
  }

  public String getId() { return id; }

  public String getFullName() { return fullName; }

  public InsuranceCard getCard() { return card; }

  public void setCard(InsuranceCard card) { this.card = card; }

  public List<Claim> getListOfClaims() { return listOfClaims; }

  public void setListOfClaims(List<Claim> listOfClaims) {
    this.listOfClaims = listOfClaims;
  }
}
