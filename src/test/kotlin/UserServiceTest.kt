import org.junit.Test

import org.junit.Assert.*

class UserServiceTest {

    @Test
    fun add() {
        val testUser = User(name = "Person")
        val addUser = UserService.add(testUser)
        val result = addUser.id
        assertNotEquals(0, result)
    }
}