import employee.Employee;
import employee.EmployeeList;
import employee.EmployeeManager;

public class Application {

    public static void main(String[] args)
    {
        Initialize();
        while(m_IsRunning)
        {
            m_IsRunning = Run();
        }
        CleanUp();
    }

    private static void Initialize()
    {
        m_EmployeeManager = new EmployeeManager();
    }

    private static boolean Run()
    {
        return m_EmployeeManager.Run();
    }

    private static void CleanUp()
    {
    }

    private static boolean m_IsRunning = true;
    private static EmployeeManager m_EmployeeManager;
}
