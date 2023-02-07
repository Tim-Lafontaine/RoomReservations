import org.junit.Assert;
import org.junit.Test;

/* Timothy Lafontaine
   ID: 261066866 */

public class Testing {
    Room room1 = new Room("queen");
    Room room2 = new Room("king");
    Room room3 = new Room("double");
    Room room4 = new Room("king");
    Room room5 = new Room("king");
    //BookingSystem booking = new BookingSystem();
    Reservation reserv1;
    Reservation reserv2;
    Reservation reserv3;
    Reservation reserv4;
    Reservation reserv5;


    /* ROOM TESTS */
    @Test
    public void getType1() { // for room1, room3, and room5
        Assert.assertTrue("queen".equals(room1.getType()));
        Assert.assertFalse("king".equals(room3.getType()));
        Assert.assertTrue("king".equals(room5.getType()));

    }

    @Test
    public void getPrice1() { //for room2 and room3
        //room2 is a king which is $150
        Assert.assertTrue(room2.getPrice() == 150);
        //room3 is a double which is 90
        Assert.assertFalse(room3.getPrice() == 150);
        Assert.assertTrue(room3.getPrice() == 90);
    }

    @Test
    public void getAvailability1() { //availability should be true for all rooms
        Assert.assertTrue(room1.getAvailability());
        Assert.assertTrue(room2.getAvailability());
        Assert.assertTrue(room3.getAvailability());
        Assert.assertTrue(room4.getAvailability());
        Assert.assertTrue(room5.getAvailability());
    }


    @Test
    public void changeAvailability1() { //changing availability for rooms will make getAvailability() return false
        room1.changeAvailability();
        Assert.assertFalse(room1.getAvailability());

        //changing room1 availability again will return true
        room1.changeAvailability();
        Assert.assertTrue(room1.getAvailability());

    }

    /* ROOMS AND HOTEL TESTS */
    @Test
    public void findAvailableRoom1() {
        //create 5 rooms through BookingSystem
        Room[] rooms = new Room[5];
        // 1 King, 3 Queens, and 1 Double
        rooms[0] = new Room("king");
        rooms[1] = new Room("queen");
        rooms[2] = new Room("queen");
        rooms[3] = new Room("queen");
        rooms[4] = new Room("double");
        //create 2 reservations at the hotels
        //hotel1 will hold guest2 reservation (queen)
        //hotel2 will hold guest1 and guest3 reservations (king and king)
        Hotel hotel1 = new Hotel("Hotel1", rooms);

        //All rooms are available
        //the availability should show 1 double, 3 queens, and 1 kings
        System.out.println(hotel1.toString());


        hotel1.createReservation("Guest2", "queen");

        //Rooms[1] will be occupied but rooms[2] will still be available for queen
        Assert.assertSame(rooms[2], Room.findAvailableRoom(rooms, "queen"));

        hotel1.createReservation("Guest1", "king");

        //since rooms[] only has one king room, there will be null available rooms for king
        Assert.assertSame(null, Room.findAvailableRoom(rooms, "king"));

        //this reservation will not be created because there are no king rooms available
        hotel1.createReservation("Guest3" , "king");

        //Since Guest3 reservation wasnt created due to unavailability, invoice will be $0
        hotel1.printInvoice("Guest3");

        //Guest1's invoice for the king room will be $150
        hotel1.printInvoice("Guest1");

        //Guest1 took the king and Guest2 took the queen
        // the availability should show 1 double, 2 queens, and 0 kings
        System.out.println(hotel1.toString());

    }

    /* RESERVATION TESTS */
    @Test
    public void getName() {
        //make 3 reservations for rooms 1, 2, and 4
        reserv1 = new Reservation(room1, "Guest1");

        Assert.assertTrue(reserv1.getName().equals("Guest1"));

        reserv2 = new Reservation(room2, "Guest2");

        Assert.assertTrue(reserv2.getName().equals("Guest2"));

        reserv4 = new Reservation(room4, "Guest4");
        Assert.assertFalse(reserv4.getName().equals("Guest1"));
    }

    @Test
    public void getRoom() {
        reserv4 = new Reservation(room4, "Guest4");
        Assert.assertSame(room4, reserv4.getRoom());
        Assert.assertNotEquals(room1, reserv4.getRoom());
    }


}