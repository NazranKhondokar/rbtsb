package com.rbtsb.enums;

public enum RecordStatus {

    DRAFT("DRAFT"),
    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String label;

    RecordStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
