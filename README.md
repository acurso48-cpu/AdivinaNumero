# Adivina el Número

Este es un sencillo juego para Android en el que el objetivo es adivinar un número secreto generado aleatoriamente. La aplicación está desarrollada en Kotlin y utiliza componentes modernos de Android Jetpack.

## Características

- **Juego Clásico de Adivinar**: Introduce un número y recibe pistas sobre si el número secreto es más alto o más bajo.
- **Niveles de Dificultad**: Elige entre tres niveles de dificultad que cambian el rango del número a adivinar:
  - **Baja**: Números del 1 al 100.
  - **Media**: Números del 1 al 1.000.
  - **Alta**: Números del 1 al 10.000.
- **Contador de Intentos**: La aplicación lleva la cuenta de cuántos intentos has necesitado para adivinar el número.
- **Diseño Responsivo**: La interfaz se adapta a orientaciones tanto verticales como horizontales para una experiencia de usuario consistente.
- **Lógica Separada**: La lógica del juego (`GameManager`) está separada de la interfaz de usuario (`MainActivity`), siguiendo buenas prácticas de arquitectura de software.

## Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **IDE**: Android Studio
- **Interfaz de Usuario**: Android SDK con vistas (XML) y `ConstraintLayout`.
- **Componentes de Jetpack**:
  - `AppCompatActivity`
  - `ViewCompat`

## Cómo Empezar

1.  **Clona el repositorio**:
    ```bash
    git clone https://github.com/acurso48-cpu/AdivinaNumero.git
    ```
2.  **Abre el proyecto** en Android Studio.
3.  **Sincroniza Gradle** y espera a que se descarguen las dependencias.
4.  **Ejecuta la aplicación** en un emulador o en un dispositivo físico.


---

*Este proyecto fue desarrollado como parte de un curso de desarrollo de Android.*
