


class VegetableRecord implements Comparable<VegetableRecord> {
    private String vegetableType;
    private String country;
    private int recordSize;
    private String unit;

    public VegetableRecord(String vegetableType, String country, int recordSize, String unit) {
        this.vegetableType = vegetableType;
        this.country = country;
        this.recordSize = recordSize;
        this.unit = unit;
    }

    @Override
    public int compareTo(VegetableRecord other) { // Return negative number if other is "greater than" this object, and positive if it's "less than".
        // First sort alphabetically.
        int typeComparison = this.vegetableType.compareTo(other.vegetableType);
        if (typeComparison != 0) { //If the types aren't the same.
            return typeComparison;
        }

        
        int sizeComparison = Integer.compare(other.recordSize, this.recordSize);
        if (sizeComparison != 0) { // If their sizes aren't the same.
            return sizeComparison;
        }

        // If none of the other things to sort by differ, we simply sort by country.
        return this.country.compareTo(other.country);
    }

    public String toString() {
        return this.vegetableType + " " + this.country + " " + this.recordSize + " " + this.unit;
    }
    public String getType(){
        return this.vegetableType;
    }
    public int getSize(){
        return this.recordSize;
    }
    public String getCountry(){
        return this.country;
    }
}
