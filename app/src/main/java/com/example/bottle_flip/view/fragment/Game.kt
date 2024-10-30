package com.example.bottle_flip.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.os.CountDownTimer
import com.example.bottle_flip.R
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator

class Game : Fragment() {

    private lateinit var countdownText: TextView
    private var backgroundMediaPlayer: MediaPlayer? = null
    private var effectMediaPlayer: MediaPlayer? = null // Para el sonido de efecto
    private lateinit var bottleImage: ImageView
    private lateinit var btnSpin: LottieAnimationView
    private var lastAngle = 0f // Variable para almacenar el último ángulo de la botella


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.game, container, false)
        countdownText = view.findViewById(R.id.countdownText)

        // Inicializar el ImageView de la botella y el botón LottieAnimationView
        bottleImage = view.findViewById(R.id.centerImageView)
        btnSpin = view.findViewById(R.id.btn_spin)
        countdownText = view.findViewById(R.id.countdownText)

        // Reproducir el sonido de fondo al iniciar el fragmento
        playBackgroundSound()

        // Configurar el OnClickListener para que al presionar el botón gire la botella
        btnSpin.setOnClickListener {
            // Iniciar la animación de Lottie (opcional si quieres que el botón también se anime)
            btnSpin.playAnimation()
            // Iniciar la animación de rotación de la botella
            playBottleSound() // Reproduce el sonido de la botella solo al presionar el botón
            stopBackgroundSound() // Detiene el sonido de fondo
            playEffectSound() // Reproduce el sonido de efecto inicia el giro de la botella
            rotateBottleClockwise()
        }

        return view
    }

    private fun playBackgroundSound() {
        if (backgroundMediaPlayer == null) {
            backgroundMediaPlayer = MediaPlayer.create(
                context,
                R.raw.background_audio
            ) // Cambia esto al nombre del archivo de sonido de fondo
            backgroundMediaPlayer?.isLooping = true // Hacer que el sonido de fondo se repita
            backgroundMediaPlayer?.start() // Reproduce el sonido de fondo
        }
    }

    private fun stopBackgroundSound() {
        backgroundMediaPlayer?.stop() // Detiene el sonido de fondo
        backgroundMediaPlayer?.release() // Libera recursos
        backgroundMediaPlayer = null // Resetea el objeto mediaPlayer
    }

    private fun playEffectSound() {
        if (effectMediaPlayer == null) {
            effectMediaPlayer = MediaPlayer.create(
                context,
                R.raw.audio_bottle
            ) // Cambia esto al nombre del archivo del sonido de efecto
            effectMediaPlayer?.start() // Reproduce el sonido de efecto
            effectMediaPlayer?.setOnCompletionListener {
                it.release() // Libera recursos al terminar
                effectMediaPlayer = null // Resetea el objeto mediaPlayer
            }

            // Detener el efecto de sonido después de 5 segundos
            object : CountDownTimer(5000, 5000) {
                override fun onTick(millisUntilFinished: Long) {
                    // No hacemos nada aquí
                }

                override fun onFinish() {
                    stopEffectSound() // Detiene el sonido de efecto después de 5 segundos
                }
            }.start() // Iniciar el temporizador para el sonido de efecto
        }
    }

    private fun stopEffectSound() {
        effectMediaPlayer?.stop() // Detiene la reproducción del sonido de efecto
        effectMediaPlayer?.release() // Libera los recursos
        effectMediaPlayer = null // Resetea el objeto mediaPlayer
    }


    private fun rotateBottleClockwise() {

        // Oculta el contador al iniciar la rotación
        countdownText.visibility = View.GONE


        // Genera un ángulo de rotación aleatorio entre 0 y 360 grados
        val rotationAnimator = ValueAnimator.ofFloat(0f, 360f)
        rotationAnimator.duration = 5000// 2 segundos de duración
        rotationAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            bottleImage.rotation = animatedValue % 360// Actualiza la rotación de la botella
        }
        // Detener la rotación después de 1.5 segundos
        rotationAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                stopBottleSound() // Detiene el sonido al finalizar
                showCountdown() // Muestra el contador después de que la botella se detiene
            }
        })
        rotationAnimator.start()
    }

    private fun showCountdown() {
        countdownText.visibility = View.VISIBLE // Hacer visible el contador
        countdownText.text = "3" // Comenzar con 3

        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                countdownText.text =
                    secondsRemaining.toString() // Actualiza el texto con el tiempo restante
            }

            override fun onFinish() {
                countdownText.text = "0" // Muestra 0 al final
            }
        }.start() // Inicia el temporizador
    }

    private fun playBottleSound() {
        if (effectMediaPlayer == null) {
            effectMediaPlayer = MediaPlayer.create(context, R.raw.audio_bottle) // Carga el sonido
            effectMediaPlayer?.start() // Reproduce el sonido
            effectMediaPlayer?.setOnCompletionListener {
                it.release() // Libera recursos al terminar
                effectMediaPlayer = null // Resetea el objeto mediaPlayer
            }
        }
    }

    private fun stopBottleSound() {
        effectMediaPlayer?.stop() // Detiene la reproducción
        effectMediaPlayer?.release() // Libera los recursos
        effectMediaPlayer = null // Resetea el objeto mediaPlayer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Liberar el MediaPlayer cuando el fragmento se destruye
        effectMediaPlayer?.release()
        effectMediaPlayer = null
    }

}
