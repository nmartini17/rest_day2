package facades;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EmployeeFacade {
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;

    private EmployeeFacade() {}

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    //TODO Create an Employee facade class that can
    // - getEmployeeById
    // - getEmployeesByName
    // - getAllEmployees
    // - getEmployeesWithHighestSalary
    // - createEmployee


    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

}
