namespace java com.xunfos.playground


struct User {
    1: string id
    2: string name
}

struct GetUserRequest {
    1: string id
}

struct GetUserResponse {
    1: User user
}

struct GetUsersResponse {
    1: list<User> users
}

service PlaygroundService {
    void ping()
    void doWork()
    GetUserResponse getUser(1: GetUserRequest request)
    GetUsersResponse getUsers()
}
