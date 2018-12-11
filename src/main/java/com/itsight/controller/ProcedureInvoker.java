package com.itsight.controller;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProcedureInvoker {

	    private final EntityManager entityManager;
	  
	    @Autowired
	    public ProcedureInvoker(final EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	    
	    public void insertaPais() {
	    	System.out.println("ENTRO A PROCEDURE__");
	        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PromartCopaCapaBD.sp_insertarPais");

	        System.out.println("PIDE VALOR__");
	        storedProcedureQuery.registerStoredProcedureParameter("Descripcion", String.class, ParameterMode.IN);
	 
	        // Configuramos el valor de entrada
	        System.out.println("VALOR DE ENTRADA__");
	        storedProcedureQuery.setParameter("Descripcion", "value");
	 
	        // Realizamos la llamada al procedimiento
	        System.out.println("EJECUTA__");
	        storedProcedureQuery.execute();
	 
	        // Obtenemos los valores de salida
	        System.out.println("SALIDA__");
	        final String entrada = (String) storedProcedureQuery.getOutputParameterValue("Descripcion");

		}
	    
  //        final Long outputValue2 = (Long) query.getOutputParameterValue("OUTPUT_PROCEDURE_PARAMETER_NAME2");

	        
	        // Registrar los parámetros de entrada y salida
	   }
