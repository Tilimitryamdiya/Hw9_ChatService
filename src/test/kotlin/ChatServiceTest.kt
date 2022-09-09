import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    private val testUser1 = UserService.add(User(name = "First user"))
    private val testUser2 = UserService.add(User(name = "Second user"))
    private val testUser3 = UserService.add(User(name = "Third user"))
    private val testMessage1 = Message(fromUser = testUser1, toUser = testUser2, text = "Text 1")
    private val testMessage2 = Message(fromUser = testUser2, toUser = testUser1, text = "Text 1")


    @Test
    fun addMessageNewChat() {
        ChatService.addMessage(testMessage1)
        val result = testUser1.chats.isNotEmpty()
        assertEquals(true, result)
    }

    @Test
    fun deleteMessage() {
        ChatService.addMessage(testMessage1)
        ChatService.addMessage(testMessage2)
        ChatService.deleteMessage(testUser1, testUser2, testMessage1)
        val result = testUser1.chats.values.any { it.messages.values.contains(testMessage1) }
        assertEquals(false, result)
    }

    @Test
    fun editMessage() {
        ChatService.addMessage(testMessage1)
        val newTestMessage1 =
            Message(id = testMessage1.id, fromUser = testUser1, toUser = testUser2, text = "New text 1")
        ChatService.editMessage(testUser1, testUser2, newTestMessage1)
        val result = ChatService.getMessage(testUser1, testUser2, testMessage1.id).text.startsWith("New")
        assertEquals(true, result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun getMessage() {
        ChatService.getMessage(testUser1, testUser2, testMessage1.id)
    }

    @Test
    fun getLastMessages() {
        ChatService.addMessage(testMessage1)
        ChatService.addMessage(testMessage2)
        val get = ChatService.getLastMessages(testUser1, testUser2, 1)
        val result = get.component1().read
        assertEquals(true, result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun getLastMessagesException() {
        ChatService.getLastMessages(testUser1, testUser3, 1)
    }

    @Test
    fun deleteChat() {
        ChatService.addMessage(testMessage1)
        ChatService.addMessage(testMessage2)
        ChatService.deleteChat(testUser1, testUser2)
        val result = testUser1.chats[testUser2.id]
        assertEquals(null, result)
    }

    @Test
    fun countUnreadChats() {
        ChatService.addMessage(testMessage1)
        ChatService.addMessage(testMessage2)
        val result = ChatService.countUnreadChats(testUser1)
        assertEquals(1, result)
    }
}