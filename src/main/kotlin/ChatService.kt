object ChatService {

    private var curMessageId = 0

    fun addMessage(message: Message) {
        curMessageId++
        message.id = curMessageId
        message.fromUser.chats.getOrPut(message.toUser.id) { Chat() }.messages[message.id] = message
        message.toUser.chats.getOrPut(message.fromUser.id) { Chat() }.messages[message.id] = message
    }

    fun deleteMessage(fromUser: User, toUser: User, message: Message) {
        fromUser.chats.getValue(toUser.id).messages.remove(message.id)
        toUser.chats.getValue(fromUser.id).messages.remove(message.id)

        if (fromUser.chats.getValue(toUser.id).messages.isEmpty()) {
            deleteChat(fromUser, toUser)
        }
    }

    fun editMessage(fromUser: User, toUser: User, newMessage: Message) {
        fromUser.chats.getValue(toUser.id).messages[newMessage.id]?.apply {
            text = newMessage.text
            read = false
        }
    }

    fun printMessages(fromUser: User, toUser: User) {
        println(fromUser.chats[toUser.id]?.messages.toString())
    }

    fun getMessage(fromUser: User, toUser: User, messageId: Int): Message {
        return fromUser.chats[toUser.id]?.messages?.get(messageId) ?: throw MessageNotFoundException()
    }

    fun getLastMessages(fromUser: User, toUser: User, count: Int): List<Message> {
        val chat = fromUser.chats[toUser.id] ?: throw ChatNotFoundException()
        return chat.messages.values.toList().takeLast(count).onEach { it.read = true }
    }

    fun showAllChats(owner: User) {
        val allChats = owner.chats.values.takeIf { it.isNotEmpty() }?.isNotEmpty() ?: "No chats"

        println(allChats)
    }

    fun deleteChat(fromUser: User, toUser: User) = fromUser.chats.remove(toUser.id)

    fun countUnreadChats(owner: User) = owner.chats.values.count { chat ->
        chat.messages.any {
            !it.value.read && it.value.fromUser != owner
        }
    }

}