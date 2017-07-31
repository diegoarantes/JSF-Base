package com.absoft.model;

import java.io.Serializable;
/**
 * Interface de base para entidades que serão persistidas
 *
 * @author Diego Arantes Data alteração 31/07/2017
 */
public interface BaseEntity extends Serializable {
    public Number getId();
}
