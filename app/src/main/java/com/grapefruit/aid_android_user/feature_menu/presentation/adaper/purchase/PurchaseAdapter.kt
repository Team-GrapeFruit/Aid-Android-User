package com.grapefruit.aid_android_user.feature_menu.presentation.adaper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.MenuPopupWindow.MenuDropDownListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ShoppingBasketMenulistItemBinding
import com.grapefruit.aid_android_user.feature_menu.data.api.PurchaseService
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.MenuPageActivity
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel

class PurchaseAdapter(val itemList: ArrayList<PurchaseDTO>) :
    RecyclerView.Adapter<PurchaseAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ShoppingBasketMenulistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val purchase = itemList[position]
        holder.bind(purchase)
    }

    class Holder(private val binding: ShoppingBasketMenulistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            with(binding) {
                changeOptionBtn.setOnClickListener {
                    val intent = Intent(context, MenuPageActivity::class.java)
                    context.startActivity(intent)
                }

                deleteBtn.setOnClickListener {
                    
                }
            }
        }

        private val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        private val cost = itemView.findViewById<TextView>(R.id.cost)
        private val quantity = itemView.findViewById<TextView>(R.id.quantity)
        private val menuImg = itemView.findViewById<ImageView>(R.id.menu_image)

        fun bind(purchaseDTO: PurchaseDTO) {
            menuName.text = purchaseDTO.menuName.toString()
            cost.text = purchaseDTO.cost.toString()
            quantity.text = purchaseDTO.quantity.toString()
            Glide.with(menuImg)
                .load(purchaseDTO.menuImgUrl)
                .into(binding.menuImage)
        }
    }

}