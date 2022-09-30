package employee;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManager {

    public EmployeeManager()
    {
        System.out.println("WELCOME TO");
        System.out.println("▒█▀▀▀ ▒█▀▄▀█ ▒█▀▀█ ▒█░░░ ▒█▀▀▀█ ▒█░░▒█ ▒█▀▀▀ ▒█▀▀▀ 　 ▒█▀▄▀█ ░█▀▀█ ▒█▄░▒█ ░█▀▀█ ▒█▀▀█ ▒█▀▀▀ ▒█▀▀█");
        System.out.println("▒█▀▀▀ ▒█▒█▒█ ▒█▄▄█ ▒█░░░ ▒█░░▒█ ▒█▄▄▄█ ▒█▀▀▀ ▒█▀▀▀ 　 ▒█▒█▒█ ▒█▄▄█ ▒█▒█▒█ ▒█▄▄█ ▒█░▄▄ ▒█▀▀▀ ▒█▄▄▀");
        System.out.println("▒█▄▄▄ ▒█░░▒█ ▒█░░░ ▒█▄▄█ ▒█▄▄▄█ ░░▒█░░ ▒█▄▄▄ ▒█▄▄▄ 　 ▒█░░▒█ ▒█░▒█ ▒█░░▀█ ▒█░▒█ ▒█▄▄█ ▒█▄▄▄ ▒█░▒█");

        Initialize();
        System.out.println("System ready to begin!");
    }

    private void Initialize()
    {
        m_EmployeeList = new EmployeeList();
        m_InternalScanner = new Scanner(System.in);
        System.out.println("\nInitialization Done!");
    }

    public boolean Run()
    {
        switch(m_MenuType)
        {
            case Main:   PrintMainMenu(); break;
            case Add:    AddNewUser();    break;
            case Show:   ShowUsers();     break;
            case Edit:   EditUsers();     break;
            case Remove: RemoveUsers();   break;
            case Exit: return false;
        }
        return true;
    }

    private void PrintMainMenu()
    {
        while(true)
        {
            System.out.println("==================================");
            System.out.println("Employee Manager Menu:");
            System.out.println("  1. Add Employee");
            System.out.println("  2. Show Employee");
            System.out.println("  3. Edit Employee");
            System.out.println("  4. Remove Employee");
            System.out.println("  5. Exit");
            int menuTypeInt = m_InternalScanner.nextInt();
            m_MenuType = ConvertIntToMenuType(menuTypeInt);
            if(m_MenuType == MenuType.Undefined)
            {
                System.out.println("\nIncorrect key code! Try again!");
            }
            else
            {
                break;
            }
        }
    }

    private void AddNewUser()
    {
        System.out.println("==================================");
        System.out.println("Add new Employee:");
        m_InternalScanner.nextLine();

        System.out.print("  Name: ");
        String name = m_InternalScanner.nextLine();

        System.out.print("  Prename: ");
        String prename = m_InternalScanner.nextLine();

        System.out.println("  Gender: ");
        System.out.println("    1. Male ");
        System.out.println("    2. Female ");

        int genderInt = m_InternalScanner.nextInt();
        Employee.Gender gender = Employee.Gender.Undefined;
        switch (genderInt)
        {
            case 1: gender = Employee.Gender.Male; break;
            case 2: gender = Employee.Gender.Female; break;
        }

        m_EmployeeList.Add(new Employee(name, prename, gender));


        boolean continueBool = ShowContinueMenu("Continue adding:");
        if(!continueBool)
            m_MenuType = MenuType.Main;
        /*

        System.out.println("==================================");
        System.out.println("Continue:");
        System.out.println("  1. Yes");
        System.out.println("  2. No");
        int nextAction = m_InternalScanner.nextInt();
        if(nextAction == 2)
            m_MenuType = MenuType.Main;
         */
    }

    private void ShowUsers()
    {
        System.out.println("==================================");
        System.out.println("Show Employees:");
        System.out.println("  1. Search (by ID)");
        System.out.println("  2. Show All");
        int nextAction = m_InternalScanner.nextInt();
        if(nextAction == 1)
        {
            while(true)
            {
                System.out.println("==================================");
                System.out.print("Employee ID: ");
                Integer employeeId = m_InternalScanner.nextInt();
                if (m_EmployeeList.CheckIfIsAvailable(employeeId))
                {
                    Employee selectedEmployee = m_EmployeeList.GetEmployee(employeeId);
                    ShowUser(selectedEmployee);
                    break;
                }
                else
                {
                    System.out.println("Employee ID not found!");
                    boolean continueBool = ShowContinueMenu("Continue searching an employee:");
                    if(!continueBool)
                        break;
                }
            }

        }
        else if(nextAction == 2)
        {
            HashMap<Integer, Employee> employeesMap = m_EmployeeList.GetEmployeesAsHashMap();

            System.out.println("==================================");
            for(Map.Entry<Integer, Employee> object : employeesMap.entrySet())
            {
                System.out.println("Employee ID: " + object.getKey());
                Employee selectedEmployee = m_EmployeeList.GetEmployee(object.getKey());
                ShowUser(selectedEmployee);
                System.out.println("__________________________________");
            }
        }

        boolean continueBool = ShowContinueMenu("Continue searching:");
        if(!continueBool)
            m_MenuType = MenuType.Main;
        /*

        System.out.println("==================================");
        System.out.println("Continue:");
        System.out.println("  1. Yes");
        System.out.println("  2. No");
        nextAction = m_InternalScanner.nextInt();
        if(nextAction == 2)
            m_MenuType = MenuType.Main;
         */
    }

    private void ShowUser(Employee employee)
    {
        System.out.println("Name: " + employee.GetName());
        System.out.println("Prename: " + employee.GetPrename());
        System.out.println("Gender: " + Employee.GetStringFromGender(employee.GetGender()));
    }

    private void EditUsers()
    {
        System.out.println("==================================");
        System.out.print("Employee ID:");
        int employeeId = m_InternalScanner.nextInt();

        System.out.println("==================================");
        if(m_EmployeeList.CheckIfIsAvailable(employeeId))
        {
            System.out.println("Employee Found!");

            while(true)
            {
                System.out.println("What would you like to change?");
                System.out.println("  1. Name");
                System.out.println("  2. Prename");
                System.out.println("  3. Gender");
                int action = m_InternalScanner.nextInt();
                if (action == 1) {
                    System.out.print("  New name: ");
                    m_InternalScanner.nextLine();
                    String newName = m_InternalScanner.nextLine();

                    Employee employee = m_EmployeeList.GetEmployee(employeeId);
                    employee.SetName(newName);
                } else if (action == 2) {
                    System.out.print("  New prename: ");
                    m_InternalScanner.nextLine();
                    String newPrename = m_InternalScanner.nextLine();

                    Employee employee = m_EmployeeList.GetEmployee(employeeId);
                    employee.SetPrename(newPrename);
                } else if (action == 3) {
                    System.out.println("  New gender: ");
                    System.out.println("    1. Male ");
                    System.out.println("    2. Female ");

                    Employee.Gender newGender = Employee.Gender.Undefined;
                    int genderAction = m_InternalScanner.nextInt();
                    switch (genderAction) {
                        case 1:
                            newGender = Employee.Gender.Male;
                            break;
                        case 2:
                            newGender = Employee.Gender.Female;
                            break;
                    }


                    Employee employee = m_EmployeeList.GetEmployee(employeeId);
                    employee.SetGender(newGender);
                }

                boolean continueBool = ShowContinueMenu("Continue editing:");
                if (!continueBool)
                {
                    m_MenuType = MenuType.Main;
                    break;
                }
            }
        }



    }


    private void RemoveUsers()
    {
        System.out.println("==================================");
        System.out.print("Employee ID:");
        int employeeId = m_InternalScanner.nextInt();

        System.out.println("==================================");

        while(true)
        {

            if(m_EmployeeList.CheckIfIsAvailable(employeeId))
            {
                m_EmployeeList.RemoveEmployee(employeeId);
                System.out.println("Employee deleted!");

                boolean continueBool = ShowContinueMenu("Continue removing:");
                if (!continueBool)
                {
                    m_MenuType = MenuType.Main;
                    break;
                }
            }
            else
            {
                System.out.println("Employee not found!");

                boolean continueBool = ShowContinueMenu("Continue removing:");
                if (!continueBool)
                {
                    m_MenuType = MenuType.Main;
                    break;
                }
            }

        }
    }


    private enum MenuType
    {
        Undefined,
        Main,
        Add,
        Show,
        Edit,
        Remove,
        Exit,
    }
    private MenuType ConvertIntToMenuType(int menuType)
    {
        switch (menuType)
        {
            case 0: return MenuType.Main;
            case 1: return MenuType.Add;
            case 2: return MenuType.Show;
            case 3: return MenuType.Edit;
            case 4: return MenuType.Remove;
            case 5: return MenuType.Exit;
            //default: return MenuType.Undefined; // TODO: Handled by Exceptions
        }
        return MenuType.Undefined;
    }

    private boolean ShowContinueMenu(String msg)
    {
        System.out.println("==================================");
        System.out.println(msg);
        System.out.println("  1. Yes");
        System.out.println("  2. No");
        int nextAction = m_InternalScanner.nextInt();
        return nextAction == 1 ? true : false;
    }



    private EmployeeList m_EmployeeList;
    private Scanner m_InternalScanner;
    private MenuType m_MenuType = MenuType.Main;

}
