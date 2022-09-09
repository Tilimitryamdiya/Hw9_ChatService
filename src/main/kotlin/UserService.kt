object UserService {
    private var userId = 0
    private var users = mutableListOf<User>()

    private fun setId(): Int {
        userId++
        return userId
    }

    fun add(user: User): User {
        users += user.copy(id = setId())
        return users.last()
    }
}