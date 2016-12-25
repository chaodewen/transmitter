package zju.ccnt.mdsp.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Cc on 2016/12/23.
 */
@Entity
public class AssayItem {
    private int id;
    private Integer assayId;
    private String name;
    private String result;
    private String hint;
    private String reference;
    private String unit;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "assayId", nullable = true)
    public Integer getAssayId() {
        return assayId;
    }

    public void setAssayId(Integer assayId) {
        this.assayId = assayId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "result", nullable = true, length = 40)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "hint", nullable = true, length = 40)
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Basic
    @Column(name = "reference", nullable = true, length = 40)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 40)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssayItem assayItem = (AssayItem) o;

        if (id != assayItem.id) return false;
        if (assayId != null ? !assayId.equals(assayItem.assayId) : assayItem.assayId != null) return false;
        if (name != null ? !name.equals(assayItem.name) : assayItem.name != null) return false;
        if (result != null ? !result.equals(assayItem.result) : assayItem.result != null) return false;
        if (hint != null ? !hint.equals(assayItem.hint) : assayItem.hint != null) return false;
        if (reference != null ? !reference.equals(assayItem.reference) : assayItem.reference != null) return false;
        if (unit != null ? !unit.equals(assayItem.unit) : assayItem.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (assayId != null ? assayId.hashCode() : 0);
        result1 = 31 * result1 + (name != null ? name.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (hint != null ? hint.hashCode() : 0);
        result1 = 31 * result1 + (reference != null ? reference.hashCode() : 0);
        result1 = 31 * result1 + (unit != null ? unit.hashCode() : 0);
        return result1;
    }
}
