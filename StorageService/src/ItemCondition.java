public enum ItemCondition {
    NEW{
        @Override public String toString(){
            return "New";
        }
    },
    USED{
        @Override public String toString(){
            return "Used";
        }
    },
    REFURBISHED{
        @Override public String toString(){
            return "Refurbished";
        }
    };
}
