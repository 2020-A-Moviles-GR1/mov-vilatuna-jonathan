/**
 * Empleado.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
tableName: 'empleado',
  attributes: {
	  
	  codEmpleado:{ // nombre atributo
      type: 'number',
		},
			  nombreEmpleado:{ // nombre atributo
      type: 'string',
      maxLength: 25
		},
			  sueldo:{ // nombre atributo
      type: 'float',
		},
			  fechaNacimiento:{ // nombre atributo
      type: 'string', 
	  columnType: 'date'
		},
	  estado:{ // nombre atributo
      type: 'boolean',
		},
	codDepartamento:{ // nombre atributo
      type: 'number',
		},
	departamento:{
		model: 'departamento',
		required: true
		}
  },

};

