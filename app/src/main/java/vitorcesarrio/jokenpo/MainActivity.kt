package vitorcesarrio.jokenpo

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val PEDRA = 1
    val PAPEL = 2
    val TESOURA = 3

    var numeroAleatorio: Random? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroAleatorio = Random()

        imgPedra.setOnClickListener {
            imgJogador!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pedra))
            realizarJogada(PEDRA)
        }

        imgPapel.setOnClickListener {
            imgJogador!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.papel))
            realizarJogada(PAPEL)
        }
        imgTesoura.setOnClickListener {
            imgJogador!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tesoura))
            realizarJogada(TESOURA)
        }
    }

        fun realizarJogada(jogadaPlayer: Int){
            val player = MediaPlayer.create(this, R.raw.jokenpo)
            player.start()

            val jogadaPC = numeroAleatorio!!.nextInt(3) + 1

            when (jogadaPC){
                PEDRA -> {
                    imgPC!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pedra))
                    when(jogadaPlayer){
                        PAPEL -> venceu()
                        PEDRA -> empatou()
                        TESOURA -> perdeu()
                    }
                }
                PAPEL -> {
                    imgPC!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.papel))
                    when(jogadaPlayer){
                        PAPEL -> empatou()
                        PEDRA -> perdeu()
                        TESOURA -> venceu()
                    }
                }
                TESOURA -> {
                    imgPC!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tesoura))
                    when(jogadaPlayer){
                        PAPEL -> perdeu()
                        PEDRA -> venceu()
                        TESOURA -> empatou()
                    }
                }
            }
        }

        fun venceu(){
            tvVencedor!!.text = getString(R.string.venceu);
            tvVencedor!!.setTextColor(ContextCompat.getColor(this, R.color.vitoria))
        }

        fun perdeu(){
            tvVencedor!!.text = getString(R.string.perdeu);
            tvVencedor!!.setTextColor(ContextCompat.getColor(this, R.color.derrota))
        }

        fun empatou(){
            tvVencedor!!.text = getString(R.string.empatou);
            tvVencedor!!.setTextColor(ContextCompat.getColor(this, R.color.empate))
        }
}
