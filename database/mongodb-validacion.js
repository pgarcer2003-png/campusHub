// Ejecutar desde mongosh conectado a la base campushub.
// Se usan nombres de colección de Spring Data para las clases Contacto y Seguimiento.
// Revisar con show collections antes de ejecutar en una base con datos.

const dbCampus = db.getSiblingDB('campushub');

function ensureCollection(name) {
  if (!dbCampus.getCollectionNames().includes(name)) {
    dbCampus.createCollection(name);
  }
}

ensureCollection('seguimiento');
ensureCollection('contacto');

dbCampus.getCollection('seguimiento').createIndex({ actividadId: 1 });

dbCampus.runCommand({
  collMod: 'seguimiento',
  validator: { $jsonSchema: {
    bsonType: 'object',
    required: ['actividadId', 'tipo', 'datos', 'creadoEn'],
    properties: {
      actividadId: { bsonType: ['long', 'int'] },
      tipo: { bsonType: 'string' },
      datos: { bsonType: 'object' },
      creadoEn: { bsonType: 'string' }
    }
  } },
  validationLevel: 'strict',
  validationAction: 'error'
});

dbCampus.runCommand({
  collMod: 'contacto',
  validator: { $jsonSchema: {
    bsonType: 'object',
    required: ['_id'],
    properties: {
      _id: { bsonType: ['long', 'int'] },
      tutorLegal: { bsonType: ['string', 'null'] },
      telefono: { bsonType: ['string', 'null'] },
      email: { bsonType: ['string', 'null'] },
      autorizado: { bsonType: ['bool', 'null'] },
      fechaAutorizacion: { bsonType: ['string', 'null'] }
    }
  } },
  validationLevel: 'strict',
  validationAction: 'error'
});

print('Índices y validadores configurados.');
