<!--Título-->
<!--# Formulario de Registro de Laptops (CRUD)-->
<h1 align="center">Formulario de Registro de Laptops (CRUD)</h1>

<!--Banner-->
<img src="https://i.imgur.com/XyXtTng.gif">

## Descripción

>[!NOTE]
> Este proyecto de Android Studio desarrollado en Kotlin permite el ingreso de información de laptops a través de un formulario, con la capacidad de listar, editar y eliminar registros, así como buscar registros específicos.

## Capturas de Pantalla

>[!NOTE]
> Visualiza a continuación algunas capturas de pantalla de la interfaz de usuario de la aplicación en acción.
>
> <br>
>
> | Registrar | Buscar | Editar | Eliminar |
> |----------|----------|----------|----------|
> | <img src="https://i.imgur.com/8h4iVNc.gif" alt="Registrar" width="210px"> | <img src="https://i.imgur.com/BE362L2.gif" alt="Buscar" width="210px"> | <img src="https://i.imgur.com/NIhRi5r.gif" alt="Editar" width="210px"> | <img src="https://i.imgur.com/NTT3HZ1.gif" alt="Eliminar" width="210px"> |

<!-- Moestrar imágenes en su tamaño original
![Registrar](https://i.imgur.com/8h4iVNc.gif)
![Buscar](https://i.imgur.com/BE362L2.gif) 
![Editar](https://i.imgur.com/NIhRi5r.gif) 
![Eliminar](https://i.imgur.com/NTT3HZ1.gif)

------------------------------------------------------------------->

<!-- Mostrar imágenes en la tabla expandidas a todo el ancho de la pantalla
> | Registrar | Buscar | Editar | Eliminar |
> |----------|----------|----------|----------|
> | ![Registrar][1] | ![Buscar][2] | ![Editar][3] | ![Eliminar][4] |

[1]: https://i.imgur.com/8h4iVNc.gif
[2]: https://i.imgur.com/BE362L2.gif
[3]: https://i.imgur.com/NIhRi5r.gif
[4]: https://i.imgur.com/NTT3HZ1.gif 

------------------------------------------------------------------->

<!-- Moestrar imágenes apiladas de forma horizontal y tamaño editado
<div style="display: flex;">
    <img src="https://i.imgur.com/8h4iVNc.gif" alt="Registrar" width="200px">
    <img src="https://i.imgur.com/BE362L2.gif" alt="Buscar" width="200px">
    <img src="https://i.imgur.com/NIhRi5r.gif" alt="Editar" width="200px">
    <img src="https://i.imgur.com/NTT3HZ1.gif" alt="Eliminar" width="200px">
</div> 

------------------------------------------------------------------->

## Características

>[!NOTE]
> - Registro de datos utilizando SharedPreferences para almacenamiento local.
> - Navegación entre pantallas.
> - Uso de RecyclerView para listar registros.
> - Implementación de CardView para mostrar tarjetas de los registros.
> - Implementación de SearchView para buscar registros.
> - Uso de PopupMenu en RecyclerView para editar y eliminar los registros.
> - Utilización de AlertDialog para confirmaciones de eliminación.

## Tecnologías Utilizadas

>[!NOTE]
> - Kotlin
> - Android SDK
> - Bibliotecas:
> 	- Gson (para la serialización de datos)

## Instalación

>[!TIP]
> Para descargar y configurar el proyecto en tu entorno de desarrollo local, sigue estos pasos:
>
> 1. Clona este repositorio en tu máquina local.
> 2. Abre el proyecto en Android Studio.
> 3. Realiza cualquier configuración adicional necesaria (como sincronizar dependencias, si es necesario).
> 4. Compila y ejecuta el proyecto en tu dispositivo o emulador.

## Uso

>[!TIP]
> Una vez configurada la aplicación, sigue estos pasos para utilizarla:
>
> 1. Ingresa los datos de la laptop en el formulario.
> 2. Presiona el botón "Registrar" para guardar la información.
> 3. Presiona el botón "Ver Lista" para ver el listado completo de laptops registradas.
> 4. Utiliza el icono (···) del menú emergente para las opciones de edición y eliminación de cada tarjeta.
> 5. Presiona el icono de la lupa para realizar la búsqueda de un registro.
> 6. Utiliza la flecha de navegación para volver a la pantalla anterior.

## Gradle

>[!TIP]
> Considera utilizar esta biblioteca para la serialización y deserialización de objetos JSON en tu proyecto, agregue la siguiente dependencia a su archivo `build.gradle`:
>
> ```gradle
> dependencies {
> 	implementation 'com.google.code.gson:gson:2.8.6'
> }
> ```

## Contribución

>[!NOTE]
> ¡Gracias por considerar contribuir a este proyecto! Si deseas participar, asegúrate de seguir estas pautas:
>
> 1. Si encuentras algún problema o error, por favor, reportalo utilizando la sección de "Issues" en este repositorio.
> 2. Si deseas contribuir con código, asegúrate de crear una rama dedicada para tu trabajo y enviar una "Pull Request" cuando esté listo para ser revisado.

## Licencia

>[!IMPORTANT]
> Este proyecto se distribuye bajo la Licencia Apache 2.0. Consulta el archivo [LICENSE](https://github.com/jcallally/android-crud-app/blob/main/LICENSE) para más detalles.
>
> ```
>    Copyright 2023 - 2024 Javier Callally
>    
>    Licensed under the Apache License, Version 2.0 (the "License");
>    you may not use this file except in compliance with the License.
>    You may obtain a copy of the License at
>    
>    http://www.apache.org/licenses/LICENSE-2.0
>    
>    Unless required by applicable law or agreed to in writing, software
>    distributed under the License is distributed on an "AS IS" BASIS,
>    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>    See the License for the specific language governing permissions and
>    limitations under the License.
> ```

## Contacto

>[!IMPORTANT]
> Para cualquier pregunta o comentario sobre este proyecto, no dudes en ponerte en contacto con el desarrollador:
>
> - Nombre: Javier Callally.
> - Correo Electrónico: jcallally@gmail.com
