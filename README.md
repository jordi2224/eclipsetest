# DimiXStega
### Autor: Jorge "**DimiX**" Huete

## Introducción
Esta aplicación Java está diseñada para ocultar información sin que parezca que estas escondiendo algo.
Encriptar archivos es un metodo común para asegurar la intimidad y confidencialidad. El problema es
que los ficheros encriptados revelan las intenciones de su dueño, ocultar información.
Gracias a esta app se puede ocultar cualquier ficher de manera segura dentro de una imagen formato .bmp.

Esta técnica de esconder información no es nueva, se llama esteganografia

>Del griego "escrita escondida"

## Seguridad
La técnica de esteganografia utilizada en por este código modifica ligeramente las imagenes.
Ademas, los archivos ocultos son encriptados con AES 128-bit.

## Como usar:
### Esteganografia
#### Encriptar
![alt text](https://i.imgur.com/RLCFo4V.png)
  1. Seleccionar el archivo a ocultar y la imagen (en formato .bmp sín compresión, transparencia y 8bits por color)
  Algunas fotos compatibles vienen con el programa en una carpeta llamada "images"
  2. Elegir el nombre del archivo de destino
  3. Dar una contraseña y clicar en "Hide File"
  
  #### Desencriptar
![alt text](https://imgur.com/z8hnuUf.png)
  1. Seleccionar un archivo que contenga información oculta y el archivo de destino (la extensión será sobreescrita)
  2. Introducir la contraseña y clicar en "Find File"
  
  ### Gestor de contraseñas
Aparte de poder ocultar y recuperar información en imagenes .bmp este programa también puede gestionar contraseñas.
![alt text](https://imgur.com/MBT9ZMb.png)

Nota: Si la contraseña usaba al abrir una librería es incorrecta no podrás ver las contraseñas y dará "ERROR"

Nota 2: ¡Prueba a guardar tu libreria en una imagen!
