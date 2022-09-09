class Chat(
    val messages: MutableMap<Int, Message> = mutableMapOf()
) {
    override fun toString(): String {
        return "Chat: ${messages.count()} messages"
    }
}