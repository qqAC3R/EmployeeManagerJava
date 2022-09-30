package employee;

import java.util.HashMap;

/*
1 etapa:
        Tip: Consola
        Descrierea: Utilizatorul vede un mesaj de salut si o explicatie cum sa foloseasca aplicatia.
        Functionalitatile disponibile trebuie sa fie: introducerea, vizualizarea, editarea, stergerea datelor despre angajati.
        La prima introducere utilizatorul trebuie sa decida numarul de angajati care trebuie introdus in sistem.
        Utilizatorul trebuie sa cunoasca pentru care camp el introduce datele.
        La vizualizare trebuie sa fie 2 optiuni: fie toate inregistrarile, fie un angajat specific.
        La editare/stergere, utilizatorul trebuie sa introduca pozitia necesara.
*/

public class EmployeeList {

    public EmployeeList()
    {
        m_EmployeeMap = new HashMap<Integer, Employee>();
    }

    public void Add(Employee employee)
    {
        m_EmployeeMap.put(s_GlobalIDIncrement, employee);
        s_GlobalIDIncrement++;

        System.out.println(s_GlobalIDIncrement-1);
    }

    public void SetEmployee(Integer id, String name, String prename, Employee.Gender gender)
    {
        if(CheckIfIsAvailable(id))
        {
            Employee employee = m_EmployeeMap.get(id);
            employee.SetName(name);
            employee.SetPrename(prename);
            employee.SetGender(gender);
        }
    }

    public void RemoveEmployee(Integer id)
    {
        m_EmployeeMap.remove(id);
    }

    public Employee GetEmployee(Integer id)  { return m_EmployeeMap.get(id); }
    public Employee[] GetEmployees()  { return m_EmployeeMap.values().toArray(new Employee[0]); }
    public HashMap<Integer, Employee> GetEmployeesAsHashMap()  { return m_EmployeeMap; }

    public boolean CheckIfIsAvailable(Integer id) { return m_EmployeeMap.containsKey(id); }

    // Hash Map containing ID + Employee data
    private HashMap<Integer, Employee> m_EmployeeMap;

    // `s_GlobalIDIncrement` is the variable that gives an id to the newly created Employee class and
    // then increments it by 1, for the next class
    private static int s_GlobalIDIncrement = 0;
}
