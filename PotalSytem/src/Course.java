public class Course {
    private String Name;
    private String Code;
    private int credit;
    Course(){}
    Course(String Code, String Name, int credit)
    {
        this.Code = Code;
       this.Name = Name;
       this.credit = credit;

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
}
