data class Message(
    var id: Int = 0,
    val fromUser: User,
    val toUser: User,
    var text: String,
    var read: Boolean = false
) {
    override fun toString(): String {
        return "From: $fromUser to: $toUser. Message: $text ($read)"
    }
}