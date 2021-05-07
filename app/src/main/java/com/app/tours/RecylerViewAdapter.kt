package com.app.tours
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_model.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val itemTitles=arrayOf("San Jose Tour", "Heredia Tour", "Alajuela Tour")

    private val itemDetails= arrayOf("Get to know San Jose!", "Get to know Heredia!", "Get to know Alajuela!")

    private val itemPrices = arrayOf("Desde $120","Desde $200","Desde $50")

    private val itemCalifications = arrayOf(3,2,1)

    private val itemOpinions = arrayOf("5 opiniones","10 opiniones","1 opinion")

    private val itemImages=intArrayOf(
        R.drawable.image,
        R.drawable.image1,
        R.drawable.image2
    )

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView
        var textTitle:TextView
        var textDes: TextView
        var textPrice: TextView
        var barCalification: RatingBar
        var textOpinion: TextView

        init{
            image=itemView.findViewById(R.id.item_image)
            textTitle=itemView.findViewById(R.id.item_title)
            textDes=itemView.findViewById(R.id.item_details)
            textPrice=itemView.findViewById(R.id.price)
            barCalification=itemView.findViewById(R.id.ratingBar)
            textOpinion=itemView.findViewById(R.id.opinions)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_model,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = itemTitles[position]
        holder.textDes.text=itemDetails[position]
        holder.image.setImageResource(itemImages [position])
        holder.barCalification.rating = itemCalifications[position].toFloat()
        holder.textOpinion.text = itemOpinions[position]
        holder.textPrice.text = itemPrices[position]


        holder.itemView.setOnClickListener { v:View->
            Toast.makeText(v.context,"Clicked on the item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemTitles.size
    }
}