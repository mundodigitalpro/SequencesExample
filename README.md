
# Kotlin Sequence vs List Performance Comparison

This project demonstrates the performance benefits of using sequences over lists in Kotlin for processing large collections with multiple steps. The provided example code filters and maps a list of products, measuring the execution time for both lists and sequences.

## Advantages of Using Sequences

- **Lazy Evaluation**: Processes elements one by one, reducing memory usage.
- **Improved Performance**: Avoids creation of intermediate collections, enhancing performance in chained operations.

## Example Code

The provided Kotlin code compares the performance of lists and sequences using a list of 100 products.

### Kotlin Code

```kotlin
import kotlin.system.measureTimeMillis

data class Product(val name: String, val description: String, val purchased: Boolean, val price: Double)

fun main() {
    val productList = List(100) {
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
```

### How to Run

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/mundodigitalpro/SequencesExample.git
    cd SequencesExample
    ```

2. **Open the Project**: Open the project in your preferred Kotlin IDE (e.g., IntelliJ IDEA).

3. **Run the Code**: Execute the `main` function in `Main.kt` to see the results.

### Handling Large Collections

For large datasets (e.g., 100,000+ items), sequences offer significant performance improvements by processing data lazily and avoiding intermediate collections. Adjust the size of the `productList` to test with larger collections:

```kotlin
val productList = List(100000) {
    Product(
        name = "Product ${it + 1}",
        description = "Description of product ${it + 1}",
        purchased = it % 2 == 0,
        price = (it + 1) * 10.0
    )
}
```

### Results

The console output will show the time taken to process the list using both lists and sequences, allowing you to compare their performance.

## Contributing

Feel free to open issues or submit pull requests with improvements and bug fixes. Contributions are always welcome!

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
