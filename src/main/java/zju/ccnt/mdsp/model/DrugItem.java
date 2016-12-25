package zju.ccnt.mdsp.model;

import java.util.List;

/**
 * Created by Dewayne on 2016/12/25.
 */
public class DrugItem {
    private int id;
    private Integer recipeId;
    private String dosageUnit;
    private Integer dosageQuantity;
    private Integer timePerDay;
    private String note;
    private List<SingleDrugItem> singleDrugItemsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public Integer getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(Integer dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public Integer getTimePerDay() {
        return timePerDay;
    }

    public void setTimePerDay(Integer timePerDay) {
        this.timePerDay = timePerDay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugItem drugItem = (DrugItem) o;

        if (id != drugItem.id) return false;
        if (recipeId != null ? !recipeId.equals(drugItem.recipeId) : drugItem.recipeId != null) return false;
        if (dosageUnit != null ? !dosageUnit.equals(drugItem.dosageUnit) : drugItem.dosageUnit != null) return false;
        if (dosageQuantity != null ? !dosageQuantity.equals(drugItem.dosageQuantity) : drugItem.dosageQuantity != null)
            return false;
        if (timePerDay != null ? !timePerDay.equals(drugItem.timePerDay) : drugItem.timePerDay != null) return false;
        if (note != null ? !note.equals(drugItem.note) : drugItem.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (recipeId != null ? recipeId.hashCode() : 0);
        result = 31 * result + (dosageUnit != null ? dosageUnit.hashCode() : 0);
        result = 31 * result + (dosageQuantity != null ? dosageQuantity.hashCode() : 0);
        result = 31 * result + (timePerDay != null ? timePerDay.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    public List<SingleDrugItem> getSingleDrugItemsById() {
        return singleDrugItemsById;
    }

    public void setSingleDrugItemsById(List<SingleDrugItem> singleDrugItemsById) {
        this.singleDrugItemsById = singleDrugItemsById;
    }
}
