db.createUser(
    {
        user: "user",
        pwd: "xxx",
        roles: [
            { role: "readWrite", db: "p1_dokumenty" }
        ]
    }
);
