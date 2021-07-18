package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private Integer did;
    private Integer bid;
    private String dname;
    private Integer beds;
    private Float fee;
    private Integer currentBeds;
    private Integer freeBeds;
}
