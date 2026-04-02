Johany_Flores_Libreria
Este repositorio contiene la estructura básica para una Librería Compartida de Jenkins (JSL).

Contenido del Ejercicio 1
Siguiendo los requerimientos de la práctica, se ha creado la estructura de carpetas necesaria para organizar la lógica de automatización:


/vars: Directorio para scripts globales que definen pasos personalizados (custom steps).


/src/org/obsschool: Directorio para clases de Groovy que contienen lógica de negocio o utilidades.


/resources/org/obsschool: Espacio para archivos auxiliares no ejecutables.

Notas de Implementación
Cada carpeta contiene un archivo .placeholder para asegurar que Git mantenga la estructura de directorios, incluso si están vacíos inicialmente.

Esta librería se integrará en Jenkins bajo el nombre obsschool-sharedlib para su uso en pipelines declarativos.