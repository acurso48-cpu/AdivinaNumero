package com.kuvuni.adivinanumero

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // --- 1. Propiedades de la Vista (UI) ---
    private lateinit var numberInput: EditText
    private lateinit var btnComprobar: Button
    private lateinit var resultText: TextView
    private lateinit var resetButton: Button
    private lateinit var btnDifBaja: Button
    private lateinit var btnDifMedia: Button
    private lateinit var btnDifAlta: Button

    private lateinit var gameManager: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 3. Inicializar el GameManager ---
        gameManager = GameManager()

        // --- 4. Conectar las Vistas del XML ---
        numberInput = findViewById(R.id.et_numero)
        btnComprobar  = findViewById(R.id.btn_comprobar)
        resultText = findViewById(R.id.tv_info)
        resetButton = findViewById(R.id.btn_reset)
        btnDifBaja = findViewById(R.id.btn_dif_baja)
        btnDifMedia = findViewById(R.id.btn_dif_media)
        btnDifAlta = findViewById(R.id.btn_dif_alta)

        // --- 5. Configurar los Listeners ---
        btnComprobar.setOnClickListener {
            handleGuess()
        }

        resetButton.setOnClickListener {
            handleReset()
        }

        btnDifBaja.setOnClickListener {
            handleDifficultyChange(Difficulty.BAJA)
        }
        btnDifMedia.setOnClickListener {
            handleDifficultyChange(Difficulty.MEDIA)
        }
        btnDifAlta.setOnClickListener {
            handleDifficultyChange(Difficulty.ALTA)
        }

        // --- 6. Preparar la UI inicial ---
        updateUIForNewGame()
    }

    /**
     * Gestiona el evento de clic en el botón "Adivinar".
     */
    private fun handleGuess() {
        // Deshabilitar los botones de dificultad al empezar a jugar
        setDifficultyButtonsEnabled(false)

        val guessedNumberString = numberInput.text.toString()

        if (guessedNumberString.isEmpty()) {
            resultText.text = "Por favor, introduce un número."
            return
        }

        val guessedNumber = guessedNumberString.toInt()
        val result = gameManager.checkGuess(guessedNumber)
        resultText.text = result

        if (gameManager.isGameWon) {
            btnComprobar.isEnabled = false
            hideKeyboard()
        }

        numberInput.text.clear()
    }

    /**
     * Gestiona el evento de clic en el botón "Reiniciar".
     */
    private fun handleReset() {
        gameManager.resetGame()
        updateUIForNewGame()
    }

    /**
     * Gestiona el cambio de dificultad e inicia una nueva partida.
     */
    private fun handleDifficultyChange(difficulty: Difficulty) {
        gameManager.startNewGame(difficulty)
        updateUIForNewGame()
        // Deshabilitar botones después de seleccionar una dificultad
        setDifficultyButtonsEnabled(false)
    }

    /**
     * Actualiza la interfaz de usuario para una nueva partida.
     */
    private fun updateUIForNewGame() {
        resultText.text = gameManager.getGameStartMessage()
        numberInput.text.clear()
        btnComprobar.isEnabled = true
        // El botón de reset siempre es visible
        resetButton.visibility = View.VISIBLE
        // Habilitar los botones de dificultad
        setDifficultyButtonsEnabled(true)
    }

    /**
     * Habilita o deshabilita los botones de dificultad.
     */
    private fun setDifficultyButtonsEnabled(isEnabled: Boolean) {
        btnDifBaja.isEnabled = isEnabled
        btnDifMedia.isEnabled = isEnabled
        btnDifAlta.isEnabled = isEnabled
    }

    /**
     * Oculta el teclado virtual.
     */
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
