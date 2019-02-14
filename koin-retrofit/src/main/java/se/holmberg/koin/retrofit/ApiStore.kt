package se.holmberg.koin.retrofit

import kotlin.reflect.KClass

private val apis = mutableMapOf<KClass<out Any>, Any>()

@Suppress("UNCHECKED_CAST")
fun <T : Any> getApi(clazz: KClass<in T>): T? = apis[clazz] as T

fun <T : Any> putApi(api: T) {
    apis[api::class] = api
}
