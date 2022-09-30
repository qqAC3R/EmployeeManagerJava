package employee;

public class Employee {

    // `s_GlobalIDIncrement` is the variable that gives an id to the newly created Employee class and
    // then increments it by 1, for the next class
    //private static int s_GlobalIDIncrement = 0;

    public Employee()
    {
        // Set up the default name, prename and gender
        m_Name = "";
        m_Prename  = "";
        m_Gender = Gender.Undefined;
    }

    public Employee(String name, String prename, Gender gender)
    {
        // Set up the default name, prename and gender
        m_Name = name;
        m_Prename  = prename;
        m_Gender = gender;
    }

    // TODO: Employee creation with custom ID?
    // public Employee(String name, String prename, Gender gender, int customID) {}

    public String GetName() { return m_Name; }
    public String GetPrename() { return m_Prename; }
    public Gender GetGender() { return m_Gender; }

    public void SetName(String name) { m_Name = name; }
    public void SetPrename(String prename) { m_Prename = prename; }
    public void SetGender(Gender gender) { m_Gender = gender; }


    public enum Gender
    {
        Undefined, Male, Female
    }
    public static String GetStringFromGender(Gender gender)
    {
        switch (gender)
        {
            case Undefined: return "Undefined";
            case Male: return "Male";
            case Female: return "Female";
        }
        return "";
    }

    //private int m_ID;
    private String m_Name;
    private String m_Prename;
    private Gender m_Gender;
}
