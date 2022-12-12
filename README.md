# Desafío Backend NBCH

API REST realizada para la consulta productos.
A continuación se dejarán detallados los requerimientos necesarios para ejecutar la API y cualquier otra información otra que se considere de utilidad

## Prerrequistos 
Se necesita que previamente se tengan instalados los siguientes programas:
- Docker Desktop 
- Postman
- IDE IntelliJ IDEA

### Pasos de ejecución:
1) Descargar el proyecto y abrirlo en el IDE IntelliJ IDEA
2) Una vez que se tenga el proyecto abierto en el IDE, descargar el plugin de Docker. Para eso seguir el siguiente camino: File -> Settings -> Plugin -> poner "Docker" en el buscador y descargar el plugin. Es probable que pida reiniciar el IDE
3) Luego ir a extremo superior derecho, donde se encuentra la barra de Run/Debug Configurations, desplegar la herramienta y seleccionar Edit Configurations
4) Vemos el extremo superior izquierdo donde aparece un "+" y clickeamos para añadir una configuración nueva
5) Se nos abre un desplegable con múltiples opciones, buscar el que diga Docker y seleccionar la opción de Docker Compose
6) A continuación vamos a completar los siguientes campos:
- En "name" completar con algun nombre. Ej: Docker Deployment
- En "run" seleccionar la carpeta que aparece a la derecha y buscar el archivo docker.compose.yml del proyecto
- En "docker compose up" vamos a la parte de Modify y seleccionamos: Recreate containers -> all / Build -> always
- En "before lauch" clickeamos el + y elegimos la opción Run Gradle task. Luego, se nos abrirá una pestaña y completar: Gradle project -> seleccionamos el proyecto / Tasks -> escribimos clean build jar y damos Ok
7) Seleccionamos Apply, luego Ok y con esto tenemos la configuración preparada para levantar nuestro proyecto con Docker
8) Abrimos el Docker Desktop. Una vez abierto, vamos al IDE para correr el proyecto con la configuración previamente realizada. Se empezará a recrear todo el ambiente necesario para poder probar el proyecto
9) Volvemos a Docker Desktop y podemos observar un container que dentro tendrá la BBDD MySQL y SpringBoot. Si seleccionamos SpringBoot vamos a ver que se abrirá la consola
10) Abrir el siguiente link que va directo a Swagger: http://localhost:8090/swagger-ui/index.html#/

Y listo! Estamos con todo preparado para probar los endpoints de la API Productos! 

#### Postman:
Aclaración importante: si se desea utilizar Postman, correr nuevamente el proyecto para que este elimine y recree nuevamente la BBDD. Esto será para no tener conflictos con los ID que luego se van a utilizar para testear

1) Abrimos Postman y vamos a la opción Import en el extremo superior izquierdo
2) Seleccionamos "Choose Files" y buscamos la carpeta con nombre Extra en el proyecto. Dentro se encuentran 3 archivos, elegimos el que diga PostmanCollection y ponemos en Open
3) Damos en Import y se descargará una colección con los endpoints de la API
4) Vamos al extremo superior derecho donde esta la opción de Ambientes y creamos uno nuevo con lo siguiente:
- En "new enviroment" completar con algun nombre. Ej: Dev
- En "variables" vamos a crear 3. La primera con nombre "url" y el initial value de "http://localhost:8090/api/v1"; la segunda con el nombre "idCorrecto" y el initial value de "1"; la última y tercera variable con el nombre "idIncorrecto" y el initial value de "3"
5) Damos en Guardar y dejamos seleccionado el ambiente creado
6) Vamos a la opción Runner en el extremo inferior derecho y arrastramos la Collecion de la API importada en "Run Order"
7) Clickeamos en "Run Desafio Backend NBCH" y se empezarán a correr las requests para probar los endpoints automaticamente

Y listo! Con esto, vamos a ver los resultados de los tests que dirán si se pudieron realizar las requests según la finalidad para las que fueron diseñadas!
