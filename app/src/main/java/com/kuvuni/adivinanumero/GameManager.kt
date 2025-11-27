package com.kuvuni.adivinanumero

import kotlin.random.Random

// 1. Creamos una enumeración para definir los niveles de dificultad de forma clara.
enum class Difficulty(val range: IntRange) {
    BAJA(1..100),
    MEDIA(1..1000),
    ALTA(1..10000)
}

/**
 * Esta clase gestiona la lógica y el estado del juego "Adivina el Número".
 * No sabe nada sobre la interfaz de usuario (Activities, Buttons, etc.).
 * Su única responsabilidad es manejar las reglas del juego.
 */
class GameManager {

    // --- 1. Propiedades (Estado del juego) ---
    private var secretNumber = 0
    private var attempts = 0
    var isGameWon = false
        private set // El 'set' es privado, así que desde fuera solo se puede leer.
    private lateinit var currentDifficulty: Difficulty

    // --- 2. Bloque de inicialización ---
    // Este código se ejecuta cada vez que se crea un objeto de la clase GameManager.
    init {
        startNewGame(Difficulty.BAJA)
    }

    // --- 3. Métodos Públicos (Las Acciones que puede hacer el juego) ---

    /**
     * Inicia o reinicia una partida, generando un nuevo número secreto.
     */
    fun startNewGame(difficulty: Difficulty) {
        currentDifficulty = difficulty
        secretNumber = Random.nextInt(difficulty.range.first, difficulty.range.last + 1)
        attempts = 0
        isGameWon = false
    }

    /**
     * Reinicia el juego con la dificultad actual.
     */
    fun resetGame() {
        startNewGame(currentDifficulty)
    }

    /**
     * Devuelve el mensaje inicial para la UI.
     */
    fun getGameStartMessage(): String {
        return "¡Adivina el número del ${currentDifficulty.range.first} al ${currentDifficulty.range.last}!"
    }

    /**
     * Comprueba el número introducido por el usuario y devuelve una pista.
     * @param guessedNumber El número que el usuario ha adivinado.
     * @return Un String con el mensaje de resultado (pista o felicitación).
     */
    fun checkGuess(guessedNumber: Int): String {
        // Si el juego ya ha sido ganado, no hacemos nada más.
        if (isGameWon) {
            return "¡Ya has ganado! Inicia un nuevo juego."
        }

        attempts++ // Incrementamos el número de intentos.

        return when {
            guessedNumber < secretNumber -> {
                "¡Incorrecto! El número es MÁS ALTO. Intentos: $attempts"
            }
            guessedNumber > secretNumber -> {
                "¡Incorrecto! El número es MÁS BAJO. Intentos: $attempts"
            }
            else -> { // El número es correcto
                isGameWon = true
                "¡Felicidades! ¡Has adivinado el número en $attempts intentos!"
            }
        }
    }
}
