package models.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_PATH = "users.json";
    private Gson gson;
    private List<User> users;

    public UserRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.users = loadUsers();
    }

    public void saveUsers() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("[ERROR]: Failed to save users: " + e.getMessage());
        }
    }

    private List<User> loadUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> loadedUsers = gson.fromJson(reader, listType);
            return loadedUsers != null ? loadedUsers : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("[ERROR]: Failed to load users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean isUsernameTaken(String username) {
        return getUserByUsername(username) != null;
    }

    public boolean addUser(User user) {
        if (isUsernameTaken(user.getUsername())) {
            return false;
        }
        users.add(user);
        saveUsers();
        return true;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}
