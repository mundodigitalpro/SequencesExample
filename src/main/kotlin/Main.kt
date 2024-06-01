package dev.josejordan

import kotlin.system.measureTimeMillis

data class Product(val name: String, val description: String, val purchased: Boolean, val price: Double)

fun main() {
    val productList = List(100000) {
        Product(
            name = "Product ${it + 1}",
            description = "Description of product ${it + 1}",
            purchased = it % 2 == 0,
            price = (it + 1) * 10.0
        )
    }

    val listTime = measureTimeMillis {
        val pricesWithList = processWithLists(productList)
        println("Prices with lists: $pricesWithList")
    }

    val sequenceTime = measureTimeMillis {
        val pricesWithSequences = processWithSequences(productList)
        println("Prices with sequences: $pricesWithSequences")
    }

    println("Processing time with lists: $listTime ms")
    println("Processing time with sequences: $sequenceTime ms")
}

fun processWithLists(products: List<Product>): List<Pair<String, Double>> {
    return products
        .filter { it.purchased }
        .map { it.name to it.price }
}

fun processWithSequences(products: List<Product>): List<Pair<String, Double>> {
    return products.asSequence()
        .filter { it.purchased }
        .map { it.name to it.price }
        .toList()
}
