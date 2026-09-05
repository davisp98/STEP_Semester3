class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public int getBeds() {
        return beds;
    }

    public int getOccupied() {
        return occupied;
    }
}

public class F3_HostelAllocation {

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom r : rooms) {
            if (r != null && r.getOccupied() < r.getBeds()) {
                return r;
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom availableRoom = findAvailableRoom(rooms);
        if (availableRoom != null) {
            availableRoom.allot(studentName);
            System.out.println(studentName + " allotted to room " + availableRoom.getRoomNo());
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] roomsAvailable = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsAvailable, "Divya");

        HostelRoom[] roomsFull = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsFull, "Divya");
    }
}