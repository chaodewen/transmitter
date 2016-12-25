package zju.ccnt.mdsp.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dewayne on 2016/12/25.
 */
public class Assay {
    private int id;
    private Integer userId;
    private String event;
    private String patient;
    private String sample;
    private Integer gender;
    private String department;
    private Integer age;
    private String demander;
    private String coroner;
    private String checker;
    private String note;
    private Date startedDate;
    private Date finishedDate;
    private List<AssayItem> assayItemsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDemander() {
        return demander;
    }

    public void setDemander(String demander) {
        this.demander = demander;
    }

    public String getCoroner() {
        return coroner;
    }

    public void setCoroner(String coroner) {
        this.coroner = coroner;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assay assay = (Assay) o;

        if (id != assay.id) return false;
        if (userId != null ? !userId.equals(assay.userId) : assay.userId != null) return false;
        if (event != null ? !event.equals(assay.event) : assay.event != null) return false;
        if (patient != null ? !patient.equals(assay.patient) : assay.patient != null) return false;
        if (sample != null ? !sample.equals(assay.sample) : assay.sample != null) return false;
        if (gender != null ? !gender.equals(assay.gender) : assay.gender != null) return false;
        if (department != null ? !department.equals(assay.department) : assay.department != null) return false;
        if (age != null ? !age.equals(assay.age) : assay.age != null) return false;
        if (demander != null ? !demander.equals(assay.demander) : assay.demander != null) return false;
        if (coroner != null ? !coroner.equals(assay.coroner) : assay.coroner != null) return false;
        if (checker != null ? !checker.equals(assay.checker) : assay.checker != null) return false;
        if (note != null ? !note.equals(assay.note) : assay.note != null) return false;
        if (startedDate != null ? !startedDate.equals(assay.startedDate) : assay.startedDate != null) return false;
        if (finishedDate != null ? !finishedDate.equals(assay.finishedDate) : assay.finishedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (demander != null ? demander.hashCode() : 0);
        result = 31 * result + (coroner != null ? coroner.hashCode() : 0);
        result = 31 * result + (checker != null ? checker.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (startedDate != null ? startedDate.hashCode() : 0);
        result = 31 * result + (finishedDate != null ? finishedDate.hashCode() : 0);
        return result;
    }

    public List<AssayItem> getAssayItemsById() {
        return assayItemsById;
    }

    public void setAssayItemsById(List<AssayItem> assayItemsById) {
        this.assayItemsById = assayItemsById;
    }
}
