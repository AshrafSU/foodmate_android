package com.food.foodmate.dataModel.products

import com.food.foodmate.R


data class ProductCategory(
    val name: String,
    val products: List<Product>
)

data class Product(
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int
)

fun getDummyCategories(): List<ProductCategory> = listOf(
    ProductCategory(
        name = "Rice Item",
        products = listOf(
            Product("Chicken Reshmi Kebab", "A smoky charred texture to a creamy and luscious barbequed chicken", 350.00, R.drawable.food_img),
            Product("Chicken Kebab 1", "Delicious and juicy kebab", 300.00, R.drawable.food_img)
        )
    ),
    ProductCategory(
        name = "Chicken",
        products = listOf(
            Product("Chicken Biryani", "Aromatic rice with flavorful chicken", 400.00, R.drawable.food_img),
            Product("Chicken Curry", "Spicy and savory curry with tender chicken pieces", 320.00, R.drawable.food_img)
        )
    )
)