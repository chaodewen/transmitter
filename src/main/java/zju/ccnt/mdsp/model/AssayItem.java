package zju.ccnt.mdsp.model;

/**
 * Created by Dewayne on 2016/12/25.
 */
public class AssayItem {
    private int id;
    private Integer assayId;
    private String itemName;
    private String code;
    private String result;
    private String hint;
    private String reference;
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAssayId() {
        return assayId;
    }

    public void setAssayId(Integer assayId) {
        this.assayId = assayId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

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
        if (itemName != null ? !itemName.equals(assayItem.itemName) : assayItem.itemName != null) return false;
        if (code != null ? !code.equals(assayItem.code) : assayItem.code != null) return false;
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
        result1 = 31 * result1 + (itemName != null ? itemName.hashCode() : 0);
        result1 = 31 * result1 + (code != null ? code.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (hint != null ? hint.hashCode() : 0);
        result1 = 31 * result1 + (reference != null ? reference.hashCode() : 0);
        result1 = 31 * result1 + (unit != null ? unit.hashCode() : 0);
        return result1;
    }
}
