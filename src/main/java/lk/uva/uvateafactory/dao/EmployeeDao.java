package lk.uva.uvateafactory.dao;

import lk.uva.uvateafactory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

     Employee findByNumber(String number);
     Employee findByNic(String nic);
     Employee findByMobile(String mobile);

     List<Employee> findAllByDesignationId(Integer id);

     @Query("select e from Employee e where e.id = :id")
     Employee findByMyId(@Param("id") Integer id);

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name in('Field-Officer','Supervisor')")
     List<Employee> findAllFieldofficerAndSupervisors();

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name ='Plucker'")
     List<Employee> findAllPluckers();

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name ='Kankani'")
     List<Employee> findAllKankanis();

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name ='Driver'")
     List<Employee> findAllDrivers();

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name ='Manager'")
     List<Employee> findAllManagers();

     @Query("select new Employee(e.id,e.callingname,e.designation) from Employee e,Designation d where e.designation.id = d.id and d.name ='TeaMaker'")
     List<Employee> findAllTeamakers();

     @Query("SELECT NEW Employee (e.id,e.callingname) FROM Employee e")
     List<Employee> findAllNameId();


}
