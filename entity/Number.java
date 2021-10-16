package entity;

public class Number {
  private int numberId;
  private String name;
  
  public Number(int numberId, String name) {
    this.numberId = numberId;
    this.name = name;
  }

  public int getNumberId() {
    return numberId;
  }

  public void setNumberId(int numberId) {
    this.numberId = numberId;
  }

    public String getName() {
      return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}