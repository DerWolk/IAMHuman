package de.bitfull.iamh.types;

public class HumanType {

    public enum SelectType {
        XPATH("XPATH");

        private String type;

        SelectType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum ActionType{
        TYPE("TYPE"),
        TYPE_RANDOM_PW("TYPE_RANDOM_PW");

        private String type;

        ActionType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
