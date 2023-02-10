/*
Created on : February 10, 2022
Author     : Ayo Oguntuyi
*/

package controllers

import ie.setu.employees
import models.Employee


var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }

    fun create(employee: Employee) {
        //employee.employeeID = getId()
        employees.add(employee)
    }

    fun delete(employee: Employee) {
        employees.remove(employee)
    }

    fun update(employee: Employee, updatedEmployee: Employee) {
        var em = findOne(employee.employeeID)
        if (em != null) {
            em.annualBonus = updatedEmployee.annualBonus
            em.gender = updatedEmployee.gender
            em.firstName = updatedEmployee.firstName
            em.grossSalary = updatedEmployee.grossSalary
            em.cycleToWorkMonthlyDeduction = updatedEmployee.cycleToWorkMonthlyDeduction
            em.payePercentage = updatedEmployee.payePercentage
            em.prsiPercentage = updatedEmployee.prsiPercentage
            em.surname = updatedEmployee.surname
        }

    }

    fun sortByGender(): List<Employee> {
        return findAll().sortedBy { it.gender }

    }

    fun listEmployeesUnderAmount(amount: Double) : List<Employee> {
return  findAll().filter { it.grossSalary < amount }
    }
}





