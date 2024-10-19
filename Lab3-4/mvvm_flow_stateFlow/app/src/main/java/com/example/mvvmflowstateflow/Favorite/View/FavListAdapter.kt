package com.example.mvvmflowstateflow.favorite.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmflowstateflow.R
import com.example.mvvmflowstateflow.model.Product

/**
 * Adapter for managing the list of favorite products in a RecyclerView.
 * The adapter uses [DiffUtil] for efficient updates and provides a callback
 * for deleting a product from the list.
 *
 * @property deleteCallback A lambda function that is triggered when the delete button is clicked.
 */
class FavListAdapter(private val deleteCallback: (Product) -> Unit) :
    ListAdapter<Product, FavListAdapter.FavViewHolder>(ProductDiffCallback()) {

    /**
     * Callback for calculating the difference between two non-null [Product] items in the list.
     * It optimizes RecyclerView updates.
     */
    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        /**
         * Checks whether two [Product] items represent the same product by comparing their IDs.
         *
         * @param oldItem The old [Product] item.
         * @param newItem The new [Product] item.
         * @return `true` if both items have the same ID, `false` otherwise.
         */
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        /**
         * Checks whether two [Product] items have the same content.
         *
         * @param oldItem The old [Product] item.
         * @param newItem The new [Product] item.
         * @return `true` if both items have the same content, `false` otherwise.
         */
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Inflates the layout for each product item in the RecyclerView.
     *
     * @param parent The parent ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new [FavViewHolder] that holds the inflated product item view.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_product_item, parent, false)
        return FavViewHolder(view)
    }

    /**
     * Binds the data from a [Product] to the [FavViewHolder].
     *
     * @param holder The ViewHolder to bind the data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(getItem(position), deleteCallback)
    }

    /**
     * ViewHolder class that represents each item in the RecyclerView.
     * Responsible for displaying the product data and handling delete actions.
     */
    class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewTitle: TextView = itemView.findViewById(R.id.editTitle)
        private val textViewPrice: TextView = itemView.findViewById(R.id.editPrice)
        private val textViewBrand: TextView = itemView.findViewById(R.id.editBrand)
        private val textViewDescription: TextView = itemView.findViewById(R.id.editDescription)
        private val deleteButton: TextView = itemView.findViewById(R.id.btndeletefromFav)

        /**
         * Binds the product data to the views and sets up the delete button's click listener.
         *
         * @param product The product to bind to the ViewHolder.
         * @param deleteCallback A lambda function that will be invoked when the delete button is clicked.
         */
        fun bind(product: Product, deleteCallback: (Product) -> Unit) {
            textViewTitle.text = product.title
            textViewPrice.text = product.price.toString()
            textViewBrand.text = product.brand
            textViewDescription.text = product.description
            deleteButton.setOnClickListener {
                deleteCallback(product) // Trigger the delete callback when clicked
            }
        }
    }
}
