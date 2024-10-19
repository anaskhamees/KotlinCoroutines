package com.example.mvvmflowstateflow.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmflowstateflow.R
import com.example.mvvmflowstateflow.model.Product

/**
 * Adapter for displaying a list of products in a RecyclerView.
 *
 * This adapter binds product data to the views in each item of the RecyclerView, allowing users
 * to interact with the product list, such as adding products to favorites.
 */
class AllProductAdapter : RecyclerView.Adapter<AllProductAdapter.ProductViewHolder>() {

    private var products: List<Product> = listOf()
    private var onFavoriteClickListener: ((Product) -> Unit)? = null

    /**
     * Creates a new ViewHolder for the RecyclerView.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an
     *               adapter position.
     * @param viewType The view type of the new View.
     * @return A new instance of ProductViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_products_item, parent, false)
        return ProductViewHolder(view)
    }

    /**
     * Binds the data of the product at the specified position to the ViewHolder.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item
     *               at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)

        holder.btnAddToFav.setOnClickListener {
            onFavoriteClickListener?.invoke(product)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The number of items in the data set.
     */
    override fun getItemCount(): Int = products.size

    /**
     * Updates the adapter's product list and refreshes the RecyclerView.
     *
     * @param productList The new list of products to display.
     */
    fun setProducts(productList: List<Product>) {
        this.products = productList
        notifyDataSetChanged()
    }

    /**
     * Sets the listener to be called when a favorite button is clicked.
     *
     * @param listener The listener to invoke when a product is marked as favorite.
     */
    fun setOnFavoriteClickListener(listener: (Product) -> Unit) {
        this.onFavoriteClickListener = listener
    }

    /**
     * ViewHolder class for holding and binding product views.
     *
     * @param itemView The view to hold for a single product item.
     */
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.editTitle)
        private val textViewPrice: TextView = itemView.findViewById(R.id.editPrice)
        private val textViewBrand: TextView = itemView.findViewById(R.id.editBrand)
        private val textViewDescription: TextView = itemView.findViewById(R.id.editDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.favProductImg)
        val btnAddToFav: Button = itemView.findViewById(R.id.btnAddToFav)

        /**
         * Binds the product data to the views.
         *
         * @param product The product to bind to the views.
         */
        fun bind(product: Product) {
            textViewTitle.text = product.title
            textViewPrice.text = product.price.toString()
            textViewBrand.text = product.brand
            textViewDescription.text = product.description

            // Load image using Glide
            Glide.with(itemView.context)
                .load(product.thumbnail)
                .into(imageView)
        }
    }
}
