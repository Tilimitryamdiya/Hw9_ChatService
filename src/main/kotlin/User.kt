data class User(
    var id: Int = 0,
    val name: String,
    val chats: MutableMap<Int, Chat> = mutableMapOf()
) {
    override fun toString(): String {
        return "($id) $name"
    }
}