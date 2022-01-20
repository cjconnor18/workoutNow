package com.workoutnow.workoutnow.models;

import javax.persistence.Entity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RepsAndWeights extends AbstractEntity {

    private String reps = "";
    private String weights = "";

    public RepsAndWeights() {};

    public String getWeights() {
        return weights;
    }

    public String getReps() {
        return reps;
    }

    public ArrayList<Double> getRepsList() {
        return convertStringToArrayList(this.reps);
    }

    public ArrayList<Double> getWeightsList( ){
        return convertStringToArrayList(this.weights);
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public void addSet(Double aWeight, Double aReps) {
        if(aReps == 0 || aWeight < 0) {
            return;
        }
        ArrayList<Double> currentWeights = this.getWeightsList();
        currentWeights.add(aWeight);
        this.weights = this.convertArrayListToString(currentWeights);

        ArrayList<Double> currentReps = this.getRepsList();
        currentReps.add(aReps);
        this.reps = this.convertArrayListToString(currentReps);
    }

    public void deleteSet(Integer i) {
        ArrayList<Double>  weightsList = this.getWeightsList();
        ArrayList<Double> repsList = this.getRepsList();

        if(i >= -1 && i < this.getWeightsList().size() && i< this.getRepsList().size()) {
            weightsList.remove(weightsList.get(i));
            repsList.remove(repsList.get(i));
            this.setWeights(convertArrayListToString(weightsList));
            this.setReps(convertArrayListToString(repsList));
        }
    }

    private ArrayList<Double> convertStringToArrayList(String s) {
        if(s.isEmpty()) {
            return new ArrayList<Double>();
        }
        String[] someS = s.split(",");
        ArrayList<Double> currentValues = new ArrayList<>();
        for( String theArr : someS) {
            try {
                Double currentValue = Double.parseDouble(theArr);
                currentValues.add(currentValue);
            } catch (NumberFormatException n) {
                currentValues.add(-1.0);
            }
        }
        return currentValues;
    }

    private String convertArrayListToString(ArrayList<Double> curList) {
        ArrayList<String> newString = new ArrayList<>();
        for( Double item: curList) {
            newString.add(item.toString());
        }

        return String.join(",",newString);
    }


}
