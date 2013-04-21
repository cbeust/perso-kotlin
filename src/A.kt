
//fun main(args: Array<String>) {
//    var r = (1..25)
////    println(r. { (n: Int) -> n * n })
//}

class Multimap<K, V> {
    val map: MutableMap<K, MutableList<V>> = hashMapOf()

    fun put(k: K, v: V) {
        var l : MutableList<V>? = map.get(k)
        if (l == null) {
            l = arrayListOf()
            map.put(k, l!!)
        }
        l!!.add(v)
    }

    fun get(k: K) : List<V>? {
        return map.get(k)
    }
}