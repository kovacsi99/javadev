package com.epam.training.ticketservice.ui.command;


import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.RoomDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.ui.security.SecuredCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.StringJoiner;

@ShellComponent
public class RoomCommand extends SecuredCommand {

    private final RoomService roomService;


    @Autowired
    public RoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create room", value = "Creates new room in the Database")
    public String createNewRoom(String name, Long rows, Long columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        roomService.addNewRoom(roomDto);
        return "Room saved.";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update room", value = "Updates an existing room in the Database")
    public String updateRoom(String name, Long rows, Long columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        roomService.updateExistingRoom(roomDto);
        return "Room updated.";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete room", value = "Deletes room from Database")
    public String deleteRoom(String name) {
        roomService.deleteExistingRoom(name);
        return "Room probably deleted.";
    }

    @ShellMethod(key = "list rooms", value = "List all rooms.")
    public String listAllRooms() {
        List<Room> roomList = roomService.listAllRooms();

        if (roomList.isEmpty()) {
            return "There are no rooms at the moment";
        }

        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Room room : roomList) {
            stringJoiner.add(room.toString());
        }

        return stringJoiner.toString();
    }
}
