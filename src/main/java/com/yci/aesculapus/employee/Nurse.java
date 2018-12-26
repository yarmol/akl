package com.yci.aesculapus.employee;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Nurse extends Employee {



}
