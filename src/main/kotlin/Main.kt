fun main() {
    val user1 = UserService.add(User(name = "A"))
    val user2 = UserService.add(User(name = "B"))

    val message1 = Message(fromUser = user1, toUser = user2, text = "Message1")
    val message2 = Message(fromUser = user1, toUser = user2, text = "Message2")
    ChatService.addMessage(message1)
    ChatService.addMessage(message2)
    ChatService.addMessage(Message(fromUser = user2, toUser = user1, text = "Message3"))

    val newMessage = Message(id = 1, fromUser = user1, toUser = user2, text = "New message1")
    ChatService.editMessage(user1, user2, newMessage)
    ChatService.printMessages(user1, user2)
    ChatService.printMessages(user2, user1)
    println()

    ChatService.deleteMessage(user1, user2, message1)
    ChatService.printMessages(user1, user2)
    ChatService.printMessages(user2, user1)
    println()

    ChatService.getLastMessages(user2, user1, 1)
    ChatService.printMessages(user2, user1)
    ChatService.printMessages(user1, user2)
    println()

    ChatService.showAllChats(user2)
    println()

    println(ChatService.countUnreadChats(user1))
    println(ChatService.countUnreadChats(user2))
    println()

    ChatService.deleteChat(user1, user2)
    ChatService.showAllChats(user1)
    println()


}


