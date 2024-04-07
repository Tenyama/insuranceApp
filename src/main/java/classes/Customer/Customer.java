package classes.Customer;
/**
 * @author Le Thien Son - s3977955
 * @reference mabe02. (n.d.). Mabe02/Lanterna: Java library for creating text-based guis. GitHub. https://github.com/mabe02/lanterna?tab=readme-ov-file
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Claim;
import classes.InsuranceCard;
import classes.fileManip.MaxIdFinder;

import static classes.fileManip.FileLineRemover.removeLineById;

public abstract class Customer {
  private static String filePath = "src/main/java/docs/customers.txt";
  private String id;
  private String fullName;
  private InsuranceCard card;
  private List<Claim> listOfClaims = new ArrayList<>();

  public Customer() throws IOException {
    this.id = formatId(MaxIdFinder.getMaxCustomerId(filePath) + 1);
  }

  public Customer(String id) {
    this.id = id;
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

  public void addClaim(String claimId) throws IOException {
    Claim dummy = new Claim.Builder().id(claimId).build();
    this.listOfClaims.add(dummy);
  }
  private List<String> abbreviatedClaimList() {
    List<String> dummy = new ArrayList<>();
    if (listOfClaims != null) {
      for (int i = 0; i < listOfClaims.size(); i++) {
        dummy.add(listOfClaims.get(i).getId());
      }
    }
    return dummy;
  }
  @Override
  public String toString() {
    return "id=" + id +
            ", fullName=" + fullName +
            ", card=" + (card == null? "null" : card.getCardNumber()) +
            ", listOfClaims=" + abbreviatedClaimList() ;
  }
}
