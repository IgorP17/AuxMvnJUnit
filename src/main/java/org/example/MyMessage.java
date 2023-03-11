package org.example;

import java.util.Objects;

public class MyMessage {
    private String fieldA;
    private String fieldB;
    private String fieldC;

    public MyMessage(String fieldA, String fieldB, String fieldC) {
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.fieldC = fieldC;
    }

    public MyMessage setField(MyMessageFields field, String value){
        switch (field){
            case FIELD_1:
                this.fieldA = value;
                break;
            case FIELD_2:
                this.fieldB = value;
                break;
            case FIELD_3:
                this.fieldC = value;
                break;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fieldA='" + fieldA + '\'' +
                ", fieldB='" + fieldB + '\'' +
                ", fieldC='" + fieldC + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyMessage myMessage = (MyMessage) o;

        if (!Objects.equals(fieldA, myMessage.fieldA)) return false;
        if (!Objects.equals(fieldB, myMessage.fieldB)) return false;
        return Objects.equals(fieldC, myMessage.fieldC);
    }

    @Override
    public int hashCode() {
        int result = fieldA != null ? fieldA.hashCode() : 0;
        result = 31 * result + (fieldB != null ? fieldB.hashCode() : 0);
        result = 31 * result + (fieldC != null ? fieldC.hashCode() : 0);
        return result;
    }
}

