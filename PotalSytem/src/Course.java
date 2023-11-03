public class Course {
    private String Name;
    private String Code;
    private int credit;
    private int Total_section;
    Course(){}
    Course(String Code, String Name, int credit)
    {
        this.Code = Code;
        this.Name = Name;
        this.credit = credit;

    }
    Course(String Code, String Name, int credit, int Total_section)
    {
        this.Code = Code;
       this.Name = Name;
       this.credit = credit;
       this.Total_section = Total_section;

    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }
    public int getCredit() {
        return credit;
    }

    public void setTotal_section(int total_section) {
        Total_section = total_section;
    }

    public int getTotal_section() {
        return Total_section;
    }

    @Override
    public String toString() {
        return Code + "\t" +Name  + "\t" + credit + "\t" + Total_section + "\n" ;
    }
}
