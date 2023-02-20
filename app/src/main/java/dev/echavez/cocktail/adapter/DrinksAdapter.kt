package dev.echavez.cocktail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.echavez.cocktail.R
import dev.echavez.cocktail.data.model.Drink


class DrinksAdapter (val list: List<Drink>, val clickListener: (item: Drink) -> Unit):
    RecyclerView.Adapter<DrinksAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drink, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("${list[position].strDrinkThumb}")
            .into(holder.img)
        holder.txtName.text = list[position].strDrink
        holder.txtAlcoholic.text = list[position].strAlcoholic
        holder.txtCategory.text = list[position].strAlcoholic
        holder.txtIBA.text = list[position].strIBA

        holder.view.setOnClickListener {
            clickListener(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val view = itemView
        val img: ImageView = ItemView.findViewById(R.id.img)
        val txtName: TextView = ItemView.findViewById(R.id.txtName)
        val txtCategory: TextView = ItemView.findViewById(R.id.txtCategory)
        val txtAlcoholic: TextView = ItemView.findViewById(R.id.txtAlcoholic)
        val txtIBA: TextView = ItemView.findViewById(R.id.txtIBA)
    }
}