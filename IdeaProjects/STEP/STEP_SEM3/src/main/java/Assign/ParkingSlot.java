package Assign;

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;

            System.out.println(
                    vehicleNo + " allotted to slot " + slotNo
            );
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot != null &&
                    slot.occupiedCount < slot.capacity) {

                return slot;
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots,
                          String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        // The array contains references to ParkingSlot objects.
        // Passing the array does not create copies of those objects.

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println(
                    "No slots available for " + vehicleNo
            );
        }
    }
}
