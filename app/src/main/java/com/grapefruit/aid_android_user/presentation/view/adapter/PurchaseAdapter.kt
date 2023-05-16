package com.grapefruit.aid_android_user.presentation.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ShoppingBasketMenulistItemBinding
import com.grapefruit.aid_android_user.data.dto.PurchaseDTO
import com.grapefruit.aid_android_user.presentation.view.MenuPageActivity
import com.grapefruit.aid_android_user.presentation.view.ShoppingBasketPageActivity
import com.grapefruit.aid_android_user.presentation.viewmodel.MenuPageViewModel

class PurchaseAdapter(
    val itemList: ArrayList<PurchaseDTO>,
    val activity: ShoppingBasketPageActivity,
) :
    RecyclerView.Adapter<PurchaseAdapter.Holder>() {

    private val viewModel = MenuPageViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ShoppingBasketMenulistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("Adapter-itemList-size", itemList.size.toString())
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val purchase = itemList[position]

        with(holder) {
            with(binding) {
                deleteBtn.setOnClickListener {
                    activity.deleteItem(position)
                    viewModel.purchaseListRoad(1L)
                }
                plusBtn.setOnClickListener {
                    activity.quantityControl(position, purchase.quantity+1)
                    viewModel.purchaseListRoad(1L)
                    notifyDataSetChanged()
                }
                minusBtn.setOnClickListener {
                    activity.quantityControl(position, purchase.quantity-1)
                    viewModel.purchaseListRoad(1L)
                    notifyDataSetChanged()
                }
            }
            bind(purchase)
        }
    }

    inner class Holder(val binding: ShoppingBasketMenulistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            with(binding) {
                changeOptionBtn.setOnClickListener {
                    val intent = Intent(context, MenuPageActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }

        private val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        private val cost = itemView.findViewById<TextView>(R.id.cost)
        private val quantity = itemView.findViewById<TextView>(R.id.quantity)
        private val menuImg = itemView.findViewById<ImageView>(R.id.menu_image)

        fun bind(purchaseDTO: PurchaseDTO) {
            Log.d("adapter", purchaseDTO.toString())
            menuName.text = purchaseDTO.purchaseMenu.menuName
            cost.text = purchaseDTO.purchaseMenu.cost.toString() + "원"
            quantity.text = purchaseDTO.quantity.toString()
            Glide.with(menuImg)
                .load(purchaseDTO.purchaseMenu.menuImgURL)
                .into(binding.menuImage)
        }
    }

}