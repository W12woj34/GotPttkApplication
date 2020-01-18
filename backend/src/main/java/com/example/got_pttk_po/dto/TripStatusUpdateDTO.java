package com.example.got_pttk_po.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Update data for a trip status")
public class TripStatusUpdateDTO {

    @ApiModelProperty(notes = "Status of a trip; 0 - not verified, 1 - positively verified, 2 - negatively verified, 3 - forwarded", example = "0")
   private int status;

    public TripStatusUpdateDTO(int status) {
        this.status = status;
    }

    public TripStatusUpdateDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
