package com.yci.aesculapus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributesDto {

    private Long id;

    private String name;

}
