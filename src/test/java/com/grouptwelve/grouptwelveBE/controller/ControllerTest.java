package com.grouptwelve.grouptwelveBE.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grouptwelve.grouptwelveBE.model.FavoriteTeam;
import com.grouptwelve.grouptwelveBE.repository.FavoriteTeamRepository;
import com.grouptwelve.grouptwelveBE.model.User;
import com.grouptwelve.grouptwelveBE.repository.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteTeamRepository favoriteTeamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User("Test User", "test@example.com", "password");
        userRepository.save(user);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Test User"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));
    }

    @Test
    void testCreateUser() throws Exception {
        User newUser = new User("New User", "new@example.com", "newpassword");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New User"))
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User("Test User", "test@example.com", "password");
        User savedUser = userRepository.save(user);

        mockMvc.perform(get("/api/users/" + savedUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }



    // Tests for Games 



    @Test
    void testGetAllFavoriteTeams() throws Exception {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        favoriteTeamRepository.save(favoriteTeam);

        mockMvc.perform(get("/api/favoriteteams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].teamId").value(2L));
    }

    @Test
    void testGetFavoriteTeamsByUserId() throws Exception {
        FavoriteTeam favoriteTeam1 = new FavoriteTeam(1L, 2L);
        FavoriteTeam favoriteTeam2 = new FavoriteTeam(1L, 3L);
        favoriteTeamRepository.save(favoriteTeam1);
        favoriteTeamRepository.save(favoriteTeam2);

        mockMvc.perform(get("/api/favoriteteams/user/" + favoriteTeam1.getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].teamId").value(2L))
                .andExpect(jsonPath("$[1].userId").value(1L))
                .andExpect(jsonPath("$[1].teamId").value(3L));
    }

    @Test
    void testGetFavoriteTeamByUserIdAndTeamId() throws Exception {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        favoriteTeamRepository.save(favoriteTeam);

        mockMvc.perform(get("/api/favoriteteams/user/" + favoriteTeam.getUserId() + "/team/" + favoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.teamId").value(2L));
    }

    @Test
    void testAddFavoriteTeamByUserIdAndTeamId() throws Exception {
        Long userId = 1L;
        Long teamId = 2L;

        mockMvc.perform(post("/api/favoriteteams/user/" + userId + "/team/" + teamId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.teamId").value(teamId));
    }

    @Test
    void testUpdateFavoriteTeam() throws Exception {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        FavoriteTeam savedFavoriteTeam = favoriteTeamRepository.save(favoriteTeam);

        FavoriteTeam updatedFavoriteTeam = new FavoriteTeam(3L, 4L);

        mockMvc.perform(put("/api/favoriteteams/user/" + savedFavoriteTeam.getUserId() + "/team/" + savedFavoriteTeam.getTeamId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFavoriteTeam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(3L))
                .andExpect(jsonPath("$.teamId").value(4L));
    }

    @Test
    void testDeleteFavoriteTeamByUserIdAndTeamId() throws Exception {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        favoriteTeamRepository.save(favoriteTeam);

        // Verify it exists first
        mockMvc.perform(get("/api/favoriteteams/user/" + favoriteTeam.getUserId() + "/team/" + favoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.teamId").value(2L));

        // Delete it
        mockMvc.perform(delete("/api/favoriteteams/user/" + favoriteTeam.getUserId() + "/team/" + favoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("deleted")));

        // Verify it's gone (should return null or 404)
        mockMvc.perform(get("/api/favoriteteams/user/" + favoriteTeam.getUserId() + "/team/" + favoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testDeleteFavoriteTeamById() throws Exception {
        FavoriteTeam favoriteTeam = new FavoriteTeam(1L, 2L);
        FavoriteTeam savedFavoriteTeam = favoriteTeamRepository.save(favoriteTeam);

        // Verify it exists first
        mockMvc.perform(get("/api/favoriteteams/user/" + savedFavoriteTeam.getUserId() + "/team/" + savedFavoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.teamId").value(2L));

        // Delete it by ID
        mockMvc.perform(delete("/api/favoriteteams/" + savedFavoriteTeam.getFavoriteTeamId()))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("deleted")));

        // Verify it's gone (should return null or 404)
        mockMvc.perform(get("/api/favoriteteams/user/" + savedFavoriteTeam.getUserId() + "/team/" + savedFavoriteTeam.getTeamId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
>>>>>>> main
}