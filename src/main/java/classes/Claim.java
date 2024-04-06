package classes;

/**
 * @author Le Thien Son - s3977955
 */

import classes.Customer.Customer;
import classes.fileManip.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static classes.fileManip.FileLineRemover.removeLineById;
import static classes.fileManip.Reader.readFile;

public class Claim implements ClaimProcessManager {
  private static final String filePath = "src/main/java/docs/claims.txt";
  private final String id;
  private LocalDate claimDate;
  private Customer insuredPerson;
  private String cardNumber;
  private LocalDate examDate;
  private List<String> documents;
  private double claimAmount;
  private String status;
  private String receiverBankingInfo;

  public static class Builder {
    private final String id = formatId(MaxIdFinder.getMaxCustomerId(filePath) + 1);;
    private LocalDate claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private LocalDate examDate;
    private List<String> documents;
    private double claimAmount;
    private String status;
    private String receiverBankingInfo;

    public Builder() throws IOException {
    }

    public Builder claimDate(LocalDate claimDate) {
      this.claimDate = claimDate;
      return this;
    }

    public Builder insuredPerson(Customer insuredPerson) {
      this.insuredPerson = insuredPerson;
      return this;
    }

    public Builder cardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
      return this;
    }

    public Builder examDate(LocalDate examDate) {
      this.examDate = examDate;
      return this;
    }

    public Builder documents(List<String> documents) {
      this.documents = documents;
      return this;
    }

    public Builder claimAmount(double claimAmount) {
      this.claimAmount = claimAmount;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder receiverBankingInfo(String receiverBankingInfo) {
      this.receiverBankingInfo = receiverBankingInfo;
      return this;
    }

    public Claim build() throws IOException {
      return new Claim(this);
    }
  }

  private Claim(Builder builder) throws IOException {
    this.id = formatId(MaxIdFinder.getMaxCustomerId(filePath) + 1);
    this.claimDate = builder.claimDate;
    this.insuredPerson = builder.insuredPerson;
    this.cardNumber = builder.cardNumber;
    this.examDate = builder.examDate;
    this.documents = builder.documents;
    this.claimAmount = builder.claimAmount;
    this.status = builder.status;
    this.receiverBankingInfo = builder.receiverBankingInfo;
  }

  public Claim() throws IOException {
    this.id = formatId(MaxIdFinder.getMaxCustomerId(filePath) + 1);
  }

  public Claim(LocalDate claimDate, Customer insuredPerson, String cardNumber, LocalDate examDate, List<String> documents, double claimAmount, String status, String receiverBankingInfo) throws IOException {
    this.id = formatId(MaxIdFinder.getMaxCustomerId(filePath) + 1);
    this.claimDate = claimDate;
    this.insuredPerson = insuredPerson;
    this.cardNumber = cardNumber;
    this.examDate = examDate;
    this.documents = documents;
    this.claimAmount = claimAmount;
    this.status = status;
    this.receiverBankingInfo = receiverBankingInfo;
  }

  public static String formatId(int number) {
    // Padding with leading zeros if necessary
    return String.format("f-%010d", number);
  }

  public String getId() {
    return id;
  }

  public LocalDate getClaimDate() {
    return claimDate;
  }

  public void setClaimDate(LocalDate claimDate) {
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

  public LocalDate getExamDate() {
    return examDate;
  }

  public void setExamDate(LocalDate examDate) {
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
    return "id=" + id +
            ", claimDate=" + claimDate +
            ", insuredPerson=" + (insuredPerson == null? "null" : insuredPerson.getId()) +
            ", cardNumber=" + cardNumber +
            ", examDate=" + examDate +
            ", documents=" + documents +
            ", claimAmount=" + claimAmount +
            ", status=" + status +
            ", receiverBankingInfo=" + receiverBankingInfo;
  }
  @Override
  public void add(){
    ObjectWriter.writeObject(new ClaimWriter(this));
  }

  @Override
  public void update() {

  }

  @Override
  public void delete(String name) {
    removeLineById(filePath, name);
  }

  @Override
  public String getOne(List<String> data) {
    StringBuilder message = new StringBuilder();
    message.append("Id: ").append(data.get(0)).append("\n");
    message.append("Claim Date: ").append(data.get(1)).append("\n");
    message.append("Insured Person: ").append(data.get(2)).append("\n");
    message.append("Exam Date: ").append(data.get(3)).append("\n");
    message.append("Documents: ").append(data.get(4)).append("\n");
    message.append("Claim Amount: ").append(data.get(5)).append("\n");
    message.append("Status: ").append(data.get(5)).append("\n");
    message.append("Receiver Banking Info: ").append(data.get(5)).append("\n");
    return message.toString();
  }

  @Override
  public List<String[]> getAll() throws IOException {
    return readFile(3);
  }
}
