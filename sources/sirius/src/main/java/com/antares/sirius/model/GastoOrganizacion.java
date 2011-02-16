package com.antares.sirius.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("1")
@SuppressWarnings("serial")
public class GastoOrganizacion extends Gasto {

}
