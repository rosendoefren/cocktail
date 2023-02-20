package dev.echavez.cocktail.core

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import dev.echavez.cocktail.data.model.TypeSnack

object Snack {
    fun build(contex: View, mns: String, tipo: TypeSnack): Snackbar {
        val snack: Snackbar = Snackbar.make(contex, mns, Snackbar.LENGTH_LONG).apply {
            this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.apply {
                maxLines = 5
                isSingleLine = false
            }
        }
        when(tipo){
            TypeSnack.OK ->snack.setBackgroundTint(Color.parseColor("#4CAF50"))
            TypeSnack.ERROR ->snack.setBackgroundTint(Color.parseColor("#EF1D49"))
            TypeSnack.SERVER ->snack.setBackgroundTint(Color.parseColor("#FFC107"))
            TypeSnack.NOTAVAILABLE ->snack.setBackgroundTint(Color.parseColor("#FF5722"))
        }
        snack.setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
        return snack
    }
}