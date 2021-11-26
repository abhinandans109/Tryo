package com.example.tryo;

public class items {
    String acttivity,trainer_name,type,time;

    public items() {
    }

    public items(String acttivity, String trainer_name, String type, String time) {
        this.acttivity = acttivity;
        this.trainer_name = trainer_name;
        this.type = type;
        this.time = time;
    }

    public items(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getActtivity() {
        return acttivity;
    }

    public void setActtivity(String acttivity) {
        this.acttivity = acttivity;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
