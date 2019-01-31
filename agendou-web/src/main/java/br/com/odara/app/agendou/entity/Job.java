package br.com.odara.app.agendou.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

@javax.persistence.Entity
@Table(name="job")
public class Job extends br.com.odara.app.agendou.entity.Entity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column
    private String companyName;

    @NotBlank
    @Column
    private String description;


    @Column
    private BigDecimal salary;

    @NotBlank
    @Column
    private String office;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public static Job build (Long id){

        Job job = new Job();
        job.setId( id );

        return job;
    }

    public static Job build(String companyName, String description, BigDecimal salary, String office){

        Job job = new Job();
        job.setCompanyName( companyName );
        job.setDescription( description );
        job.setSalary( salary );
        job.setOffice( office );

        return job;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        br.com.odara.app.agendou.entity.Job job = (br.com.odara.app.agendou.entity.Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
