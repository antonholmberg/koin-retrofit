package se.holmberg.koin.retrofit

import kotlin.reflect.KClass

private val apis: MutableMap<String, MutableMap<KClass<out Any>, Any>> = mutableMapOf()

@Suppress("UNCHECKED_CAST")
fun <T : Any> getApi(clazz: KClass<in T>, retrofitName: String = ""): T? = apis[retrofitName]?.get(clazz) as? T

fun <T : Any> putApi(api: T, retrofitName: String = "") {
    val map = apis[retrofitName] ?: mutableMapOf()
    map[api::class] = api
    apis[retrofitName] = map
}
