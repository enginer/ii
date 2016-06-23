package org.ayfaar.app.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TermRecordKey implements Serializable{

    private String term;
    private String record;

}
