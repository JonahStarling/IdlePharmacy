package com.jonahstarling.idlepharmacy

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_drug.view.*

class DrugAdapter(private val context: Context): RecyclerView.Adapter<DrugAdapter.DrugViewHolder>() {

    var drugAdapterListener: OnDrugTappedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        return DrugViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_drug, parent, false))
    }

    override fun getItemCount(): Int = Drugs.allDrugs.size

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val drug = Drugs.allDrugs[position]
        holder.drugImage.setImageResource(drug.drugImageResource)
        holder.name.text = drug.name
        val priceString = "${context.getString(R.string.price)} ${drug.price}"
        holder.price.text = priceString
        val quantityString = "${context.getString(R.string.quantity)} ${drug.quantity}"
        holder.quantity.text = quantityString
        val costToUnlockString = "${drug.cost} to unlock"
        holder.costToUnlock.text = costToUnlockString
        holder.itemView.isEnabled = drug.unlocked
        if (drug.unlocked) {
            holder.itemView.setOnClickListener {
                MainFragment.piggyBank.coins += drug.price
                MainFragment.piggyBank.pills += drug.quantity
                drugAdapterListener?.onDrugTapped()
            }
            holder.costToUnlock.visibility = View.GONE
            holder.locked.visibility = View.GONE
        } else {
            holder.costToUnlock.visibility = View.VISIBLE
            holder.locked.visibility = View.VISIBLE
            holder.costToUnlock.setOnClickListener {
                if (MainFragment.piggyBank.coins >= drug.cost) {
                    drug.unlocked = true
                    MainFragment.piggyBank.coins -= drug.cost
                    notifyItemChanged(position)
                    drugAdapterListener?.onDrugTapped()
                }
            }
        }

    }

    interface OnDrugTappedListener {
        fun onDrugTapped()
    }

    class DrugViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val drugImage: ImageView = view.drugImage
        val name: TextView = view.name
        val price: TextView = view.price
        val quantity: TextView = view.quantity
        val costToUnlock: TextView = view.costToUnlock
        val locked: View = view.locked
    }
}