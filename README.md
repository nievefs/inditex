## Test Inditex: Prueba Técnica

- Construir una aplicación/servicio en SpringBoot que provea un end ponit rest de consulta.

### Contenido
- Introducción
- Arquitectura y Diseño
- Introducciones de Ejecución
  * 1.Ejecutar la Aplicación
  * 2.Ejecutar las Test
- Configurar la Conexión a la Base de Datos
  * 1.Acceder a la Consola H2
  * 2.Configurar la conexión a la Base de Datos
  * 3.Consultar Datos
  * 4.Ejecutar Pruebas
- Documentación de la API

## Introducción
- En la base de datos del comercio electrónico de la compañía, disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

	Campos:
```
BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador código de producto.
PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
PRICE: precio final de venta.
CURR: iso de la moneda.
```


* Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los datos del ejemplo:
- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
- Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)


 ****Nota**: Estas 5 casuísticas de prueba se encuentran implementadas en el fichero de `/src/test/java/infrastructure/adapter/PriceFinderServiceTest`

## Arquitectura y Diseño
- Este proyecto sigue una arquitectura hexagonal (Hexagonal Architecture) y utiliza el enfoque de Domain-Driven Design (DDD) para organizar el código y garantizar una estructura clara y mantenible. Los detalles de diseño específicos se pueden encontrar en los paquetes y clases relevantes dentro del código fuente.


## Introducciones de ejecución

- A continuación, se describen los pasos para ejecutar y probar el proyecto:
#### 1. Ejecutar la Aplicación
Abrir el proyecto en el IDE preferido (por ejemplo, IntelliJ IDEA o Eclipse).
Localizar la clase principal (/src/main/java/com.ecommerce.apih2/Entrypoint) y ejecutarla.
La aplicación se iniciará en http://localhost:8080.
#### 2. Ejecutar los Test
Se han implementado pruebas unitarias y de integración que se pueden ejecutar utilizando un IDE o Maven para realizar las pruebas.
Por ejmplo: si usamos IntelliJ IDEA, desde el directorio `/src/test/java`, click menú lateral selccionar Run 'Test in java'

## Configuración de la Base de Datos

- Este proyecto utiliza una base de datos H2 que se inicializa con registros predefinidos desde el archivo '/src/main/resources/data.sql'. A continuación, se detallan los pasos para configurar y trabajar con la base de datos:
#### 1. Acceder a la Consola de H2
H2 proporciona una consola web que te permite interactuar con la base de datos de manera sencilla. Abre tu navegador web y accede a la siguiente URL: http://localhost:8080/h2-console
#### 2. Configurar la Conexión a la Base de Datos
En la página de inicio de la consola de H2, completa los siguientes campos:
- **JDBC URL:**  jdbc:h2:mem:testdb
- **User Name:** nieve
- **Password:** 123

Luego, click en el botón "Connect".
#### 3. Consultar los Datos
Una vez realizada la conexión, se podrán consultar los datos almacenados en la base de datos H2. Se pueden realizar consultas personalizadas e interactuar con la tabla `PRICE` y los registros predefinidos por la aplicación.
#### 4. Ejecutar Pruebas
Este proyecto incluye pruebas que se ejecutan en una base de datos H2 en memoria. Las pruebas se inicializan con datos de prueba desde el archivo `/src/test/resources/db/data.sql`.


## Documentación del API
- La API proporciona un punto final REST que permite consultar los precios de acuerdo con ciertos parámetros. A continuación, se muestra cómo utilizarlo (por ejemplo desde un cliente http como Postman):
URL del Endpoint: http://localhost:8080/final-price


- **Parámetros Query de Entrada**:
```
date (Fecha de aplicación del precio en formato "yyyy-MM-dd HH:mm:ss")
productId (ID del producto)
brandId (ID de la cadena)
```
Ejemplo:
```
http://localhost:8080/final-price?date=2020-06-14 16:00:00&productId=35455&brandId=1
```

- **Respuesta:**
```
ID del producto, 
ID de la cadena, 
tarifa aplicada, 
fechas de aplicación 
y precio final.
```
Ejemplo:
```
{
    "productId": 35455,
    "brandId": 1,
    "tariff": 2,
    "starDate": "2020-06-14T15:00",
    "endDate": "2020-06-14T18:30",
    "price": 25.45
}
```

- Además, la documentación completa del API se encuentra en Swagger. Puedes acceder a ella en http://localhost:8080/swagger-ui/index.html

