package com.jumper.angel.home.music.entity;

import java.io.Serializable;

public class PregnantMusicLabel implements Serializable {
    
	private static final long serialVersionUID = -8667914835139574919L;

	private Integer id;

    private String labelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }
}