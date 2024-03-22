package classes;

/**
 * @author Le Thien Son - s3977955
 */

import classes.Claim;

import java.io.IOException;
import java.util.List;
import classes.fileManip.MaxIdFinder;

public abstract class Customer {
  private static String filePath = "src/main/java/docs/customers.txt";
  private String id;
  private String fullName;
  private InsuranceCard card;
  private List<Claim> listOfClaims;

  public Customer() throws IOException {
    this.id = formatId(MaxIdFinder.getMaxId(filePath) + 1);
  }

  public Customer(String fullName, InsuranceCard card, List<Claim> listOfClaims) throws IOException {
    this.id = formatId(MaxIdFinder.getMaxId(filePath) + 1);
    this.fullName = fullName;
    this.card = card;
    this.listOfClaims = listOfClaims;
  }

  public static String formatId(int number) {
    // Padding with leading zeros if necessary
    return String.format("c-%07d", number);
  }

  public String getId() { return id; }

  public String getFullName() { return fullName; }

  public InsuranceCard getCard() { return card; }

  public void setCard(InsuranceCard card) { this.card = card; }

  public List<Claim> getListOfClaims() { return listOfClaims; }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setListOfClaims(List<Claim> listOfClaims) {
    this.listOfClaims = listOfClaims;
  }

  @Override
  public String toString() {
    return "id='" + id + '\'' +
            ", fullName='" + fullName + '\'' +
            ", card=" + card +
            ", listOfClaims=" + listOfClaims ;
  }
}
