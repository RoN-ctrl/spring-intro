package com.learn.facade.impl;

import com.learn.config.AppConfig;
import com.learn.model.Event;
import com.learn.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = AppConfig.class)
class BookingFacadeImplTest {

    public static final String EVENT_TITLE = "Giselle";
    public static final String EVENT_DATE = "20/02/2021 18:00";
    public static final String USER_NAME = "Jack";
    public static final String USER_EMAIL = "jack@test.com";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    //    @InjectMocks
    @Autowired
    private BookingFacadeImpl bookingFacade;

//    @Mock
//    UserService userService;
//
//    @Mock
//    TicketService ticketService;
//
//    @Mock
//    EventService eventService;

//    @Mock
//    private static UserDao userDao;
//    @Mock
//    private static EventDao eventDao;
//    @Mock
//    private static TicketDao ticketDao;
//    @InjectMocks
//    private static UserService userService = new UserServiceImpl();
//    @InjectMocks
//    private static TicketService ticketService = new TicketServiceImpl();
//    @InjectMocks
//    private static EventService eventService = new EventServiceImpl();
//
//    @BeforeAll
//    static void beforeAll() {
//        bookingFacade = new BookingFacadeImpl(userService, ticketService, eventService);
//    }


//    @BeforeAll
//    static void beforeAll() {
//        booki
//    }

    @Test
    void getEventByIdTest() {
        Event event = createTestEvent();
        Event eventById = bookingFacade.getEventById(event.getId());

        assertNotNull(eventById);
        assertEquals(event, eventById);
    }

    @Test
    void getEventsByTitleTest() {
        Event event = createTestEvent();
        List<Event> eventsByTitle = bookingFacade.getEventsByTitle(event.getTitle());

        assertNotNull(eventsByTitle);
        assertTrue(eventsByTitle.contains(event));
    }

    @Test
    void getEventsForDayTest() throws ParseException {
        Event event = createTestEvent();
        List<Event> eventsByDay = bookingFacade.getEventsForDay(DATE_FORMAT.parse(EVENT_DATE));

        assertNotNull(eventsByDay);
        assertTrue(eventsByDay.contains(event));
    }

    @Test
    void createEventTest() {
        Event event = createTestEvent();

        assertNotNull(event);
        assertAll(
                () -> assertEquals(EVENT_TITLE, event.getTitle()),
                () -> assertEquals(EVENT_DATE, DATE_FORMAT.format(event.getDate()))
        );
    }

    @Test
    void updateEventTest() throws ParseException {
        Event event = createTestEvent();
        String updatedTitle = "Updated title";
        String updatedDate = "10/02/2022 10:00";
        Event updatedEvent =
                bookingFacade.updateEvent(event.getId(), updatedTitle, DATE_FORMAT.parse(updatedDate));

        assertNotNull(updatedEvent);
        assertAll(
                () -> assertEquals(updatedTitle, updatedEvent.getTitle()),
                () -> assertEquals(updatedDate, DATE_FORMAT.format(updatedEvent.getDate()))
        );
    }

    @Test
    void deleteEventTest() {
        Event event = createTestEvent();

        assertTrue(bookingFacade.deleteEvent(event.getId()));
        assertFalse(bookingFacade.getEventsByTitle(event.getTitle()).contains(event));
    }

    @Test
    void getUserByIdTest() {
        User user = createTestUser();
        User userById = bookingFacade.getUserById(user.getId());

        assertNotNull(userById);
        assertEquals(user, userById);
    }

    @Test
    void getUserByEmailTest() {
        User user = createTestUser();
        User userByEmail = bookingFacade.getUserByEmail(user.getEmail());

        assertNotNull(userByEmail);
        assertEquals(user, userByEmail);
    }

    @Test
    void getUsersByNameTest() {
        User user = createTestUser();
        List<User> usersByName = bookingFacade.getUsersByName(user.getName());

        assertNotNull(usersByName);
        assertTrue(usersByName.contains(user));
    }

    @Test
    void createUserTest() {
        User user = createTestUser();

        assertNotNull(user);
        assertAll(
                () -> assertEquals(USER_NAME, user.getName()),
                () -> assertEquals(USER_EMAIL, user.getEmail())
        );
    }

    @Test
    void updateUserTest() {
        User user = createTestUser();
        String updatedName = "Updated Jack";
        String updatedEmail = "jach@updated.com";
        User updatedUser = bookingFacade.updateUser(user.getId(), updatedName, updatedEmail);

        assertNotNull(updatedUser);
        assertAll(
                () -> assertEquals(updatedName, updatedUser.getName()),
                () -> assertEquals(updatedEmail, updatedUser.getEmail())
        );
    }

    @Test
    void deleteUserTest() {
        User user = createTestUser();

        assertTrue(bookingFacade.deleteUser(user.getId()));
        assertFalse(bookingFacade.getUsersByName(user.getName()).contains(user));
    }

    @Test
    void bookTicketTest() {
        fail();
    }

    @Test
    void getBookedTicketsTest() {
        fail();
    }

    @Test
    void testGetBookedTicketsTest() {
        fail();
    }

    @Test
    void cancelTicketTest() {
        fail();
    }

    @SneakyThrows
    private Event createTestEvent() {
        return bookingFacade.createEvent(EVENT_TITLE, DATE_FORMAT.parse(EVENT_DATE));
    }

    private User createTestUser() {
        return bookingFacade.createUser(USER_NAME, USER_EMAIL);
    }
}