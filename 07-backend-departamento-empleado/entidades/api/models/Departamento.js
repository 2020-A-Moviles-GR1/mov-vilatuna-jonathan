/**
 * Departamento.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName: 'departamento',
  attributes: {
	  
	  nombreDepartamento:{ // nombre atributo
      type: 'string',
      maxLength: 25
		},
			  ciudad:{ // nombre atributo
      type: 'string',
      maxLength: 25
		},
			  estado:{ // nombre atributo
      type: 'boolean',
		},
			  numero:{ // nombre atributo
      type: 'float',
		},
	  codDepartamento:{ // nombre atributo
      type: 'number',
		},
	empleados: { // One to Many (plural)
      collection: 'empleado', // Referencia al modelo
      via: 'departamento' // Nombre Foreign Key en 'Pokemon'
    }
	  
  },

};

