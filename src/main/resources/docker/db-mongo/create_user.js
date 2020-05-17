db.createUser(
    {
        user: "user",
        pwd: "xxx",
        roles: [
            { role: "readWrite", db: "zoo" }
        ]
    }
);
