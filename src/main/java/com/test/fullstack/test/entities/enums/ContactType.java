package com.test.fullstack.test.entities.enums;

public enum ContactType {
    PHONE(1),
    EMAIL(2);
    private int id;

    ContactType (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ContactType valueOf(Integer id){

        if(id == null){
            return null;
        }
        for (ContactType type : ContactType.values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}