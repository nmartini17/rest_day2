package facades;

import dtos.EmployeeDTO;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeFacade {
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;

    //Create employees
    Employee e1 = new Employee("Anita","Abcvej 1", 142000);
    Employee e2 = new Employee("Benny","Bennyvej 1", 197000);

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

    public List<EmployeeDTO> getEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> q = em.createQuery("Select e from Employee e where e.name = :name", Employee.class);
            q.setParameter("name", name);
            List<Employee> e = q.getResultList();
            return (List<EmployeeDTO>)(List<?>)e;
        } finally {
            em.close();
        }
    }
}
