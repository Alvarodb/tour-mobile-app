package com.app.tours
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.tours.services.dto.TourDto
import kotlinx.android.synthetic.main.recyclerview_model.view.*

class RecyclerViewAdapter(val results: List<TourDto>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


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
        val tourDto = results?.get(position)
        holder.textTitle.text = tourDto.name
        holder.textDes.text=tourDto.description
        holder.textPrice.text = tourDto.price_for_person
        holder.image.setImageResource( R.drawable.image)
        holder.barCalification.rating = tourDto.calification.toFloat()




        holder.itemView.setOnClickListener { v:View->
            Toast.makeText(v.context,"Clicked on the item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
}