package itmo.rbdp2_2.server.controller;

import itmo.rbdp2_2.server.model.User;
import itmo.rbdp2_2.server.repository.UserRepository;
import itmo.rbdp2_2.server.service.CalendarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private CalendarService calendarService;
    private UserRepository userRepository;

    CalendarController(CalendarService calendarService,
                       UserRepository userRepository) {
        this.calendarService = calendarService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(String username, String password) {
        User user = new User(username, password);
        this.userRepository.save(user);
        return user;
    }

    @GetMapping("/login")
    public Long loginUser(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        return user.getId();
    }

    @GetMapping("/day")
    public String getDay(String year, String month, String day, Long id) {
        User user = userRepository.findUserById(id);
        user.setHistory(user.getHistory() + "day " + year + month + day);
        userRepository.save(user);
        try {
            return this.calendarService.getDay(year, month, day);
        } catch (ParseException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/interval")
    public String getInterval(String firstDate, String secondDate, Long id) {
        User user = userRepository.findUserById(id);
        user.setHistory(user.getHistory() + "calc " + firstDate + secondDate);
        userRepository.save(user);
        try {
            return this.calendarService.getInterval(firstDate, secondDate);
        } catch (ParseException ex) {
            return ex.getMessage();
        }
    }

    @GetMapping("/is_leap")
    public Boolean isLeap(String year, Long id) {
        User user = userRepository.findUserById(id);
        user.setHistory(user.getHistory() + "check " + year);
        userRepository.save(user);
        return this.calendarService.isLeap(year);
    }
}
