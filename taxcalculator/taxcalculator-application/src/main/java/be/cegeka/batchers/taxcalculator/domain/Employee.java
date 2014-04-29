package be.cegeka.batchers.taxcalculator.domain;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    private Integer income;

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime calculationDate;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@Parameter(name = "currencyCode", value = "EUR")})
    private Money taxTotal = Money.zero(CurrencyUnit.EUR);

    public void setIncome(int income) {
        this.income = income;
    }

    public Integer getIncome() {
        return income;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public DateTime getCalculationDate() {
        return calculationDate;
    }

    public void addTax(Money amount) {
        this.taxTotal = Money.total(taxTotal, amount);
        this.calculationDate = new DateTime();
    }

    public Money getTaxTotal() {
        return taxTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (calculationDate != null ? !calculationDate.equals(employee.calculationDate) : employee.calculationDate != null)
            return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (income != null ? !income.equals(employee.income) : employee.income != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (taxTotal != null ? !taxTotal.equals(employee.taxTotal) : employee.taxTotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = income != null ? income.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (calculationDate != null ? calculationDate.hashCode() : 0);
        result = 31 * result + (taxTotal != null ? taxTotal.hashCode() : 0);
        return result;
    }
}