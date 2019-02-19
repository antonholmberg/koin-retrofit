# Koin Retrofit

This is a project that helps with instantiating API interfaces with retrofit.

## Usage

Given this class

```kotlin
class ClassToInject(api: SomeRetrofitApiInterface)
```

We might inject it like this:

```kotlin
val appModule = module {
    callAdapter { SomeCallAdapterFactory.create() }
    converterFactrory { SomeConverterFactory.create() }
    retrofit { retrofit("https://example.com/") }
    
    factory { ClassToInject(retrofitApi()) }
}
```

The library handles creating a single instance for each of the API per retrofit.

### Multiple APIs

You can provide a name to the retrofit instance in order to have retrofit instances for multiple different backends.

```kotlin
val appModule = module {
    callAdapter { SomeCallAdapterFactory.create() }
    converterFactrory { SomeConverterFactory.create() }
    retrofit(name = "example") { retrofit("https://example.com/") }
    retrofit(name = "example2") { retrofit("https://example2.com/") }
    
    factory { ClassToInject(retrofitApi(retrofitName = "example")) }
    factory { ClassToInject(retrofitApi(retrofitName = "example2")) }
}
```
