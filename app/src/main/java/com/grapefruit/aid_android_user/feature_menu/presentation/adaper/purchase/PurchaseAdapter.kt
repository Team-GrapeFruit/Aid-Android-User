package com.grapefruit.aid_android_user.feature_menu.presentation.adaper.purchase

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
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.MenuPageActivity
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel

class PurchaseAdapter(val itemList: List<PurchaseDTO>) :
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
        return itemList.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val purchase = itemList[position]

        with(holder) {
            with(binding) {
                deleteBtn.setOnClickListener {
                    viewModel.deleteMenuRoad(1)
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                }
                /*plusBtn.setOnClickListener {
                    val _quantity = quantity.text.toString().toInt() + 1
                    quantity.text = _quantity.toString()
                    viewModel.quantityControlRoad(1, 1)
                    binding.quantity.text = quantity.toString()
                    notifyDataSetChanged()
                }*/
                minusBtn.setOnClickListener {
                    viewModel.quantityControlRoad(1, 1)

                    binding.quantity.text = quantity.toString()
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
                plusBtn.setOnClickListener {
                    val _quantity = quantity.text.toString().toInt() + 1
                    Log.d("quantity", _quantity.toString())
                    quantity.text = _quantity.toString()

                    viewModel.quantityControlRoad(1, 1)
                }
            }
        }

        private val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        private val cost = itemView.findViewById<TextView>(R.id.cost)
        private val quantity = itemView.findViewById<TextView>(R.id.quantity)
        private val menuImg = itemView.findViewById<ImageView>(R.id.menu_image)

        fun bind(purchaseDTO: PurchaseDTO) {
            menuName.text = purchaseDTO.purchaseMenu.menuName
            cost.text = purchaseDTO.purchaseMenu.cost.toString() + "원"
            quantity.text = purchaseDTO.quantity.toString()
            Glide.with(menuImg)
                .load(purchaseDTO.purchaseMenu.menuImgUrl)
                .into(binding.menuImage)
        }
    }

}