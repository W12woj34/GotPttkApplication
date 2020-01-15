package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Response data for a mountain subgroup")
public class SubgroupReplyDTO {
    @ApiModelProperty(notes = "Unique mountain subgroup id", example = "Ł01")
    private String id;
    @ApiModelProperty(notes = "Mountain subgroup name", example = "Podtatrze")
    private String nazwa;
    @ApiModelProperty(notes = "Mountain subgroup group", example = "Tatry i Podtatrze")
    private String grupa;

    public SubgroupReplyDTO(String id, String nazwa, String grupa) {
        this.id = id;
        this.nazwa = nazwa;
        this.grupa = grupa;
    }

    public SubgroupReplyDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
}
