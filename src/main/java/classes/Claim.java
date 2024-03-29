package classes;

/**
 * @author Le Thien Son - s3977955
 */

import classes.Customer.Customer;

import java.util.Date;
import java.util.List;

public class Claim {
  private String id;
  private Date claimDate;
  private Customer insuredPerson;
  private String cardNumber;
  private Date examDate;
  private List<String> documents;
  private double claimAmount;
  private String status;
  private String receiverBankingInfo;

  public Claim() {
  }

  public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate, List<String> documents, double claimAmount, String status, String receiverBankingInfo) {
    this.id = formatId(id);
    this.claimDate = claimDate;
    this.insuredPerson = insuredPerson;
    this.cardNumber = cardNumber;
    this.examDate = examDate;
    this.documents = documents;
    this.claimAmount = claimAmount;
    this.status = status;
    this.receiverBankingInfo = receiverBankingInfo;
  }

  public static String formatId(String number) {
    // Padding with leading zeros if necessary
    StringBuilder paddedNumber = new StringBuilder(number);
    while (paddedNumber.length() < 10) {
      paddedNumber.insert(0, "0");
    }
    return "f-" + paddedNumber.toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = formatId(id);
  }

  public Date getClaimDate() {
    return claimDate;
  }

  public void setClaimDate(Date claimDate) {
    this.claimDate = claimDate;
  }

  public Customer getInsuredPerson() {
    return insuredPerson;
  }

  public void setInsuredPerson(Customer insuredPerson) {
    this.insuredPerson = insuredPerson;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Date getExamDate() {
    return examDate;
  }

  public void setExamDate(Date examDate) {
    this.examDate = examDate;
  }

  public List<String> getDocuments() {
    return documents;
  }

  public void setDocuments(List<String> documents) {
    this.documents = documents;
  }

  public double getClaimAmount() {
    return claimAmount;
  }

  public void setClaimAmount(double claimAmount) {
    this.claimAmount = claimAmount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getReceiverBankingInfo() {
    return receiverBankingInfo;
  }

  public void setReceiverBankingInfo(String receiverBankingInfo) {
    this.receiverBankingInfo = receiverBankingInfo;
  }

  @Override
  public String toString() {
    return "Claim{" +
            "id='" + id + '\'' +
            ", claimDate=" + claimDate +
            ", insuredPerson=" + insuredPerson +
            ", cardNumber='" + cardNumber + '\'' +
            ", examDate=" + examDate +
            ", documents=" + documents +
            ", claimAmount=" + claimAmount +
            ", status='" + status + '\'' +
            ", receiverBankingInfo='" + receiverBankingInfo + '\'' +
            '}';
  }
}
