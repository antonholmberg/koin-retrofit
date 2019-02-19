package se.holmberg.koin.retrofit.dsl

import okhttp3.OkHttpClient
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.definition.Definition
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import se.holmberg.koin.retrofit.getApi
import se.holmberg.koin.retrofit.putApi

fun ModuleDefinition.retrofit(
    name: String = "",
    override: Boolean = false,
    definition: Definition<Retrofit>
) {
    single(name, false, override, definition)
}

fun ModuleDefinition.callAdapter(
    name: String = "",
    override: Boolean = false,
    definition: Definition<CallAdapter.Factory>
) {
    single(name, false, override, definition)
}

fun ModuleDefinition.converterFactory(
    name: String = "",
    override: Boolean = false,
    definition: Definition<Converter.Factory>
) {
    single(name, false, override, definition)
}

inline fun <reified T : Any> ModuleDefinition.retrofitApi(retrofitName: String = ""): T {
    val retrofit: Retrofit = get(name = retrofitName)

    return getApi(T::class)
        ?: retrofit.create(T::class.java).also { putApi(it, retrofitName) }
}

fun ModuleDefinition.retrofit(
    baseUrl: String,
    client: OkHttpClient = OkHttpClient.Builder().build(),
    callAdapterName: String = "",
    converterFactoryName: String = ""
): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(get(callAdapterName))
    .addConverterFactory(get(converterFactoryName))
    .client(client)
    .baseUrl(baseUrl)
    .build()
