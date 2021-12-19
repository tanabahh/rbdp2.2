package itmo.rbdp2_2.client;

import itmo.rbdp2_2.server.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Long id;

    public static void main(String[] args){
        System.out.println("Hi!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("choose command");
            String command = scanner.nextLine();
            switch (command) {
                case "check": getCheck(); break;
                case "day": getDay(); break;
                case "calc": getInterval(); break;
                case "register": register(); break;
                case "login": login(); break;
                case "exit": System.exit(0); break;
                default:
                    System.out.println("I don't know this command:(");
            }
        }
    }

    private static void register() {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();

        String BASE_URL = "http://localhost:8080/calendar/register";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("username", username)
                .queryParam("password", password);
        try {
            restTemplate.exchange(builder.toUriString(),
                    HttpMethod.POST, null, new ParameterizedTypeReference<User>(){});
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();

        String BASE_URL = "http://localhost:8080/calendar/login";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("username", username)
                .queryParam("password", password);
        try {
            ResponseEntity<Long> responseEntity = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Long>(){});
            //System.out.println(responseEntity.getBody());
            id = responseEntity.getBody();
        } catch (Exception e) {
            System.out.println("wrong username or password");
        }
    }

    private static void getCheck() {
        Scanner scanner = new Scanner(System.in);
        String year = scanner.nextLine();

        String BASE_URL = "http://localhost:8080/calendar/is_leap";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("year", year)
                .queryParam("id", id);
        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<Boolean>(){});
            System.out.println(responseEntity.getBody());
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }

    private static void getInterval() {
        Scanner scanner = new Scanner(System.in);
        String firstDate = scanner.nextLine();
        String secondDate = scanner.nextLine();

        String BASE_URL = "http://localhost:8080/calendar/interval";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("firstDate", firstDate)
                .queryParam("secondDate", secondDate)
                .queryParam("id", id);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<String>(){});
            System.out.println(responseEntity.getBody());
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }

    private static void getDay() {
        Scanner scanner = new Scanner(System.in);
        String year = scanner.nextLine();
        String month = scanner.nextLine();
        String day = scanner.nextLine();

        String BASE_URL = "http://localhost:8080/calendar/day";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("year", year)
                .queryParam("month", month)
                .queryParam("day", day)
                .queryParam("id", id);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<String>(){});
            System.out.println(responseEntity.getBody());
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }
}
