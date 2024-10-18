# KotlinCoroutines

### The Difference between Collect and CollectLatest

### 1. **`collect`**:

- **Behavior**: `collect` simply collects each value emitted by the `Flow` and processes them in sequence. Each emission is processed one by one in the order they arrive. It waits until the previous emission processing is finished before it processes the next one.
- **Use Case**: `collect` is used when you want to ensure that every emission is processed fully, even if it takes time.

#### Example:

```kotlin
kotlinCopy codeflow {
    emit(1)
    delay(1000)
    emit(2)
    delay(1000)
    emit(3)
}.collect {
    println("Collecting $it")
    delay(1500) 
}
```

### Output:

```
Collecting 1
Collecting 2
Collecting 3
```

Here, `collect` ensures that each value emitted (1, 2, and 3) is processed fully, even though the processing (`delay(1500)`) takes longer than the delay between emissions (`delay(1000)`).

------

### 2. **`collectLatest`**:

- **Behavior**: `collectLatest` cancels the collection of the previous emission if a new emission arrives before the previous one is finished processing. This means it always processes only the latest value and discards any previous values if they are still being processed.
- **Use Case**: `collectLatest` is useful when you only care about the latest value and don’t need to process every single emission, especially when emissions happen frequently or when processing takes time.

#### Example:

```kotlin
kotlinCopy codeflow {
    emit(1)
    delay(1000)
    emit(2)
    delay(1000)
    emit(3)
}.collectLatest {
    println("Collecting latest $it")
    delay(1500) 
}
```

### Output:

```
Collecting latest 1
Collecting latest 3
```

In this case, when the second emission (2) arrives while the processing of the first one is still ongoing (because of the `delay(1500)`), the previous emission is canceled, and processing moves to the latest one. The emission `2` is skipped entirely because `collectLatest` is designed to only process the latest value.

